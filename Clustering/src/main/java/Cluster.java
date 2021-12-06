import java.util.ArrayList;
import java.util.List;

public class Cluster {

    private List<Point> points;
    public Cluster(List<Point> points){
        this.points = points;
    }
    public Cluster(){
        points = new ArrayList<>();
    }


    public void addPoint(Point point){
        this.points.add(point);
    }

    public void addPoints(List<Point> points){
        this.points.addAll(points);
    }

    public void printNames(){
        for(Point p : points){
            System.out.print(p.getName());
        }
    }

    public boolean isSingleton(){
        return (this.points.size() == 1);
    }

    public List<Point> getPoints(){
        return points;
    }


}
