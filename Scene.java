import java.util.ArrayList;
import java.util.HashMap;

/**
 * Models a scene to be used in the language, including description text and choices
 *
 * @author Archie Malvern
 * @version 1.0
 */

public class Scene {

    private StringBuilder sceneDescriptions = new StringBuilder();
    private ArrayList<String> choices = new ArrayList<String>();
    private HashMap<Integer, String> choiceScenes = new HashMap<Integer, String>();

    public Scene() {
    }

    public void addDescription(String description) {
        if (!sceneDescriptions.isEmpty()) {
            sceneDescriptions.append("\n");
        }
        sceneDescriptions.append(description.trim());
    }

    public String returnDescriptionList() {
        return sceneDescriptions.toString();
    }

    public void addChoice(String choiceToAdd) {
        choices.add(choiceToAdd.trim());
    }

    public void addChoiceScene(Integer index, String sceneToAdd) {
        choiceScenes.put(index, sceneToAdd);
    }

    public HashMap<Integer, String> getChoiceScenes() {
        return choiceScenes;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }
}