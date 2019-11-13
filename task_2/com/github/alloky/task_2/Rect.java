package com.github.alloky.task_2;

public class Rect {
    private Point topLeft, bottomRight;

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public Rect(Point topLeft, Point bottomRight) {
        if( topLeft.getX() >= bottomRight.getX()  || topLeft.getY() <= bottomRight.getY()){
            throw new IllegalArgumentException();
        }
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }


}
