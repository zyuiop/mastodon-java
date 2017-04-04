package mastodon.api.internal;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.MultipartContent;

/**
 * @author zyuiop
 */
public class FormDataContent extends MultipartContent {
    public void addPart(String name, String value) {
        MultipartContent.Part part = new MultipartContent.Part(ByteArrayContent.fromString(null, value));
        part.setHeaders(new HttpHeaders().set("Content-Disposition", String.format("form-data; name=\"%s\"", name)));
        this.addPart(part);
    }
}
