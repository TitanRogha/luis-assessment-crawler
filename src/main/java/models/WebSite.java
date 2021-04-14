package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "website")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WebSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "website_id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "url_id")
    @JsonProperty("http://schema.org/url")
    private Url url;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "search_id")
    @JsonProperty("http://schema.org/potentialAction")
    private SearchAction potentialAction;


    @Entity
    @Table(name = "search_action")
    @Data
    public static class SearchAction {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "search_id")
        private Integer ID;

        @Column(name = "type")
        @JsonProperty("@type")
        private String type;

        @Column(name = "query_input")
        @JsonProperty("http://schema.org/query-input")
        private String query_input;

        @Column(name = "target")
        @JsonProperty("http://schema.org/target")
        private String target;
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
}