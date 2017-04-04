package mastodon.api;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.*;
import com.google.gson.reflect.TypeToken;
import mastodon.api.internal.FormDataContent;
import mastodon.api.objects.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static mastodon.api.internal.RequestsHelper.factory;
import static mastodon.api.internal.RequestsHelper.getStringUrl;
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

    public List<MastodonAccount> search(String q) throws IOException {
        return search(q, 40);
    }

    public List<MastodonAccount> search(String q, int limit) throws IOException {
        TypeToken<List<MastodonAccount>> list = new TypeToken<List<MastodonAccount>>() {
        };

        return raw.get("/api/v1/accounts/search?q=" + q + "&limit=" + limit, list.getType());
    }

    public List<MastodonAccount> blocks() throws IOException {
        TypeToken<List<MastodonAccount>> list = new TypeToken<List<MastodonAccount>>() {
        };

        return raw.get("/api/v1/blocks", list.getType());
    }

    public List<MastodonStatus> favourites() throws IOException {
        TypeToken<List<MastodonStatus>> list = new TypeToken<List<MastodonStatus>>() {
        };

        return raw.get("/api/v1/favourites", list.getType());
    }

    public List<MastodonAccount> followRequests() throws IOException {
        TypeToken<List<MastodonAccount>> list = new TypeToken<List<MastodonAccount>>() {
        };

        return raw.get("/api/v1/follow_requests", list.getType());
    }

    public void authorizeFollowRequest(int accountId) throws IOException {
        raw.postUnparsed("/api/v1/follow_requests/authorize", FormDataContent.singleKey("id", String.valueOf(accountId)));
    }

    public void rejectFollowRequest(int accountId) throws IOException {
        raw.postUnparsed("/api/v1/follow_requests/reject", FormDataContent.singleKey("id", String.valueOf(accountId)));
    }

    /**
     * Follow a remote user
     *
     * @param uri username@domain of the person you want to follow
     * @return local representation of the followed account
     */
    public MastodonAccount follow(String uri) throws IOException {
        return raw.post("/api/v1/follows", FormDataContent.singleKey("uri", uri), MastodonAccount.class);
    }

    public MastodonInstance getInstance() throws IOException {
        return app.getInstance();
    }

    public MastodonAttachment uploadAttachment(File file) throws IOException {
        FileContent content = new FileContent(null, file);
        MultipartContent c = new MultipartContent();
        c.addPart(new MultipartContent.Part(new HttpHeaders().set("Content-Disposition", String.format("form-data; name=\"%s\"", "file")), content));

        return raw.post("/api/v1/media", c, MastodonAttachment.class);
    }

    public List<MastodonAccount> mutes() throws IOException {
        TypeToken<List<MastodonAccount>> list = new TypeToken<List<MastodonAccount>>() {
        };

        return raw.get("/api/v1/mutes", list.getType());
    }

    public List<MastodonNotification> notifications() throws IOException {
        TypeToken<List<MastodonNotification>> list = new TypeToken<List<MastodonNotification>>() {
        };

        return raw.get("/api/v1/notifications", list.getType());
    }

    public MastodonNotification notification(int id) throws IOException {
        return raw.get("/api/v1/notifications/" + id, MastodonNotification.class);
    }

    public void clearNotifications() throws IOException {
        raw.postUnparsed("/api/v1/notifications/clear", new EmptyContent());
    }

    public List<MastodonReport> getReports() throws IOException {
        TypeToken<List<MastodonReport>> list = new TypeToken<List<MastodonReport>>() {
        };

        return raw.get("/api/v1/reports", list.getType());
    }

    public MastodonReport report(int accountToReport, int[] statusesToReport, String comment) throws IOException {
        Validate.isTrue(statusesToReport.length > 0, "statusesToReport cannot be empty");

        return raw.post("/api/v1/reports", new FormDataContent()
                        .addPart("account_id", String.valueOf(accountToReport))
                        .addPart("status_ids", StringUtils.join(statusesToReport, ' '))
                        .addPart("comment", comment),
                MastodonReport.class);
    }

    public MastodonResults search(String q, boolean resolveNonLocal) throws IOException {
        return raw.get("/api/v1/search?q=" + q + "&resolve=" + resolveNonLocal, MastodonResults.class);
    }

    public MastodonStatus getStatus(int id) throws IOException {
        return raw.get("/api/v1/statuses/" + id, MastodonStatus.class);
    }

    public MastodonContext getStatusContext(int statusId) throws IOException {
        return raw.get("/api/v1/statuses/" + statusId + "/contexts", MastodonContext.class);
    }

    public MastodonCard getStatusCard(int statusId) throws IOException {
        return raw.get("/api/v1/statuses/" + statusId + "/card", MastodonCard.class);
    }

    public List<MastodonAccount> getStatusRebloggedBy(int statusId) throws IOException {
        TypeToken<List<MastodonAccount>> list = new TypeToken<List<MastodonAccount>>() {
        };

        return raw.get("/api/v1/statuses/" + statusId + "/reblogged_by", list.getType());
    }

    public List<MastodonAccount> getStatusFavouritedBy(int statusId) throws IOException {
        TypeToken<List<MastodonAccount>> list = new TypeToken<List<MastodonAccount>>() {
        };

        return raw.get("/api/v1/statuses/" + statusId + "/favourited_by", list.getType());
    }

    public MastodonStatus postStatus(String status) throws IOException {
        return postStatus(status, -1, null, false, null, null);
    }

    public MastodonStatus postStatus(String status, Visibility visibility) throws IOException {
        return postStatus(status, -1, null, false, null, visibility);
    }

    public MastodonStatus postStatus(String status, int replyTo) throws IOException {
        return postStatus(status, replyTo, null, false, null, null);
    }

    public MastodonStatus postStatus(String status, String spoilerText, Visibility visibility) throws IOException {
        return postStatus(status, -1, null, false, spoilerText, visibility);
    }

    public MastodonStatus postStatus(String status, int replyTo, int[] mediasIds, boolean sensitiveMedia, String spoilerText, Visibility visibility) throws IOException {
        Validate.isTrue(mediasIds == null || (mediasIds.length >= 0 && mediasIds.length <= 4), "maximum 4 medias");

        FormDataContent content = new FormDataContent();
        content.addPart("status", status);
        if (replyTo >= 0)
            content.addPart("in_reply_to_id", String.valueOf(replyTo));
        if (mediasIds != null && mediasIds.length > 0)
            content.addPart("media_ids", Arrays.toString(mediasIds));
        content.addPart("sensitive", String.valueOf(sensitiveMedia));
        if (spoilerText != null)
            content.addPart("spoiler_text", spoilerText);
        if (visibility != null)
            content.addPart("visibility", visibility.name().toLowerCase());

        return raw.post("/api/v1/statuses", content, MastodonStatus.class);
    }

    public void deleteStatus(int id) throws IOException {
        HttpRequest request = factory().buildDeleteRequest(getUrl(app.getMastodonInstance(), "/api/v1/statuses/" + id));
        credential.initialize(request);
        request.execute();
    }

    public MastodonStatus reblogStatus(int id) throws IOException {
        return raw.post("/api/v1/statuses/" + id + "/reblog", new EmptyContent(), MastodonStatus.class);
    }

    public MastodonStatus unreblogStatus(int id) throws IOException {
        return raw.post("/api/v1/statuses/" + id + "/unreblog", new EmptyContent(), MastodonStatus.class);
    }

    public MastodonStatus favouriteStatus(int id) throws IOException {
        return raw.post("/api/v1/statuses/" + id + "/favourite", new EmptyContent(), MastodonStatus.class);
    }

    public MastodonStatus unfavouriteStatus(int id) throws IOException {
        return raw.post("/api/v1/statuses/" + id + "/unfavourite", new EmptyContent(), MastodonStatus.class);
    }

    private List<MastodonStatus> getTimeline(String tl, boolean local) throws IOException {
        TypeToken<List<MastodonAccount>> list = new TypeToken<List<MastodonAccount>>() {
        };

        return raw.get("/api/v1/timelines/" + tl + (tl.equals("home") ? "" : "?local=" + local), list.getType());
    }

    public List<MastodonStatus> getHomeTimeline() throws IOException {
        return getTimeline("home", false);
    }

    public List<MastodonStatus> getPublicTimeline(boolean local) throws IOException {
        return getTimeline("public", local);
    }

    public List<MastodonStatus> getHashtagTimeline(String tag, boolean local) throws IOException {
        return getTimeline("tag/" + tag, local);
    }

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
