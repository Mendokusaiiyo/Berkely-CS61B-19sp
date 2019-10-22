public class NBody {

/**Return the radius of the universe from the file.*/
	public static double readRadius(String filename) {
		In in=new In(filename);
		in.readInt();
		double Radius=in.readDouble();
		return Radius;
	}	
	/**
	public static void main(String[] args) {
		System.out.println(readRadius("./data/planets.txt"));
	}
	*/

/* Return an array of Bodys corresponding to the bodies in the file. */
	public static Body[] readBodies(String filename) {
		In in=new In(filename);
		int N = in.readInt();
		Body[] Planets=new Body[N];
		for(int i=0; i<N; i+=1) {
			double xP=in.readDouble();
			double yP=in.readDouble();
			double vX=in.readDouble();
			double vY=in.readDouble();
			int m=in.readInt();
			String img=in.readString();
			Planets[i]=new Body(xP, yP, vX, vY, m, img);
		}
		return Planets;
	}

	/** Draw the initial universe state. */

	/** Collect all needed input. */
	public static void main(String[] args) {
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		double R=NBody.readRadius(filename);
		Body[] Planets=NBody.readBodies(filename);
			
	/** Draw the background. */
		StdDraw.SetScale(-R, R);
		StdDraw.clear();
		StdDraw.picture(0,0,images/starfield.jpg);

	/** Draw every body in the body array. */
		for(Body b : Planets) {
			b.Draw();
		}
	
	/** Create an animation. */
		StdDraw.enableDoubleBuffering();
		double time=0;
		while(time<=T) {
			double[] xForces=new double[Planets.length];
			double[] yFOrces= new double[Planets.length];
			for(int i=0; i<Planets.length; i+=1) {
				xForces[i]=Planets[i].calcNetForceExertedByX(Planets);
				yFOrces[i]=Planets[i].calcNetForceExertedByY(Planets);
			}
			for(int i=0; i<planets.length; i+=1){
				Planets[i].update(dt, xForces[i], yFOrces[i]);
			}
		

			/** Draw the background. */
			StdDraw.picture(0,0,images/starfield.jpg);

			/** Draw every body in the body array. */
			for(Body b : Planets) {
				b.Draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time+=dt;
		}
	
		/** print the universe. */
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
	}
}