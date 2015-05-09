package cz.vutbr.fit.vin.heartgenerator.ui.text;

import cz.vutbr.fit.vin.heartgenerator.properties.IAppProp;
import javafx.scene.paint.Color;

/**
 *
 * @author Matej Marecek
 */
public interface ITextProperties  extends IAppProp {
    public String getText();
    public Color getColor();
    public boolean isStrikethrough();
    public boolean isUnderline();
    public double getSizeX();
    public double getSizeY();
    public double getPositionX();
    public double getPositionY();
}
