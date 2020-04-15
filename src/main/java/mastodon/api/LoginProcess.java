package mastodon.api;

import com.google.api.client.auth.oauth2.*;
import mastodon.api.internal.RequestsHelper;

import java.io.IOException;

/**
 * @author zyuiop
 */
public class LoginProcess {
    private final String redirectUri;
    private AuthorizationCodeFlow flow;
    private MastodonApp app;

    LoginProcess(MastodonApp app, String redirectUri, Scope... scopes) {
        this.flow = RequestsHelper.createFlow(app, scopes);
        this.app = app;
        this.redirectUri = redirectUri;
    }

    public String getLoginURL() {
        AuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        if (redirectUri != null)
            url.setRedirectUri(redirectUri);
        return url.build();
    }

    public TokenResponse authorize(String code) throws IOException {
        AuthorizationCodeTokenRequest request = flow.newTokenRequest(code);
        if (redirectUri != null)
            request.setRedirectUri(redirectUri);
        return request.execute();
    }
}
