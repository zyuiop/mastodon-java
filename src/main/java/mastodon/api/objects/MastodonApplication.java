package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * @author zyuiop
 */
public class MastodonApplication extends GenericJson {
    @Key
    private String name;
    @Key
    private String website;

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }
}
