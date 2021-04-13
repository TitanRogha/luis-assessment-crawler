import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WebCrawler {

    private String selector = "a[href]";

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
                for (Element element : scriptElements) {
                        if(element.attr("type").equals("application/ld+json")) {
                            //System.out.println(element.data());
                            Object jsonObject = JsonUtils.fromString(element.data());
                            Object compact = JsonLdProcessor.compact(jsonObject,new HashMap<>(),new JsonLdOptions());
                            String compactContent = JsonUtils.toPrettyString(compact);
                            //System.out.println(compactContent);
                            ObjectMapper objectMapper = new ObjectMapper();
                            WebSite webSite = objectMapper.readValue(compactContent, WebSite.class);
                            System.out.println(webSite.getUrl().getId());
                            System.out.println(webSite.getPotentialAction().getTarget());
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
            System.out.println(e.fillInStackTrace());
                return null;
            }

    }
}
