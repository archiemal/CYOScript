import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * The graphical user interface component of the program
 *
 * @author Archie Malvern
 * @version 1.0
 */
public class Graphics {
    private Stage stage;
    private Facade facade;

    public Graphics(Stage stage, Facade facade) {
        this.stage = stage;
        this.facade = facade;
    }

    public void display() {
        stage.setTitle("CYOScript GUI");
        Label mainText = new Label();
        mainText.setText(facade.getSceneDescription().trim());
        ArrayList<Button> choiceButtons = new ArrayList<Button>();
        if (!facade.getUserChoices().isEmpty()) {
            for (int i = 0; i < facade.getUserChoices().size(); i++) {
                choiceButtons.add(new Button());
                choiceButtons.get(i).setText(facade.getUserChoices().get(i));
                int finalI = i + 1;
                choiceButtons.get(i).setOnAction(e -> {
                    facade.takeUserInput(finalI);
                    display();
                });
            }
        }
        VBox main = new VBox();
        main.setSpacing(10);
        main.setAlignment(Pos.CENTER);
        main.getChildren().add(mainText);
        if (!choiceButtons.isEmpty()) {
            for (Button b : choiceButtons) {
                main.getChildren().add(b);
            }
        }
        stage.setScene(new Scene(main, 800, 400));
        stage.show();
    }
}
