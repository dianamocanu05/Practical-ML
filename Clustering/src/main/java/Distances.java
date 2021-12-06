import java.util.ArrayList;
import java.util.List;

public class Distances {
    public static double euclideanDistance(Point point1, Point point2) {
        List<Double> coordinates1 = point1.getCoordinates();
        List<Double> coordinates2 = point2.getCoordinates();

        int d = coordinates1.size();
        double distance = 0;
        for (int idx = 0; idx < d; idx++) {
            distance += Math.pow((coordinates1.get(idx) - coordinates2.get(idx)), 2);
        }
        return Math.sqrt(distance);
    }

    public static double completeLinkage(Cluster cluster1, Cluster cluster2){
        if (cluster1.isSingleton() && cluster2.isSingleton()) {
            return euclideanDistance(cluster1.getPoints().get(0), cluster2.getPoints().get(0));
        }
        List<Point> points1 = cluster1.getPoints();
        List<Point> points2 = cluster2.getPoints();

        double maximum = -1;
        double distance;
        for (Point point1 : points1) {
            for (Point point2 : points2) {
                    distance = euclideanDistance(point1, point2);
                    if (distance > maximum) {
                        maximum = distance;
                }
            }
        }
        return maximum;
    }

    public static double singleLinkage(Cluster cluster1, Cluster cluster2) {
        if (cluster1.isSingleton() && cluster2.isSingleton()) {
            return euclideanDistance(cluster1.getPoints().get(0), cluster2.getPoints().get(0));
        }
        List<Point> points1 = cluster1.getPoints();
        List<Point> points2 = cluster2.getPoints();

        double minimum = 10000;
        double distance;
        for (Point point1 : points1) {
            for (Point point2 : points2) {
                    distance = euclideanDistance(point1, point2);
                    if (distance < minimum) {
                        minimum = distance;
                }
            }
        }
        return minimum;
    }

    public static double averageLinkage(Cluster cluster1, Cluster cluster2){
        if (cluster1.isSingleton() && cluster2.isSingleton()) {
            return euclideanDistance(cluster1.getPoints().get(0), cluster2.getPoints().get(0));
        }
        List<Point> points1 = cluster1.getPoints();
        List<Point> points2 = cluster2.getPoints();

        int average = 0;
        double distance;
        int count = points1.size() + points2.size();

        for (Point point1 : points1) {
            for (Point point2 : points2) {
                    distance = euclideanDistance(point1, point2);
                    average += distance;
            }
        }

        average/=count;
        return average;

    }

    public static double wardMetric(Cluster cluster1, Cluster cluster2){
        double distance = 0;

        int size1 = cluster1.getPoints().size();
        int size2 = cluster2.getPoints().size();

        Point centroid1 = computeCentroid(cluster1);
        Point centroid2 = computeCentroid(cluster2);
        distance = ((size1*size2)/(size1+size2))*euclideanDistance(centroid1, centroid2);

        return distance;
    }

    private static Point computeCentroid(Cluster cluster){
        List<Point> points = cluster.getPoints();
        int d = points.get(0).getCoordinates().size();
        List<Double> coordinates = new ArrayList<>();
        for(int i=0;i<d;i++) {
            double coord = 0;
            for (Point point : points) {
                coord += point.getCoordinates().get(i);
            }
            coord /= points.size();
            coordinates.add(coord);
        }
        return new Point("Centroid", coordinates);
    }


}
