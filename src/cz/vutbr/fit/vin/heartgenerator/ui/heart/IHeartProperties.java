package cz.vutbr.fit.vin.heartgenerator.ui.heart;

import cz.vutbr.fit.vin.heartgenerator.properties.IAppProp;

/**
 *
 * @author Matej Marecek
 */
public interface IHeartProperties extends IAppProp {
    public IBasicHeartProperties getBasicProperties();
    public IHeartOutlineProperties getOutlineProperties();
    public IHeartShapeProperties getShapeProperties();
    public IHeartEffects getEffects();
}
