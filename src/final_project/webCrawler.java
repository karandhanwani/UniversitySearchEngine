package final_project;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class webCrawler {

    private static final String BASE_URL = "https://www.universitystudy.ca/canadian-universities/";
    private static final String OUTPUT_DIRECTORY = "D:\\windsor summer 2023 term 1\\8547 files advanced computing concepts\\project\\CrwaledPages\\";
    private static final int MAX_PAGES = 600; // Maximum number of pages to crawl

    public static void main(String[] args) {
        crawl(BASE_URL);
    }

    public static void crawl(String url) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int pageCount = 0;

        queue.add(url);
        visited.add(url);

        while (!queue.isEmpty() && pageCount < MAX_PAGES) {
            String currentUrl = queue.poll();
            if (pageCount > 0)
            	visited.add(currentUrl);

            try {
                // Fetch the HTML content of the page
                Document document = Jsoup.connect(currentUrl).get();
                saveHtmlFile(document.html(), pageCount);
                pageCount++;

                // Extract links from the page
                Elements links = document.select("a[href]");
                for (Element link : links) {
                    String linkUrl = link.attr("abs:href");

                    if (!visited.contains(linkUrl)) {
                        queue.add(linkUrl);
//                        visited.add(linkUrl);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error fetching URL: " + currentUrl);
            }
        }
    }

    public static void saveHtmlFile(String html, int fileCount) {
        String filename = OUTPUT_DIRECTORY + "file_" + fileCount + ".html";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(html);
        } catch (IOException e) {
            System.err.println("Error saving HTML file: " + filename);
        }
    }
}
