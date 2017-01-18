package layton;

/**
 * Created by 22362 on 1/18/2017.
 */
public class Main {

    public static void main(String[] args) {
        Player p = new Player("Dane", 100, 100, 100);
        p.addEquipment("sword",100);
        p.addEquipment("shield",10);
        p.addEquipment("bracer", 5);
        Game g = new Game(p);
        System.out.println(g.toString());
        String fileName = g.saveGame();
        Game g2 = Game.loadGame(fileName);
        System.out.println(g2.toString());
    }
}
