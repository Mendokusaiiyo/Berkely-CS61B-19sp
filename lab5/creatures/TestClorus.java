package creatures;

import static org.junit.Assert.*;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(5.08);
        assertEquals(5.08, c.energy(), 0.001);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        c.move();
        assertEquals(5.02, c.energy(), 0.001);
        Plip p = new Plip(2);
        c.attack(p);
        assertEquals(7.02, c.energy(), 0.001);
        c.stay();
        c.stay();
        assertEquals(7, c.energy(), 0.001);
        c.replicate();
        assertEquals(3.5, c.energy(), 0.001);
    }

    @Test
    public void testChoose() {
        // No empty squares, STAY. Even if Plips.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        //Otherwise, if any Plips are seen, ATTACK one of them randomly.
        c = new Clorus(1.2);

        HashMap<Direction, Occupant> topPlip = new HashMap<Direction, Occupant>();
        topPlip.put(Direction.TOP, new Plip());
        topPlip.put(Direction.BOTTOM, new Impassible());
        topPlip.put(Direction.LEFT, new Impassible());
        topPlip.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(topPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);


        // Move to a random empty square
        c = new Clorus(.99);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());
        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);
    }
}
