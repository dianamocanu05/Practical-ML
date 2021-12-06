import java.util.*;

public class Algorithms {

    private static final int INFINITE = 10000;
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

    public static void topDownClustering(List<Point> points){
        //the graph is represented by the list of edges
        List<Edge> graph = new ArrayList<>();
        //1. compute the distance d(x,y) between each point in S
        int nPoints = points.size();
        for(int i=0;i<nPoints-1;i++){
            for(int j=i+1;j<nPoints;j++){
                Point point1 = points.get(i);
                Point point2 = points.get(j);
                double distance = euclideanDistance(point1, point2);

                Edge edge = new Edge(point1,point2);
                edge.setCost(distance);

                graph.add(edge);
            }
        }

        //2.create mst tree
        List<Edge> mst = kruskalAlgorithm(graph);


        //3.eliminate max cost edge(recursively)
        while(true){

        }

    }


    private static List<Edge> kruskalAlgorithm(List<Edge> edges){
        List<Edge> mst = new ArrayList<>();

        //1. sort edges in non-decreasing weight
        //Arrays.sort(edges, Comparator.comparing(Edge::getCost));
        //2. pick the lowest edge -> if cycle in msr: discard, else: keep

        //3. repeat 2 until V-1 edges in mst

        return mst;
    }

    private static Edge findLowestCost(List<Point> visited, List<Point> remaining, List<Edge> edges){
        double minimum = INFINITE;
        Edge minEdge = null;
        for(Edge e : edges){
                if(e.getCost() < minimum){
                    minEdge= e;
                    minimum = e.getCost();
                }
            }
        return minEdge;
    }


}
