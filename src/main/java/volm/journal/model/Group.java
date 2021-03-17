package volm.journal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import volm.journal.annotation.IgnoreColumn;

import java.util.List;

@Data
//@AllArgsConstructor
public class Group {

    private long id;

    private long teacherId;

    @IgnoreColumn
    private List<Student> students;

    private String info;

    public Group(long id, long teacherId, List<Student> students, String info) {
        this.id = id;
        this.teacherId = teacherId;
        this.students = students;
        this.info = info;
    }
}
