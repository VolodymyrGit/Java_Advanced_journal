package volm.journal.model;


public class HomeWork {

    private Long id;

    private Long lesson_id;

    private Long student_id;

    private boolean done;

    private String hv_description;


    public HomeWork(Long id, Long lessonId, Long studentId, boolean done, String hvDescription) {
        this.id = id;
        this.lesson_id = lessonId;
        this.student_id = studentId;
        this.done = done;
        this.hv_description = hvDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Long lesson_id) {
        this.lesson_id = lesson_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getHv_description() {
        return hv_description;
    }

    public void setHv_description(String hv_description) {
        this.hv_description = hv_description;
    }
}
