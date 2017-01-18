package layton;

import com.google.gson.Gson;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 22362 on 1/18/2017.
 */
public class TweetLoader {

    private Twitter _twitter;

    public TweetLoader() {
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("Nu2vU4OcSgn2Y2fNRIqqqcGYL");
        cb.setOAuthConsumerSecret("1OXfJzyznvIxxfZAyvHCXWXwn7AYrQ6pFNatJkB8re3Jqr4cZe");
        cb.setOAuthAccessToken("821760680244039680-BGGqeqhdn7Ahk6BtVgeZntPjycfKpdk");
        cb.setOAuthAccessTokenSecret("mT5VYn1WCmAloLQZKJINTTELbLZhfuKNYGPFPopvAx7i7");
        cb.setJSONStoreEnabled(true);

        TwitterFactory tf = new TwitterFactory(cb.build());
        this._twitter = tf.getInstance();
    }

    public Map<String, BYUITweet> retrieveTweetsWithHashTag(String hashtag) {
        TreeMap<String, BYUITweet> tweets = new TreeMap<>();
        Query query = new Query(hashtag);
        try {
            QueryResult qr = _twitter.search(query);
            for (Status s : qr.getTweets()) {
                System.out.println("@" + s.getUser().getScreenName() + ":" + s.getText());
            }
        } catch (twitter4j.TwitterException t) {
            t.printStackTrace();
        }

        return tweets;
    }
}
