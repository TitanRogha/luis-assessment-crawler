package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @JsonProperty("http://schema.org/productId")
    @Column(name = "product_id")
    private Integer productId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "audience_id")
    @JsonProperty("http://schema.org/audience")
    private Audience audience;

    @Column(name = "brand")
    @JsonProperty("http://schema.org/brand")
    private String brand;

    @Column(name = "description")
    @JsonProperty("http://schema.org/description")
    private String description;

    @Column(name = "qtin13")
    @JsonProperty("http://schema.org/gtin13")
    private long qtin13;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "url_image_id")
    @JsonProperty("http://schema.org/image")
    private ImageUrl image;

    @Column(name = "name")
    @JsonProperty("http://schema.org/name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "offer_id")
    @JsonProperty("http://schema.org/offers")
    private Offers offers;

    @Column(name = "sku")
    @JsonProperty("http://schema.org/sku")
    private Integer sku;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "url_id")
    @JsonProperty("http://schema.org/url")
    private Url url;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "weight_id")
    @JsonProperty("http://schema.org/weight")
    private Weight weight;

    @Data
    @Entity
    @Table(name = "audience")
    public static class Audience {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "audience_id")
        private Integer id;

        @Column(name = "type")
        @JsonProperty("@type")
        private String type;

        @Column(name = "audience_type")
        @JsonProperty("http://schema.org/audienceType")
        private String audienceType;
    }

    @Entity
    @Table(name = "offers")
    @Data
    public static class Offers {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "offer_id")
        private Integer id;

        @Column(name = "type")
        @JsonProperty("@type")
        private String type;

        @Column(name = "availability")
        @JsonProperty("http://schema.org/availability")
        private String availability;

        @Column(name = "item_condition")
        @JsonProperty("http://schema.org/itemCondition")
        private String itemCondition;

        @Column(name = "price")
        @JsonProperty("http://schema.org/price")
        private Double price;

        @Column(name = "price_currency")
        @JsonProperty("http://schema.org/priceCurrency")
        private String priceCurrency;

        @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
        @JoinColumn(name = "url_id" )
        @JsonProperty("http://schema.org/url")
        private Url url;

    }
    @Entity
    @Table(name = "weight")
    @Data
    public static class Weight {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "weight_id")
        private Integer id;

        @Column(name = "type")
        @JsonProperty("@type")
        private String type;

        @Column(name = "unit_code")
        @JsonProperty("http://schema.org/unitCode")
        private String unitCode;

        @Column(name = "value")
        @JsonProperty("http://schema.org/value")
        private Integer value;
    }
    @Entity
    @Table(name = "url")
    @Data
    public static class Url {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "url_id")
        private Integer id;

        @Column(name = "url")
        @JsonProperty("@id")
        private String url;
    }
    @Entity
    @Table(name = "image_url")
    @Data
    public static class ImageUrl {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "url_image_id")
        private Integer id;

        @Column(name = "url")
        @JsonProperty("@id")
        private String url;
    }

}
