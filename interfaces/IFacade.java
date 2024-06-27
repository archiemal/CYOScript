package interfaces;

import java.util.ArrayList;

/**
 * An interface to model the facade pattern used to access the library
 *
 * @author Archie Malvern
 * @version 1.0
 */
public interface IFacade {
    public String getSceneDescription();

    public ArrayList<String> getUserChoices();

    public void takeUserInput(int choice);
}
