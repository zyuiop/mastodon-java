package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * @author zyuiop
 */
public class MastodonMention extends GenericJson {
    @Key
    private String url;
    @Key
    private String username;
    @Key
    private String acct;
    @Key
    private int id;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getAcct() {
        return acct;
    }

    public int getId() {
        return id;
    }
}
