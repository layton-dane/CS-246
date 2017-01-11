package layton;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;
import java.util.List;

/**
* The "brains" of the game, which controls all of the creature activites.
* <p>
* @author  Brother Falin
* @version 1.0
* @since   2016-12-08 
* @see Creature
*/
public class CreatureHandler
{
	int _rows;
	int _cols;
	Random _rand;
	List<Creature> _creatures; 

	/**
	* Retrieves all of the creatures that exist in the world
	* @return A list of the creatures in the world.
	*/
	public List<Creature> getCreatures() {
		return _creatures;
	}

	/**
	* @param worldRows How many rows the world grid should have.
	* @param worldCols How many columns the world grid should have.
	* @param creatures A list of creatures to start the world with.
	*/
	public CreatureHandler(int worldRows, int worldCols, List<Creature> creatures) {
		_rand = new Random();
		_rows = worldRows;
		_cols = worldCols;
		_creatures = creatures;
	}

	/**
	* Sets the initial positions of the creatures to random locations within the 
	* world's grid.
	*/
	public void setStartingPositions() {

		// Java's enhanced for loop (AKA for-each loop).
		// See: http://stackoverflow.com/a/11685345/28106
		for(Creature c : _creatures) {
			c.setLocation(getRandomLocation());
		}
	}

	private Point getRandomLocation() {
		int r = _rand.nextInt(_rows);
		int c = _rand.nextInt(_cols);
		
		return new Point(r, c);
	}

	private void wrapPoint(Point p) {

		// If we go too far left or right, wrap around
		// to the other side.
		if(p.x > _cols)
			p.x = 0;
		else if(p.x < 0)
			p.x = _cols;
		
		// If we go too far up or down, wrap around
		// to the other side.
		if(p.y > _rows)
			p.y = 0;
		else if(p.y < 0)
			p.y = _rows;
	}

	private Creature getTarget(Creature source, int xOffset, int yOffset) {
		
		// Determine where we are looking based on our current location
		// and the offset
		Point newPoint = (Point)source.getLocation().clone();
		newPoint.x += xOffset;
		newPoint.y += yOffset;

		// Wrap the target point if necessary
		wrapPoint(newPoint);

		// Look at every creature in our list, and see if it is sitting in the spot
		// where we are looking. (ignoring dead creatures)
		for(Creature c : _creatures) {
			if(c == source || !c.isAlive())
				continue;
				
			if(c.getLocation().x == newPoint.x && 
			   c.getLocation().y == newPoint.y)
				return c;
		}
		
		// If we didn't find anything, return null.
		return null;
	}

	private void handleMove(Creature c) {
		
		Movable m = (Movable)c;
		m.move();
		
		wrapPoint(c.getLocation());
	}

	/**
	* Updates all of the living creatures in the game by making they each get a chance to
	* act on their implemented behaviors.
	*/
	public void updateCreatures() {
        List<Creature> newCreatures = new ArrayList<Creature>();
		// Handle all our creature behaviors here. Since we don't know ahead of time
		// which creatures implement which behaviors, we can use the instanceof keyword
		// to see if a given instance implements a particular interface.
		for(Creature c : _creatures) {

            // Skip dead creatures
            if(!c.isAlive())
                continue;

            if(c instanceof Aware) {
            	if (c instanceof Wolf) {
                    Creature above = getTarget(c, 0, -2);
                    Creature below = getTarget(c, 0, 2);
                    Creature left = getTarget(c, -2, 0);
                    Creature right = getTarget(c, 2, 0);

                    Aware a = (Aware) c;
                    a.senseNeighbors(above, below, left, right);
                }
                else {
            		Creature above = getTarget(c, 0, -1);
					Creature below = getTarget(c, 0, 1);
					Creature left = getTarget(c, -1, 0);
					Creature right = getTarget(c, 1, 0);

					Aware a = (Aware)c;
					a.senseNeighbors(above, below, left, right);
				}
            }

            if(c instanceof Movable) {
                handleMove(c);
            }

            if(c instanceof Aggressor) {
                if (c instanceof Hunter) {
                    Random rand = new Random();
                    int dir = rand.nextInt(4);
                    Creature target = null;
                    for (int i = 1; i < 11; i++) {
                        switch (dir) {
                            case 0:
                                target = getTarget(c, i,0);
                                break;
                            case 1:
                                target = getTarget(c, -i, 0);
                                break;
                            case 2:
                                target = getTarget(c,0, i);
                                break;
                            case 3:
                                target = getTarget(c,0, -i);
                                break;
                            default:
                                break;
                        }
                        if (target != null) {
                            break;
                        }
                    }
                    Aggressor a = (Aggressor)c;
                    a.attack(target);
                }
                else {
                    Creature target = getTarget(c, 0, 0);
                    Aggressor a = (Aggressor)c;
                    a.attack(target);
                }

            }

            if (c instanceof Spawner) {
                Spawner s = (Spawner)c;
                Creature newC = s.spawnNewCreature();
                if (newC != null) {
                	newCreatures.add(newC);
				}
            }
        }
        for (Creature c : newCreatures) {
		    Creature newC = c;
		    _creatures.add(newC);
        }
	}
}