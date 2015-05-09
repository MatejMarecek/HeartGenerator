package cz.vutbr.fit.vin.heartgenerator.ui.icon;

/**
 *
 * @author Matej Marecek
 */
public enum AppIcon {
    APP_ICON_LARGE("appIcon_512x512.png", "Icon of this application");
    
    private final String relativePath;
    private final String description;
    
    private AppIcon(String relativePath, String description){
        this.relativePath = relativePath;
        this.description = description;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public String getDescription() {
        return description;
    }
    
}
