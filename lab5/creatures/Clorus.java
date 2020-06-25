package creatures;

import edu.princeton.cs.algs4.Average;
import edu.princeton.cs.algs4.ST;
import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;
import static huglife.HugLifeUtils.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * @author Jacky Qu
 * An implementation of a clorus, a fierce blue-colored predator
 * that enjoys nothing more than snacking on hapless Plips.
 */
public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r = 34;
    /**
     * green color.
     */
    private int g = 0;
    /**
     * blue color.
     */
    private int b = 231;
    /**
     * Minimum energy of a clorus.
     */
    private final double minEnergy = 0;
    /**
     * Energy to lose on a Move action.
     */
    private final double moveEnergyLost = 0.03;
    /**
     * Energy to lose on a Stay action.
     */
    private final double stayEnergyLost = 0.01;
    /**
     * Fraction of energy remained on a Replicate action.
     */
    private final double repEnergyRemain = 0.5;


    /**
     * Creates a clorus with given energy {@code e}.
     */
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    /**
     * Clorus move while losing given energy.
     */
    @Override
    public void move() {
        energy -= moveEnergyLost;
        if (energy < minEnergy) {
            energy = minEnergy;
        }
    }

    /**
     * Clorus gain the attacked creatrue's energy while Attack action.
     */
    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Clorus and its offspring each get 50% of the energy. None lost
     * in this process.
     */
    @Override
    public Clorus replicate() {
        double totalEnergy = energy;
        energy = energy * repEnergyRemain;
        return new Clorus(totalEnergy - energy);
    }

    /**
     * Clorus stay still while losing certain energy.
     */
    @Override
    public void stay() {
        energy -= stayEnergyLost;
        if (energy < minEnergy) {
            energy = minEnergy;
        }
    }

    /**
     * Color of clorus is fixed.
     * red = 34, green = 0, blue = 231.
     */
    @Override
    public Color color() {
        return color(r, g ,b);
    }

    /**
     * Rule 1 : No empty squares nearby, clorus will stay.
     *
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbours = new ArrayDeque<>();
        Deque<Direction> plipNeigh = new ArrayDeque<>();
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            Direction getDir = entry.getKey();
            Occupant getOcp = entry.getValue();
            if (getOcp.name().equals("empty")) {
                emptyNeighbours.add(getDir);
            }
            if (getDir.name().equals("plip")) {
                plipNeigh.add(getDir);
            }
        }
        // Rule 1.
        if (emptyNeighbours.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }
        // Rule 2.
        if (!plipNeigh.isEmpty()) {
            Direction attDir = randomEntry(plipNeigh);
            return new Action(Action.ActionType.ATTACK, attDir);
        }
        // Rule 3.
        if (energy >= 1) {
            Direction repDir = randomEntry(emptyNeighbours);
            return new Action(Action.ActionType.REPLICATE, repDir);
        }
        // Rule 4.
        Direction movDir = randomEntry(emptyNeighbours);
        return new Action(Action.ActionType.MOVE, movDir);
    }
}
