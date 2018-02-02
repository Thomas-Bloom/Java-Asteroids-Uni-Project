package game1;

import utilities.Vector2D;
import java.awt.*;

public class Bullet extends GameObject {
    double timeToLive = 1;
    double bulletSpeed = 100;

    Bullet(Vector2D spawnPos, Vector2D velocity){
        super(new Vector2D(spawnPos.x,spawnPos.y), new Vector2D(0,0));
        this.velocity.addScaled(Ship.direction, bulletSpeed * Constants.DT);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.green);
        g.fillOval((int)position.x, (int)position.y, 5, 5);
    }
}
