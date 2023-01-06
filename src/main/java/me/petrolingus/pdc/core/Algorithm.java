package me.petrolingus.pdc.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Algorithm implements Runnable {

    private final List<Particle> particles;

    private final double dt = 0.000001;

    public Algorithm(int particlesNumber) {

        this.particles = new ArrayList<>(particlesNumber);

        // Generate particles
        for (int i = 0; i < particlesNumber; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();
            double direction = ThreadLocalRandom.current().nextDouble(2.0 * Math.PI);
            double vx = 0.01 * Math.cos(direction);
            double vy = 0.01 * Math.sin(direction);
            particles.add(new Particle(x, y, vx, vy));
        }
    }

    private void step() {
        for (Particle a : particles) {
            a.x += a.vx * dt;
            a.y += a.vy * dt;
            a.periodic();
        }
    }

    @Override
    public void run() {
        while (true) {
            step();
            Thread.yield();
        }
    }

    public List<Particle> getParticles() {
        return particles;
    }
}
