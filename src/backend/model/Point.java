package backend.model;

public class Point {

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Point aux){
            return x == aux.getX() && y == aux.getY();
        }
        return false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void movePoint(double diffX, double diffY) {
        x += diffX;
        y += diffY;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

}
