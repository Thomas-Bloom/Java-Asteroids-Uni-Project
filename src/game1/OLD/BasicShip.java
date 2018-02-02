package game1.OLD;

import game1.Action;
import game1.BasicController;
import game1.Constants;
import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BasicShip{
    public static final int RADIUS = 8;
    public boolean thrusting = false;

    // Rotation velocity in radians per second
    public static final double STEER_RATE = 2 * Math.PI;

    // Acceleration when thrust is applied
    public static final double MAG_ACC = 200;

    // Constant speed loss factor
    public static final double DRAG = 0.01;

    public static final Color COLOR = Color.cyan;

    public Vector2D position; // On frame
    public Vector2D velocity; // Per second
    // Direction of ship pointing
    // Direction of thrust
    // Unit vector representing angle by which ship has rotated
    public Vector2D direction;

    // Controller which provies an Action object in each frame
    private BasicController ctrl;


    public BasicShip(BasicController ctrl){
        this.ctrl = ctrl;
        position = new Vector2D(0,0);
        position.set(Constants.FRAME_WIDTH / 2, Constants.FRAME_HEIGHT / 2);
        velocity = new Vector2D();
        direction = new Vector2D(position);
    }

    public void update(){
        Action action = ctrl.action();
        direction.rotate(action.turn * STEER_RATE * Constants.DT);
        //System.out.println(direction);
        velocity.addScaled(direction, (MAG_ACC * Constants.DT * action.thrust));
        //System.out.println("Velocity = " + velocity);
        position.addScaled(velocity, DRAG * Constants.DT).wrap(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

        if(action.thrust == 1){
            thrusting = true;
        } else{
            thrusting = false;
        }
    }

    public void draw(Graphics2D g){

        int[] XP = {-25,0, 25, 0};
        int[] YP = {25, 0, 25, -50};
        int[] XPThrust = {-13,0,13,0};
        int[] YPThrust = {13, 0, 13, 40};
        Polygon shipPoly = new Polygon(XP, YP, XP.length);
        Polygon thrustPoly = new Polygon(XPThrust, YPThrust, XPThrust.length);
        AffineTransform at = g.getTransform();
        g.translate(position.x, position.y);
        double rot = direction.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(0.5,0.5);
        g.setColor(COLOR);
        g.fillPolygon(shipPoly);

        if(thrusting){
            Color thrustColour = new Color(255, 85, 0);
            g.setColor(thrustColour);
            g.fillPolygon(thrustPoly);
        }
        g.setTransform(at);




        /* OLD CODE
        g.setColor(COLOR);
        g.fillOval((int) position.x - RADIUS, (int) position.y - RADIUS, 2 * RADIUS, 2 * RADIUS);
        g.setColor(Color.green);

        */
        //g.drawLine((int)position.x, (int)position.y, (int)(direction.x + position.x), (int)(direction.y + position.y));
    }
}
