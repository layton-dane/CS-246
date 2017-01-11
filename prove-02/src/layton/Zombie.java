package layton;

import java.awt.Color;

/**
 * Zombie class to add to Game of Life
 *
 * A zombie moves only left to right, and eats anything in its path, except plants.
 * They do 10 damage when they attack.
 *
 * Created by 22362 on 1/6/2017.
 */
public class Zombie extends Creature implements Movable , Aggressor {

    public Zombie() {
        _health = 1;
    }

    /**
     * If the creature we've encountered is not a plant, we attack.
     * @param target The {@link Creature} we've encountered.
     */
    public void attack(Creature target) {
        // Zombies don't eat plants, but deal 10 damage to other creatures.
        if (target instanceof Animal) {
            target.takeDamage(10);
        }
        // Zombies attack hunters, but hunters deal damage as well
        else if (target instanceof Hunter) {
            target.takeDamage(1);
            this.takeDamage(1);
        }
    }

    /**
     * Zombies only move left to right
     */
    public void move() { _location.x++; }

    public Shape getShape() { return Shape.Square; }

    public Color getColor() { return Color.BLUE; }

    public Boolean isAlive() { return _health > 0; }
}