package smarthomesimulator.model;

import java.util.ArrayList;

public class ConsoleOutput {

    private ArrayList<String> message; // Create an ArrayList object

    public ConsoleOutput(){
        this.message = new ArrayList<String>();
    }

    public String getMessage(int i){
        return this.message.get(i);
    }

    public void setMessage(String s){
        this.message.add(s);
    }

    public int getSize(){
        return this.message.size();
    }

}
