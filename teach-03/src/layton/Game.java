package layton;

import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

/**
 * Created by 22362 on 1/18/2017.
 */
public class Game {

    private Player _player;

    public Game(Player p) {
        _player = p;
    }

    public String saveGame() {
        Gson g = new Gson();
        String game = g.toJson(_player);
        String fileName = "test.json";

        try {
            File f = new File(fileName);
            FileOutputStream is = new FileOutputStream(f);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);
            w.write(game);
            w.close();
        } catch (IOException io) {
            System.err.println("Problem writing to the file \'test.json\'");
        }

        return fileName;
    }

    public static Game loadGame(String filename) {
        Gson g = new Gson();
        Game newGame = null;
        try {
            File f = new File(filename);
            StringBuilder fileContents = new StringBuilder((int)f.length());
            Scanner s = new Scanner(f);
            try {
                while (s.hasNextLine()) {
                    fileContents.append(s.nextLine());
                }
                Player p = g.fromJson(fileContents.toString(), Player.class);
                newGame = new Game(p);
            } finally {
                s.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(1);
        }

        return newGame;
    }

    @Override
    public String toString() {
        return "Player: " + _player.toString();
    }
}
