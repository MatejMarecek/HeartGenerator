package cz.vutbr.fit.vin.heartgenerator.renderer;

/**
 * Some heart equations in parametric form.
 * http://hubpages.com/hub/Equations-to-Draw-Hearts-on-a-Graphing-Calculator
 * @author Matej Marecek
 */
public enum HeartEquationsEnum {
    SHAPE1("Shape - 1", "2*PI",
            "4*pow(sin(t), 3)",
            "3*cos(t)-1.3*cos(2*t)-0.6*cos(3*t)-0.2*cos(4*t)"),
    SHAPE2("Shape - 2", "8*PI",
            "3.5*sin(t)-sin(3*t)+0.7*sin(29.25*t)",
            "4*cos(t)-1.5*cos(2*t)-0.6*cos(3*t)-0.2*cos(4*t)"),
    SHAPE3("Shape - 3", "8*PI",
            "3.5*sin(t)-sin(3*t)",
            "4*cos(t)-1.5*cos(2*t)-0.6*cos(3*t)-0.2*cos(4*t)+0.7*cos(29.25*t)"),
    SHAPE4("Shape - 4", "8*PI",
            "4.5*sin(t)-sin(3*t)+0.8*sin(15.25*t)",
            "4*cos(t)-1.5*cos(2*t)-0.6*cos(3*t)-0.3*cos(4*t)+0.8*cos(15.25*t)"),
    SHAPE5("Shape - 5", "4*PI",
            "4*sin(t)-sin(3*t)-0.2*cos(10.5*t)",
            "4*cos(t)-2*cos(2*t)-cos(3*t)+0.15*sin(8*t)"),
    SHAPE6("Shape - 6", "2*PI",
            "5*sin(t)-sin(3*t)",
            "5*cos(t)-1.5*cos(2*t)-cos(3*t)"),
    SHAPE7("Shape - 7", "32*PI",
            "2.5*sin(t)-0.8*sin(3*t)+0.5*cos((36*t)/7)",
            "2.7*cos(t)-cos(2*t)-0.5*cos(3*t)+0.5*sin((36*t)/7)"),
    SHAPE8("Shape - 8", "2*PI",
            "3*sin(sin(t))-sin(3*sin(t))",
            "2*cos(t)-cos(3*sin(t))"),
    SHAPE9("Shape - 9", "2*PI",
            "3*sin(t)-sin(3*t)+0.5*cos(t)",
            "3.3*cos(t)-1.5*cos(2*t)-cos(3*t)");
    
    private final String name;
    private final String paramSize;
    private final String eqX;
    private final String eqY;
    
    HeartEquationsEnum(String name, String paramSize, String eqX, String eqY){
        this.name = name;
        this.paramSize = paramSize;
        this.eqX = eqX;
        this.eqY = eqY;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getEqX() {
        return eqX;
    }

    public String getEqY() {
        return eqY;
    }

    public String getParamSize() {
        return paramSize;
    }
}
