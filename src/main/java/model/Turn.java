package model;

import exception.PinfallsFrameException;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class Turn {
    private Integer pinfall1;
    private Integer pinfall2;
    private Integer pinfall3;
    private Integer score;

    public Turn(){}

    public Turn(int pinfall1){
        this.pinfall1 = pinfall1;
    }

    public Turn(Integer pinfall1, Integer pinfall2, Integer pinfall3) {
        this.pinfall1 = pinfall1;
        this.pinfall2 = pinfall2;
        this.pinfall3 = pinfall3;
    }

    public Turn(Integer pinfall1, Integer pinfall2, Integer pinfall3, Integer score) {
        this.pinfall1 = pinfall1;
        this.pinfall2 = pinfall2;
        this.pinfall3 = pinfall3;
        this.score = score;
    }

    public Integer getPinfall1() {
        return pinfall1;
    }

    public void setPinfall1(Integer pinfall1) {
        this.pinfall1 = pinfall1;
    }

    public Integer getPinfall2() {
        return pinfall2;
    }

    public void setPinfall2(Integer pinfall2) {
        this.pinfall2 = pinfall2;
    }

    public Integer getPinfall3() {
        return pinfall3;
    }

    public void setPinfall3(Integer pinfall3) {
        this.pinfall3 = pinfall3;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer orElseOptional(Integer pinfall){
        Optional<Integer> optional = Optional.ofNullable(pinfall);
        return optional.orElse(0);
    }

    public void validateFramePinfalls(int frame){
        if(frame != 10){
            if (this.getPinfall1() + orElseOptional(this.getPinfall2()) > 10){
                throw new PinfallsFrameException("In a frame with two throws, " +
                        "both throws cannot sum more than 10 pinfalls");
            }
        }
    }

    public void calculateScores(Map<Integer, Turn> turns){
        int score = 0;

        Iterator tn = turns.keySet().iterator();
        while(tn.hasNext()){
            Integer keyTurn = (Integer) tn.next();
            Turn turn = turns.get(keyTurn);
            Integer pinfall1 = orElseOptional(turn.getPinfall1());
            Integer pinfall2 = orElseOptional(turn.getPinfall2());
            Integer pinfall3 = orElseOptional(turn.getPinfall3());

            if(keyTurn != 10){
                int scoreFrame = pinfall1 + pinfall2;
                int flagBall = 0;
                //score += scoreFrame;
                if(scoreFrame < 10){
                    score += scoreFrame;
                }else if(pinfall1 == 10){
                    Integer nextPinfall1 = turns.get(keyTurn+1).getPinfall1();
                    Integer nextPinfall2 = turns.get(keyTurn+1).getPinfall2();
                    Integer nextPinfall3 = turns.get(keyTurn+1).getPinfall3();
                    Integer nextPinfall4 = null;
                    if(keyTurn < 9){
                        nextPinfall4 = turns.get(keyTurn+2).getPinfall1();
                    }
                    if(nextPinfall1 != null){
                        score += nextPinfall1;
                        flagBall += 1;
                    }
                    if (nextPinfall2 != null && flagBall < 2){
                        score += nextPinfall2;
                        flagBall += 1;
                    }
                    if (nextPinfall3 != null && flagBall < 2){
                        score += nextPinfall3;
                        flagBall += 1;
                    }
                    if (nextPinfall4 != null && flagBall < 2){
                        score += nextPinfall4;
                    }
                    score += scoreFrame;
                }else if(scoreFrame == 10){
                    score += scoreFrame + turns.get(keyTurn+1).getPinfall1();
                }
            }else{
                score += pinfall1 + pinfall2 + pinfall3;
            }
            turn.setScore(score);
        }
    }
}
