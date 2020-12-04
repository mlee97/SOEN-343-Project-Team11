package smarthomesimulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Zone {
    @JsonProperty()
    public String name;
    //Cooling = false, Heating = true
    @JsonProperty()
    public boolean setting;
    @JsonProperty()
    public double temperature;

    public Zone(String name, boolean setting, double temperature) {
        this.name = name;
        this.setting = setting;
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSetting() {
        return setting;
    }

    public void setSetting(boolean setting) {
        this.setting = setting;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

}
