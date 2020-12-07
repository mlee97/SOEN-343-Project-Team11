package smarthomesimulator.interfaces;

public interface Observable {
    public void attachObserver(Observer o);
    public void detachObserver(Observer o);
    void notifyObserver(Observable observable);
}
