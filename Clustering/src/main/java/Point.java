import java.util.List;

public class Point {

    private String name;
    private List<Double> coordinates;

    public Point(String name, List<Double> coordinates){
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public void print(){
        System.out.printf("%s: ", this.name);
        for(Double coord : coordinates){
            System.out.printf("%f ", coord);
        }
    }
}
