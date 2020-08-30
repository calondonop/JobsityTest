package view;

import model.Player;
import model.Turn;

import java.util.*;

public class Printer{

    public final int numberFrame = 10;

    public void printHeader(){
        String header = "Frame       ";
        for (int i = 1; i <= numberFrame; i++) {
            header = header + i + "         ";
        }
        System.out.print(header);
    }

    public void printPinfallsScore(Map<String, Player> players){
        printHeader();
        List<String> pinfalls = new ArrayList<>();
        Map<Integer, Turn> turns =new HashMap<>();
        String strike = "X    ";
        String spare = "/    ";
        String tab = "    ";
        for (String keyPlayer : players.keySet()) {
            turns = players.get(keyPlayer).getTurns();
            Iterator tn = turns.keySet().iterator();
            System.out.println("\n" + keyPlayer);
            System.out.print("Pinfalls    ");
            pinfalls.clear();
            while (tn.hasNext()) {
                Integer keyTurn = (Integer) tn.next();
                Turn turn = turns.get(keyTurn);
                Integer pinfall1 = turn.getPinfall1();
                Integer pinfall2 = turn.getPinfall2();
                Integer pinfall3 = turn.getPinfall3();
                if (keyTurn != 10) {
                    if (pinfall1 == 10) {
                        pinfalls.add("     ");
                        pinfalls.add(strike);
                    } else if (pinfall1 + pinfall2 == 10) {
                        pinfalls.add(pinfall1.toString() + tab);
                        pinfalls.add(spare);
                    } else {
                        pinfalls.add(pinfall1 + tab);
                        pinfalls.add(pinfall2 + tab);
                    }
                } else {
                    if (pinfall1 == 10) {
                        pinfalls.add(strike);
                        if (pinfall2 == 10)
                            pinfalls.add(strike);
                        else
                            pinfalls.add(pinfall2.toString() + tab);

                        if (pinfall3 == 10)
                            pinfalls.add(strike);
                        else
                            pinfalls.add(pinfall3.toString() + tab);

                    } else if (pinfall1 + pinfall2 == 10) {
                        pinfalls.add(pinfall1.toString() + tab);
                        pinfalls.add(spare);
                        if (pinfall3 == 10)
                            pinfalls.add(strike);
                        else
                            pinfalls.add(pinfall3.toString() + tab);
                    } else if(pinfall1 + pinfall2 < 10){
                        pinfalls.add(pinfall1.toString() + tab);
                        pinfalls.add(pinfall2.toString() + tab);
                    }
                }
                turn.calculateScores(turns);
            }
            pinfalls.stream().forEach(System.out::print);
            System.out.print("\nScore       ");
            turns.entrySet().stream().forEach(e-> System.out.print(e.getValue().getScore()+"        "));
        }
    }
}
