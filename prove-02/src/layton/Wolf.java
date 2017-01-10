package layton;

import java.awt.*;
import java.util.Random;

/**
 * Created by 22362 on 1/6/2017.
 */
public class Wolf extends Creature implements Movable, Aware, Aggressor, Spawner {

    /*
        used to determine which direction the wolf is travelling
        0- right
        1- left
        2- down
        3- up
    */
    private int _direction;
    private boolean _canSpawn;

    public Wolf() {
        _direction = new Random().nextInt(4);
        _health = 1;
        _canSpawn = false;
    }

    @Override
    public Creature spawnNewCreature() {
        if (_canSpawn) {
            Wolf w = new Wolf();
            int thisX = this._location.x;
            int thisY = this._location.y;
            w.setLocation(new Point(--thisX, thisY));
            _canSpawn = false;
            return w;
        }
        return null;
    }

    @Override
    public void move() {
        switch (_direction) {
            case 0:
                _location.x++;
                break;
            case 1:
                _location.x--;
                break;
            case 2:
                _location.y++;
                break;
            case 3:
                _location.y--;
                break;
            default:
                break;
        }
    }

    @Override
    public void attack(Creature target) {
        if (target instanceof Animal) {
            target.takeDamage(5);
            if (!target.isAlive())
                _canSpawn = true;

            _health++;
        }
    }

    @Override
    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {
        switch (_direction) {
            case 0: // moving right
                // check right
                if (right instanceof Animal) {} // do not change direction
                // check down
                else if (below instanceof Animal)
                    _direction = 2;
                // check left
                else if (left instanceof Animal)
                    _direction = 1;
                // check up
                else if (above instanceof Animal)
                    _direction = 3;
                break;
            case 1: // moving left
                // check left
                if (left instanceof Animal) {} // do not change direction
                // check up
                else if (above instanceof Animal)
                    _direction = 3;
                // check right
                else if (right instanceof Animal)
                    _direction = 0;
                // check down
                else if (below instanceof Animal)
                    _direction = 2;
                break;
            case 2: // moving down
                // check down
                if (below instanceof Animal) {} // do not change direction
                // check left
                else if (left instanceof Animal)
                    _direction = 1;
                // check up
                else if (above instanceof Animal)
                    _direction = 3;
                // check right
                else if (right instanceof Animal)
                    _direction = 0;
                break;
            case 3: // moving up
                // check up
                if (above instanceof Animal) {} // do not change direction
                // check right
                else if (right instanceof Animal)
                    _direction = 0;
                // check down
                else if (below instanceof Animal)
                    _direction = 2;
                // check left
                else if (left instanceof Animal)
                    _direction = 1;
                break;
            default:
                break;
        }
    }

    @Override
    Shape getShape() { return Shape.Square; }

    @Override
    Color getColor() { return Color.GRAY; }

    @Override
    Boolean isAlive() { return _health > 0; }
}