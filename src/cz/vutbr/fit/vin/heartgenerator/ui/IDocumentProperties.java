package cz.vutbr.fit.vin.heartgenerator.ui;

import cz.vutbr.fit.vin.heartgenerator.ui.background.IBackgroundProperties;
import cz.vutbr.fit.vin.heartgenerator.ui.heart.IHeartProperties;
import cz.vutbr.fit.vin.heartgenerator.ui.text.ITextProperties;

/**
 *
 * @author Matej Marecek
 */
public interface IDocumentProperties {

    public IBackgroundProperties getBackgroundProperties();

    public IHeartProperties getHeartProperties();

    public double getImageHeight();

    public double getImageRotation();

    public double getImageWidth();

    public ITextProperties getTextProperties();

    public boolean isAutoUpdateEnabled();
    
}
