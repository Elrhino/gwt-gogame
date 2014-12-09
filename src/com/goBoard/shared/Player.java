package com.goBoard.shared;

import java.io.Serializable;

public class Player implements Serializable {
    public String name;
    public String status;
    public int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.status = "Waiting";
    }

    public Player() {
        this("John Doe");
    }
}



