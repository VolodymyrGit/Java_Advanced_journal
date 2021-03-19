package volm.journal.model;

import volm.journal.enums.Role;

// Usr is an abbreviation of User
public class Usr {

    private long id;

    private String u_name;

    private String email;

    private String phone_number;

    private Long group_id;

    private Role role;

    public Usr(long id, String name, String email, String phoneNumber, Long groupId, Role role) {
        this.id = id;
        this.u_name = name;
        this.email = email;
        this.phone_number = phoneNumber;
        this.group_id = groupId;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
