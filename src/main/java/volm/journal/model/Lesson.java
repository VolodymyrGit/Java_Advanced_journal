package volm.journal.model;

import lombok.Data;

import java.util.List;

@Data
public class Lesson {

    private long id;

    private long teacherId;

    private List<Student> students;
}
