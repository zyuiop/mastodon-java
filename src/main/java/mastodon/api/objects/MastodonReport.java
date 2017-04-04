package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * @author zyuiop
 */
public class MastodonReport extends GenericJson {
    @Key
    private int id;
    @Key("action_taken")
    private String actionTaken;

    public int getId() {
        return id;
    }

    public String getActionTaken() {
        return actionTaken;
    }
}
