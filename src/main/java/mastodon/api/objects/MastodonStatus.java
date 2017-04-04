package mastodon.api.objects;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import mastodon.api.MastodonApp;

/**
 * @author zyuiop
 */
public class MastodonStatus extends GenericJson {
    @Key
    private int id;
    @Key
    private String uri;
    @Key
    private String url;
    @Key
    private MastodonAccount account;
    @Key("in_reply_to_id")
    private Integer inReplyToId;
    @Key("in_reply_to_account_id")
    private Integer inReplyToAccountId;
    @Key
    private MastodonStatus reblog;
    @Key
    private String content;
    @Key("created_at")
    private String createdAt;
    @Key("reblog_count")
    private int reblogCount;
    @Key("favourites_count")
    private int favouritesCount;
    @Key
    private Boolean reblogged;
    @Key
    private Boolean favourited;
    @Key
    private Boolean sensitive;
    @Key("spoiler_text")
    private String spoilerText;
    @Key
    private String visibility;
    @Key("media_attachments")
    private MastodonAttachment[] mediaAttachments;
    @Key
    private MastodonMention[] mentions;
    @Key
    private MastodonTag[] tags;
    @Key
    private MastodonApplication application;

    public int getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getUrl() {
        return url;
    }

    public MastodonAccount getAccount() {
        return account;
    }

    public Integer getInReplyToId() {
        return inReplyToId;
    }

    public Integer getInReplyToAccountId() {
        return inReplyToAccountId;
    }

    public MastodonStatus getReblog() {
        return reblog;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getReblogCount() {
        return reblogCount;
    }

    public int getFavouritesCount() {
        return favouritesCount;
    }

    public boolean isReblogged() {
        return reblogged;
    }

    public boolean isFavourited() {
        return favourited;
    }

    public boolean isSensitive() {
        return sensitive;
    }

    public String getSpoilerText() {
        return spoilerText;
    }

    public String getVisibility() {
        return visibility;
    }

    public MastodonAttachment[] getMediaAttachments() {
        return mediaAttachments;
    }

    public MastodonMention[] getMentions() {
        return mentions;
    }

    public MastodonTag[] getTags() {
        return tags;
    }

    public MastodonApplication getApplication() {
        return application;
    }
}
