package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * @author zyuiop
 */
public class MastodonTag extends GenericJson {
    @Key
    private String name;
    @Key
    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
