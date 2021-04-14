package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "image_object")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_object_id")
    private int id;

    @Column(name = "author")
    @JsonProperty("http://schema.org/author")
    private String author;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "content_url_id")
    @JsonProperty("http://schema.org/contentUrl")
    private ContentUrl contentUrl;

    @Column(name="description")
    @JsonProperty("http://schema.org/description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "main_entity_of_page_id")
    @JsonProperty("http://schema.org/mainEntityOfPage")
    private MainEntityOfPage mainEntityOfPage;

    @Column(name = "name")
    @JsonProperty("http://schema.org/name")
    private String name;

    @Column(name = "representative_of_page")
    @JsonProperty("http://schema.org/representativeOfPage")
    private Boolean representativeOfPage;

    @Data
    @Entity
    @Table(name = "content_url")
    public static class ContentUrl {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "content_url_id")
        private Integer id;

        @JsonProperty("@id")
        private String url;
    }

    @Data
    @Entity
    @Table(name ="main_entity")
    public static class MainEntityOfPage {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "main_entity_of_page_id")
        private Integer id;

        @JsonProperty("@id")
        private String url;
    }

}
