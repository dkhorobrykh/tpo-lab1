package ru.itmo.tpo;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SinTaylorTest {

    private final static int DEFAULT_TERMS = 10;

    @ParameterizedTest
    @MethodSource("getBasicData")
    void basicTest(Pair<Pair<Double, Integer>, Double> data) {
        test(data);
    }

    @ParameterizedTest
    @MethodSource("getCycleData")
    void cycleTest(Pair<Pair<Double, Integer>, Double> data) {
        test(data);
    }

    @ParameterizedTest
    @MethodSource("getNonPiData")
    void nonPiTest(Pair<Pair<Double, Integer>, Double> data) {
        test(data);
    }

    @ParameterizedTest
    @MethodSource("getEdgeData")
    void edgeTest(Pair<Pair<Double, Integer>, Double> data) {
        test(data);
    }

    private void test(Pair<Pair<Double, Integer>, Double> data) {
        Pair<Double, Integer> input = data.getLeft();
        double expected = data.getRight();
        double actual = SinTaylor.sin(input.getLeft(), input.getRight());
        assertEquals(expected, actual, 1e-5);
    }

    private static Stream<Pair<Pair<Double, Integer>, Double>> getBasicData() {
        return Stream.of(
            Pair.of(Pair.of(-PI, DEFAULT_TERMS), .0),
            Pair.of(Pair.of(-3 * PI / 4, DEFAULT_TERMS), -sqrt(2) / 2),
            Pair.of(Pair.of(-PI / 2, DEFAULT_TERMS), -1.),
            Pair.of(Pair.of(-PI / 4, DEFAULT_TERMS), -sqrt(2) / 2),
            Pair.of(Pair.of(.0, DEFAULT_TERMS), .0),
            Pair.of(Pair.of(PI / 4, DEFAULT_TERMS), sqrt(2) / 2),
            Pair.of(Pair.of(PI / 2, DEFAULT_TERMS), 1.),
            Pair.of(Pair.of(3 * PI / 4, DEFAULT_TERMS), sqrt(2) / 2),
            Pair.of(Pair.of(PI, DEFAULT_TERMS), 0.)
        );
    }

    private static Stream<Pair<Pair<Double, Integer>, Double>> getCycleData() {
        return Stream.of(
            Pair.of(Pair.of(PI + 2 * PI, DEFAULT_TERMS), 0.),
            Pair.of(Pair.of(-PI + 2 * PI, DEFAULT_TERMS), 0.),
            Pair.of(Pair.of(PI / 3 + 2 * PI, DEFAULT_TERMS), Math.sin(PI / 3)),
            Pair.of(Pair.of(-PI / 3 - 2 * PI, DEFAULT_TERMS), Math.sin(-PI / 3))
        );
    }

    private static Stream<Pair<Pair<Double, Integer>, Double>> getEdgeData() {
        return Stream.of(
            Pair.of(Pair.of(1e6, DEFAULT_TERMS), Math.sin(1e6)),
            Pair.of(Pair.of(-1e6, DEFAULT_TERMS), Math.sin(-1e6)),

            Pair.of(Pair.of(1e-10, DEFAULT_TERMS), 1e-10),
            Pair.of(Pair.of(-1e-10, DEFAULT_TERMS), -1e-10),

            Pair.of(Pair.of(PI / 4, 1), PI / 4),
            Pair.of(Pair.of(PI / 4, 5), Math.sin(PI / 4)),
            Pair.of(Pair.of(PI / 4, 20), Math.sin(PI / 4))
        );
    }

    private static Stream<Pair<Pair<Double, Integer>, Double>> getNonPiData() {
        return Stream.of(
            Pair.of(Pair.of(0.1, DEFAULT_TERMS), Math.sin(0.1)),
            Pair.of(Pair.of(0.5, DEFAULT_TERMS), Math.sin(0.5)),
            Pair.of(Pair.of(1.0, DEFAULT_TERMS), Math.sin(1.0)),
            Pair.of(Pair.of(2.0, DEFAULT_TERMS), Math.sin(2.0)),
            Pair.of(Pair.of(2.5, DEFAULT_TERMS), Math.sin(2.5)),
            Pair.of(Pair.of(3.0, DEFAULT_TERMS), Math.sin(3.0)),
            Pair.of(Pair.of(-0.1, DEFAULT_TERMS), Math.sin(-0.1)),
            Pair.of(Pair.of(-0.5, DEFAULT_TERMS), Math.sin(-0.5)),
            Pair.of(Pair.of(-1.0, DEFAULT_TERMS), Math.sin(-1.0)),
            Pair.of(Pair.of(-2.0, DEFAULT_TERMS), Math.sin(-2.0))
        );
    }
}
