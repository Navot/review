import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GetReviews {
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
        File results = new File("results.csv");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(results.getPath()), StandardCharsets.UTF_8));;
        writer.append("movieIndex,rank,review\n");
        writer.flush();
        ChromeDriver driver = new ChromeDriver();

        try {
            for (int j = 1005; j < 2000; j++) {
                driver.get("https://www.seret.co.il/movies/index.asp?catCase=12&MID=" + j);
                System.out.println("movie index: " + j);
                try {
                    int reviewNum = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/span[2]")).getText());
                    System.out.println("reviewNum: " + reviewNum);

                    try {
                        WebElement reviews = driver.findElement(By.xpath("//*[@id=\"reviews\"]"));
                        List<WebElement> reviewList = reviews.findElements(By.xpath("div"));
                        System.out.println("reviewList size: " + reviewList.size());
                        for (int i = 0; i < reviewList.size(); i++) {
                            String rankString = reviewList.get(i).findElement(By.xpath("div[1]/div/span[1]")).getText();
                            int rank = Integer.parseInt(rankString.substring(0, rankString.indexOf("/")));
                            System.out.println("rank: " + rank);
                            String reviewText = reviewList.get(i).findElement(By.xpath("div[1]/span[2]/div")).getText();
                            System.out.println("reviewText: " + reviewText);
                            writer.append(j + "," + rank + "," + reviewText + "\n");
                            writer.flush();
                        }
                    } catch (Exception e) {
                        System.out.println("Couldn't get the reviews!!! Panic!!!");
                    }
                } catch (Exception e) {
                    System.out.println("Couldn't get the reviews number!! No reviews??");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.close();
        driver.quit();
    }
}
