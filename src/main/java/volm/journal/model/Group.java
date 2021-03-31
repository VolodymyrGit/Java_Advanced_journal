package volm.journal.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Group {

    @Id
    private Long id;

    private Long teacher_id;

    private String info;


    public Group() {
    }

    public Group(Long id, Long teacherId, String info) {
        this.id = id;
        this.teacher_id = teacherId;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
