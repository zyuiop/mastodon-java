package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * @author zyuiop
 */
public class MastodonRelationship extends GenericJson {
    @Key
    private boolean following;
    @Key("followed_by")
    private boolean followedBy;
    @Key
    private boolean blocking;
    @Key
    private boolean muting;
    @Key
    private boolean requested;

    public boolean isFollowing() {
        return following;
    }

    public boolean isFollowedBy() {
        return followedBy;
    }

    public boolean isBlocking() {
        return blocking;
    }

    public boolean isMuting() {
        return muting;
    }

    public boolean isRequested() {
        return requested;
    }
}
