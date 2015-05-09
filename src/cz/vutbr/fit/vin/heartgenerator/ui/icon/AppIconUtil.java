package cz.vutbr.fit.vin.heartgenerator.ui.icon;

import javafx.scene.image.Image;

/**
 * Utility that loads image from .../ui/icon/ package. 
 * @author Matej Marecek
 */
public class AppIconUtil {

    /**
     * 
     * @param appImage enumerator value that describes the image
     * @return loaded image
     */
    public static Image getImage(AppIcon appImage){
        return getImage(appImage.getRelativePath());
    }
        
    /**
     * @param imagePath image name/path in .../ui/icon/ folder
     * @return loaded image
     */
    public static Image getImage(String imagePath){
        Image image = new Image(AppIconUtil.class.getResourceAsStream(imagePath));
        return image;
    }
}
