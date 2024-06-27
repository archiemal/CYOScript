import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the game based on parsed data
 *
 * @author Archie Malvern
 * @version 1.0
 */

public class Runner {
    private Reader reader;
    private ArrayList<String> visitedChoices = new ArrayList<String>();

    public Runner(String fileName) throws IOException {
        reader = new Reader();
        reader.fileRead(fileName); // creates a scanner and reads in a file name from the user
    }

    public String returnDescriptions() {
        String s = reader.getPlayerScene();
        return reader.getScenes().get(s).returnDescriptionList(); // finds the current scene and prints out the scene description
    }

    public ArrayList<String> returnChoices() {
        String s = reader.getPlayerScene();
        ArrayList<String> choicesToReturn = reader.getScenes().get(s).getChoices();
        ArrayList<String> choicesToRemove = new ArrayList<>();
        for (String c : choicesToReturn) {
            if (reader.getTrackChoices().contains(c) && visitedChoices.contains(c)) {
                choicesToRemove.add(c);
            }
        }
        choicesToReturn.removeAll(choicesToRemove);
        return choicesToReturn;
    }

    public void parseChoice(int choiceVal) {
        String s = reader.getPlayerScene();
        visitedChoices.add(reader.getScenes().get(s).getChoices().get(choiceVal - 1));
        reader.setPlayerScene(reader.getScenes().get(s).getChoiceScenes().get(choiceVal)); // changes the current scene number if the scene matches up
    }
}
