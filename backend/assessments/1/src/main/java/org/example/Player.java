package org.example;

public class Player {
    String name;
    String team;

    public String getTeam(){
        return team;
    }

    String Role;
    int Matches;

    int Runs;
    double Average;

    int wicket;

    public Player(String name, String team,String Role,int Macthes,int Runs, double Average,int wicket){
        this.name=name;
        this.team=team;
        this.Role=Role;
        this.Matches=Macthes;
        this.Runs=Runs;
        this.Average=Average;
        this.wicket=wicket;
    }


}
