/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.online.voting.system;

public abstract class SurveySystem {
    private int[] votes; 
    private String[] options;

    public Surveys(String question, String[] options) {
        super(question);
        this.options = options;
        this.votes = new int[options.length];
    }

    @Override
    public synchronized void castVotes(String option) {
        boolean validOption = false;
        for (int i = 0; i < options.length; i++) {
            if (options[i].equalsIgnoreCase(option)) {
                votes[i]++;
                validOption = true;
                break;
            }
        }
        if (validOption) {
            totalvotes++;
            SaveToFile("Survey Vote:" + option, "surveysresults.txt");
        } else {
            System.out.println("Please enter a valid option.");
        }
    }

    @Override
    public synchronized void displayResults() {
        System.out.println("Survey results:");
        for (int i = 0; i < options.length; i++) {
            System.out.println("Votes in option " + options[i] + ": " + votes[i]);
        }
        System.out.println("Total Votes: " + totalvotes);
    }
    
}

