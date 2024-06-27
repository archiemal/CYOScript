import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Serves as a parser for the language, converting passed in data from a file into internal data structures
 *
 * @author Archie Malvern
 * @version 1.0
 */

public class Reader {

    private HashMap<String, Scene> scenes = new HashMap<String, Scene>();
    private String currentScene;
    private String playerScene;
    Boolean startScene = true;
    private ArrayList<String> trackChoices = new ArrayList<String>();

    public void fileRead(String filename) throws FileNotFoundException, IOException {
        try (FileReader fr = new FileReader(filename);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {
            while (infile.hasNext()) {
                switch (infile.next().toLowerCase()) { // this is the parser
                    case "scene":
                        currentScene = infile.next();
                        if (startScene) {
                            playerScene = currentScene;
                            startScene = false;
                        }
                        scenes.put(currentScene, new Scene());
                        break;
                    case "|":
                        scenes.get(currentScene).addDescription(infile.nextLine());
                        break;
                    case "choice":
                        Integer choiceIndex = 1;
                        while (true) {
                            infile.nextLine();
                            String prevLine = infile.nextLine().trim();
                            if (prevLine.toLowerCase().equals("endchoice")) {
                                break;
                            } else {
                                prevLine = prevLine.substring(1);
                                StringBuilder sb = new StringBuilder(prevLine);
                                String nextLine = infile.next().trim();
                                while (nextLine.charAt(0) == '|') {
                                    sb.append("\n");
                                    sb.append(infile.nextLine().trim());
                                    nextLine = infile.next().trim();
                                }
                                if (nextLine.toLowerCase().equals("-once")) {
                                    trackChoices.add(sb.toString().trim());
                                    nextLine = infile.next().trim();
                                }
                                scenes.get(currentScene).addChoiceScene(choiceIndex, nextLine);
                                scenes.get(currentScene).addChoice(sb.toString());
                                choiceIndex += 1;
                            }
                        }
                        break;
                }
            }
        }
    }

    public HashMap<String, Scene> getScenes() {
        return scenes;
    }

    public String getPlayerScene() {
        return playerScene;
    }

    public void setPlayerScene(String playerScene) {
        this.playerScene = playerScene;
    }

    public ArrayList<String> getTrackChoices() {
        return trackChoices;
    }
}