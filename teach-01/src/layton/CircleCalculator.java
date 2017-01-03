package layton;

import java.util.Scanner;

/**
 * Created by Dane on 1/3/2017.
 *
 * Stretch Challenge.
 */
public class CircleCalculator {

    public float getRadius() {
        Scanner s = new Scanner(System.in);
        System.out.print("Radius: ");
        float radius = s.nextFloat();
        return radius;
    }

    public void displayCircumference(float radius) {
        double circum = radius * 2 * Math.PI;
        System.out.format("%.2f%n", circum);
    }

    public void displayArea(float radius) {
        double area = Math.PI * radius * radius;
        System.out.format("%.2f%n", area);
    }

    public static void main(String[] args) {
        CircleCalculator c = new CircleCalculator();
        float radius = c.getRadius();
        c.displayCircumference(radius);
        c.displayArea(radius);
    }
}
