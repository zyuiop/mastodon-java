package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

import java.util.List;

/**
 * @author zyuiop
 */
public class MastodonResults extends GenericJson {
    @Key
    private List<MastodonAccount> accounts;
    @Key
    private List<MastodonStatus> statuses;
    @Key
    private List<String> hashtags;

    public List<MastodonAccount> getAccounts() {
        return accounts;
    }

    public List<MastodonStatus> getStatuses() {
        return statuses;
    }

    public List<String> getHashtags() {
        return hashtags;
    }
}
