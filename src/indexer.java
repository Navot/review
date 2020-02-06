import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class indexer {
    public static void main(String[] args) throws IOException {
        File f = new File("CleanText.csv");
        BufferedReader csvReader = new BufferedReader(new FileReader(f.getPath()));
        String row = "";
        Map<String, Integer> map = new HashMap<>();
        while ((row = csvReader.readLine()) != null) {
            if (!row.startsWith("index")) {
                String text = Cleaner.getCleanText(row);
//                System.out.println(text);
                String[] words = text.split(" ");
                for (String word : words) {
                    if (map.get(word) != null) {
                        map.put(word, map.get(word) + 1);
                    } else {
                        map.put(word, 1);
                    }
                }
            }
        }
        csvReader.close();

//        System.out.println(map);
        FileWriter fr = new FileWriter(new File("index.csv"));
        int index = 0;
        try {
            for (Map.Entry entry : map.entrySet()) {
                System.out.println(entry.getKey() + "," + entry.getValue());
                fr.write((index++) + "," + entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
