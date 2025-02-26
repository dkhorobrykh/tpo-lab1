package ru.itmo.tpo.task3;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ControlPanel {
    private final List<ControlElement> controlElements;

    public void handleLeverMovement(ControlLever lever, Direction direction) {
        if (direction == Direction.BACKWARD) {
            lever.deactivate();
            lever.setDirection(direction);
        } else if (direction == Direction.FORWARD) {
            lever.activate();
            lever.setDirection(direction);
        } else {
            throw new IllegalArgumentException("Invalid direction");
        }
    }
}
