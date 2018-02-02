package game1;

import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Ship extends GameObject{
    public static Vector2D direction = new Vector2D();
    private BasicController ctrl;
    public static boolean thrusting = false;
    // Rotation velocity in radians per second
    public static final double STEER_RATE = 2 * Math.PI;
    // Acceleration when thrust is applied
    public static final double MAG_ACC = 2;
    // Constant speed loss factor
    public static final double DRAG = 0.1;

    // Constructor
    Ship(BasicController ctrl) {
        super(new Vector2D(Constants.FRAME_WIDTH / 2, Constants.FRAME_HEIGHT / 2), new Vector2D(0, 0), Color.cyan);
        this.ctrl = ctrl;
        direction = new Vector2D(0, -position.y);
    }

    public void update() {
        // Enables user to control forward/backward movement
        super.update();

        // Code specific to the ship gameObject
        Action action = ctrl.action();
        direction.rotate(action.turn * STEER_RATE * Constants.DT);
        velocity.addScaled(direction, (MAG_ACC * Constants.DT * action.thrust));
        position.addScaled(velocity, DRAG  * Constants.DT);
        position.wrap(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        // Checks if the user is pressing forwards
        if(action.thrust == 1){
            thrusting = true;
        } else{
            thrusting = false;
        }
        if(action.shoot){
            mkBullet();
            action.shoot = false;
        }
    }

    // Create a bullet
    private void mkBullet(){

    }

    @Override
    public void draw(Graphics2D g) {
        int[] XP = {-25, 0, 25, 0};
        int[] YP = {25, 0, 25, -50};
        int[] XPThrust = {-13, 0, 13, 0};
        int[] YPThrust = {13, 0, 13, 40};
        Polygon shipPoly = new Polygon(XP, YP, XP.length);
        Polygon thrustPoly = new Polygon(XPThrust, YPThrust, XPThrust.length);
        AffineTransform at = g.getTransform();
        g.translate(position.x, position.y);
        double rot = Ship.direction.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(0.5, 0.5);
        g.setColor(color);
        g.fillPolygon(shipPoly);

        if (Ship.thrusting) {
            Color thrustColour = new Color(255, 85, 0);
            g.setColor(thrustColour);
            g.fillPolygon(thrustPoly);
        }
        g.setTransform(at);
    }
}
