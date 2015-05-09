package cz.vutbr.fit.vin.heartgenerator.ui.heart;

import cz.vutbr.fit.vin.heartgenerator.properties.AppProp;
import cz.vutbr.fit.vin.heartgenerator.renderer.HeartEquationsEnum;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Matej Marecek
 */
public class HeartShapePropertiesController extends AppProp implements Initializable, IHeartShapeProperties {

    @FXML
    protected ComboBox<HeartEquationsEnum> listOfEquations;
    @FXML
    protected TextField maxParamSize;
    @FXML
    protected TextArea equationX;
    @FXML
    protected TextArea equationY;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initEquationList();
        maxParamSize.textProperty().addListener(this);
        equationX.textProperty().addListener(this);
        equationY.textProperty().addListener(this);
    }    

    boolean allowInvalidation = true;
    private void initEquationList() {
        listOfEquations.getItems().addAll(HeartEquationsEnum.values());
        listOfEquations.getSelectionModel()
                .selectedIndexProperty().addListener((Observable observable) -> {
                    HeartEquationsEnum value = listOfEquations.valueProperty().get();
                    
                    // optimization so invalidation event will be triggered only once
                    allowInvalidation = false;
                    
                    equationX.setText(value.getEqX());
                    equationY.setText(value.getEqY());
                    maxParamSize.setText(value.getParamSize());
                    
                    allowInvalidation = true;
                    
                    invalidated(this);
        });
        listOfEquations.getSelectionModel().select(0);
    }   

    @Override
    public void invalidated(Observable observable) {
        if(allowInvalidation == true){ // optimization
            super.invalidated(observable);
        }
    }

    @Override
    public String getEquationX() {
        return equationX.getText();
    }

    @Override
    public String getEquationY() {
        return equationY.getText();
    }

    @Override
    public String getParameterMaxSize() {
        return maxParamSize.getText();
    }
    
}
