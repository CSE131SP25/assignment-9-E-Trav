package assignment9;

import java.util.LinkedList;
import edu.princeton.cs.introcs.StdDraw;


public class Snake {

    private static final double SEGMENT_SIZE = 0.02;
    private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;

    private LinkedList<BodySegment> segments;
    private double deltaX, deltaY;
    private boolean shouldGrow;

    public Snake() {
        segments = new LinkedList<>();
        segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
        deltaX = 0;
        deltaY = 0;
        shouldGrow = false;
    }

    public void changeDirection(int direction) {
        if (direction == 1) {
            deltaX = 0;
            deltaY = MOVEMENT_SIZE;
        } else if (direction == 2) {
            deltaX = 0;
            deltaY = -MOVEMENT_SIZE;
        } else if (direction == 3) {
            deltaX = -MOVEMENT_SIZE;
            deltaY = 0;
        } else if (direction == 4) {
            deltaX = MOVEMENT_SIZE;
            deltaY = 0;
        }
    }

    public void move() {
        BodySegment head = segments.getFirst();
        double newX = head.getX() + deltaX;
        double newY = head.getY() + deltaY;

        // Add new head
        segments.addFirst(new BodySegment(newX, newY, SEGMENT_SIZE));

        if (!shouldGrow) {
            // Only remove tail if we're not growing
            segments.removeLast();
        } else {
            shouldGrow = false; // Reset grow flag
        }
    }

    public boolean eat(Food f) {
        BodySegment head = segments.getFirst();
        double dx = head.getX() - f.getX();
        double dy = head.getY() - f.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < SEGMENT_SIZE) {
            shouldGrow = true;
            return true;
        }

        return false;
    }

    public boolean hasCollided() {
        BodySegment head = segments.getFirst();
        for (int i = 1; i < segments.size(); i++) {
            BodySegment segment = segments.get(i);
            double dx = head.getX() - segment.getX();
            double dy = head.getY() - segment.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance < SEGMENT_SIZE / 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isInbounds() {
        BodySegment head = segments.getFirst();
        double x = head.getX();
        double y = head.getY();
        return x >= 0 && x <= 1 && y >= 0 && y <= 1;
    }

    public void draw() {
        for (BodySegment segment : segments) {
            segment.draw();
        }
    }
}