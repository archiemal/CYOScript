import interfaces.IFacade;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The facade pattern used to access the library
 *
 * @author Archie Malvern
 * @version 1.0
 */
public class Facade implements IFacade {
    private Runner runner;

    public Facade(String fileName) throws IOException {
        runner = new Runner(fileName);
    }

    @Override
    public String getSceneDescription() {
        return runner.returnDescriptions();
    }

    @Override
    public ArrayList<String> getUserChoices() {
        return runner.returnChoices();
    }

    @Override
    public void takeUserInput(int choice) {
        runner.parseChoice(choice);
    }
}
