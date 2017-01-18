package layton;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 22362 on 1/18/2017.
 */
public class User {

    private String _name;

    @SerializedName("followers_count")
    private int _followers;

    public int getFollowers() {
        return _followers;
    }

    public void setFollowers(int followers) {
        this._followers = followers;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }
}
