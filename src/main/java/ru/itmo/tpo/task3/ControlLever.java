package ru.itmo.tpo.task3;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ControlLever extends ControlElement{
    private Direction direction;
}
