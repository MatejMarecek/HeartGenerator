package cz.vutbr.fit.vin.heartgenerator.renderer;

import cz.vutbr.fit.vin.heartgenerator.ui.heart.IBasicHeartProperties;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.IHeartEffects;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.IHeartOutlineProperties;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.IHeartProperties;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.IHeartShapeProperties;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * Instance of this class is responsible for heart rendering.
 * @author Matej Marecek
 */
public class HeartRenderer {

    private final Canvas renderCanvas;
    
    private final double canvasWidth;
    private final double canvasHeight;
    
    private final IHeartProperties properties;
    private final IBasicHeartProperties basicProperties;
    private final IHeartShapeProperties shapeProperties;
    private final IHeartOutlineProperties outlineProperties;
    private final IHeartEffects effects;

    public HeartRenderer(IHeartProperties properties, Canvas renderCanvas) {
        this.properties = properties;
        this.basicProperties = properties.getBasicProperties();
        this.shapeProperties = properties.getShapeProperties();
        this.outlineProperties = properties.getOutlineProperties();
        this.effects = properties.getEffects();
        
        this.renderCanvas = renderCanvas;
        this.canvasWidth = renderCanvas.getWidth();
        this.canvasHeight = renderCanvas.getHeight();
    }

    public void render() throws Exception {
        final GraphicsContext gc = renderCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        final double stepSize = getEquationStepSize();

        final String parameterMaxSize = shapeProperties.getParameterMaxSize();
        final String equationX = shapeProperties.getEquationX();
        final String equationY = shapeProperties.getEquationY();
        
        final List<Double> xPoints = getEquationResult(stepSize, parameterMaxSize, equationX);
        if(xPoints == null){
            return;
        }
        
        final List<Double> yPoints = getEquationResult(stepSize, parameterMaxSize, equationY);
        if(yPoints == null){
            return;
        }

        final List<Double> xPointsModif = xPoints.stream()
                .map(numb -> sizeCorrectionX(numb))
                .map(numb -> postionCorrectionX(numb))
                .collect(Collectors.toList());
        
        final List<Double> yPointsModif = yPoints.stream()
                .map(numb -> sizeCorrectionY(numb))
                .map(numb -> postionCorrectionY(numb))
                .collect(Collectors.toList());
        
        final double[] xArr = toDoubleArray(xPointsModif);
        final double[] yArr = toDoubleArray(yPointsModif);
        
        renderHeart(gc, xArr, yArr);
        renderHeartOutline(gc, xArr, yArr);
        renderEffects(gc);
    }

    private void renderHeart(final GraphicsContext gc, double[] xArr, double[] yArr) {
        gc.setFill(properties.getBasicProperties().getFillColor());
        gc.fillPolygon(xArr, yArr, xArr.length);
    }
    
    private void renderHeartOutline(final GraphicsContext gc, double[] xArr, double[] yArr) {
        gc.setStroke(outlineProperties.getColor());
        gc.setLineJoin(outlineProperties.getLineJoin());
        gc.setLineWidth(outlineProperties.getStrokeWidth());
        gc.strokePolygon(xArr, yArr, xArr.length);
    }

    private double[] toDoubleArray(List<Double> list) {
        double[] result = new double[list.size()];
        for(int i = 0; i < result.length; i++){
            result[i] = list.get(i);
        }
        return result;
    }

    private double sizeCorrectionX(double x) {
        return x * properties.getBasicProperties().getSizeX();
    }

    private double sizeCorrectionY(double y) {
        return y * properties.getBasicProperties().getSizeY();
    }

    private double postionCorrectionX(double positionX) {
        double x = positionX;
        if(basicProperties.centerHeart() == true){
            x += canvasWidth/2.0;
        }
        
        if(basicProperties.isFlipX() == true){
            x = canvasWidth-x;
        }
        x += basicProperties.getRelativePositionX();
        return x;
    }

    private double postionCorrectionY(double positionY) {
        double y = positionY;
        if(basicProperties.centerHeart() == true){
            y += canvasHeight/2.0;
        }
        
        if(basicProperties.isFlipY() == true){
            y = canvasHeight-y;
        }
        y += basicProperties.getRelativePositionY();
        return y;
    }

    private double getEquationStepSize() {
        return (2.0*Math.PI)/basicProperties.getPrecision();
    }

    private List<Double> getEquationResult(double stepSize, String paramSize, String equation) {
        final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

        /**
         * Let's define some basic mathematic functions for users.
         */
        String code = "var PI = Math.PI;\n";
        code += "var pi = Math.PI;\n";
        code += "var Pi = Math.PI;\n";
        code += "var cos = Math.cos;\n";
        code += "var cosh = Math.cosh;\n";
        code += "var sin = Math.sin;\n";
        code += "var sinh = Math.sinh;\n";
        code += "var tan = Math.tan;\n";
        code += "var tanh = Math.tanh;\n";
        code += "var asin = Math.asin;\n";
        code += "var atan = Math.atan;\n";
        code += "var exp = Math.exp;\n";
        code += "var log = Math.log;\n";
        code += "var pow = Math.pow;\n";
        code += "var abs = Math.abs;\n";
        code += "var sqrt = Math.sqrt;\n";
        code += "var random = Math.random();\n";
        code += "var signum = Math.signum;\n";
        
        /**
         * Algorithm that returns computed points for given parametric equation.
         */
        code += "var __internal_paramSize = "+paramSize+";\n";
        code += "var __internal_stepSize = "+stepSize+";\n";
        code += "var __internal_result = new Array();\n";
        code += "for(var t = 0.0; t < __internal_paramSize; t += __internal_stepSize){\n";
        code += "   var __internal_tmp = "+equation+";\n";
        code += "   __internal_result.push(__internal_tmp);\n";
        code += "}\n";
        code += "__internal_result";
        
        try {
            ScriptObjectMirror eval = (ScriptObjectMirror) engine.eval(code);
            final List<Double> result = eval.values().stream()
                    .map(number -> {
                        // computed value can be Integer or Double
                        if(number instanceof Integer){
                            return ((Number)number).doubleValue();
                        }
                        return (Double)number;
                    })
                    .collect(Collectors.toList());
            
            /**
             * We have to check whether all numbers are finite.
             * Rendering points at "infinite or NaN position" would freeze the program.
             */
            boolean nonFiniteNumber = result.stream()
                    .anyMatch(number -> Double.isFinite(number) == false);
            if(nonFiniteNumber == true){
                return null;
            }
            
            return result;
        } catch (Exception e) {
            return null; // user probably wrote invalid equation
        }
    }

    private void renderEffects(GraphicsContext gc) {
        effects.getEffects()
                .forEach((Effect effect)-> gc.applyEffect(effect));
    }
}
