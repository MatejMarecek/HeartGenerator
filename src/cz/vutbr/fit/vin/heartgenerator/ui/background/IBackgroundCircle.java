package cz.vutbr.fit.vin.heartgenerator.ui.background;

import cz.vutbr.fit.vin.heartgenerator.properties.IAppProp;
import javafx.scene.paint.Color;

/**
 *
 * @author Matej Marecek
 */
public interface IBackgroundCircle extends IAppProp {
    public boolean isCircleAllowed();
    public Color getCircleColor();
    public int getCircleCount();
    public double getCircleMinSize();
    public double getCircleMaxSize();
}
