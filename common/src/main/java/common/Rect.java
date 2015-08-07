package org.sk.common;

public final class Rect {

    public static final Rect NOT_FOUNT = new Rect(0, 0, 0, 0);

    private double minX;

    private double minY;

    private double maxX;

    private double maxY;

    public Rect(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public double getWidth() {
        return maxX - minX;
    }

    public double getHeight() {
        return maxY - minY;
    }

    public Point getCenter() {
        return new Point(minX + ((maxX - minX) / 2), minY + ((maxY - minY) / 2));
    }
}
