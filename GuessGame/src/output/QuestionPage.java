package output;
import interfaces.Imenu;

public class QuestionPage implements Imenu {

    @Override
    public void createPage(String[][] screen) {
       
        screen[5][1] = "";
        screen[6][1] =  "";


        screen[10][1] = "";
        screen[11][1] = "";
        screen[12][1] = "";
        screen[13][1] = "[4] leave";
        screen[14][1] = "#########################";
    }

}
