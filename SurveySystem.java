import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Survey {
    private String title;
    private List<String> questions;

    public Survey(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(String question) {
        questions.add(question);
    }

    public List<String> getQuestions() {
        return questions;
    }
}

class SurveyResponse {
    private String username;
    private String surveyTitle;
    private List<String> answers;

    public SurveyResponse(String username, String surveyTitle) {
        this.username = username;
        this.surveyTitle = surveyTitle;
        this.answers = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void addAnswer(String answer) {
        answers.add(answer);
    }

    public List<String> getAnswers() {
        return answers;
    }
}

class SurveySystem {
    private List<User> users;
    private List<Survey> surveys;
    private List<SurveyResponse> responses;
    private Scanner scanner;

    public SurveySystem() {
        users = new ArrayList<>();
        surveys = new ArrayList<>();
        responses = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void createSurvey(User user) {
        System.out.print("Enter survey title: ");
        String title = scanner.nextLine();

        Survey survey = new Survey(title);
        surveys.add(survey);

        boolean addQuestion = true;
        while (addQuestion) {
            System.out.print("Enter survey question (or 'done' to finish): ");
            String question = scanner.nextLine();

            if (question.equalsIgnoreCase("done")) {
                addQuestion = false;
            } else {
                survey.addQuestion(question);
            }
        }

        System.out.println("Survey created successfully.");
    }

    public void takeSurvey(User user) {
        System.out.print("Enter the survey title you want to take: ");
        String surveyTitle = scanner.nextLine();

        Survey survey = findSurvey(surveyTitle);
        if (survey == null) {
            System.out.println("Survey not found.");
            return;
        }

        SurveyResponse response = new SurveyResponse(user.getUsername(), surveyTitle);

        System.out.println("Survey Questions:");
        List<String> questions = survey.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            System.out.print("Q" + (i + 1) + ": " + questions.get(i) + " - Your Answer: ");
            String answer = scanner.nextLine();
            response.addAnswer(answer);
        }

        responses.add(response);
        System.out.println("Survey response recorded.");
    }

    public void viewSurveyResults(User user) {
        System.out.print("Enter the survey title you want to view results for: ");
        String surveyTitle = scanner.nextLine();

        Survey survey = findSurvey(surveyTitle);
        if (survey == null) {
            System.out.println("Survey not found.");
            return;
        }

        List<SurveyResponse> surveyResponses = findResponsesForSurvey(surveyTitle);
        if (surveyResponses.isEmpty()) {
            System.out.println("No responses recorded for this survey yet.");
            return;
        }

        System.out.println("Survey Title: " + surveyTitle);
        List<String> questions = survey.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Q" + (i + 1) + ": " + questions.get(i));
        }

        System.out.println("\nSurvey Responses:");
        for (SurveyResponse response : surveyResponses) {
            System.out.println("User: " + response.getUsername());
            List<String> answers = response.getAnswers();
            for (int i = 0; i < answers.size(); i++) {
                System.out.println("Q" + (i + 1) + " Answer: " + answers.get(i));
            }
            System.out.println("---------------");
        }
    }

    private Survey findSurvey(String surveyTitle) {
        for (Survey survey : surveys) {
            if (survey.getTitle().equalsIgnoreCase(surveyTitle)) {
                return survey;
            }
        }
        return null;
    }

    private List<SurveyResponse> findResponsesForSurvey(String surveyTitle) {
        List<SurveyResponse> surveyResponses = new ArrayList<>();
        for (SurveyResponse response : responses) {
            if (response.getSurveyTitle().equalsIgnoreCase(surveyTitle)) {
                surveyResponses.add(response);
            }
        }
        return surveyResponses;
    }

    public static void main(String[] args) {
        SurveySystem surveySystem = new SurveySystem();
        surveySystem.addUser(new User("user1", "password1"));
        surveySystem.addUser(new User("user2", "password2"));

        while (true) {
            System.out.println("\nOnline Survey System Menu:");
            System.out.println("1. Create Survey");
            System.out.println("2. Take Survey");
            System.out.println("3. View Survey Results");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = surveySystem.scanner.nextInt();
            surveySystem.scanner.nextLine(); // Consume the newline character

            if (choice == 4) {
                System.out.println("Exiting Online Survey System.");
                surveySystem.scanner.close();
                System.exit(0);
            }

            System.out.print("Enter your username: ");
            String username = surveySystem.scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = surveySystem.scanner.nextLine();

            User user = surveySystem.getUser(username);

            if (user == null || !user.getPassword().equals(password)) {
                System.out.println("Invalid username or password.");
            } else {
                switch (choice) {
                    case 1:
                        surveySystem.createSurvey(user);
                        break;
                    case 2:
                        surveySystem.takeSurvey(user);
                        break;
                    case 3:
                        surveySystem.viewSurveyResults(user);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }
}
