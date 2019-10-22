package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;
import static huglife.HugLifeUtils.randomEntry;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plipes with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * Should return a color with red = 34, green = 0 and blue = 231.
     */
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    /**
     * If a Cloruses attacks another creature, it should gain that creature’s energy
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Cloruses should lose 0.03 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= 0.03;
        if (energy < 0) {
            energy = 0;
        }
    }


    /**
     * CLoruses gain 0.2 energy when staying.
     * Cloruses have no restrictions on their maximum energy.
     */
    public void stay() {
        energy -= 0.01;
        if (energy < 0) {
            energy = 0;
        }
    }

    /**
     * Cloruses and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Clorus.
     */
    public Clorus replicate() {
        energy = energy * 0.5;
        double babyEnergy = energy;
        return new Clorus(babyEnergy);
    }

    /** 1. If there are no empty squares, the Clorus will STAY
     * (even if there are Plips nearby they could attack since
     * Plip squares do not count as empty squares).
     * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3. Otherwise, if the Clorus has energy greater than or equal to one,
     * It will REPLICATE to a random empty square.
     * 4. Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> hasPlip = new ArrayDeque<>();
        boolean anyPlip = false;
        for (Direction x : neighbors.keySet()) {
            if (neighbors.get(x).name().equals("empty")) {
                emptyNeighbors.add(x);
            }
            if(neighbors.get(x).name().equals("plip")) {
                hasPlip.add(x);
                anyPlip = true;
            }
        }
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        else if (anyPlip) {
            return new Action(Action.ActionType.ATTACK, randomEntry(hasPlip));
        }

        // Rule 3
        else if (energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }

        // Rule 4
        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }
}
