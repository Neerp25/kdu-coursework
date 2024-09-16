package org.example;

import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static List<Player> loadPlayer() {
        List<Player> players = new ArrayList<>();
        try {
            List<CSVRecord> csvRecords = CSVLoader.loadCSVFromResources("IPL_2021-data.csv");
            for (CSVRecord csvRecord : csvRecords) {

                String name = csvRecord.get("Name");
                String Team = csvRecord.get("Team");
                String Role = csvRecord.get("Role");
                int Matches = Integer.parseInt(csvRecord.get("Matches"));
                int Runs = Integer.parseInt(csvRecord.get("Runs"));
                double Average = Double.parseDouble(csvRecord.get("Average"));
                int wickets = Integer.parseInt(csvRecord.get("Wickets"));

                Player playernew = new Player(name,Team,Role, Matches,Runs, Average,wickets);
                players.add(playernew);
            }
        } catch (Exception e) {
            System.out.println("unable to load palyers");
        }
        return players;
    }

    public static void getblowertakes40(String Teamname, Map<String, List<Player>> team){


        List<Player> temp= null;

        if(team.containsKey(Teamname)){
            temp = team.get(Teamname);
        }

        System.out.println("Blower with more40:");



        for (Player p1: temp) {
            if(p1.wicket>40){
                System.out.println(p1.name);
            }

        }



    }

    public static void highestwicket(String Teamname, Map<String, List<Player>> team){


        List<Player> temp= null;

        if(team.containsKey(Teamname)){
            temp = team.get(Teamname);
        }

        System.out.println("higest wickettaker:");
        int high =0;
        String highest ="";



        for (Player p1: temp) {
            if(high<p1.wicket){
                high = p1.wicket;
                highest= p1.name;
            }

        }

        System.out.println("Highest Wicket taker:");
        System.out.println(highest);



    }

    public static void highestruns(String Teamname, Map<String, List<Player>> team){


        List<Player> temp= null;

        if(team.containsKey(Teamname)){
            temp = team.get(Teamname);
        }

        int high =0;
        String highest ="";



        for (Player p1: temp) {
            if(high<p1.Runs){
                high = p1.Runs;
                highest= p1.name;
            }

        }

        System.out.println("Highest Runner:");
        System.out.println(highest);



    }



    public static void main(String[] args) {

        List<Player> allplayer = loadPlayer();

        // creating hasmap to store team and player

        Map<String, List<Player>> team = new HashMap<>();
        for (Player playerone : allplayer) {
            String teamname = playerone.getTeam();
            if (team.containsKey(teamname)) {
                team.get(teamname).add(playerone);
            } else {
                List<Player> list = new ArrayList<>();
                list.add(playerone);
                team.put(teamname, list);
            }
        }

        getblowertakes40("CSK",team);
        highestwicket("CSK",team);
        highestruns("CSK",team);




    }
}
