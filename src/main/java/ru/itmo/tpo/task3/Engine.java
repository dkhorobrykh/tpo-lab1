package ru.itmo.tpo.task3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Engine implements Controllable {
    private final Direction direction;
    private double thrustPower = 0;

    @Override
    public void activate() {
        thrustPower = 100;
        System.out.println("Engine in direction " + direction + " activated with thrust: " + thrustPower);
    }

    @Override
    public void deactivate() {
        thrustPower = 0;
        System.out.println("Engine in direction " + direction + " deactivated");
    }
}
