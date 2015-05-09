package cz.vutbr.fit.vin.heartgenerator.ui.heart;

import cz.vutbr.fit.vin.heartgenerator.properties.IAppProp;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;

/**
 *
 * @author Matej Marecek
 */
public interface IHeartOutlineProperties extends IAppProp {
    public Color getColor();
    public double getStrokeWidth();
    public StrokeLineJoin getLineJoin();
}
