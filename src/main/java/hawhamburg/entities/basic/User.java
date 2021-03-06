package hawhamburg.entities.basic;

import com.google.gson.JsonObject;
import hawhamburg.entities.group.Assignment;
import hawhamburg.entities.group.Group;

import java.util.Collection;
import java.util.Map;

public class User {
    private String userId;
    private Group ownerOfGroup;
    public String name;
    public String password;
    public JsonObject _links;
    public int[] deliverables_done;
    public int[] delivered;
    public String ip;
    public String location;
    public Map<String,Assignment> assignments;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setOwnerOfGroup(Group ownerOfGroup) {
        this.ownerOfGroup = ownerOfGroup;
    }

    public void setAssignments(Map<String, Assignment> assignments) {
        this.assignments = assignments;
    }

    public static User generateUser(String username, String password) {
        return new User(username, password);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public Group getOwnerOfGroup() {
        return ownerOfGroup;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", ownerOfGroup=" + ownerOfGroup +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", ip='" + ip + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
