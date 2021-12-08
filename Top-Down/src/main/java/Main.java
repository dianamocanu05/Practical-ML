import java.io.IOException;
import java.util.List;

public class Main {

    private static String dataPath = "data.csv";
    private static int dimensions = 2;


    public static void main(String[] args) throws IOException {
        List<Point> points = Utils.parseDataset(dataPath,2);
        Algorithms.topDownClustering(points);
    }
}
