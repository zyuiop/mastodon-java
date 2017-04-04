package mastodon.api;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.gson.reflect.TypeToken;
import mastodon.api.internal.FormDataContent;
import mastodon.api.objects.MastodonAccount;
import mastodon.api.objects.MastodonStatus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import static mastodon.api.internal.RequestsHelper.factory;
import static mastodon.api.internal.RequestsHelper.getUrl;

/**
 * @author zyuiop
 */
public class MastodonSession {
    private final MastodonApp app;
    private final Credential credential;
    private final Raw raw = new Raw();

    MastodonSession(MastodonApp app, Credential credential) {
        this.app = app;
        this.credential = credential;
    }

    public Raw raw() {
        return raw;
    }

    public MastodonAccount getAccount(int id) throws IOException {
        return raw.get("/api/v1/accounts/" + id, MastodonAccount.class);
    }

    public MastodonAccount getCurrentUser() throws IOException {
        return raw.get("/api/v1/accounts/verify_credentials", MastodonAccount.class);
    }

    public List<MastodonAccount> getFollowers(int id) throws IOException {
        TypeToken<List<MastodonAccount>> list = new TypeToken<List<MastodonAccount>>() {
        };

        return raw.get("/api/v1/accounts/" + id + "/followers", list.getType());
    }

    public List<MastodonAccount> getFollowing(int id) throws IOException {
        TypeToken<List<MastodonAccount>> list = new TypeToken<List<MastodonAccount>>() {
        };

        return raw.get("/api/v1/accounts/" + id + "/following", list.getType());
    }

    public List<MastodonStatus> getStatuses(int id) throws IOException {
        return getStatuses(id, false, false);
    }

    public List<MastodonStatus> getStatuses(int id, boolean onlyMedia, boolean excludeReplies) throws IOException {
        String param = "?only_media=" + String.valueOf(onlyMedia) + "&exclude_replies=" + String.valueOf(excludeReplies);
        TypeToken<List<MastodonStatus>> list = new TypeToken<List<MastodonStatus>>() {
        };

        return raw.get("/api/v1/accounts/" + id + "/statuses" + param, list.getType());
    }

    public MastodonAccount follow(int id) throws IOException {
        return raw.get("/api/v1/accounts/" + id + "/follow", MastodonAccount.class);
    }

    public MastodonAccount unfollow(int id) throws IOException {
        return raw.get("/api/v1/accounts/" + id + "/unfollow", MastodonAccount.class);
    }

    public MastodonAccount block(int id) throws IOException {
        return raw.get("/api/v1/accounts/" + id + "/block", MastodonAccount.class);
    }

    public MastodonAccount unblock(int id) throws IOException {
        return raw.get("/api/v1/accounts/" + id + "/unblock", MastodonAccount.class);
    }

    public MastodonAccount mute(int id) throws IOException {
        return raw.get("/api/v1/accounts/" + id + "/mute", MastodonAccount.class);
    }

    public MastodonAccount unmute(int id) throws IOException {
        return raw.get("/api/v1/accounts/" + id + "/unmute", MastodonAccount.class);
    }

    // Todo : relationships

    public class Raw {
        public <T> T get(String endpoint, Class<T> tClass) throws IOException {
            return getUnparsed(endpoint).parseAs(tClass);
        }

        public <T> T get(String endpoint, Type tClass) throws IOException {
            return (T) getUnparsed(endpoint).parseAs(tClass);
        }

        public HttpResponse getUnparsed(String endpoint) throws IOException {
            HttpRequest request = factory().buildGetRequest(getUrl(app.getMastodonInstance(), endpoint));
            credential.initialize(request);
            return request.execute();
        }

        public <T> T post(String endpoint, HttpContent content, Class<T> tClass) throws IOException {
            return postUnparsed(endpoint, content).parseAs(tClass);
        }

        public <T> T post(String endpoint, HttpContent content, Type tClass) throws IOException {
            return (T) postUnparsed(endpoint, content).parseAs(tClass);
        }

        public HttpResponse postUnparsed(String endpoint, HttpContent content) throws IOException {
            HttpRequest request = factory().buildPostRequest(getUrl(app.getMastodonInstance(), endpoint), content);
            credential.initialize(request);
            return request.execute();
        }
    }
}
