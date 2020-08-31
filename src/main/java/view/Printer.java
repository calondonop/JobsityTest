package view;

import model.Player;
import model.Turn;

import java.util.*;

public class Printer{

    public final int numberFrame = 10;

    public void printHeader(){
        StringBuilder header = new StringBuilder("Frame\t\t");
        for (int i = 1; i <= numberFrame; i++) {
            header.append(i).append("\t\t");
        }
        System.out.print(header);
    }
    public void printPinfallsScore(List<String> pinfalls, Map<Integer, Turn> turns) {
        pinfalls.stream().forEach(System.out::print);
        System.out.print("\nScore\t\t");
        turns.entrySet().stream().forEach(e-> System.out.print(e.getValue().getScore()+"\t\t"));
    }

    public void printNamePlayerAndPinfalls(String namePlayer) {
        System.out.println("\n" + namePlayer);
        System.out.print("Pinfalls\t");
    }
}
