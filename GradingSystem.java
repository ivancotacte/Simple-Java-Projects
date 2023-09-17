import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private List<Integer> scores;

    public Student(String name) {
        this.name = name;
        this.scores = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addScore(int score) {
        scores.add(score);
    }

    public double getAverageScore() {
        if (scores.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    }
}

class GradingSystem {
    private List<Student> students;
    private Scanner scanner;

    public GradingSystem() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void inputScores() {
        for (Student student : students) {
            System.out.print("Enter scores for " + student.getName() + " (comma-separated): ");
            String[] scoreTokens = scanner.nextLine().split(",");
            for (String scoreToken : scoreTokens) {
                int score = Integer.parseInt(scoreToken.trim());
                student.addScore(score);
            }
        }
    }

    public void calculateGrades() {
        for (Student student : students) {
            double averageScore = student.getAverageScore();
            char grade = calculateGrade(averageScore);
            System.out.println(student.getName() + " - Average Score: " + averageScore + " - Grade: " + grade);
        }
    }

    private char calculateGrade(double averageScore) {
        if (averageScore >= 90) {
            return 'A';
        } else if (averageScore >= 80) {
            return 'B';
        } else if (averageScore >= 70) {
            return 'C';
        } else if (averageScore >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static void main(String[] args) {
        GradingSystem gradingSystem = new GradingSystem();
        gradingSystem.addStudent(new Student("John"));
        gradingSystem.addStudent(new Student("Alice"));
        gradingSystem.addStudent(new Student("Bob"));

        gradingSystem.inputScores();
        gradingSystem.calculateGrades();
    }
}

