package com.github.alloky.task_2;

public class Utility {
    private Utility(){}

    public static float square(Rect a){
        return (a.getBottomRight().getX() - a.getTopLeft().getX()) * (a.getTopLeft().getY() - a.getBottomRight().getY());
    }

    public static boolean isInside(Point a, Rect b){
        return (b.getTopLeft().getX() <= a.getX() && a.getX() <= b.getBottomRight().getX()) &&
                (b.getBottomRight().getY() <= a.getY() && a.getY() <= b.getTopLeft().getY());
    }

    public static boolean isOnBorder(Point a, Rect b){
        return a.getX() == b.getTopLeft().getX() && (b.getBottomRight().getY() <= a.getY() && a.getY() <= b.getTopLeft().getY())
                || a.getX() == b.getBottomRight().getX() && (b.getBottomRight().getY() <= a.getY() && a.getY() <= b.getTopLeft().getY())
                || a.getY() == b.getBottomRight().getY() && (b.getTopLeft().getX() <= a.getX() && a.getX() <= b.getBottomRight().getX())
                || a.getY() == b.getTopLeft().getY() && (b.getTopLeft().getX() <= a.getX() && a.getX() <= b.getBottomRight().getX());

    }
}
