import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * The main class for the language which starts the game runner
 *
 * @author Archie Malvern
 * @version 1.0
 */
public class Main extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select CYOScript File");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CYOScript files", "*.cyo"));
        File f = fc.showOpenDialog(stage);
        Graphics g = new Graphics(stage, new Facade(f.getAbsolutePath()));
        g.display();
    }
}