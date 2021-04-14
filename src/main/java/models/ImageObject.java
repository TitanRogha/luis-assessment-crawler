package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ImageObject {

    @JsonProperty("http://schema.org/author")
    private String author;

    @JsonProperty("http://schema.org/contentUrl")
    private ContentUrl contentUrl;

    @JsonProperty("http://schema.org/description")
    private String description;

    @JsonProperty("http://schema.org/mainEntityOfPage")
    private MainEntityOfPage mainEntityOfPage;

    @JsonProperty("http://schema.org/name")
    private String name;

    @JsonProperty("http://schema.org/representativeOfPage")
    private Boolean representativeOfPage;

    @Data
    private static class ContentUrl {

        @JsonProperty("@id")
        private String id;
    }

    @Data
    private static class MainEntityOfPage {

        @JsonProperty("@id")
        private String id;
    }

}
