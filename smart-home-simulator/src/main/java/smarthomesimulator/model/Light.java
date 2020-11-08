package smarthomesimulator.model;

public class Light {

    private boolean isOn;
   

    public Light() {
    	this.isOn = false;
    }
    
    public boolean OnOff() {
        return isOn;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

  
}
