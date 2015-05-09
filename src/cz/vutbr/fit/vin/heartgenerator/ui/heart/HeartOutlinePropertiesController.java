package cz.vutbr.fit.vin.heartgenerator.ui.heart;

import cz.vutbr.fit.vin.heartgenerator.properties.AppProp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;

/**
 * FXML Controller class
 *
 * @author Matej Marecek
 */
public class HeartOutlinePropertiesController extends AppProp implements Initializable, IHeartOutlineProperties {

    @FXML
    protected ColorPicker outlineColor;
    @FXML
    protected Slider outlineWidht;
    @FXML
    protected ComboBox<StrokeLineJoin> outlineLineJoin;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initLineJoin();
        initColor();
        outlineWidht.valueProperty().addListener(this);
    }    

    private void initColor() {
        outlineColor.setValue(Color.DARKRED);
        outlineColor.valueProperty().addListener(this);
    }

    private void initLineJoin() {
        outlineLineJoin.getItems().addAll(StrokeLineJoin.values());
        outlineLineJoin.getSelectionModel().selectedIndexProperty().addListener(this);
        outlineLineJoin.getSelectionModel().select(2);
    }

    @Override
    public Color getColor() {
        return outlineColor.getValue();
    }

    @Override
    public double getStrokeWidth() {
        return outlineWidht.getValue();
    }

    @Override
    public StrokeLineJoin getLineJoin() {
        return outlineLineJoin.getValue();
    }
    
}
