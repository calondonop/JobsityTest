import exception.PinfallsFrameException;
import model.Turn;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TurnTest {

    Turn turn = new Turn();

    @Test
    public void calculatePerfectScore(){
        //arrange
        int score = 0;
        Map<Integer, Turn> turnsToProcess = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            if(i==10)
                turnsToProcess.put(i, new Turn(10, 10, 10));
            else
                turnsToProcess.put(i, new Turn(10,null,null));
        }
        score = 0;
        Map<Integer, Turn> turnsExpected = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            score += 30;
            if(i==10)
                turnsExpected.put(10, new Turn(10, 10, 10, score));
            else
                turnsExpected.put(i, new Turn(10,null,null,score));
        }
        //act
        turn.calculateScores(turnsToProcess);
        //assert
        for (Integer j : turnsToProcess.keySet()){
            assertEquals(turnsExpected.get(j).getScore(),turnsToProcess.get(j).getScore());
        }
    }

    @Test
    public void calculateZeroScore(){
        //arrange
        int score = 0;
        Map<Integer, Turn> turnsToProcess = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            if(i==10)
                turnsToProcess.put(i, new Turn(0,0,null));
            else
                turnsToProcess.put(i, new Turn(0,0,null));
        }
        score = 0;
        Map<Integer, Turn> turnsExpected = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            if(i==10)
                turnsExpected.put(i, new Turn(0, 0, null, score));
            else
                turnsExpected.put(i, new Turn(0,0,null,score));
        }
        //act
        turn.calculateScores(turnsToProcess);
        //assert
        for (Integer j : turnsToProcess.keySet()){
            assertEquals(turnsExpected.get(j).getScore(),turnsToProcess.get(j).getScore());
        }
    }

    @Test
    public void calculateScore2Players(){
        //arrange
        int score = 0;
        Map<Integer, Turn> turnsToProcess1 = new HashMap<>();
        turnsToProcess1.put(1, new Turn(10,null,null));
        turnsToProcess1.put(2, new Turn(7,3,null));
        turnsToProcess1.put(3, new Turn(9,0,null));
        turnsToProcess1.put(4, new Turn(10,null,null));
        turnsToProcess1.put(5, new Turn(0,8,null));
        turnsToProcess1.put(6, new Turn(8,2,null));
        turnsToProcess1.put(7, new Turn(0,6,null));
        turnsToProcess1.put(8, new Turn(10,null,null));
        turnsToProcess1.put(9, new Turn(10,null,null));
        turnsToProcess1.put(10, new Turn(10,8,1));

        Map<Integer, Turn> turnsToProcess2 = new HashMap<>();
        turnsToProcess2.put(1, new Turn(3,7,null));
        turnsToProcess2.put(2, new Turn(6,3,null));
        turnsToProcess2.put(3, new Turn(10,null,null));
        turnsToProcess2.put(4, new Turn(8,1,null));
        turnsToProcess2.put(5, new Turn(10,null,null));
        turnsToProcess2.put(6, new Turn(10,null,null));
        turnsToProcess2.put(7, new Turn(9,0,null));
        turnsToProcess2.put(8, new Turn(7,3,null));
        turnsToProcess2.put(9, new Turn(4,4,null));
        turnsToProcess2.put(10, new Turn(10,9,0));

        Map<Integer, Turn> turnsExpected1 = new HashMap<>();
        turnsExpected1.put(1, new Turn(10,null,null,20));
        turnsExpected1.put(2, new Turn(7,3,null,39));
        turnsExpected1.put(3, new Turn(9,0,null,48));
        turnsExpected1.put(4, new Turn(10,null,null,66));
        turnsExpected1.put(5, new Turn(0,8,null,74));
        turnsExpected1.put(6, new Turn(8,2,null,84));
        turnsExpected1.put(7, new Turn(0,6,null,90));
        turnsExpected1.put(8, new Turn(10,null,null,120));
        turnsExpected1.put(9, new Turn(10,null,null,148));
        turnsExpected1.put(10, new Turn(10,8,1,167));

        Map<Integer, Turn> turnsExpected2 = new HashMap<>();
        turnsExpected2.put(1, new Turn(3,7,null,16));
        turnsExpected2.put(2, new Turn(6,3,null,25));
        turnsExpected2.put(3, new Turn(10,null,null,44));
        turnsExpected2.put(4, new Turn(8,1,null,53));
        turnsExpected2.put(5, new Turn(10,null,null,82));
        turnsExpected2.put(6, new Turn(10,null,null,101));
        turnsExpected2.put(7, new Turn(9,0,null,110));
        turnsExpected2.put(8, new Turn(7,3,null,124));
        turnsExpected2.put(9, new Turn(4,4,null,132));
        turnsExpected2.put(10, new Turn(10,9,0,151));

        //act
        turn.calculateScores(turnsToProcess1);
        turn.calculateScores(turnsToProcess2);
        //assert
        for (Integer j : turnsToProcess1.keySet()){
            assertEquals(turnsExpected1.get(j).getScore(),turnsToProcess1.get(j).getScore());
        }
        for (Integer k : turnsToProcess2.keySet()){
            assertEquals(turnsExpected2.get(k).getScore(),turnsToProcess2.get(k).getScore());
        }
    }

    @Test
    public void orElseOptional(){
        Integer number = null;
        //act
        number = turn.orElseOptional(number);
        //assert
        Assert.assertNotNull(number);
    }

    @Test(expected = PinfallsFrameException.class)
    public void validateFramePinfalls(){
        //arrange
        Turn turn1 = new Turn(7,7,null);
        //act
        turn1.validateFramePinfalls(1);
    }
}
