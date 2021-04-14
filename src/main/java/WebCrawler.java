import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import models.ImageObject;
import models.Product;
import models.WebSite;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import  config.Persist;

public class WebCrawler {


    public static void main(String[] args) {
        String url = "https://www.britishcornershop.co.uk/";

        crawl(1, url, new ArrayList<>());
    }

    private static void crawl(int level, String url, ArrayList<String> visited) {
        if (level <= 1000) {
            Document doc = request(url, visited);
            if (doc != null) {
                for (Element link : doc.select("a[href]")) {
                    String next_link = link.absUrl("href");
                    if (!visited.contains(next_link) && next_link.contains(url)) {
                        crawl(level++, next_link, visited);
                    }
                }
            }
        }
    }

    private static Document request(String url, ArrayList<String> v) {
        try {
            Connection con = Jsoup.connect(url).timeout(10000);
            Document doc = con.get();
            if (con.response().statusCode() == 200) {
                Elements scriptElements = doc.getElementsByTag("script");
                int counter = 0;
                for (Element element : scriptElements) {

                        if(element.attr("type").equals("application/ld+json")) {

                            ObjectMapper objectMapper = new ObjectMapper();
                            JsonNode jsonNode = objectMapper.readTree(element.data());
                            String type = jsonNode.get("@type").asText();
                            System.out.println(type);
                            if(type.equals("ImageObject")) {
                                Object jsonObject = JsonUtils.fromString(element.data());
                                Object compact = JsonLdProcessor.compact(jsonObject, new HashMap<>(), new JsonLdOptions());
                                String compactContent = JsonUtils.toPrettyString(compact);
                                System.out.println(compactContent);

                                switch (type) {

                                    case "WebSite":
                                        WebSite webSite = objectMapper.readValue(compactContent, WebSite.class);
                                        Persist.addWebSite(webSite.getPotentialAction(), webSite.getUrl());
                                        break;
                                    case "Product":
                                        Product product = objectMapper.readValue(compactContent, Product.class);
                                        Persist.addProduct(product.getProductId(), product.getAudience(), product.getBrand(), product.getDescription(), product.getImage(), product.getQtin13(), product.getName(), product.getOffers(), product.getSku(), product.getUrl(), product.getWeight());
                                        counter++;
                                        break;
                                    case "ImageObject":
                                        ImageObject imageObject = objectMapper.readValue(compactContent,ImageObject.class);
                                        Persist.addImageObject(counter,imageObject.getAuthor(),imageObject.getContentUrl(),imageObject.getDescription(),imageObject.getMainEntityOfPage(),imageObject.getName(),imageObject.getRepresentativeOfPage());
                                        break;
                                    default:


                                }
                            }


                        }
                }
                //System.out.println("Link " + url);
                //System.out.println("Title " + doc.title());
                v.add(url);
                return doc;
            }
            System.out.println("Algo fallo aca");
            return null;
        } catch (Exception e){
                return null;
            }

    }
}
