package smarthomesimulator.interfaces;

public interface Observable {

    public void attachObserver(Observer o);
    public void detachObserver(Observer o);
    public void notifyObserver(Observable observable);
}
