package com.github.alloky;

// don't remove default imports
// for portability purposes

import com.github.alloky.Point;

public class Main {
    public static void main(String[] args) {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 1);
        assert (distance(p0, p1) - Math.sqrt(2)) < Math.ulp(1.0);
        System.out.println("Sucsess. " + Double.toString(distance(p0, p1)));
    }

    public static double distance(Point a, Point b) {
        return Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }
}
