package mastodon.api.internal;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;
import mastodon.api.MastodonApp;
import mastodon.api.Scope;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author zyuiop
 */
public class RequestsHelper {
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new GsonFactory();
    private static final HttpRequestFactory REQUEST_FACTORY = HTTP_TRANSPORT.createRequestFactory(
            httpRequest -> httpRequest.setParser(new JsonObjectParser(JSON_FACTORY)));

    public static HttpRequestFactory factory() {
        return REQUEST_FACTORY;
    }

    public static GenericUrl getUrl(String instance, String path) {
        return new GenericUrl(getStringUrl(instance, path));
    }


    public static String getStringUrl(String instance, String path) {
        // todo : detect instance pattern
        return "https://" + instance + (path.startsWith("/") ? "" : "/") + path;
    }

    public static AuthorizationCodeFlow createFlow(MastodonApp app, Scope... scopes) {
        AuthorizationCodeFlow.Builder builder = new AuthorizationCodeFlow.Builder(BearerToken.authorizationHeaderAccessMethod(),
                HTTP_TRANSPORT, JSON_FACTORY, getUrl(app.getMastodonInstance(), "/oauth/token"),
                new ClientParametersAuthentication(app.getClientId(), app.getClientSecret()), app.getClientId(), getStringUrl(app.getMastodonInstance(), "/oauth/authorize"));
        builder.setScopes(Arrays.stream(scopes).map(s -> s.name().toLowerCase()).collect(Collectors.toList()));
        return builder.build();
    }
}
