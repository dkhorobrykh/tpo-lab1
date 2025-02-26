package ru.itmo.tpo.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ControlElement implements Controllable {
    private boolean isActive = false;

    @Override
    public void activate() {
        if (!this.isActive) {
            isActive = true;
            System.out.println(getClass().getSimpleName() + " activated");
        } else {
            System.out.println(getClass().getSimpleName() + " is already activated");
        }
    }

    @Override
    public void deactivate() {
        if (this.isActive) {
            isActive = false;
            System.out.println(getClass().getSimpleName() + " deactivated");
        } else {
            System.out.println(getClass().getSimpleName() + " is already deactivated");
        }
    }
}
