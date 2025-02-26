package ru.itmo.tpo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.itmo.tpo.task3.ControlElement;
import ru.itmo.tpo.task3.ControlLever;
import ru.itmo.tpo.task3.ControlPanel;
import ru.itmo.tpo.task3.Direction;
import ru.itmo.tpo.task3.Engine;
import ru.itmo.tpo.task3.EvasiveManeuverStrategy;
import ru.itmo.tpo.task3.ManeuverStrategy;
import ru.itmo.tpo.task3.Spaceship;

public class DomainModelTest {

    @Nested
    class ControlElementTests {

        @Test
        void testActivate() {
            var element = new ControlElement();

            element.activate();
            assertTrue(element.isActive());

            element.activate();
            assertTrue(element.isActive());
        }

        @Test
        void testDeactivate() {
            var element = new ControlElement();
            element.activate();

            element.deactivate();
            assertFalse(element.isActive());

            element.deactivate();
            assertFalse(element.isActive());
        }
    }


    @Nested
    class ControlLevelTests {

        @Test
        public void testHandleLeverMovementForward() {
            var lever = new ControlLever(Direction.BACKWARD);
            var panel = new ControlPanel(List.of(lever));

            panel.handleLeverMovement(lever, Direction.FORWARD);

            assertTrue(lever.isActive());
            assertEquals(Direction.FORWARD, lever.getDirection());
        }

        @Test
        public void testHandleLeverMovementBackward() {
            var lever = new ControlLever(Direction.FORWARD);
            var panel = new ControlPanel(List.of(lever));

            panel.handleLeverMovement(lever, Direction.BACKWARD);

            assertFalse(lever.isActive());
            assertEquals(Direction.BACKWARD, lever.getDirection());
        }

        @Test
        public void testHandleLeverInvalidMovement() {
            var lever = new ControlLever(Direction.BACKWARD);
            var panel = new ControlPanel(List.of(lever));

            var thrown =
                assertThrows(IllegalArgumentException.class, () -> panel.handleLeverMovement(lever, Direction.RIGHT));

            assertEquals("Invalid direction", thrown.getMessage());
        }
    }

    @Nested
    class EngineTest {

        @Test
        public void testActivate() {
            var engine = new Engine(Direction.FORWARD);

            engine.activate();
            assertEquals(100, engine.getThrustPower());
        }

        @Test
        public void testDeactivate() {
            var engine = new Engine(Direction.FORWARD);
            engine.activate();

            engine.deactivate();
            assertEquals(0, engine.getThrustPower());
        }
    }


    @Nested
    class EvasiveManeuverStrategyTest {

        @Test
        public void testExecute() {
            var engines = List.of(new Engine(Direction.FORWARD), new Engine(Direction.BACKWARD));
            var strategy = new EvasiveManeuverStrategy();

            engines.getFirst().activate();

            strategy.execute(engines);
            for (var engine : engines) {
                assertEquals(0, engine.getThrustPower());
            }
        }
    }


    @Nested
    class SpaceshipTest {

        @Test
        public void testApplyThrust() {
            var engine = new Engine(Direction.FORWARD);
            var spaceship = new Spaceship(List.of(engine), null, null);

            spaceship.applyThrust(List.of(engine));
            assertEquals(100, engine.getThrustPower());
        }

        @Test
        public void testReleaseThrust() {
            var engine = new Engine(Direction.FORWARD);
            var spaceship = new Spaceship(List.of(engine), null, null);
            spaceship.applyThrust(List.of(engine));

            spaceship.releaseThrust(List.of(engine));
            assertEquals(0, engine.getThrustPower());
        }

        @Test
        void testExecuteManeuverCallsStrategyExecute() {
            var mockStrategy = mock(ManeuverStrategy.class);

            List<Engine> engines = new ArrayList<>();
            Spaceship spaceship = new Spaceship(engines, null, mockStrategy);

            spaceship.executeManeuver();
            verify(mockStrategy, times(1)).execute(engines);
        }
    }
}
