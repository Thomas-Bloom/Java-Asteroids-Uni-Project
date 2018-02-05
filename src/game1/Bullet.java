package game1;

import utilities.Vector2D;
import java.awt.*;

public class Bullet extends GameObject {
    double bulletSpeed = 100;
    double time = 80;

    Bullet(Vector2D spawnPos){
        super(new Vector2D(spawnPos.x,spawnPos.y), new Vector2D(0,0));
        this.velocity.addScaled(Ship.direction, bulletSpeed * Constants.DT);
    }

    @Override
    public void update() {
        super.update();
        time -= 1;
        if(time <= 0){
            dead = true;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.green);
        g.fillOval((int)position.x, (int)position.y, 5, 5);
    }
}
