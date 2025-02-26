package ru.itmo.tpo.task3;

import java.util.List;

public interface ManeuverStrategy {
    void execute(List<Engine> engineList);
}
