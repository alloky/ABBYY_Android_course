package com.github.alloky.task_2;

public class Utility {
    private Utility() {
    }

    public static float square(Rect a) {
        return (a.getBottomRight().getX() - a.getTopLeft().getX()) * (a.getTopLeft().getY() - a.getBottomRight().getY());
    }

    public static boolean isInside(Point a, Rect b) {
        return (b.getTopLeft().getX() <= a.getX() && a.getX() <= b.getBottomRight().getX()) &&
                (b.getBottomRight().getY() <= a.getY() && a.getY() <= b.getTopLeft().getY());
    }

    public static boolean isOnBorder(Point a, Rect b) {
        return isOnLeftBorder(a, b)
                || isOnRightBorder(a, b)
                || isOnBottomBorder(a, b)
                || isOnTopBorder(a, b);
    }

    private static boolean isOnTopBorder(Point a, Rect b) {
        return a.getY() == b.getTopLeft().getY() && (b.getTopLeft().getX() <= a.getX() && a.getX() <= b.getBottomRight().getX());
    }

    private static boolean isOnBottomBorder(Point a, Rect b) {
        return a.getY() == b.getBottomRight().getY() && (b.getTopLeft().getX() <= a.getX() && a.getX() <= b.getBottomRight().getX());
    }

    private static boolean isOnRightBorder(Point a2, Rect b2) {
        return a2.getX() == b2.getBottomRight().getX() && (b2.getBottomRight().getY() <= a2.getY() && a2.getY() <= b2.getTopLeft().getY());
    }

    public static boolean isOnLeftBorder(Point a, Rect b) {
        return a.getX() == b.getTopLeft().getX() && (b.getBottomRight().getY() <= a.getY() && a.getY() <= b.getTopLeft().getY());
    }
}
