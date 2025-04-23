package assignment9;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {

    private Snake snake;
    private Food food;

    public Game() {
        StdDraw.enableDoubleBuffering();
        snake = new Snake();
        food = new Food();
    }

    public void play() {
        while (true) {
            // Main game loop — ends on out of bounds or self collision
            while (snake.isInbounds() && !snake.hasCollided()) {
                int dir = getKeypress();
                if (dir != -1) {
                    snake.changeDirection(dir);
                }

                snake.move();

                if (snake.eat(food)) {
                    food = new Food(); // Only respawn food on eating
                }

                updateDrawing();
                StdDraw.pause(100); // Control game speed
            }

            // Game over conditions reached — restart game
            System.out.println("Game over! Restarting...");
            StdDraw.pause(1000); // Pause before restart
            snake = new Snake();
            food = new Food();
        }
    }

    private int getKeypress() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) return 1;
        if (StdDraw.isKeyPressed(KeyEvent.VK_S)) return 2;
        if (StdDraw.isKeyPressed(KeyEvent.VK_A)) return 3;
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) return 4;
        return -1;
    }

    private void updateDrawing() {
        StdDraw.clear();
        snake.draw();
        food.draw();
        StdDraw.show();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}