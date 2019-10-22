public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Body(double xP, double yP, double xV, double yV, double m, String img) {
	    xxPos=xP;
	    yyPos=yP;
	    xxVel=xV;
	    yyVel=yV;
	    mass=m;
	    imgFileName=img;
    }
	public Body(Body b) {
		xxPos=b.xxPos;
		yyPos=b.yyPos;
		xxVel=b.xxVel;
		yyVel=b.yyVel;
		mass=b.mass;
		imgFileName=b.imgFileName;
	}
	/** Calculate the distance between two bodies. */
	public double calcDistance(Body b) {
		double dx=this.xxPos-b.xxPos;
		double dy=this.yyPos-b.yyPos;
		double r=Math.hypot(dx,dy);
		return r;
	}
	/** Calculate the force exerted on this body by the
	given body. */
	public double calcForceExertedBy(Body b) {
		double G=6.67e-11;
		double F=(G*this.mass*b.mass)/(Math.pow(this.calcDistance(b),2));
		return F;
	}
	/** For "Double Check Your Understanding", it should return 1.334E-9.
	Delete the description if you wanna check it out.
	public static void main(String[] args) {
		Body samh= new Body(1.0, 0.0, 0.0, 0.0, 10.0, "samh");
		Body Rocinante= new Body(5.0, -3.0, -3.0, -3.0, 50.0, "Rocinante");
		System.out.println(samh.calcForceExertedBy(Rocinante));
	} */

	/** Describe the force exerted in the X and Y directions respectively. */
	public double calcForceExertedByX(Body b) {
		double Fx=(this.calcForceExertedBy(b)*(b.xxPos-this.xxPos))/(this.calcDistance(b));
		return Fx;
	}
	public double calcForceExertedByY(Body b) {
		double Fy=(this.calcForceExertedBy(b)*(b.yyPos-this.yyPos))/(this.calcDistance(b));
		return Fy;
	}

	/** Calculate the net X and net Y force exerted by all 
	bodies in the array upon the current body.*/
	
	public double calcNetForceExertedByX(Body[] bs) {
		double Fnx=0.0;
		for(int i=0; i<bs.length; i+=1) {
			if(this.equals(bs[i])) {
				continue;	
			}
			Fnx=Fnx+this.calcForceExertedByX(bs[i]);
		}
		return Fnx;	
	}
	public double calcNetForceExertedByY(Body[] bs) {
		double Fny=0.0;
		for(int i=0; i<bs.length; i+=1) {
			if(this.equals(bs[i])) {
				continue;	
			}
			Fny=Fny+this.calcForceExertedByY(bs[i]);
		}
		return Fny;	
	}

	/** Update the body's position and velocity instance variables. */
	public void update(double dt, double fX, double fY) {
		double anetx=fX/this.mass;
		double anety=fY/this.mass;
		this.xxVel+=anetx*dt;
		this.yyVel+=anety*dt;
		this.xxPos+=this.xxVel*dt;
		this.yyPos+=this.yyVel*dt;
	}

	/** Draw one body. */
	public void Draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
	}
}