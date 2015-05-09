package cz.vutbr.fit.vin.heartgenerator.properties;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 *
 * @author Matej Marecek
 */
public class AppProp implements IAppProp {
    private final List<InvalidationListener> invalidationListeners = new ArrayList<>();

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListeners.remove(listener);
    }

    @Override
    public void invalidated(Observable observable) {
        invalidationListeners
                .forEach((InvalidationListener listener) -> listener.invalidated(observable));
    }
}
