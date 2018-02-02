package game1.OLD;

import utilities.Vector2D;

import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;
import static game1.Constants.DT;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class BasicAsteroid{
    public static final int RADIUS = 10;
    public static final double MAX_SPEED = 100;

    private double x, y;
    private double vx, vy;
    private Vector2D vec = new Vector2D();

    public BasicAsteroid(double x, double y, double vx, double vy){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        vec.set(x, y);
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
        vec.x += vx * DT;
        vec.y += vy * DT;
        vec.x = (vec.x + FRAME_WIDTH) % FRAME_WIDTH;
        vec.y = (vec.y + FRAME_HEIGHT) % FRAME_HEIGHT;
    }

    public void draw(Graphics2D g){
        g.setColor(Color.red);
        g.fillOval((int) vec.x - RADIUS, (int) vec.y - RADIUS, 2 * RADIUS, 2 * RADIUS);
    }
}
