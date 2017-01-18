package layton;

import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        TweetLoader tl = new TweetLoader();
        tl.retrieveTweetsWithHashTag("#byui");
    }
}
