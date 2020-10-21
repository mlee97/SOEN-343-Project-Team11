package smarthomesimulator.model;

enum Role {
    PARENT,
    CHILD,
    GUEST,
    STRANGER
}

public class Profile {

    private String name;
    private String role;
    private String location;

    public Profile() {
        this.role = this.getRole();
        this.location = this.getLocation();
        this.name = this.getName();
    }
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
