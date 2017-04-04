package mastodon.api;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.gson.reflect.TypeToken;
import mastodon.api.internal.FormDataContent;
import mastodon.api.objects.MastodonAccount;
import mastodon.api.objects.MastodonStatus;

import java.io.IOException;
import java.util.List;

import static mastodon.api.internal.RequestsHelper.factory;
import static mastodon.api.internal.RequestsHelper.getUrl;

/**
 * @author zyuiop
 */
public class MastodonSession {
    private final MastodonApp app;
    private final Credential credential;

    MastodonSession(MastodonApp app, Credential credential) {
        this.app = app;
        this.credential = credential;
    }

    private HttpResponse getRequest(String endpoint) throws IOException {
        HttpRequest request = factory().buildGetRequest(getUrl(app.getMastodonInstance(), endpoint));
        credential.initialize(request);
        return request.execute();
    }


    private HttpResponse postRequest(String endpoint, FormDataContent content) throws IOException {
        HttpRequest request = factory().buildPostRequest(getUrl(app.getMastodonInstance(), endpoint), content);
        credential.initialize(request);
        return request.execute();
    }

    public MastodonAccount getAccount(int id) throws IOException {
        return getRequest("/api/v1/accounts/" + id).parseAs(MastodonAccount.class);
    }

    public MastodonAccount getCurrentUser() throws IOException {
        return getRequest("/api/v1/accounts/verify_credentials").parseAs(MastodonAccount.class);
    }

    public List<MastodonAccount> getFollowers(int id) throws IOException {
        TypeToken<List<MastodonAccount>> list = new TypeToken<List<MastodonAccount>>() {
        };

        return (List<MastodonAccount>) getRequest("/api/v1/accounts/" + id + "/followers").parseAs(list.getType());
    }

    public List<MastodonAccount> getFollowing(int id) throws IOException {
        TypeToken<List<MastodonAccount>> list = new TypeToken<List<MastodonAccount>>() {
        };

        return (List<MastodonAccount>) getRequest("/api/v1/accounts/" + id + "/following").parseAs(list.getType());
    }

    public List<MastodonStatus> getStatuses(int id) throws IOException {
        return getStatuses(id, false, false);
    }

    public List<MastodonStatus> getStatuses(int id, boolean onlyMedia, boolean excludeReplies) throws IOException {
        String param = "?only_media=" + String.valueOf(onlyMedia) + "&exclude_replies=" + String.valueOf(excludeReplies);
        TypeToken<List<MastodonStatus>> list = new TypeToken<List<MastodonStatus>>() {
        };

        return (List<MastodonStatus>) getRequest("/api/v1/accounts/" + id + "/statuses" + param).parseAs(list.getType());
    }

    public MastodonAccount follow(int id) throws IOException {
        return getRequest("/api/v1/accounts/" + id + "/follow").parseAs(MastodonAccount.class);
    }

    public MastodonAccount unfollow(int id) throws IOException {
        return getRequest("/api/v1/accounts/" + id + "/unfollow").parseAs(MastodonAccount.class);
    }

    public MastodonAccount block(int id) throws IOException {
        return getRequest("/api/v1/accounts/" + id + "/block").parseAs(MastodonAccount.class);
    }

    public MastodonAccount unblock(int id) throws IOException {
        return getRequest("/api/v1/accounts/" + id + "/unblock").parseAs(MastodonAccount.class);
    }

    public MastodonAccount mute(int id) throws IOException {
        return getRequest("/api/v1/accounts/" + id + "/mute").parseAs(MastodonAccount.class);
    }

    public MastodonAccount unmute(int id) throws IOException {
        return getRequest("/api/v1/accounts/" + id + "/unmute").parseAs(MastodonAccount.class);
    }

    // Todo : relationships
    // Todo : all the rest :D
}
