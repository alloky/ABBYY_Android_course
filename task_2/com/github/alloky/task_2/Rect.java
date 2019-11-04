package com.github.alloky.task_2;

public class Rect {
    private Point topLeft, bottomRight;

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public Rect(Point topLeft, Point bottomDown) {
        if( topLeft.getX() >= bottomDown.getX()  || topLeft.getY() <= bottomDown.getY()){
            throw new IllegalArgumentException();
        }
        this.topLeft = topLeft;
        this.bottomRight = bottomDown;
    }


}
