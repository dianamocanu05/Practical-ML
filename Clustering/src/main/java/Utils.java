import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Utils {


        public static SimilarityFunctions getSimilarityFunction (String function){
            return switch (function) {
                case "single" -> SimilarityFunctions.SINGLE_LINKAGE;
                case "double" -> SimilarityFunctions.COMPLETE_LINKAGE;
                case "average" -> SimilarityFunctions.AVERAGE_LINKAGE;
                case "ward" -> SimilarityFunctions.WARD_METRIC;
                default -> null;
            };
        }

        public static List<Point> parseDataset (String dataPath,int dimensions) throws IOException {
            Reader reader = Files.newBufferedReader(Path.of(dataPath));
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    .withIgnoreQuotations(true)
                    .build();
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(0)
                    .withCSVParser(parser)
                    .build();
            List<String[]> lines = csvReader.readAll();
            List<Point> points = new ArrayList<>();

            for (String[] line : lines) {
                String name = line[0];
                List<Double> coordinates = new ArrayList<>();
                for (int idx = 1; idx <= dimensions; idx++) {
                    coordinates.add(Double.parseDouble(line[idx]));
                }
                Point point = new Point(name, coordinates);
                points.add(point);
            }

            return points;

        }
    }
