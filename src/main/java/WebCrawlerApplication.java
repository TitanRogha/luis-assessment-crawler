
import java.util.ArrayList;

import static crawler.Crawler.crawl;

public class WebCrawlerApplication {

    public static void main(String[] args) {
        String url = "https://www.britishcornershop.co.uk/";
        crawl(1000, url, new ArrayList<>());
    }



}
