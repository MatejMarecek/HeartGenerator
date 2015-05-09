package cz.vutbr.fit.vin.heartgenerator;

import cz.vutbr.fit.vin.heartgenerator.ui.icon.AppIcon;
import cz.vutbr.fit.vin.heartgenerator.ui.icon.AppIconUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is where the magic begins. :-)
 * @author Matej Marecek
 */
public class HeartGenerator extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("ui/MainFrame.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);

        stage.getIcons().add(AppIconUtil.getImage(AppIcon.APP_ICON_LARGE)); 
        stage.setTitle("Heart Generator");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
