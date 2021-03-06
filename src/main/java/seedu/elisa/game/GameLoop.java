package seedu.elisa.game;

import java.util.TreeSet;
import java.util.logging.Logger;

import javafx.scene.canvas.GraphicsContext;
import seedu.elisa.commons.core.LogsCenter;

/**
 * The game loop class.
 */
public class GameLoop implements Runnable {
    private final Grid grid;
    private final GraphicsContext context;
    private int currentScore;
    private int frameRate;
    private float interval;
    private boolean running;
    private boolean paused;
    private boolean keyIsPressed;

    private TreeSet<Integer> scorelist;

    private final Logger logger = LogsCenter.getLogger(getClass());

    public GameLoop(final Grid grid, final GraphicsContext context, TreeSet<Integer> scorelist) {
        this.grid = grid;
        this.context = context;
        frameRate = 20;
        interval = 1000.0f / frameRate; // 1000 ms in a second
        running = true;
        paused = false;
        keyIsPressed = false;
        currentScore = 0;
        this.scorelist = scorelist;
    }

    @Override
    public void run() {
        while (running && !paused) {
            // Time the update and paint calls
            float time = System.currentTimeMillis();

            keyIsPressed = false;
            grid.update();
            Painter.paint(grid, context);

            if (!grid.getSnake().isSafe()) {
                pause();
                currentScore = Painter.getCurrentScore();
                Painter.paintResetMessage(context);
                Painter.paintHighScore(context, scorelist.last());
                Painter.resetScore();
                break;
            }

            time = System.currentTimeMillis() - time;

            // Adjust the timing correctly
            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException ignore) {
                    logger.warning("Error with adding listener to primary stage for popup");
                }
            }
        }
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void stop() {
        running = false;
    }

    public boolean isKeyPressed() {
        return keyIsPressed;
    }

    public void setKeyPressed() {
        keyIsPressed = true;
    }

    public void resume() {
        paused = false;
    }

    public void pause() {
        paused = true;
    }

    public boolean isPaused() {
        return paused;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }
}
