package smarthomesimulator.model;

public class Profile {

public enum Role {
    CHILD(0, "Child"),
    GUEST(0, "Guest"),
    PARENT(1, "Parent"),
    STRANGER(0, "Stranger");

    private int accessibility;
    private String key;

    Role(int accessibility, String key) {
        this.accessibility = accessibility;
        this.key = key;
    }

    public int getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(int accessibility) {
        this.accessibility = accessibility;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

    private String name;
    private Role role;
    private String location;

    public Profile() {
        this.role = this.getRole();
        this.location = this.getLocation();
        this.name = this.getName();
    }

    public Profile(String name, Role role, String location) {
        this.name = name;
        this.role = role;
        this.location = location;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

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
