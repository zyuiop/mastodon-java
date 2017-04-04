package mastodon.api.responses;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * @author zyuiop
 */
public class PostAppResponse extends GenericJson {
    @Key
    private int id;
    @Key("client_id")
    private String clientId;
    @Key("client_secret")
    private String clientSecret;

    public int getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
