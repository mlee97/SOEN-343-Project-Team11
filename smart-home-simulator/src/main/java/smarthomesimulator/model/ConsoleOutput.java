package smarthomesimulator.model;

public class ConsoleOutput {

    private String message;

    public ConsoleOutput(){
        this.message = "";
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String s){
        this.message = s;
    }
}
