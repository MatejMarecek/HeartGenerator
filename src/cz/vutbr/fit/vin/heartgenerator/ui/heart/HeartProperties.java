package cz.vutbr.fit.vin.heartgenerator.ui.heart;

import cz.vutbr.fit.vin.heartgenerator.properties.AppProp;

/**
 *
 * @author Matej Marecek
 */
public class HeartProperties extends AppProp implements IHeartProperties{
    
    private IBasicHeartProperties basicHeartProperties;
    private IHeartOutlineProperties heartOutlineProperties;
    private IHeartShapeProperties heartShapeProperties;
    private IHeartEffects heartEffects;

    public void setBasicHeartProperties(IBasicHeartProperties prop) {
        if(basicHeartProperties != null){
            basicHeartProperties.removeListener(this);
        }
        prop.addListener(this);
        this.basicHeartProperties = prop;
    }

    public void setHeartOutlineProperties(IHeartOutlineProperties prop) {
        if(heartOutlineProperties != null){
            heartOutlineProperties.removeListener(this);
        }
        prop.addListener(this);
        this.heartOutlineProperties = prop;
    }

    public void setHeartShapeProperties(IHeartShapeProperties prop) {
        if(heartShapeProperties != null){
            heartShapeProperties.removeListener(this);
        }
        prop.addListener(this);
        this.heartShapeProperties = prop;
    }

    public void setHeartEffects(IHeartEffects prop) {
        if(heartEffects != null){
            heartEffects.removeListener(this);
        }
        prop.addListener(this);
        this.heartEffects = prop;
    }
    
    @Override
    public IBasicHeartProperties getBasicProperties() {
        return basicHeartProperties;
    }

    @Override
    public IHeartOutlineProperties getOutlineProperties() {
        return heartOutlineProperties;
    }

    @Override
    public IHeartShapeProperties getShapeProperties() {
        return heartShapeProperties;
    }

    @Override
    public IHeartEffects getEffects() {
        return heartEffects;
    }
    
}
