package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

import java.util.Collection;

/**
 * @author zyuiop
 */
public class MastodonContext extends GenericJson {
    // Collection might not work. Fallback on array ?
    @Key
    private Collection<MastodonStatus> ancestors;
    @Key
    private Collection<MastodonStatus> descendents;

    public Collection<MastodonStatus> getAncestors() {
        return ancestors;
    }

    public Collection<MastodonStatus> getDescendents() {
        return descendents;
    }
}
