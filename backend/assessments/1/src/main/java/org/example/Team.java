package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team {
//    List<Player> Members = new ArrayList<>();
    String teamname;
    public void setTeamname(String teamname){
        this.teamname=teamname;
    }
    public String getTeamname(){
        return teamname;
    }

    List<Player> playerList = new ArrayList<>();

   public void  setplayerlist (List<Player> P1){
        this.playerList=P1;

    }
    public List<Player> getPlayerList(){
       return this.playerList;
    }

}
