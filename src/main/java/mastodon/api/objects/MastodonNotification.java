package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * @author zyuiop
 */
public class MastodonNotification extends GenericJson {
    @Key
    private int id;
    @Key
    private String type;
    @Key("created_at")
    private String createdAt;
    @Key
    private MastodonAccount account;
    @Key
    private MastodonStatus status;
}
