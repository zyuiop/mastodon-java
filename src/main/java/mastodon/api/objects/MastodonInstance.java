package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * @author zyuiop
 */
public class MastodonInstance extends GenericJson {
    @Key
    private String uri;
    @Key
    private String title;
    @Key
    private String description;
    @Key
    private String email;

    public String getUri() {
        return uri;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }
}
