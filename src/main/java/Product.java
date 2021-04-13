import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Product implements JsonLd{

    @JsonProperty("http://schema.org/audience")
    private Audience audience;

    @JsonProperty("http://schema.org/brand")
    private String brand;

    @JsonProperty("http://schema.org/description")
    private String description;

    @JsonProperty("http://schema.org/gtin13")
    private String qtin13;

    @JsonProperty("http://schema.org/image")
    private Image image;

    @JsonProperty("http://schema.org/name")
    private String name;

    @JsonProperty("http://schema.org/offers")
    private Offers offers;

    @Data
    private static class Audience{
        @JsonProperty("@type")
        private String type;

        @JsonProperty("http://schema.org/audienceType")
        private String audienceType;
    }

    @Data
    private static class Image{
        @JsonProperty("@id")
        private String id;
    }

    @Data
    private static class Offers{
        @JsonProperty("@type")
        private String type;

        @JsonProperty("http://schema.org/availability")
        private String availability;

        @JsonProperty("http://schema.org/itemCondition")
        private String itemCondition;

        @JsonProperty("http://schema.org/price")
        private Double price;

        @JsonProperty("http://schema.org/priceCurrency")
        private String priceCurrency;

        @JsonProperty("http://schema.org/url")
        private Url url;

        private static class Url{
            @JsonProperty("@id")
            private String id;
        }

    }

    @JsonProperty("http://schema.org/productId")
    private Integer productId;

    @JsonProperty("http://schema.org/sku")
    private Integer sku;

    @JsonProperty("http://schema.org/url")
    private Url url;

    @JsonProperty("http://schema.org/weight")
    private Weight weight;

    private static class Weight{
        @JsonProperty("@type")
        private String type;

        @JsonProperty("http://schema.org/unitCode")
        private String unitCode;

        @JsonProperty("http://schema.org/value")
        private Integer value;
    }


    private static class Url{
        @JsonProperty("@id")
        private String id;
    }
}
