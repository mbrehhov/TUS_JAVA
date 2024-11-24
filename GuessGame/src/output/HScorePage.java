package output;
import data.Score;
import interfaces.Imenu;

public class HScorePage implements Imenu {
    private Score highScore = null;
    public HScorePage(Score highScore)
    {
        this.highScore = highScore;
    }
    @Override
    public void createPage(String[][] screen) {
     
        screen[2][1] = highScore.getTopFive();

        screen[10][1] = "#########################";
        screen[11][1] = "[1] new game";
        screen[12][1] = "[2] title page ";
        screen[13][1] = "[3] exit";
        screen[14][1] = "#########################";        
      }

}
