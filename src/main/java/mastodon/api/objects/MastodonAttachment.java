package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * @author zyuiop
 */
public class MastodonAttachment extends GenericJson {
    @Key
    private int id;
    @Key
    private String type;
    @Key
    private String url;
    @Key("remote_url")
    private String remoteUrl;
    @Key("preview_url")
    private String previewUrl;
    @Key("text_url")
    private String textUrl;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public String getTextUrl() {
        return textUrl;
    }
}
