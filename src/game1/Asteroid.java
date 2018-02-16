package game1;

import utilities.Vector2D;

import java.awt.*;
import java.util.Random;

import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;

public class Asteroid extends GameObject {
    static final double MAX_SPEED = 100;
    static Vector2D direction = new Vector2D();
    private int radius;

    Asteroid(Vector2D pos, Vector2D vel, int radius){
        super(pos, vel, radius);
        this.radius = radius;
        double randomAngle = direction.angle() + Math.PI / 2 * Math.random();
        direction.rotate(randomAngle);
    }

    public static Asteroid makeRandomAsteroid(){
        Random random = new Random();
        double randomXpos = FRAME_WIDTH * (Math.random() * Math.PI);
        double randomYPos = FRAME_HEIGHT * (Math.random() * Math.PI);
        double randomVx = -MAX_SPEED + (MAX_SPEED - -MAX_SPEED) * random.nextDouble();
        double randomVy = -MAX_SPEED + (MAX_SPEED - -MAX_SPEED) * random.nextDouble();
        Asteroid asteroid = new Asteroid(new Vector2D(randomXpos, randomYPos), new Vector2D(randomVx, randomVy), 30);
        return asteroid;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.red);
        g.fillOval((int)position.x, (int)position.y, radius, radius);
    }
}
