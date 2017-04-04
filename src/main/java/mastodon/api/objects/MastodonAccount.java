package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

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
    @Key("display_naame")
    private String displayName;
    @Key
    private String note;
    @Key
    private String url;
    // ...
}
