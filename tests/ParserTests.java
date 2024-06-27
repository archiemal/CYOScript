import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * A set of tests to ensure the backend of the program works correctly. Tests through the facade pattern.
 *
 * @author Archie Malvern
 * @version 1.0
 */
public class ParserTests {
    Facade facade;

    @BeforeEach
    void loadFile() throws IOException {
        File f = new File("scripts/TestScript.cyo");
        facade = new Facade(f.getAbsolutePath());
    }

    @Test
    void checkFileReadsCorrectly() {
        Assertions.assertEquals("This is a test script!", facade.getSceneDescription());
    }

    @Test
    void testChoiceMaking() {
        facade.takeUserInput(2);
        Assertions.assertEquals("Test 2", facade.getSceneDescription());
    }

    @Test
    void testNoDescriptionAndNoChoices() {
        facade.takeUserInput(3);
        facade.takeUserInput(1);
        Assertions.assertEquals("Works!", facade.getSceneDescription());
    }

    @Test
    void testPickTwo() {
        facade.takeUserInput(1);
        facade.takeUserInput(1);
        Assertions.assertEquals("Test 3", facade.getSceneDescription());
    }

    @Test
    void testPickTwoAlt() {
        facade.takeUserInput(2);
        facade.takeUserInput(2);
        Assertions.assertEquals("Test 4", facade.getSceneDescription());
    }

    @Test
    void testPickThree() {
        facade.takeUserInput(1);
        facade.takeUserInput(1);
        facade.takeUserInput(1);
        Assertions.assertEquals("Test 4", facade.getSceneDescription());
    }

    @Test
    void testLoopBack() {
        facade.takeUserInput(1);
        facade.takeUserInput(2);
        Assertions.assertEquals("Test 1", facade.getSceneDescription());
    }

    @Test
    void testChoiceTracking() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            facade.takeUserInput(2);
            facade.takeUserInput(2);
            facade.takeUserInput(2);
        });
    }

    @Test
    void testChoiceTrackingLoop() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            facade.takeUserInput(1);
            facade.takeUserInput(1);
            facade.takeUserInput(2);
            facade.takeUserInput(1);
            facade.takeUserInput(1);
            facade.takeUserInput(2);
        });
    }
    @Test
    void testPickingTooMany() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            facade.takeUserInput(1);
            facade.takeUserInput(1);
            facade.takeUserInput(1);
            facade.takeUserInput(1);
        });
    }
    @Test
    void testPickOutOfBounds() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            facade.takeUserInput(8);
        });
    }
}
