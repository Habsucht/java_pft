package distance;

class Point {

    int x;
    int y;

    public Point(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public static double distance(Point p1, Point p2) {

        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;

        return Math.sqrt(dx*dx + dy*dy);
    }


}
