import java.util.*;

class Student {
    private String studentId;
    private String name;
    private int age;
    private String className;
    private Map<String, Integer> subjects; // Subject name and marks

    public Student(String studentId, String name, int age, String className) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.className = className;
        this.subjects = new HashMap<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getClassName() {
        return className;
    }

    public void addSubject(String subjectName, int marks) {
        subjects.put(subjectName, marks);
    }

    public Map<String, Integer> getSubjects() {
        return subjects;
    }
}

class Teacher {
    private String teacherId;
    private String name;
    private List<String> classesTeaching;

    public Teacher(String teacherId, String name) {
        this.teacherId = teacherId;
        this.name = name;
        this.classesTeaching = new ArrayList<>();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getName() {
        return name;
    }

    public List<String> getClassesTeaching() {
        return classesTeaching;
    }

    public void addClassTeaching(String className) {
        classesTeaching.add(className);
    }
}

class School {
    private Map<String, Student> students; // Student ID and Student object
    private Map<String, Teacher> teachers; // Teacher ID and Teacher object

    public School() {
        students = new HashMap<>();
        teachers = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.put(teacher.getTeacherId(), teacher);
    }

    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    public Teacher getTeacher(String teacherId) {
        return teachers.get(teacherId);
    }
}

public class SchoolManagementSystem {
    public static void main(String[] args) {
        School school = new School();

        Student student1 = new Student("S101", "John Doe", 16, "Class 10");
        student1.addSubject("Math", 90);
        student1.addSubject("Science", 85);
        student1.addSubject("History", 78);
        school.addStudent(student1);

        Teacher teacher1 = new Teacher("T201", "Mr. Smith");
        teacher1.addClassTeaching("Class 10");
        school.addTeacher(teacher1);

        // You can add more students and teachers and perform various operations here.
    }
}
