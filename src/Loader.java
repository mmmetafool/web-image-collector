import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Loader {
    public static void main(String[] args) throws IOException {
        Integer count = 0;
//      System.out.println("Please enter pictures from what page you want to save: ");
//      String path = new BufferedReader(new InputStreamReader(System.in)).readLine();
        URL page = new URL("https://news.yahoo.com/");
        InputStream stream = page.openStream();
        FileOutputStream fos = new FileOutputStream("C:\\Users\\danya\\Desktop\\task10\\page.html");
        for (;;) {
            int code = stream.read();
            if (code<0) {
                break;
            }
            fos.write(code);
        }
        fos.flush();
        fos.close();
        Document doc = Jsoup.parse(new File("C:\\Users\\danya\\Desktop\\task10\\page.html"), "UTF-8");
        Elements elements = doc.select("img");
        for (Element element : elements) {
            count++;
            String src = element.attr("src");
            System.out.println(src);
            try {
                URL url = new URL(src);
                InputStream in = new BufferedInputStream(url.openStream());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n = 0;
                while (-1!=(n=in.read(buf))) {
                    out.write(buf, 0, n);
                }
                out.close();
                in.close();
                byte[] response = out.toByteArray();
                FileOutputStream fileOS = new FileOutputStream("C:\\Users\\danya\\Desktop\\task10\\page"+count+".jpg");
                fileOS.write(response);
                fileOS.close();
            } catch (MalformedURLException e) {
            } catch (Exception e) {
            }
        }
    }
}
