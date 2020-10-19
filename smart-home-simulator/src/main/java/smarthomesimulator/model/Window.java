package smarthomesimulator.model;

public class Window {
    private boolean isOpen;
    private boolean isBlocked;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Window() {
        this.isOpen = false;
        this.isBlocked = false;
    }

    public void block() {
        isBlocked = true;
    }

    public void unblock() {
        isBlocked = true;
    }

    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = true;
    }
}
