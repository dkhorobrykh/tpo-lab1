package ru.itmo.tpo;

import static java.lang.Math.PI;

public class SinTaylor {

    public static double sin(double x, int terms) {

        x %= (2 * PI);
        if (x > PI)
            x -= 2 * PI;
        else if (x < -PI)
            x += 2 * PI;

        double result = 0.0;
        double term = x;
        double n = 1;

        for (int i = 0; i < terms; i++) {
            result += term;
            term *= -x * x / ((2 * n) * (2 * n + 1));
            n++;
        }

        return result;
    }
}
