package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.*;
/**
 * @author Jacky Qu
 * Test for clorus class.
 */
public class TestClorus {

    @Test
    public void testBasic() {
        Clorus c = new Clorus(3);
        Clorus c1 = new Clorus(4);
        assertEquals(3, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(2.97, c.energy(), 0.01);
        c.move();
        assertEquals(2.94, c.energy(), 0.01);
        c.stay();
        assertEquals(2.93, c.energy(), 0.01);
        c.stay();
        assertEquals(2.92, c.energy(), 0.01);
        c.attack(c1);
        assertEquals(6.92, c.energy(), 0.01);
        c.attack(c1);
        assertEquals(10.92, c.energy(), 0.01);
        Clorus c2 = c.replicate();
        assertEquals(5.46, c2.energy(), 0.01);
        assertEquals(5.46, c.energy(), 0.01);
        Clorus c3 = c2.replicate();
        assertEquals(2.73, c2.energy(), 0.01);
        assertEquals(2.73, c3.energy(), 0.01);
    }

    @Test
    public void testAction() {
        // Test rule 1.
        Clorus c = new Clorus(2);
        HashMap<Direction, Occupant> noEmpty = new HashMap<>();
        noEmpty.put(Direction.TOP, new Impassible());
        noEmpty.put(Direction.BOTTOM, new Impassible());
        noEmpty.put(Direction.LEFT, new Impassible());
        noEmpty.put(Direction.RIGHT, new Impassible());
        Action actual = c.chooseAction(noEmpty);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        // Test Rule 1 even plip exists.
        c = new Clorus(2);
        HashMap<Direction, Occupant> onePlip = new HashMap<>();
        onePlip.put(Direction.TOP, new Plip(2));
        onePlip.put(Direction.BOTTOM, new Impassible());
        onePlip.put(Direction.LEFT, new Impassible());
        onePlip.put(Direction.RIGHT, new Impassible());
        actual = c.chooseAction(onePlip);
        expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        // Test rule 2.
        c = new Clorus(2);
        HashMap<Direction, Occupant> onePlipWithOneEmpty = new HashMap<>();
        onePlipWithOneEmpty.put(Direction.TOP, new Plip(2));
        onePlipWithOneEmpty.put(Direction.BOTTOM, new Empty());
        onePlipWithOneEmpty.put(Direction.LEFT, new Impassible());
        onePlipWithOneEmpty.put(Direction.RIGHT, new Impassible());
        actual = c.chooseAction(onePlipWithOneEmpty);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);
        assertNotEquals(expected, actual);

        c = new Clorus(0.9);
        HashMap<Direction, Occupant> twoPlipWithOneEmpty = new HashMap<>();
        twoPlipWithOneEmpty.put(Direction.TOP, new Plip(2));
        twoPlipWithOneEmpty.put(Direction.BOTTOM, new Empty());
        twoPlipWithOneEmpty.put(Direction.LEFT, new Plip(2));
        twoPlipWithOneEmpty.put(Direction.RIGHT, new Impassible());
        actual = c.chooseAction(twoPlipWithOneEmpty);
        Action unexpected1 = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);
        Action unexpected2 = new Action(Action.ActionType.STAY);
        Action unexpected3 = new Action(Action.ActionType.ATTACK, Direction.RIGHT);
        assertNotEquals(unexpected1, actual);
        assertNotEquals(unexpected2, actual);
        assertNotEquals(unexpected3, actual);

        //Test rule 3. Energy >= 1
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> noPlipEnergyGreatOne = new HashMap<>();
        noPlipEnergyGreatOne.put(Direction.TOP, new Empty());
        noPlipEnergyGreatOne.put(Direction.BOTTOM, new Impassible());
        noPlipEnergyGreatOne.put(Direction.LEFT, new Impassible());
        noPlipEnergyGreatOne.put(Direction.RIGHT, new Impassible());
        actual = c.chooseAction(noPlipEnergyGreatOne);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        assertEquals(expected, actual);

        c = new Clorus(1.2);
        HashMap<Direction, Occupant> noPlip0 = new HashMap<>();
        noPlip0.put(Direction.TOP, new Empty());
        noPlip0.put(Direction.BOTTOM, new Impassible());
        noPlip0.put(Direction.LEFT, new Empty());
        noPlip0.put(Direction.RIGHT, new Empty());
        actual = c.chooseAction(noPlip0);
        unexpected1 = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);
        unexpected2 = new Action(Action.ActionType.STAY);
        assertNotEquals(unexpected1, actual);
        assertNotEquals(unexpected2, actual);

        // Rule 4
        c = new Clorus(0.9);
        HashMap<Direction, Occupant> noPlip1 = new HashMap<>();
        noPlip1.put(Direction.TOP, new Empty());
        noPlip1.put(Direction.BOTTOM, new Impassible());
        noPlip1.put(Direction.LEFT, new Impassible());
        noPlip1.put(Direction.RIGHT, new Impassible());
        actual = c.chooseAction(noPlip1);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);
        assertEquals(expected, actual);


        c = new Clorus(0.9);
        HashMap<Direction, Occupant> noPlip2 = new HashMap<>();
        noPlip2.put(Direction.TOP, new Impassible());
        noPlip2.put(Direction.BOTTOM, new Impassible());
        noPlip2.put(Direction.LEFT, new Impassible());
        noPlip2.put(Direction.RIGHT, new Impassible());
        actual = c.chooseAction(noPlip2);
        expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

    }

}
