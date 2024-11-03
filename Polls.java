/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.online.voting.system;

/**
 *
 * @author Acer
 */
public class Polls extends Voting {
    private int a_votes = 0;
    private int b_votes = 0;
    private int c_votes = 0;
    private int d_votes = 0;
    
    public Polls(String question){
        super(question);
    }
    @Override
    public synchronized void castVote(String option){
        if(option.equalsIgnoreCase("a")){
            a_votes ++;
        }
        else if(option.equalsIgnoreCase("b")){
            b_votes ++;
        }
        else if(option.equalsIgnoreCase("c")){
            c_votes ++;
        }
        else if(option.equalsIgnoreCase("d")){
            d_votes++;
        }
        else{
            System.out.println("Please enter a valid option");
        }
        totalvotes ++;
        SaveToFile("Poll Vote:" + option + "pollresults.txt");   
}
    @Override
    public synchronized void displayResults(){
        System.out.println("Poll results:");
        System.out.println("Votes in option a:" +a_votes);
        System.out.println("Votes in option b:" +b_votes);
        System.out.println("Votes in option c:" +c_votes);
        System.out.println("Votes in option d:" +d_votes);
        System.out.println("Total Votes:" +totalvotes);
}
}