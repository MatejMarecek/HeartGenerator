package cz.vutbr.fit.vin.heartgenerator.ui.background;

import cz.vutbr.fit.vin.heartgenerator.properties.IAppProp;
import javafx.scene.paint.Color;

/**
 *
 * @author Matej Marecek
 */
public interface IBackgroundProperties extends IAppProp {
    public Color getBaseColor();
    public IBackgroundCircle getCircleProps();
    public IBackgroundRecrangle getRecrangleProps();
}
