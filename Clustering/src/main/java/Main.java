import java.io.IOException;
import java.util.List;

public class Main {

    private static String dataPath;
    // dimensions >=1
    private static int dimensions;
    // similarityFunction in {ward, single, complete, average}
    private static SimilarityFunctions similarityFunction;


    public static void main(String[] args) throws IOException {
        dataPath = args[0];
        dimensions = Integer.parseInt(args[1]);
        similarityFunction = Utils.getSimilarityFunction(args[2]);

        List<Point> pointList = Utils.parseDataset(dataPath, dimensions);

        ClusteringAlgorithm algorithm = new ClusteringAlgorithm(similarityFunction);
        algorithm.initialize(pointList, pointList.size());
        algorithm.run();

       /* System.out.println("------DENDROGRAM-----------------");
        Dendrogram dendrogram = algorithm.getDendrogram();
        dendrogram.print();
*/
    }


}
