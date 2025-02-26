package ru.itmo.tpo.task3;

import java.util.List;

public class EvasiveManeuverStrategy implements ManeuverStrategy {
    @Override
    public void execute(List<Engine> engineList) {
        System.out.println("Executing evasive maneuver strategy");

        for (Engine engine : engineList) {
            if (engine.getThrustPower() > 0) {
                engine.deactivate();
            }
        }

        System.out.println("Performing 180-degree turn");
    }
}
