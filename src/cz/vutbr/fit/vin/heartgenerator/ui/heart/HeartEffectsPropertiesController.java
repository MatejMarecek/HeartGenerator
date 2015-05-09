package cz.vutbr.fit.vin.heartgenerator.ui.heart;

import cz.vutbr.fit.vin.heartgenerator.properties.AppProp;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Matej Marecek
 */
public class HeartEffectsPropertiesController extends AppProp implements Initializable, IHeartEffects {

    @FXML
    protected CheckBox allowDropShadow;
    @FXML
    protected Slider dropShadowRadius;
    @FXML
    protected Slider dropShadowSpread;
    @FXML
    protected ColorPicker dropShadowColor;
    @FXML
    protected Spinner<Double> dropShadowOffsetX;
    @FXML
    protected Spinner<Double> dropShadowOffsetY;
    
    @FXML
    protected CheckBox allowBloom;
    @FXML
    protected Slider bloomThreshold;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDropShadow();
        initBloom();
    }    

    @Override
    public List<Effect> getEffects() {
        final List<Effect> effects = new ArrayList<>(2);
        
        if(allowDropShadow.isSelected() == true){
            final DropShadow dropShadow = getDropShadowEffect();
            effects.add(dropShadow);
        }
        
        if(allowBloom.isSelected() == true){
            final Bloom bloom = new Bloom(bloomThreshold.getValue());
            effects.add(bloom);
        }
        
        return effects;
    }

    private DropShadow getDropShadowEffect() {
        final DropShadow dropShadow = new DropShadow();
        
        dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
        dropShadow.setOffsetX(dropShadowOffsetX.getValue());
        dropShadow.setOffsetY(dropShadowOffsetY.getValue());
        dropShadow.setRadius(dropShadowRadius.getValue());
        dropShadow.setSpread(dropShadowSpread.getValue());
        dropShadow.setColor(dropShadowColor.getValue());
        
        return dropShadow;
    }

    private void initDropShadow() {
        allowDropShadow.selectedProperty().addListener(this);
        
        dropShadowColor.setValue(Color.BLACK);
        dropShadowColor.valueProperty().addListener(this);
        
        final DoubleSpinnerValueFactory positionXValueFactory = new DoubleSpinnerValueFactory(Double.MAX_VALUE*(-1.0), Double.MAX_VALUE, 15.0, 1);
        final DoubleSpinnerValueFactory positionYValueFactory = new DoubleSpinnerValueFactory(Double.MAX_VALUE*(-1.0), Double.MAX_VALUE, 15.0, 1);
        
        dropShadowOffsetX.setValueFactory(positionXValueFactory);
        dropShadowOffsetY.setValueFactory(positionYValueFactory);
        
        dropShadowOffsetX.valueProperty().addListener(this);
        dropShadowOffsetY.valueProperty().addListener(this);
        
        dropShadowRadius.valueProperty().addListener(this);
        dropShadowSpread.valueProperty().addListener(this);
    }

    private void initBloom() {
        allowBloom.selectedProperty().addListener(this);
        bloomThreshold.valueProperty().addListener(this);
    }
    
}
