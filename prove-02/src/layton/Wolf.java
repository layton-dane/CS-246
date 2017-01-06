package layton;

import java.awt.*;
import java.util.Random;

/**
 * Created by 22362 on 1/6/2017.
 */
public class Wolf extends Creature implements Movable, Aware, Aggressor{

    /*
        used to determine which direction the wolf is travelling
        0- up
        1- right
        2- down
        3- left
     */
    private int _direction;

    public Wolf() {
        _direction = new Random().nextInt(4);
        _health = 1;
    }

    @Override
    public void move() {
        switch (_direction) {
            case 0:
                _location.y++;
                break;
            case 1:
                _location.x++;
                break;
            case 2:
                _location.y--;
                break;
            case 3:
                _location.x--;
                break;
            default:
                break;
        }
    }

    @Override
    public void attack(Creature target) {
        if (target instanceof Animal) {
            target.takeDamage(5);
            _health++;
        }
    }

    @Override
    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {
        switch (_direction) {
            case 0:
                if (above != null) {} // no need to change direction
                else if (right != null)
                    _direction = 1;
                else if (below != null)
                    _direction = 2;
                else if (left != null)
                    _direction = 3;
                break;
            case 1:
                if (right != null) {} // no need to change direction
                else if (below != null)
                    _direction = 2;
                else if (left != null)
                    _direction = 3;
                else if (above != null)
                    _direction = 0;
                break;
            case 2:
                if (below != null) {} // no need to change direction
                else if (left != null)
                    _direction = 3;
                else if (above != null)
                    _direction = 0;
                else if (right != null)
                    _direction = 1;
                break;
            case 3:
                if (left != null) {} // no need to change direction
                else if (above != null)
                    _direction = 0;
                else if (right != null)
                    _direction = 1;
                else if (below != null)
                    _direction = 2;
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
