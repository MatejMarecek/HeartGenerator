package cz.vutbr.fit.vin.heartgenerator.ui.heart;

import cz.vutbr.fit.vin.heartgenerator.properties.AppProp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Matej Marecek
 */
public class BasicHeartPropertiesController extends AppProp implements Initializable, IBasicHeartProperties {

    @FXML
    protected ColorPicker heartColorPicker;
    @FXML
    protected Spinner<Double> heartSizeXSpinner;
    @FXML
    protected Spinner<Double> heartSizeYSpinner;
    @FXML
    protected Spinner<Double> heartPrecisionSpinner;
    @FXML
    protected Slider heartPositionXSlider;
    @FXML
    protected Slider heartPositionYSlider;
    
    private final ReadOnlyDoubleProperty canvasHeightProp;
    private final ReadOnlyDoubleProperty canvasWidthProp;

    public BasicHeartPropertiesController(ReadOnlyDoubleProperty canvasWidthProp, ReadOnlyDoubleProperty canvasHeightProp) {
        this.canvasHeightProp = canvasHeightProp;
        this.canvasWidthProp = canvasWidthProp;
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initSizeProps();
        initPositionProps();
        initPrecisionProp();
        initColor();
    }    

    private void initColor() {
        heartColorPicker.setValue(Color.RED);
        heartColorPicker.valueProperty().addListener(this);
    }    

    private void initPrecisionProp() {
        final DoubleSpinnerValueFactory doubleValueFactory = 
                new DoubleSpinnerValueFactory(3, Double.MAX_VALUE, 500.0, 1);
        heartPrecisionSpinner.setValueFactory(doubleValueFactory);
        heartPrecisionSpinner.valueProperty().addListener(this);
    }

    private void initPositionProps() {
        heartPositionXSlider.setMax(canvasWidthProp.get());
        heartPositionXSlider.setMin(canvasWidthProp.get()*(-1.0));
        
        heartPositionYSlider.setMax(canvasHeightProp.get());
        heartPositionYSlider.setMin(canvasHeightProp.get()*(-1.0));
        
        heartPositionXSlider.valueProperty().addListener(this);
        heartPositionYSlider.valueProperty().addListener(this);
        
        canvasWidthProp.addListener((Observable observable) -> {
            heartPositionXSlider.setMax(canvasWidthProp.get());
            heartPositionXSlider.setMin(canvasWidthProp.get()*(-1.0));
        });

        canvasHeightProp.addListener((Observable observable) -> {
            heartPositionYSlider.setMax(canvasHeightProp.get());
            heartPositionYSlider.setMin(canvasHeightProp.get()*(-1.0));
        });
    }    

    private void initSizeProps() {
        final DoubleSpinnerValueFactory sizeXValueFactory = new DoubleSpinnerValueFactory(Double.MAX_VALUE*(-1.0), Double.MAX_VALUE, 40.0, 1);
        final DoubleSpinnerValueFactory sizeYValueFactory = new DoubleSpinnerValueFactory(Double.MAX_VALUE*(-1.0), Double.MAX_VALUE, 40.0, 1);
        
        heartSizeXSpinner.setValueFactory(sizeXValueFactory);
        heartSizeYSpinner.setValueFactory(sizeYValueFactory);
        
        heartSizeXSpinner.valueProperty().addListener(this);
        heartSizeYSpinner.valueProperty().addListener(this);
    }    

    @Override
    public Color getFillColor() {
        return heartColorPicker.getValue();
    }

    @Override
    public double getPrecision() {
        return heartPrecisionSpinner.getValue();
    }

    @Override
    public double getSizeX() {
        return heartSizeXSpinner.getValue();
    }

    @Override
    public double getSizeY() {
        return heartSizeYSpinner.getValue();
    }

    @Override
    public double getRelativePositionX() {
        return heartPositionXSlider.getValue();
    }

    @Override
    public double getRelativePositionY() {
        return heartPositionYSlider.getValue();
    }
}
