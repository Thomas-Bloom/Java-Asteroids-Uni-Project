package game1;

import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;
import static game1.Constants.DT;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class BasicAsteroid {
    public static final int RADIUS = 10;
    public static final double MAX_SPEED = 100;

    private double x, y;
    private double vx, vy;

    public BasicAsteroid(double x, double y, double vx, double vy){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    // TODO public static BasicAsteroid makeRandomAsteroid(){}
    public static BasicAsteroid makeRandomAsteroid(){
        Random random = new Random();
        double randomXpos = FRAME_WIDTH * (Math.random() * Math.PI);
        double randomYPos = FRAME_HEIGHT * (Math.random() * Math.PI);
        double randomVx = -MAX_SPEED + (MAX_SPEED - -MAX_SPEED) * random.nextDouble();
        double randomVy = -MAX_SPEED + (MAX_SPEED - -MAX_SPEED) * random.nextDouble();
        BasicAsteroid asteroid = new BasicAsteroid(randomXpos, randomYPos, randomVx, randomVy);
        return asteroid;
    }

    public void update(){
        x += vx * DT;
        y += vy * DT;
        x = (x + FRAME_WIDTH) % FRAME_WIDTH;
        y = (y + FRAME_HEIGHT) % FRAME_HEIGHT;
    }

    public void draw(Graphics2D g){
        g.setColor(Color.red);
        g.fillOval((int) x - RADIUS, (int) y - RADIUS, 2 * RADIUS, 2 * RADIUS);
    }
}
