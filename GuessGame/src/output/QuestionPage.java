package output;
import interfaces.Imenu;

public class QuestionPage implements Imenu {

    @Override
    public void createPage(String[][] screen) {
       

        screen[13][1] = "[4] leave";
        screen[14][1] = "#########################";
    }

}
