import controller.Controller;
import exception.PinfallException;
import exception.TurnException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ControllerTest {

    Controller controller = new Controller();

    @Test
    public void setAndGetInputFile() throws IOException {
        //arrange
        String inputFile = "";
        //act
        String inputFile1 = controller.setAndGetInputFile("");
        //assert
        //Assert.assertNotNull("hola",inputFile1);
    }

    @Test
    public void validateDataFilePinfall(){
        //Arrange
        String pinfall = "12";
        //act
        try{
            controller.validateDataFilePinfall(pinfall);
        }catch (PinfallException e){
            assertEquals("The number of pinfalls in a throw cannot be greater than 10", e.getMessage());
        }

        //arrange
        pinfall = "-5";
        try{
            controller.validateDataFilePinfall(pinfall);
        }catch (PinfallException e){
            assertEquals("The number of pinfalls in a throw cannot be less than 0", e.getMessage());
        }

        //arrange
        pinfall = "AAA";
        try{
            controller.validateDataFilePinfall(pinfall);
        }catch (PinfallException e){
            assertEquals("The valid values to report the pinfalls in a throw are 0 to 10 or F", e.getMessage());
        }
    }

    @Test(expected = TurnException.class)
    public void validateNumberOfFrames(){
        //arrange
        int frame = 15;
        //act
        controller.validateDataFileNumberOfFrames(frame);
    }
}
