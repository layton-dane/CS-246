package layton;

import java.util.Map;
import java.util.TreeMap;

public class Player {

    private String _name;
    private int _health;
    private int _mana;
    private long _gold;
    private Map<String, Integer> _equipment;

    public Player(String name, int health, int mana, long gold) {
        _name = name;
        _health = health;
        _mana = mana;
        _gold = gold;
        _equipment = new TreeMap<>();
    }

    public void addEquipment(String name, int power) {
        _equipment.put(name, power);
    }

    @Override
    public String toString() {
        return this._name + ", Health: " + this._health + ", Mana: " + this._mana + ", Gold: " + this._gold + ", Equipment: " + _equipment.toString();
    }
}
