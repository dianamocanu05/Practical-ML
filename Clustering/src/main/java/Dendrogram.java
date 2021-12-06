import java.util.*;

public class Dendrogram {

    Map<Cluster, Double> dendrogram;
    public Dendrogram(){
        dendrogram = new HashMap<>();
    }

    public void addCluster(Cluster cluster, double height){
        dendrogram.put(cluster, height);
    }

    public void print(){
        Collection<Double> heights =  dendrogram.values();

        for(Double currentHeight : heights) {
            System.out.print(currentHeight + " ");
            for (Map.Entry<Cluster, Double> entry : dendrogram.entrySet()) {
                Cluster cluster = entry.getKey();
                Double height = entry.getValue();
                if(height.equals(currentHeight)){
                    cluster.printNames();
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
