import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by navot on 2/6/2020.
 */
public class decode {
    public static void main(String[] args) throws IOException {
        File f = new File("index.csv");
        Map<String, Integer> map = encode.loadWordIndex(f);
        String[] codeToDecode = {"29918", "24717", "21245", "26721", "21906"};
        Map<Integer, String> mapInversed =
                map.entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        String result = "";
        for (int i = 0; i < codeToDecode.length; i++) {
            result += mapInversed.get(Integer.parseInt(codeToDecode[i])) + " ";
        }
        System.out.print(result.trim());
    }
}
