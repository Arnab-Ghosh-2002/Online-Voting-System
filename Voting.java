/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.online.voting.system;

/**
 *
 * @author Acer
 */
import java.io.FileWriter;
import java.io.IOException;

abstract class Voting {
    protected String question;
    protected int totalvotes = 0;
    
    public Voting(String question){
        this.question = question;
    }
    
    public abstract castVotes(String response);
    public abstract displayResults();
    
    protected void SaveToFile(String data, String filename){
        synchronized(this){
            try(FileWriter writer = new FileWriter(filename,true)){
                writer.write(data + "\n");
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
}
}
