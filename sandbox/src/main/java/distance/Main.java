package distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите координаты первой точки x,y");
        Point point1 = new Point(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));

        System.out.println("Введите координаты второй точки x,y");
        Point point2 = new Point(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));

        double r = Point.distance(point1, point2);

        System.out.println("Расстояние между точками (" + point1.x + "," + point1.y + ") и (" + point2.x + "," + point2.y + ") равно " + r + " условных единиц.");

    }

}

