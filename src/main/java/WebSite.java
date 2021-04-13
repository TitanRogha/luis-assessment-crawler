import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WebSite implements JsonLd{

    @JsonProperty("http://schema.org/potentialAction")
    private SearchAction potentialAction;

    @JsonProperty("http://schema.org/url")
    private Url url;

    @Data
    public static class SearchAction {
        @JsonProperty("@type")
        private String type;
        @JsonProperty("http://schema.org/query-input")
        private String query_input;
        @JsonProperty("http://schema.org/target")
        private String target;
    }

    @Data
    public static class Url {
        @JsonProperty("@id")
        private String id;
    }
}