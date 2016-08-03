import distance.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

    @Test
    public void testDistance() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(1, 2);

        Assert.assertEquals(Point.distance(point1, point2), 1.0);
    }
}
