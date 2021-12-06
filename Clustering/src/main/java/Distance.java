public class Distance {

    private Cluster src;
    private Cluster destination;
    private double distance;

    public Distance(Cluster src, Cluster destination, double distance){
        this.src = src;
        this.destination = destination;
        this.distance = distance;
    }

    public void setDistance(double newDistance){
        this.distance = newDistance;
    }

    public Cluster getSrc() {
        return src;
    }

    public Cluster getDestination() {
        return destination;
    }

    public double getDistance() {
        return distance;
    }


}
