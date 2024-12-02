/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.online.voting.system;
import java.util.Scanner;
/**
 *
 * @author Acer
 */
public class OnlineVotingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Voting System!");

        while (true) {
            System.out.println("Enter '1' for Poll, '2' for Survey, or 'stop' to exit:");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("stop")) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            }

            Voting votingSystem = null;

            if (choice.equals("1")) {
                
                System.out.println("Enter the poll question:");
                String question = scanner.nextLine();
                votingSystem = new Polls(question);

                while (true) {
                    System.out.println("Enter your vote [a(Hard Agree), b(Agree), c(Disagree), d(Hard Disagree)] or type 'exit' to stop:");
                    String vote = scanner.nextLine().trim().toLowerCase();
                    if (vote.equals("exit")) {
                        break;
                    }

                    Thread voteThread = new VoteThread(votingSystem, vote);
                    voteThread.start();
                }

            } else if (choice.equals("2")) {
                System.out.println("Enter the survey question:");
                String question = scanner.nextLine();
                System.out.println("Enter number of options for the survey(Please enter an integer):");
                int numOptions = scanner.nextInt();
                scanner.nextLine(); 

                String[] options = new String[numOptions];
                System.out.println("Enter the options:");
                for (int i = 0; i < numOptions; i++) {
                    options[i] = scanner.nextLine();
                }

                votingSystem = new Surveys(question, options);

                while (true) {
                    System.out.println("Enter your vote (one of the survey options) or type 'exit' to stop:");
                    String vote = scanner.nextLine().trim();
                    if (vote.equals("exit")) {
                        break;
                    }

                    Thread voteThread = new VoteThread(votingSystem, vote);
                    voteThread.start();
                }
                System.out.println("Would you like to ask an open-ended question for suggestions or answers? (yes/no)");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("yes")) {
                    System.out.println("Enter your open-ended question:");
                    String openEndedQuestion = scanner.nextLine();
                    System.out.println("Please enter suggestions or answers (type 'exit' to stop):");

                    while (true) {
                        String suggestion = scanner.nextLine();
                        if (suggestion.equalsIgnoreCase("exit")) {
                            break;
                        }
                        
                        votingSystem.SaveToFile("Open-ended Response: " + suggestion, "openEndedResponse.txt");
                    }
                }


            } else {
                System.out.println("Invalid choice. Please enter '1' for Poll, '2' for Survey, or 'stop' to exit.");
                continue;
            }

            votingSystem.displayResults();
        }

        scanner.close();
    }
}
