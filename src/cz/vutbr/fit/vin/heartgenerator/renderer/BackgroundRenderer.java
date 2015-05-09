package cz.vutbr.fit.vin.heartgenerator.renderer;

import cz.vutbr.fit.vin.heartgenerator.ui.background.IBackgroundCircle;
import cz.vutbr.fit.vin.heartgenerator.ui.background.IBackgroundProperties;
import cz.vutbr.fit.vin.heartgenerator.ui.background.IBackgroundRecrangle;
import cz.vutbr.fit.vin.heartgenerator.utils.RandomStuffUtil;
import java.util.Random;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Instance of this class is responsible for background rendering.
 * @author Matej Marecek
 */
public class BackgroundRenderer {
    private final IBackgroundProperties properties;
    private final Canvas renderCanvas;
    private final double canvasWidth;
    private final double canvasHeight;
    private final IBackgroundCircle circleProps;
    private final IBackgroundRecrangle recrangleProps;

    public BackgroundRenderer(IBackgroundProperties properties, Canvas renderCanvas) {
        this.properties = properties;
        this.circleProps = properties.getCircleProps();
        this.recrangleProps = properties.getRecrangleProps();
        
        this.renderCanvas = renderCanvas;
        this.canvasWidth = renderCanvas.getWidth();
        this.canvasHeight = renderCanvas.getHeight();
    }
    
    public void render(){
        final GraphicsContext gc = renderCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        
        renderBaseColor(gc);
        
        if(circleProps.isCircleAllowed() == true){
            renderCircles(gc);
        }
        if(recrangleProps.isRectangleAllowed() == true){
            renderRectangles(gc);
        }
        
    }

    private void renderBaseColor(final GraphicsContext gc) {
        gc.setFill(properties.getBaseColor());
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    private void renderCircles(GraphicsContext gc) {
        gc.setFill(circleProps.getCircleColor());
        
        Random random = new Random();
        for (int i = 0; i < circleProps.getCircleCount(); i++) {
            double radiusX = RandomStuffUtil.doubleInRange(random,
                    circleProps.getCircleMinSize(),
                    circleProps.getCircleMaxSize());
            double radiusY = radiusX;
            
            double x = (random.nextDouble()*(canvasWidth+canvasWidth/10.0))-radiusX;
            double y = (random.nextDouble()*(canvasHeight+canvasHeight/10.0))-radiusY;
            
            gc.fillOval(x, y, radiusX, radiusY);
        }
    }
    private void renderRectangles(GraphicsContext gc) {
        gc.setFill(recrangleProps.getRectangleColor());
        
        Random random = new Random();
        for (int i = 0; i < recrangleProps.getRectangleCount(); i++) {
            double radiusX = RandomStuffUtil.doubleInRange(random,
                    recrangleProps.getRectangleMinSize(),
                    recrangleProps.getRectangleMaxSize());
            double radiusY = radiusX;
            
            double x = (random.nextDouble()*(canvasWidth+canvasWidth/10.0))-radiusX;
            double y = (random.nextDouble()*(canvasHeight+canvasHeight/10.0))-radiusY;
            
            gc.fillRect(x, y, radiusX, radiusY);
        }
    }
}
