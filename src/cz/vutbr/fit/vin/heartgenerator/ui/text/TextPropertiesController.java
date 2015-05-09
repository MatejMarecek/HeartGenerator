package cz.vutbr.fit.vin.heartgenerator.ui.text;

import cz.vutbr.fit.vin.heartgenerator.properties.AppProp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Matej Marecek
 */
public class TextPropertiesController  extends AppProp implements Initializable, ITextProperties {

    @FXML
    protected TextField text;
    @FXML
    protected ColorPicker color;
    @FXML
    protected CheckBox strikethrough;
    @FXML
    protected CheckBox underline;
    @FXML
    protected Spinner<Double> sizeX;
    @FXML
    protected Spinner<Double> sizeY;
    @FXML
    protected Spinner<Double> positionX;
    @FXML
    protected Spinner<Double> positionY;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        text.textProperty().addListener(this);
        color.valueProperty().addListener(this);
        strikethrough.selectedProperty().addListener(this);
        underline.selectedProperty().addListener(this);
        
        initSize();
        initPosition();
    }    

    private void initSize() {
        final DoubleSpinnerValueFactory sizeXValueFactory = new DoubleSpinnerValueFactory(0.1, Double.MAX_VALUE, 1.0, 0.1);
        final DoubleSpinnerValueFactory sizeYValueFactory = new DoubleSpinnerValueFactory(0.1, Double.MAX_VALUE, 1.0, 0.1);
        
        sizeX.setValueFactory(sizeXValueFactory);
        sizeY.setValueFactory(sizeYValueFactory);

        sizeX.valueProperty().addListener(this);
        sizeY.valueProperty().addListener(this);
    }    

    private void initPosition() {
        final DoubleSpinnerValueFactory positionXValueFactory = new DoubleSpinnerValueFactory(Double.MAX_VALUE*(-1.0), Double.MAX_VALUE, -75.0, 1);
        final DoubleSpinnerValueFactory positionYValueFactory = new DoubleSpinnerValueFactory(Double.MAX_VALUE*(-1.0), Double.MAX_VALUE, 0.0, 1);
        
        positionX.setValueFactory(positionXValueFactory);
        positionY.setValueFactory(positionYValueFactory);
        
        positionX.valueProperty().addListener(this);
        positionY.valueProperty().addListener(this);
    }    

    @Override
    public String getText() {
        return text.getText();
    }

    @Override
    public Color getColor() {
        return color.getValue();
    }

    @Override
    public boolean isStrikethrough() {
        return strikethrough.isSelected();
    }

    @Override
    public boolean isUnderline() {
        return underline.isSelected();
    }

    @Override
    public double getSizeX() {
        return sizeX.getValue();
    }

    @Override
    public double getSizeY() {
        return sizeY.getValue();
    }

    @Override
    public double getPositionX() {
        return positionX.getValue();
    }

    @Override
    public double getPositionY() {
        return positionY.getValue();
    }
    
}
