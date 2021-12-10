import java.util.*;

public class Algorithms {

    private static List<Point> visited =  new ArrayList<>();
    private static List<Point> allPoints;
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
        allPoints = new ArrayList<>(points);
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
        List<Edge> mst = kruskalAlgorithm(graph, points.size());



        //3.eliminate max cost edge(recursively)
        mst.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.getCost() > o2.getCost()) {
                    return 1;
                } else if (o1.getCost() == o2.getCost()) {
                    return 0;
                }
                return -1;
            }
        });



        int idx=2;
        while(mst.size()>=1){
            if(idx != 2) {
                mst.remove(0);
            }
            int clusters = printClusters(mst);
            System.out.println("CLUSTERS K=" + clusters);
            idx++;
        }

    }

    private static int printClusters(List<Edge> mst){
        //clusters are represented by the vertices of connected components of the mst
        return connectedComponents(allPoints,mst);
    }

    private static int connectedComponents(List<Point> points, List<Edge> mst){
        int clusters = 0;
        visited = new ArrayList<>();
        for(Point point : points){
            if(!visited.contains(point)){
                DFSUtil(point, mst);
                System.out.println();
                clusters++;
            }
        }
        return clusters;
    }

    private static void DFSUtil(Point point,List<Edge> mst){
        visited.add(point);
        System.out.print(point.getName() + " ");
        List<Point> adjList = getAdjacencyList(point,mst);
        for(Point adjPoint : adjList){
            if(!visited.contains(adjPoint)){
                DFSUtil(adjPoint, mst);
            }
        }
    }


    /**
     * https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
     */
    private static List<Edge> kruskalAlgorithm(List<Edge> edges, int V){
        List<Edge> mst = new ArrayList<>();

        //1. sort edges in increasing weight
        List<Edge> sortedEdges = new ArrayList<>(edges);
        sortedEdges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.getCost() > o2.getCost()) {
                    return 1;
                } else if (o1.getCost() == o2.getCost()) {
                    return 0;
                }
                return -1;
            }
        });



        //2. pick the lowest edge -> if cycle in msr: discard, else: keep
        //3. repeat 2 until V-1 edges in mst

        while (mst.size() < V-1){

              Edge currentEdge = sortedEdges.get(0);
              mst.add(currentEdge);
              if(isCycle(mst)){
                  mst.remove(currentEdge);
              }
              sortedEdges.remove(0);
        }


        return mst;
    }

    private static boolean isCycle(List<Edge> tree){
        visited.clear();
        if(tree.size() == 1 || tree.size() == 2){
            return false;
        }
        List<Point> points = getPoints(tree);
        for(Point point : points){ //for each vertex
            if(!alreadyVisited(point)){ //if not visited
                if(isCyclicUtil(point, null, tree)){
                    return true;
                }
            }
        }
        return false;
    }

    private static List<Point> getAdjacencyList(Point point, List<Edge> edges){
        List<Point> adjacencyList = new ArrayList<>();
        for(Edge edge : edges){
            Point x = edge.getSrc(); Point y = edge.getDest();
            if(x.equals(point) && !adjacencyList.contains(y)){
                adjacencyList.add(y);
            }
            if(y.equals(point) && !adjacencyList.contains(x)){
                adjacencyList.add(x);
            }
        }
        return adjacencyList;
    }

    private static boolean isCyclicUtil(Point point, Point parent,List<Edge> edges){
        visited.add(point);
        List<Point> adjacencyList = getAdjacencyList(point, edges);

        for(Point adjPoint : adjacencyList){
            if(!alreadyVisited(adjPoint)){
                if(isCyclicUtil(adjPoint,point,edges)){
                    return true;
                }
            }
        }
        return false;
    }

    private static List<Point> getPoints(List<Edge> edges){
        List<Point> points = new ArrayList<>();
        for(Edge edge : edges){
            Point x = edge.getSrc();
            Point y = edge.getDest();

            if(!points.contains(x)){
                points.add(x);
            }if(!points.contains(y)){
                points.add(y);
            }
        }
        return points;
    }


    private static boolean alreadyVisited(Point point){
        for(Point other : visited){
            if(other.getName().equals(point.getName())){
                return true;
            }
        }
        return false;
    }



}
