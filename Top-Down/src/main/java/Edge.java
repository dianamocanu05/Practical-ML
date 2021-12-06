public class Edge implements Comparable<Edge>{

    private Point src;
    private Point dest;
    private double cost;

    public Edge(Point src, Point dest){
        this.src = src;
        this.dest = dest;
    }

    public void setCost(double cost){
        this.cost = cost;
    }

    public Point getSrc() {
        return src;
    }

    public Point getDest() {
        return dest;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public int compareTo(Edge o) {
        if(o.getCost() > this.cost){
            return 1;
        }else if(o.getCost() == this.cost){
            return 0;
        }
        return -1;
    }
}
