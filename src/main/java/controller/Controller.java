package controller;

import exception.PinfallException;
import model.Game;
import model.Player;
import model.Turn;
import view.Printer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    private static Printer printer = new Printer();

    public void run(){
        Game game = Game.getInstance();
        readFileSaveInfo(game);
        printer.printPinfallsScore(game.getPlayers());
    }

    public void readFileSaveInfo(Game game){
        String line;
        File file;
        FileReader reader = null;
        String firstPlayer = "";
        boolean hasFirstPlayer = false;
        boolean changePlayerFromFirst = false;
        boolean existOtherPlayer = false;
        int frame = 0;
        int flagSinglePlayer = 0;

        try{
            //Ask for the input file directory and the file name
            BufferedReader bufferDir = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the path of the input file (sample: D:\\proof.txt):");
            String fileInput = "D:\\bowlingGame2.txt"; //bufferDir.readLine();

            //input file, the reader file and the buffer which save the lines
            file = new File(fileInput);
            reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);

            Map<String, Player> players = new HashMap<>();
            Map<Integer, Turn> turns;
            Player player;

            //If there are more lines to analize the system continues
            while ((line=buffer.readLine())!= null){
                String[] namePinfall = line.split("\t");
                String name = namePinfall[0];
                String pinfall = namePinfall[1];

                //Save the first name player
                if(!hasFirstPlayer){
                    firstPlayer = name;
                    hasFirstPlayer = true;
                }
                //if pinfall is equal to F, it's changed to the 0 equivalent value
                if(pinfall.equals("F")){
                    pinfall = "0";
                }

                Integer pinfallNumber = Integer.parseInt(pinfall);

                if (pinfallNumber > 10){
                    throw new PinfallException("The number of pinfalls in a throw cannot be greater than 10");
                }else if(pinfallNumber < 0){
                    throw new PinfallException("The number of pinfalls in a throw cannot be less than 0");
                }

                //Define if we changed the frame
                if (!firstPlayer.equals(name)){
                    //frame += 1; //ojo
                    existOtherPlayer = true;
                    if(!changePlayerFromFirst){
                        changePlayerFromFirst = true;
                    }
                }else if(firstPlayer.equals(name) && changePlayerFromFirst){
                    changePlayerFromFirst = false;
                    frame += 1;
                }else if(!existOtherPlayer) {//if(firstPlayer.equals(name) && !changePlayerFromFirst){
                    if (frame != 10) {
                        if (pinfall.equals("10")) {
                            frame += 1;
                        } else if (flagSinglePlayer == 2) {
                            flagSinglePlayer = 1;
                            frame += 1;
                        } else if (!pinfall.equals("10") && flagSinglePlayer < 2) {
                            flagSinglePlayer += 1;
                            if (frame == 0)
                                frame += 1;
                        }
                    }
                }

                //Validates if a player already exist in the Map of players from the Game class
                if (players.containsKey(name)){
                    player = players.get(name);
                    turns = player.getTurns();
                    //Validates if a frame already exist for a player
                    if (turns.containsKey(frame)){
                        Turn turn = turns.get(frame);
                        if(turn.getPinfall1() == null){
                            turn.setPinfall1(pinfallNumber);
                        }else if(turn.getPinfall2() == null){
                            turn.setPinfall2(pinfallNumber);
                        }else if(turn.getPinfall3() == null){
                            turn.setPinfall3(pinfallNumber);
                        }
                        turns.put(frame, turn);
                    }else{
                        turns.put(frame, new Turn(Integer.parseInt(pinfall)));
                    }
                }else{
                    //numberPlayers =
                    player = new Player(name, frame, new Turn(Integer.parseInt(pinfall)));
                    turns = player.getTurns();//ojo al parecer no se necesita
                    players.put(name, player);
                }
                player.setTurns(turns);
                game.setPlayers(players);
            }
            players.entrySet().stream().forEach(e-> System.out.println(e.getValue().getTurns()));//ojo
        }catch(NumberFormatException nfe){//Se tiene esta excepción para validar cuando el contenido del archivo no sea numérico
            System.out.println("Revise el archivo, se encuentran datos que no son numéricos.");
        }catch(Exception e){//Se hace el manejo de cualquier excepción genérica que se pueda dar y el trace para su revisión
            e.printStackTrace();
        }finally {
            //Handle exceptions to close input file
            try{
                if (reader != null){
                    reader.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}