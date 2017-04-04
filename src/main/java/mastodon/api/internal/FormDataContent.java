package mastodon.api.internal;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.MultipartContent;

import java.util.Map;

/**
 * @author zyuiop
 */
public class FormDataContent extends MultipartContent {
    public FormDataContent addPart(String name, String value) {
        MultipartContent.Part part = new MultipartContent.Part(ByteArrayContent.fromString(null, value));
        part.setHeaders(new HttpHeaders().set("Content-Disposition", String.format("form-data; name=\"%s\"", name)));
        this.addPart(part);
        return this;
    }

    public static FormDataContent fromMap(Map<String, String> map) {
        FormDataContent content = new FormDataContent();
        map.forEach(content::addPart);
        return content;
    }

    public static FormDataContent singleKey(String key, String value) {
        return new FormDataContent().addPart(key, value);
    }
}
