package assignment9;

import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public class Food {

    public static final double FOOD_SIZE = 0.02;
    private double x, y;

    /**
     * Creates a new Food at a random location
     */
    public Food() {
        respawn(); // Set a random position
    }

    /**
     * Draws the Food
     */
    public void draw() {
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledCircle(x, y, FOOD_SIZE);
    }

    /**
     * Respawns the food at a new random position
     */
    public void respawn() {
        // Ensure it spawns fully on screen (not half off the edge)
        x = FOOD_SIZE + Math.random() * (1.0 - 2 * FOOD_SIZE);
        y = FOOD_SIZE + Math.random() * (1.0 - 2 * FOOD_SIZE);
    }

    // Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}