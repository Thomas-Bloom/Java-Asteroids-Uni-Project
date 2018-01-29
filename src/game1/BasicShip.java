package game1;

import utilities.Vector2D;

import java.awt.*;

public class BasicShip {
    public static final int RADIUS = 8;

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
        position = new Vector2D();
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
    }

    public void draw(Graphics2D g){
        g.setColor(COLOR);
        g.fillOval((int) position.x - RADIUS, (int) position.y - RADIUS, 2 * RADIUS, 2 * RADIUS);
        g.setColor(Color.green);
        g.drawLine((int)position.x, (int)position.y, (int)(direction.x + position.x) - 150, (int)(direction.y + position.y) - 150);
    }
}
