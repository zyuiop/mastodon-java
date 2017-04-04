package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * @author zyuiop
 */
public class MastodonStatus extends GenericJson {
    @Key
    private int id;
    @Key
    private String uri;
    @Key
    private String url;
    @Key
    private MastodonAccount account;
    // ...
}
