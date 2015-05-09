package cz.vutbr.fit.vin.heartgenerator.ui.heart;

import cz.vutbr.fit.vin.heartgenerator.properties.IAppProp;
import javafx.scene.paint.Color;

/**
 *
 * @author Matej Marecek
 */
public interface IBasicHeartProperties extends IAppProp {
    public Color getFillColor();
    public double getPrecision();
    public double getSizeX();
    public double getSizeY();
    public double getRelativePositionX();
    public double getRelativePositionY();

    default boolean centerHeart(){
        return true;
    }

    default boolean isFlipX(){
        return true;
    }
    default boolean isFlipY(){
        return true;
    }
}
