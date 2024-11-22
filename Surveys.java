/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.online.voting.system;

import java.util.Scanner;

public abstract class SurveySystem {
    
    private String surveyTitle;
    protected Question[] questions;
    private int totalResponses;
    private int questionCount;

    // Constructor to initialize the survey with a title
    public SurveySystem(String surveyTitle, int maxQuestions) {
        this.surveyTitle = surveyTitle;
        this.questions = new Question[maxQuestions]; // Fixed-size array for questions
        this.totalResponses = 0;
        this.questionCount = 0;
    }

    // Inner class to represent a survey question (either multiple-choice or open-ended)
    public class Question {
        private String questionText;
        private String[] options;  // Multiple choice options (can be null for open-ended)
        private String[] responses;  // User responses (either choice or text)
        private int responseCount;

        // Constructor for multiple-choice question
        public Question(String questionText, String[] options) {
            this.questionText = questionText;
            this.options = options;
            this.responses = new String[10]; // Assuming a maximum of 10 responses per question
            this.responseCount = 0;
        }

        // Constructor for open-ended question
        public Question(String questionText) {
            this.questionText = questionText;
            this.options = null; // No options for open-ended
            this.responses = new String[10]; // Assuming a maximum of 10 responses per question
            this.responseCount = 0;
        }
        
        // Method to add a response (either choice or text)
        public void addResponse(String response) {
            if (responseCount < responses.length) {
                responses[responseCount++] = response;
            } else {
                System.out.println("Maximum responses reached for this question.");
            }
        }

        // Getters
        public String getQuestionText() {
            return questionText;
        }

        public String[] getOptions() {
            return options;
        }

        public String[] getResponses() {
            return responses;
        }

        public int getResponseCount() {
            return responseCount;
        }
    }

    // Add a multiple-choice question to the survey
    public void addMultipleChoiceQuestion(String questionText, String[] options) {
        if (questionCount < questions.length) {
            questions[questionCount++] = new Question(questionText, options);
        } else {
            System.out.println("Cannot add more questions. Survey is full.");
        }
    }

    // Add an open-ended question to the survey
    public void addOpenEndedQuestion(String questionText) {
        if (questionCount < questions.length) {
            questions[questionCount++] = new Question(questionText);
        } else {
            System.out.println("Cannot add more questions. Survey is full.");
        }
    }

    // Method for answering a multiple-choice question
    public void answerMultipleChoice(int questionIndex, String selectedOption) {
        if (questionIndex < 0 || questionIndex >= questionCount) {
            System.out.println("Invalid question index.");
            return;
        }

        Question question = questions[questionIndex];
        if (question.getOptions() != null) {
            boolean validOption = false;
            for (String option : question.getOptions()) {
                if (option.equalsIgnoreCase(selectedOption)) {
                    validOption = true;
                    break;
                }
            }
            if (validOption) {
                question.addResponse(selectedOption);
                totalResponses++;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    // Method for answering an open-ended question
    public void answerOpenEnded(int questionIndex, String response) {
        if (questionIndex < 0 || questionIndex >= questionCount) {
            System.out.println("Invalid question index.");
            return;
        }

        Question question = questions[questionIndex];
        if (question.getOptions() == null) { // Open-ended question
            question.addResponse(response);
            totalResponses++;
        } else {
            System.out.println("This is a multiple-choice question, please choose an option.");
        }
    }

    // Abstract method that subclasses must implement
    public abstract void casting();

    // Display the survey results
    public void displayResults() {
        System.out.println("Survey Results for: " + surveyTitle);
        System.out.println("Total Responses: " + totalResponses);
        
        for (int i = 0; i < questionCount; i++) {
            Question question = questions[i];
            System.out.println("\nQuestion: " + question.getQuestionText());
            if (question.getOptions() != null) {
                // Display multiple-choice results
                for (String option : question.getOptions()) {
                    int count = 0;
                    for (int j = 0; j < question.getResponseCount(); j++) {
                        if (question.getResponses()[j].equalsIgnoreCase(option)) {
                            count++;
                        }
                    }
                    System.out.println(option + ": " + count + " responses");
                }
            } else {
                // Display open-ended responses
                System.out.println("Open-ended responses: ");
                for (int j = 0; j < question.getResponseCount(); j++) {
                    System.out.println("- " + question.getResponses()[j]);
                }
            }
        }
    }
}

class SurveyApp extends SurveySystem {

    // Constructor for SurveyApp, passing title and max questions to parent class
    public SurveyApp(String surveyTitle, int maxQuestions) {
        super(surveyTitle, maxQuestions);
    }

    // Implementing the abstract casting() method
    @Override
    public void casting() {
        Scanner scanner = new Scanner(System.in);

        // Loop through each question in the survey
        for (int i = 0; i < super.questions.length; i++) {
            SurveySystem.Question question = super.questions[i];
            if (question == null) break; // Stop if there are no more questions

            System.out.println("\n" + question.getQuestionText());

            if (question.getOptions() != null) {
                // Show options for multiple-choice questions
                for (int j = 0; j < question.getOptions().length; j++) {
                    System.out.println((j + 1) + ". " + question.getOptions()[j]);
                }

                // Get user input for the answer
                System.out.print("Select an option (1-" + question.getOptions().length + "): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (choice >= 1 && choice <= question.getOptions().length) {
                    super.answerMultipleChoice(i, question.getOptions()[choice - 1]);
                } else {
                    System.out.println("Invalid option, please try again.");
                    int j = 0;
                    j--;
                }
            } else {
                // For open-ended questions, get user input
                System.out.print("Your response: ");
                String response = scanner.nextLine();
                super.answerOpenEnded(i, response);
            }
        }
        scanner.close();
    }
}

