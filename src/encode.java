import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class encode {
    public static void main(String[] args) throws IOException {
        File f = new File("index.csv");
        Map<String, Integer> map = loadWordIndex(f);

//        System.out.println(map);

        File newFile = new File("CleanText.csv");
        BufferedReader csvReader = new BufferedReader(new FileReader(newFile.getPath()));
        String row = "";
        FileWriter fr = new FileWriter(new File("encodeData.txt"));
        while ((row = csvReader.readLine()) != null) {
            if (!row.startsWith("index")) {
                String text = Cleaner.getCleanText(row);
                String[] words = text.split(" ");
                try {
                    if (words.length > 0) {
                        String encoding = getDigits(words, map);
                        fr.write(row + "," + encoding + "\n");
                    } else {
                        fr.write(row + "," + "[]" + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            csvReader.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getDigits(String[] words, Map<String, Integer> map) {
        String result = "[";
        for (int i = 0; i < words.length; i++) {
            result += map.get(words[i]) + ", ";
        }
        result = result.substring(0, result.lastIndexOf(",")) + "]";
        return result;
    }

    public static Map<String, Integer> loadWordIndex(File f) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(f.getPath()));
        String row = "";
        Map<String, Integer> map = new HashMap<>();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            map.put(data[1], Integer.parseInt(data[0]));
        }
        return map;
    }
}
