package cz.vutbr.fit.vin.heartgenerator.ui.background;

import cz.vutbr.fit.vin.heartgenerator.properties.AppProp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Matej Marecek
 */
public class BackgroundPropertiesController extends AppProp implements Initializable, IBackgroundProperties, IBackgroundCircle, IBackgroundRecrangle {

    @FXML
    protected ColorPicker baseColor;
    
    @FXML
    protected CheckBox allowCircle;
    @FXML
    protected ColorPicker circleColor;
    @FXML
    protected Spinner<Integer> circleCount;
    @FXML
    protected Slider circleMinSize;
    @FXML
    protected Slider circleMaxSize;
    
    @FXML
    protected CheckBox allowRectangle;
    @FXML
    protected ColorPicker rectangleColor;
    @FXML
    protected Spinner<Integer> rectangleCount;
    @FXML
    protected Slider rectangleMinSize;
    @FXML
    protected Slider rectangleMaxSize;
    
    private final ReadOnlyDoubleProperty canvasHeightProp;
    private final ReadOnlyDoubleProperty canvasWidthProp;
    
    public BackgroundPropertiesController(ReadOnlyDoubleProperty canvasWidthProp, ReadOnlyDoubleProperty canvasHeightProp) {
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
        initCircleBackground();
        initRectangleBackground();
        baseColor.valueProperty().addListener(this);
    }    

    private void initCircleBackground() {
        allowCircle.selectedProperty().addListener(this);
        
        circleColor.setValue(Color.DARKBLUE);
        circleColor.valueProperty().addListener(this);
        
        circleMinSize.valueProperty().addListener(this);
        circleMaxSize.valueProperty().addListener(this);
        circleCount.valueProperty().addListener(this);

        final IntegerSpinnerValueFactory intFactory = new IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 50, 10);
        
        circleCount.setValueFactory(intFactory);
        circleMinSize.valueProperty().addListener((Observable observable) -> {
            circleMaxSize.setMin(circleMinSize.getValue());
        });
        
        circleMaxSize.valueProperty().addListener((Observable observable) -> {
            circleMinSize.setMax(circleMaxSize.getValue());
        });
        
        canvasWidthProp
                .addListener((Observable observable) -> {
                    double maxSize = computeMaxBackgroundObjectSize();
                    circleMaxSize.setMax(maxSize);
                });
        canvasHeightProp
                .addListener((Observable observable) -> {
                    double maxSize = computeMaxBackgroundObjectSize();
                    circleMaxSize.setMax(maxSize);
                });
    }   
    
    private void initRectangleBackground() {
        allowRectangle.selectedProperty().addListener(this);

        rectangleColor.setValue(Color.DARKBLUE);
        rectangleColor.valueProperty().addListener(this);
        
        rectangleMinSize.valueProperty().addListener(this);
        rectangleMaxSize.valueProperty().addListener(this);
        rectangleCount.valueProperty().addListener(this);

        final IntegerSpinnerValueFactory intFactory = new IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 50, 10);
        
        rectangleCount.setValueFactory(intFactory);
        rectangleMinSize.valueProperty().addListener((Observable observable) -> {
            rectangleMaxSize.setMin(rectangleMinSize.getValue());
        });
        
        rectangleMaxSize.valueProperty().addListener((Observable observable) -> {
            rectangleMinSize.setMax(rectangleMaxSize.getValue());
        });
        
        canvasWidthProp
                .addListener((Observable observable) -> {
                    double maxSize = computeMaxBackgroundObjectSize();
                    rectangleMaxSize.setMax(maxSize);
                });
        canvasHeightProp
                .addListener((Observable observable) -> {
                    double maxSize = computeMaxBackgroundObjectSize();
                    rectangleMaxSize.setMax(maxSize);
                });
    }    

    @Override
    public Color getBaseColor() {
        return baseColor.getValue();
    }

    @Override
    public IBackgroundCircle getCircleProps() {
        return this;
    }

    @Override
    public IBackgroundRecrangle getRecrangleProps() {
        return this;
    }

    @Override
    public boolean isCircleAllowed() {
        return allowCircle.isSelected();
    }

    @Override
    public Color getCircleColor() {
        return circleColor.getValue();
    }

    @Override
    public int getCircleCount() {
        return circleCount.getValue();
    }

    @Override
    public double getCircleMinSize() {
        return circleMinSize.getValue();
    }

    @Override
    public double getCircleMaxSize() {
        return circleMaxSize.getValue();
    }

    @Override
    public boolean isRectangleAllowed() {
        return allowRectangle.isSelected();
    }

    @Override
    public Color getRectangleColor() {
        return rectangleColor.getValue();
    }

    @Override
    public int getRectangleCount() {
        return rectangleCount.getValue();
    }

    @Override
    public double getRectangleMinSize() {
        return rectangleMinSize.getValue();
    }

    @Override
    public double getRectangleMaxSize() {
        return rectangleMaxSize.getValue();
    }

    private double computeMaxBackgroundObjectSize() {
        double max = Math.max(canvasWidthProp.doubleValue(), canvasHeightProp.doubleValue());
        return max*0.75;
    }
    
}
