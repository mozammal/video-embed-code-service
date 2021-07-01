package com.mozammal.videoembedcodeservice.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OembedVideoResponse {
    private static final Pattern URL_FROM_IFRAME_SRC            = Pattern.compile("src=\"([^\"]+)\"");
    private static final String  TEXT_TO_REMOVE_FROM_IFRAME_SRC = "?feature=oembed";
    private String               type;
    private String               version;
    @JsonProperty("provider_name")
    private String               providerName;
    private String               title;
    @JsonProperty("author_name")
    private String               authorName;
    private String               html;

    public String iframeToUrl(String html) {
        if ((html == null) || (html.length() == 0)) {
            return null;
        }

        Matcher matcher = URL_FROM_IFRAME_SRC.matcher(html);
        matcher.find();
        String embeddableUrl  = matcher.group(1);
        int lastValidIndex = embeddableUrl.lastIndexOf(TEXT_TO_REMOVE_FROM_IFRAME_SRC);

        return (lastValidIndex == -1)
               ? embeddableUrl
               : embeddableUrl.substring(0, lastValidIndex);
    }
}