package smarthomesimulator.model;

public class Light {

    private boolean isOn;
   

    public Light() {
    	this.isOn = false;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }


    public boolean isOn() {
        return isOn;
    }
}
