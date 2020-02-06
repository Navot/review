import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by navot on 2/6/2020.
 */
public class Cleaner {
    public static void main(String[] args) throws IOException {
        File f = new File("results.csv");
        BufferedReader csvReader = new BufferedReader(new FileReader(f.getPath()));
        FileWriter fr = new FileWriter(new File("CleanText.csv"));
        String row = "";
        while ((row = csvReader.readLine()) != null) {
            if (!row.startsWith("index")) {
                String prefix = row.substring(0, row.indexOf(",", row.indexOf(",") + 1));
                String text = getCleanText(row);
                fr.write(prefix + "," +text+ "\n");
            }
        }
        try {
            csvReader.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCleanText(String row) {
        String text = row.substring(row.indexOf(",") + 1);
        text = text.substring(text.indexOf(",") + 1);
        text = text.replaceAll("[^א-ת ]+", " ");
        while (text.contains("  ")) {
            text = text.replace("  ", " ");
        }
        return text;
    }
}
