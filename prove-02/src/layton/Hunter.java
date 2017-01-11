package layton;

import java.awt.*;
import java.util.Random;

/**
 * Created by 22362 on 1/11/2017.
 */
public class Hunter extends Creature implements Aggressor {

    private Random _rand;

    public Hunter() {
        _health = 5;
        _rand = new Random();
    }

    @Override
    public void attack(Creature target) {
        if (target instanceof Zombie) {
            int headShot = _rand.nextInt(10);
            if (headShot == 7 || headShot == 2) {
                target.takeDamage(target._health);
            }
        }
        else if (target != null && !(target instanceof Plant)) {
            target.takeDamage(target._health);
        }
    }

    @Override
    Shape getShape() { return Shape.Triangle; }

    @Override
    Color getColor() { return Color.BLACK; }

    @Override
    Boolean isAlive() { return _health > 0; }
}
