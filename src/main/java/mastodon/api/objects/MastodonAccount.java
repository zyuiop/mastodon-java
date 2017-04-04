package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

import java.util.Date;

/**
 * @author zyuiop
 */
public class MastodonAccount extends GenericJson {
    @Key
    private int id;
    @Key
    private String username;
    @Key
    private String acct;
    @Key("display_name")
    private String displayName;
    @Key
    private String note;
    @Key
    private String url;
    @Key
    private String avatar;
    @Key
    private String header;
    @Key
    private boolean locked;
    @Key("created_at")
    private String createdAt;
    @Key("followers_count")
    private int followersCount;
    @Key("following_count")
    private int followingCount;
    @Key("statuses_count")
    private int statusesCount;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAcct() {
        return acct;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getNote() {
        return note;
    }

    public String getUrl() {
        return url;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getHeader() {
        return header;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public int getStatusesCount() {
        return statusesCount;
    }
}
