package mastodon.api;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import mastodon.api.internal.FormDataContent;
import mastodon.api.internal.RequestsHelper;
import mastodon.api.objects.MastodonInstance;
import mastodon.api.responses.PostAppResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static mastodon.api.internal.RequestsHelper.factory;
import static mastodon.api.internal.RequestsHelper.getUrl;

/**
 * @author zyuiop
 */
public class MastodonApp {
    private final String mastodonInstance;
    private final String clientId;
    private final String clientSecret;

    public MastodonApp(String mastodonInstance, String clientId, String clientSecret) {
        this.mastodonInstance = mastodonInstance;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public static MastodonApp registerApp(String mastodonInstance, String appName, String redirectUris, Scope... scopes) throws IOException {
        // Request content
        FormDataContent content = new FormDataContent();
        content.addPart("client_name", appName);
        if (redirectUris != null)
            content.addPart("redirect_uris", redirectUris);
        content.addPart("scopes", String.join(" ", Arrays.stream(scopes).map(scope -> scope.name().toLowerCase()).collect(Collectors.toList())));

        // Build request
        HttpRequest request = RequestsHelper.factory().buildPostRequest(RequestsHelper.getUrl(mastodonInstance, "/api/v1/apps"), content);
        HttpResponse response = request.execute();
        PostAppResponse app = response.parseAs(PostAppResponse.class);

        if (app.getClientId() == null || app.getClientSecret() == null)
            throw new IOException();

        // Build and return app
        return new MastodonApp(mastodonInstance, app.getClientId(), app.getClientSecret());
    }

    public String getMastodonInstance() {
        return mastodonInstance;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public LoginProcess login(String redirectUri, Scope... scopes) {
        return new LoginProcess(this, redirectUri, scopes);
    }

    /**
     * Returns the current instance information
     * @return instance information
     */
    public MastodonInstance getInstance() throws IOException {
        HttpRequest request = factory().buildGetRequest(getUrl(getMastodonInstance(), "/api/v1/instance"));
        return request.execute().parseAs(MastodonInstance.class);
    }
}
