package ru.itmo.tpo.task3;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Spaceship {

    private final List<Engine> engines;
    private final ControlPanel controlPanel;
    private ManeuverStrategy maneuverStrategy;

    public void applyThrust(List<Engine> activeEngines) {
        for (var engine : activeEngines) {
            engine.activate();
        }
    }

    public void releaseThrust(List<Engine> inactiveEngines) {
        for (var engine : inactiveEngines) {
            engine.deactivate();
        }
    }

    public void executeManeuver() {
        maneuverStrategy.execute(engines);
    }
}
