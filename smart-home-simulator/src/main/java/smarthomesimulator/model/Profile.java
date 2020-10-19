package smarthomesimulator.model;

enum Role {
    PARENT,
    CHILD,
    GUEST,
    STRANGER
}

public class Profile {

    private Role role;
    private String location;

    public Profile(Role role) {
        this.role = this.getRole();
        this.location = "";
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
