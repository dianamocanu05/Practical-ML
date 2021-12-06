import java.util.*;

public class ClusteringAlgorithm {

    private int nbClusters;
    private List<Cluster> clusters = new ArrayList<>();
    private SimilarityFunctions similarityFunction;
    private List<Distance> distanceMatrix;
    private Dendrogram dendrogram;

    public ClusteringAlgorithm(SimilarityFunctions similarityFunction) {
        this.similarityFunction = similarityFunction;
    }

    public void initialize(List<Point> points, int nbPoints) {
        //initially, all clusters are singleton
        for (Point point : points) {
            Cluster cluster = new Cluster();
            cluster.addPoint(point);
            clusters.add(cluster);
        }
        nbClusters = nbPoints;

        //initialize dendrogram
        dendrogram = new Dendrogram();
    }

    public void run() {
        int iteration = 1;
        while (nbClusters > 1) {
            runIteration(iteration);
            iteration++;
        }
    }

    public void runIteration(int iteration) {
        System.out.println("-------ITERATION " + iteration + "---------------");
        System.out.println("# clusters: " + nbClusters);


        //1. compute distance matrix (using specified similarity function)
        if (iteration == 1) {
            distanceMatrix = new ArrayList<>();
            for (Cluster cluster1 : clusters) {
                for (Cluster cluster2 : clusters) {
                    if (cluster1 != cluster2) {
                        distanceMatrix.add(new Distance(cluster1, cluster2, Distances.euclideanDistance(cluster1.getPoints().get(0), cluster2.getPoints().get(0))));
                    }
                }
            }
        } else {
            distanceMatrix = new ArrayList<>();
            for (Cluster cluster1 : clusters) {
                for (Cluster cluster2 : clusters) {
                    if (!cluster1.equals(cluster2)) {
                        double distance;
                        switch (similarityFunction) {
                            case SINGLE_LINKAGE -> {
                                distance = Distances.singleLinkage(cluster1, cluster2);
                            }
                            case COMPLETE_LINKAGE -> {
                                distance = Distances.completeLinkage(cluster1, cluster2);
                            }
                            case AVERAGE_LINKAGE -> {
                                distance = Distances.averageLinkage(cluster1, cluster2);
                            }
                            case WARD_METRIC -> {
                                distance = Distances.wardMetric(cluster1, cluster2);
                            }
                            default -> throw new IllegalStateException("Unexpected value: " + similarityFunction);
                        }
                        distanceMatrix.add(new Distance(cluster1, cluster2, distance));
                    }
                }
            }
        }

        //2. find minimum distance in distance matrix
        double minimum = 1000.0;
        Cluster minCluster1 = clusters.get(0), minCluster2 = clusters.get(0);
        for (Distance distance : distanceMatrix) {
            double dist = distance.getDistance();
            Cluster cluster1 = distance.getSrc();
            Cluster cluster2 = distance.getDestination();

            if (dist < minimum) {
                minimum = dist;
                minCluster1 = cluster1;
                minCluster2 = cluster2;
            }
        }

        //3. create new cluster
        Cluster newCluster = createCluster(minCluster1, minCluster2);
        //4. update cluster list
        clusters.set(clusters.indexOf(minCluster1), newCluster);
        clusters.remove(minCluster2);
        nbClusters--;


        //5. update dendrogram
        dendrogram.addCluster(newCluster, minimum);
    }

    private boolean findCluster(List<Cluster> clusters, Cluster cluster){
        for(Cluster cluster1 : clusters){
            if(cluster1.getPoints().equals(cluster.getPoints())){
                return true;
            }
        }
        return false;
    }

    private Cluster createCluster(Cluster cluster1, Cluster cluster2) {
        System.out.print("Merging: ");
        cluster1.printNames();
        System.out.print( " + ");
        cluster2.printNames();
        System.out.println();
        cluster1.addPoints(cluster2.getPoints());
        return cluster1;
    }

    public Dendrogram getDendrogram() {
        return dendrogram;
    }

}
