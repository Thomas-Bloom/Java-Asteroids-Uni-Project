package game1;

import java.awt.*;

public class Constants {
    public static final int FRAME_HEIGHT = 480;
    public static final int FRAME_WIDTH = 640;
    public static final Dimension FRAME_SIZE = new Dimension(
            Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

    // Sleep time between two frames
    public static final int DELAY = 10;
    public static final double DT = DELAY / 1000.0;
}
