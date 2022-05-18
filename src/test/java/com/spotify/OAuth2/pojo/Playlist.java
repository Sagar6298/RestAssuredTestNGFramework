
package com.spotify.OAuth2.pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value    		//we don't need to add access modifiers with variables
//@Data    		//we use this annotation add more data including getter and setter
@Builder        //It is a helpful mechanism for using the Builder pattern without writing boilerplate code. We can apply this annotation to a Class or a method

//After adding builder annotation it doesn't identify jackson binding so for jackson indentification we have add jacksonized annotation
@Jacksonized
@Getter @Setter      //while using these annotations we don't need to write getter and setter methods

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Playlist {

    @JsonProperty("collaborative")
    Boolean collaborative;
    @JsonProperty("description")
    String description;
    @JsonProperty("external_urls")
    ExternalUrls externalUrls;
    @JsonProperty("followers")
    Followers followers;
    @JsonProperty("href")
    String href;
    @JsonProperty("id")
    String id;
    @JsonProperty("images")
    List<Object> images;
//    private List<Object> images = null;
    @JsonProperty("name")
    String name;
    @JsonProperty("owner")
    Owner owner;
    @JsonProperty("primary_color")
    Object primaryColor;
    @JsonProperty("public")
    Boolean _public;
    @JsonProperty("snapshot_id")
    String snapshotId;
    @JsonProperty("tracks")
    Tracks tracks;
    @JsonProperty("type")
    String type;
    @JsonProperty("uri")
    String uri;

}
