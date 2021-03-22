package volm.journal.model;


import java.util.Date;

public class Lesson {

    private long id;

    private Long group_id;

    private Date create_date;

    public Lesson(long id, Long group_id, Date create_date) {
        this.id = id;
        this.group_id = group_id;
        this.create_date = create_date;
    }

    public Lesson(Long group_id, Date create_date) {
        this.group_id = group_id;
        this.create_date = create_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}