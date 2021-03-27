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

    private String password;


    public Usr(long id, String u_name, String email, String phone_number, Long group_id, Role role, String password) {
        this.id = id;
        this.u_name = u_name;
        this.email = email;
        this.phone_number = phone_number;
        this.group_id = group_id;
        this.role = role;
        this.password = password;
    }

    public Usr(String u_name, String email, String phone_number, Long group_id, Role role, String password) {
        this.u_name = u_name;
        this.email = email;
        this.phone_number = phone_number;
        this.group_id = group_id;
        this.role = role;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
