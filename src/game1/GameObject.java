package game1;


import utilities.Vector2D;

import java.awt.*;

public abstract class GameObject {
    public Vector2D position;
    public Vector2D velocity;
    public boolean dead;
    public int radius;
    public Color color;
    public static boolean invincible = false;

    public GameObject(Vector2D pos, Vector2D vel, int radius){
        this.position = new Vector2D(pos);
        this.velocity = new Vector2D(vel);
        this.radius = radius;
    }

    public void hit(){
        dead = true;
    }

    public boolean overlap(GameObject other){
        // TODO: Simple overlap detection based on bounding circle
        if(Math.abs((position.x - other.position.x) * (position.x - other.position.x) + (position.y - other.position.y) * (position.y - other.position.y)) < (radius + other.radius) * (radius + other.radius))
            return true;
        else
            return false;
    }

    public void collisionHandling(GameObject other, int scoreToGive){
        if(this.getClass() != other.getClass() && this.overlap(other)){

            if(this.getClass().getName().equals("game1.Ship")){

                if(Ship.invincible){

                }
                else{
                    if(Game.getLives() < 1)
                        this.hit();
                    other.hit();
                    Game.decrementLives();
                }

            }
             if(other.getClass().getName().equals("game1.Ship")){
                if(Ship.invincible){

                }
                else{
                    if(Game.getLives() < 1)
                        other.hit();
                    this.hit();
                    Game.decrementLives();
                }
             }

            if(other.getClass().getName().equals("game1.Bullet")) {
                other.hit();
                this.hit();
                Game.addScore(scoreToGive);
            }
            if(this.getClass().getName().equals("game1.Bullet")){
                this.hit();
                other.hit();
                Game.addScore(scoreToGive);
            }
        }
    }

    public void update(){
        position.addScaled(velocity, Constants.DT);
        position.wrap(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
    }

    public abstract void draw(Graphics2D g);
}
