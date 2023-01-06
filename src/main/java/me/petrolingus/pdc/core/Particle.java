package me.petrolingus.pdc.core;

public class Particle {

    double x;
    double y;
    double vx;
    double vy;

    public Particle(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public void periodic() {
        x = (x < 0) ? (x + 1) : x;
        x = (x > 1) ? (x - 1) : x;
        y = (y < 0) ? (y + 1) : y;
        y = (y > 1) ? (y - 1) : y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
