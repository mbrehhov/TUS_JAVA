package output;
import interfaces.Imenu;

public class TitlePage implements Imenu {

    @Override
    public void createPage(String[][] screen) {
       
        screen[5][1] = "OOP Asingnment";
        screen[6][1] =  "student nr. A00325954";

        screen[10][1] = "#########################";
        screen[11][1] = "[1] new game";
        screen[12][1] = "[2] highest score";
        screen[13][1] = "[3] exit";
        screen[14][1] = "#########################";        
    }

}
