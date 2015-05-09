package cz.vutbr.fit.vin.heartgenerator.ui;

import cz.vutbr.fit.vin.heartgenerator.properties.AppProp;
import cz.vutbr.fit.vin.heartgenerator.renderer.BackgroundRenderer;
import cz.vutbr.fit.vin.heartgenerator.renderer.HeartRenderer;
import cz.vutbr.fit.vin.heartgenerator.ui.background.BackgroundPropertiesController;
import cz.vutbr.fit.vin.heartgenerator.ui.background.IBackgroundProperties;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.BasicHeartPropertiesController;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.HeartEffectsPropertiesController;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.HeartOutlinePropertiesController;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.HeartProperties;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.HeartShapePropertiesController;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.IBasicHeartProperties;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.IHeartEffects;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.IHeartOutlineProperties;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.IHeartProperties;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.IHeartShapeProperties;
import cz.vutbr.fit.vin.heartgenerator.ui.text.ITextProperties;
import cz.vutbr.fit.vin.heartgenerator.ui.text.TextPropertiesController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;

/**
 *
 * @author Matej Marecek
 */
public class MainFrameController extends AppProp implements Initializable, IDocumentProperties {
    private static final Logger logger = Logger.getLogger(MainFrameController.class.getName());
    
    @FXML
    private Button saveImageButton;
    @FXML
    private Canvas heartCanvas;
    @FXML
    private Canvas backgroundCanvas;
    @FXML
    private AnchorPane canvasPane;
    @FXML
    private Spinner<Integer> imgWidthSpinner;
    @FXML
    private Spinner<Integer> imgHeightSpinner;
    @FXML
    private CheckMenuItem autoUpdateCheck;
    @FXML
    private Text heartText;
    @FXML
    private Slider canvasRotationSlider;
    @FXML
    private AnchorPane basicHeartPropsPane;
    @FXML
    private AnchorPane heartOutlinePropsPane;
    @FXML
    private AnchorPane heartShapePropsPane;
    @FXML
    private AnchorPane heartEffectsPropsPane;
    @FXML
    private AnchorPane backgroundPropsPane;
    @FXML
    private AnchorPane textPropsPane;
    
    private boolean isInitDone = false;

    private IBackgroundProperties backgroundProps;
    private IHeartProperties heartProps;
    private ITextProperties textProps;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            bindCanvasSize();

            this.heartProps = initHeartProps();
            this.heartProps.addListener(this);
            
            this.backgroundProps = initBackgroundProps();
            this.backgroundProps.addListener(this);
            
            this.textProps = initTextProps();
            this.textProps.addListener(this);
            
            initImgSizeSpinner(imgWidthSpinner);
            initImgSizeSpinner(imgHeightSpinner);
            canvasRotationSlider.valueProperty().bindBidirectional(canvasPane.rotateProperty());

            isInitDone = true;
            renderImage();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Main frame of app crashed during initialization.", e);
        }
    }    
    
    @FXML
    private void updateImageButtonAction(ActionEvent event) {
        renderImage();
    }
    
    @FXML
    private void saveImageButtonButtonAction(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save image");
        
        fileChooser.getExtensionFilters().add(new ExtensionFilter("PNG", "*.png"));
        
        final File file = fileChooser.showSaveDialog(null);
        if(file != null){
            final WritableImage snapshot = canvasPane.snapshot(new SnapshotParameters(), null);
            try {
                BufferedImage bImage = SwingFXUtils.fromFXImage(snapshot, null);
                ImageIO.write(bImage, "png", file);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Can't save an image: "+file, e);
            }
        }
    }

    @Override
    public void invalidated(Observable observable) {
        super.invalidated(observable);
        if(isAutoUpdateEnabled() == true){
            renderImage();
        }
    }
    

    private void initImgSizeSpinner(Spinner<Integer> spinner) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(25, Integer.MAX_VALUE, 600, 10));
        spinner.valueProperty().addListener(this);
    }

    private void renderImage(){
        if(isInitDone == false){
            return;
        }
        try {
            updateUI();

            new BackgroundRenderer(getBackgroundProperties(), backgroundCanvas)
                    .render();

            new HeartRenderer(getHeartProperties(), heartCanvas)
                    .render();
        } catch (Throwable e) {
            logger.log(Level.SEVERE, "Rendering failed.", e);
        }
    }

    private double getCanvasXCenter() {
        return heartCanvas.getWidth()/2.0;
    }

    private double getCanvasYCenter() {
        return heartCanvas.getHeight()/2.0;
    }

    private IBasicHeartProperties initBasicHeartProps() throws IOException {
        final BasicHeartPropertiesController basicHeartProps = 
                new BasicHeartPropertiesController(canvasPane.widthProperty(), canvasPane.heightProperty());

        final FXMLLoader loader = 
                new FXMLLoader(getClass().getResource("heart/BasicHeartProperties.fxml"));
        loader.setController(basicHeartProps);

        final Node content = loader.load();
        addPropertyToAnchorPane(content, basicHeartPropsPane);
        return basicHeartProps;
    }
    
    private IBackgroundProperties initBackgroundProps() throws IOException {
        final BackgroundPropertiesController backgroundProperties = 
                new BackgroundPropertiesController(canvasPane.widthProperty(), canvasPane.heightProperty());

        final FXMLLoader loader = 
                new FXMLLoader(getClass().getResource("background/BackgroundProperties.fxml"));
        loader.setController(backgroundProperties);

        final Node content = loader.load();
        addPropertyToAnchorPane(content, backgroundPropsPane);
        return backgroundProperties;
    }

    private IHeartProperties initHeartProps() throws IOException {
        final HeartProperties heartProperties = new HeartProperties();
        
        IBasicHeartProperties basicHeartProps = initBasicHeartProps();
        heartProperties.setBasicHeartProperties(basicHeartProps);
        
        IHeartOutlineProperties outlineProps = initHeartOutlineProps();
        heartProperties.setHeartOutlineProperties(outlineProps);
        
        IHeartShapeProperties shapeProps = initHeartShapeProps();
        heartProperties.setHeartShapeProperties(shapeProps);
        
        IHeartEffects effects = initHeartEffects();
        heartProperties.setHeartEffects(effects);
        
        return heartProperties;
    }

    private IHeartOutlineProperties initHeartOutlineProps() throws IOException {
        final FXMLLoader loader = 
                new FXMLLoader(getClass().getResource("heart/HeartOutlineProperties.fxml"));
        final Node content = loader.load();
        final HeartOutlinePropertiesController heartOutlineProperties  = loader.getController();
        
        addPropertyToAnchorPane(content, heartOutlinePropsPane);
        return heartOutlineProperties;
    }

    private IHeartShapeProperties initHeartShapeProps() throws IOException {
        final FXMLLoader loader = 
                new FXMLLoader(getClass().getResource("heart/HeartShapeProperties.fxml"));
        final Node content = loader.load();
        final HeartShapePropertiesController heartShapeProperties  = loader.getController();
        
        addPropertyToAnchorPane(content, heartShapePropsPane);
        return heartShapeProperties;
    }

    private IHeartEffects initHeartEffects() throws IOException {
        final FXMLLoader loader = 
                new FXMLLoader(getClass().getResource("heart/HeartEffectsProperties.fxml"));
        final Node content = loader.load();
        final HeartEffectsPropertiesController heartEffectsProperties  = loader.getController();
        
        addPropertyToAnchorPane(content, heartEffectsPropsPane);
        return heartEffectsProperties;
    }

    private ITextProperties initTextProps() throws IOException {
        final FXMLLoader loader = 
                new FXMLLoader(getClass().getResource("text/TextProperties.fxml"));
        final Node content = loader.load();
        final TextPropertiesController textProperties  = loader.getController();
        
        addPropertyToAnchorPane(content, textPropsPane);
        return textProperties;
    }
    
    private void addPropertyToAnchorPane(final Node property, AnchorPane pane) {
        AnchorPane.setLeftAnchor(property, 7.0);
        AnchorPane.setRightAnchor(property, 7.0);
        pane.getChildren().add(property);
    }

    @Override
    public IBackgroundProperties getBackgroundProperties() {
        return backgroundProps;
    }

    @Override
    public IHeartProperties getHeartProperties() {
        return heartProps;
    }

    @Override
    public ITextProperties getTextProperties() {
        return textProps;
    }

    @Override
    public double getImageWidth() {
        return imgWidthSpinner.getValue();
    }

    @Override
    public double getImageHeight() {
        return imgHeightSpinner.getValue();
    }

    @Override
    public double getImageRotation() {
        return canvasRotationSlider.getValue();
    }

    @Override
    public boolean isAutoUpdateEnabled() {
        return autoUpdateCheck.isSelected();
    }

    private void bindCanvasSize() {
        canvasPane.prefWidthProperty().bindBidirectional(heartCanvas.widthProperty());
        canvasPane.prefHeightProperty().bindBidirectional(heartCanvas.heightProperty());

        canvasPane.prefWidthProperty().bindBidirectional(backgroundCanvas.widthProperty());
        canvasPane.prefHeightProperty().bindBidirectional(backgroundCanvas.heightProperty());
    }

    private void updateUI() {
        updateCanvas();
        updateText();
    }
    
    private void updateCanvas() {
        canvasPane.setRotate(getImageRotation());
        setCanvasSize(getImageWidth(), getImageHeight());
    }
    
    private void setCanvasSize(double imgWidth, double imgHeight) {
        canvasPane.setMinSize(imgWidth, imgHeight);
        canvasPane.setMaxSize(imgWidth, imgHeight);
        canvasPane.setPrefSize(imgWidth, imgHeight);
    }

    private void updateText() {
        heartText.setText(textProps.getText());
        
        heartText.setTranslateX(getCanvasXCenter()+textProps.getPositionX());
        heartText.setTranslateY(getCanvasYCenter()+textProps.getPositionY());

        heartText.setScaleX(textProps.getSizeX());
        heartText.setScaleY(textProps.getSizeY());

        heartText.setFill(textProps.getColor());

        heartText.setStrikethrough(textProps.isStrikethrough());
        heartText.setUnderline(textProps.isUnderline());
    }
}
