package com.mad1.tictactoepm;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int wins;
    private int draws;
    private int losses;
    private int id;

    public Player(String name, int wins, int id, int draws, int losses) {
        this.name = name;
        this.wins = wins;
        this.id = id;
        this.draws = draws;
        this.losses = losses;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
}
