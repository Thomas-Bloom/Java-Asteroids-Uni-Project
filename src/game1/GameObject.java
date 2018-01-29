package game1;

import utilities.Vector2D;

import java.awt.*;

public abstract class GameObject {
    public Vector2D position;
    public Vector2D velocity;
    public boolean dead;
    public double radius;

    public GameObject(Vector2D pos, Vector2D vel, double rad){
        this.position = pos;
        this.velocity = vel;
        this.radius = rad;
    }

    public void hit(){

    }

    public void update(){
        position.addScaled(velocity, Constants.DT);
        position.wrap(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
    }

    public abstract void draw(Graphics2D g);
}
