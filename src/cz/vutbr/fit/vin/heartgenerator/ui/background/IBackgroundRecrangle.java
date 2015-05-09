package cz.vutbr.fit.vin.heartgenerator.ui.background;

import cz.vutbr.fit.vin.heartgenerator.properties.IAppProp;
import javafx.scene.paint.Color;

/**
 *
 * @author Matej Marecek
 */
public interface IBackgroundRecrangle extends IAppProp {
    public boolean isRectangleAllowed();
    public Color getRectangleColor();
    public int getRectangleCount();
    public double getRectangleMinSize();
    public double getRectangleMaxSize();
}
