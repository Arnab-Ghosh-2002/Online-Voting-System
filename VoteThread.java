/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.online.voting.system;

/**
 *
 * @author Acer
 */
public class VoteThread extends Thread{
    private Voting votingSystem;
    private String voteOption;

    public VoteThread(Voting votingSystem, String voteOption) {
        this.votingSystem = votingSystem;
        this.voteOption = voteOption;
    }

    @Override
    public void run() {
        votingSystem.castVotes(voteOption);
    }
}
