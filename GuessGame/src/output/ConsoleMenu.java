package output;

import data.Quiz;
import data.Score;
import data.Stats;
import data.Timing;
import data.Tools;
import java.util.HashMap;

public class ConsoleMenu {

    StringBuilder title = null;
    StringBuilder listHighScore = null;

    static String[][] twoDim = null;
    static HashMap<String, String> twoDimValues = new HashMap<String, String>();
    private int rows = 15;
    private int columns = 50;
    private Score highScore = new Score();
    private boolean showTitle = false;
    private boolean showScore = true;
    
    public void execute() {

        output();

        // Scanner saveInput = null;
        char selection;
        boolean exit = false;
        while (exit != true) {

            try {

                selection = (new String(System.console().readPassword()).toLowerCase()).charAt(0);

                switch (selection) {
                    case '1' -> {
                        // starting new game
                        Stats stats = Tools.getInstance().getGameStat();

                        if (stats == null || stats.isQuestionsEnabled()) {
                            Tools.getInstance().setGameStat(new Stats());
                            Tools.getInstance().getGameStat().setQuestionsEnabled(false);
                            getQuestionOption(new Quiz());
                            var time = new Timing(this); // class that supports threading
                            Tools.getInstance().getGameStat().setTiming(time);
                            var childThread = new Thread(time); // child thread
                            childThread.start();
                            Tools.getInstance().getGameStat().setChildThread(childThread); // to access eventually

                        }

                    }
                    case '4' -> {
                         showTitle = false;
                         showScore = true;
                        leaveGameToMainMenu();

                    }

                    case '3' -> {
                        exit = true;
                        leaveGameToMainMenu();

                        // on exit we can clear console.
                        // https://stackoverflow.com/questions/10241217/how-to-clear-console-in-java
                        Tools.getInstance().closeAll();
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        
                    }
                    case '2' -> {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        if(showScore)
                        {
                            showScore =false;  
                            showTitle = true; 
                            moveCursorBeginning();
                            outputHS();
                           
                        }
                        else if(showTitle)
                        {
                            showScore =true;  

                            showTitle = false;
                            move();
                           
                        }
                    }

                    // answers for quiz
                    case 'a' -> {
                        Stats stats = Tools.getInstance().getGameStat();

                        if (stats != null || stats.isQuestionsEnabled() == false) {
                            if (Tools.getInstance().getGameStat().getCurrentQuestionAnswer()
                                    .equalsIgnoreCase(String.valueOf(selection))) {
                                Tools.getInstance().getGameStat().setScore(Tools.getInstance().getGameStat().getScore()
                                        + (float) Tools.getInstance().getGameStat().getTiming().getTime());
                                Tools.getInstance().getGameStat().getTiming().setNewQuestion(true);

                            } else {

                                Tools.getInstance().getGameStat().getTiming().setWrongAnswer(true);
                            }

                        }
                        returnCursorOnePostion();
                    }
                    case 'b' -> {
                        Stats stats = Tools.getInstance().getGameStat();

                        if (stats != null || stats.isQuestionsEnabled() == false) {
                            if (Tools.getInstance().getGameStat().getCurrentQuestionAnswer()
                                    .equalsIgnoreCase(String.valueOf(selection))) {
                                Tools.getInstance().getGameStat().setScore(Tools.getInstance().getGameStat().getScore()
                                        + (float) Tools.getInstance().getGameStat().getTiming().getTime());
                                Tools.getInstance().getGameStat().getTiming().setNewQuestion(true);

                            } else {

                                Tools.getInstance().getGameStat().getTiming().setWrongAnswer(true);
                            }

                        }

                        returnCursorOnePostion();

                    }

                    case 'c' -> {

                        Stats stats = Tools.getInstance().getGameStat();

                        if (stats != null || stats.isQuestionsEnabled() == false) {
                            if (Tools.getInstance().getGameStat().getCurrentQuestionAnswer()
                                    .equalsIgnoreCase(String.valueOf(selection))) {
                                Tools.getInstance().getGameStat().setScore(Tools.getInstance().getGameStat().getScore()
                                        + (float) Tools.getInstance().getGameStat().getTiming().getTime());
                                Tools.getInstance().getGameStat().getTiming().setNewQuestion(true);

                            } else {

                                Tools.getInstance().getGameStat().getTiming().setWrongAnswer(true);
                            }

                        }

                        returnCursorOnePostion();

                    }

                    case 'd' -> {

                        Stats stats = Tools.getInstance().getGameStat();

                        if (stats != null || stats.isQuestionsEnabled() == false) {
                            if (Tools.getInstance().getGameStat().getCurrentQuestionAnswer()
                                    .equalsIgnoreCase(String.valueOf(selection))) {
                                Tools.getInstance().getGameStat().setScore(Tools.getInstance().getGameStat().getScore()
                                        + (float) Tools.getInstance().getGameStat().getTiming().getTime());
                                Tools.getInstance().getGameStat().getTiming().setNewQuestion(true);

                            } else {

                                Tools.getInstance().getGameStat().getTiming().setWrongAnswer(true);
                            }
                        }
                        returnCursorOnePostion();

                    }

                    default ->
                        returnCursorOnePostion();
                }

            } catch (Exception e) {
                returnCursorOnePostion();
            }
        }

    }

    // trying bit different approach how to output data on console, instead creating
    // new lines on each selection of user
    // program tries to reprint existing print from top, by cursor manipulation.
    // This way it creates illusion of rendering.
    // plan is move cursor up and output updaed array ( updated information
    // somehwere in the
    // middle) and after that landing cursor back to original place.
    public void getQuestionOption(Quiz quiz) {
        try {
            String question = quiz.read();
            int linenr = Integer.parseInt(String.valueOf(question.charAt(0))); // add some validations here later
            String options = quiz.options(linenr);
            String correctAnswer = quiz.correctAnsw(linenr);
            Tools.getInstance().getGameStat()
                    .setCurrentQuestionAnswer(correctAnswer.substring(correctAnswer.length() - 1));
            move(question, options);

        } catch (Exception e) {
            // ignore at the moment

        }
    }

    private void returnCursorOnePostion() {
        System.out.print("\u001B[A"); // we moved next line, bringing coursor back
    }

    private void outputHS() {
        output(true);
        printMenu();

        listHighScore = new StringBuilder();
        listHighScore.append(highScore.getTopFive());
        drawArray(0, listHighScore.toString(), null, null, false);

    }

    private void output() {
        output(true);
        printMenu();

        // string builder usage
        if (null == title) {
            title = new StringBuilder();

            title.append("OOP Asingnment");
            title.append(System.getProperty("line.separator"));
            title.append(" student nr. A00325954");
            title.append(System.getProperty("line.separator")); // this line can be removed
        }

        drawArray(0, title.toString(), null, null, false);

    }

    private void output(boolean reinit) {
        int random = 1;
        drawArray(random, null, null, null, reinit);

    }

    private void output(String data) {
        int random = 1;
        printMenu();

        drawArray(random, data + random, null, null, false);

    }

    private void outputTime(String time) {
        int random = 1;
        drawArray(random, null, null, time, false);
        // printMenu();
        printQuestionMenu();

    }

    private void output(String question, String options) {
        int random = 1;
        printQuestionMenu();
        drawArray(random, question, options, null, false);
        // printMenu();

    }

    private void printMenu() {

        twoDim[10][1] = "#########################";
        twoDim[11][1] = "[1] new game";
        if(showTitle)
        {
            twoDim[12][1] = "[2] title page ";
        }
        
        if(showScore)
        {
            twoDim[12][1] = "[2] highest score";
        }
 
        
        twoDim[13][1] = "[3] exit";
        twoDim[14][1] = "#########################";

    }

    private void printQuestionMenu() {

        twoDim[10][1] = "                         ";
        twoDim[11][1] = "             ";
        twoDim[12][1] = "                ";
        twoDim[13][1] = "[4] leave";
        twoDim[14][1] = "#########################";

    }

    private void drawArray(int value, String questions, String options, String timing, boolean nullDim) {

        if (nullDim) {
            twoDim = null;
        }

        if (twoDim == null) {
            twoDim = new String[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    twoDim[row][column] = " ";
                }

            }
        } else {
            // draw scores

            if (Tools.getInstance().getGameStat() != null && Tools.getInstance().getGameStat().getScore() != 0) {
                twoDim[0][3] = "";
                twoDim[0][3] = "Scores:" + Tools.getInstance().getGameStat().getScore();

            }
            // else twoDim[0][3] = "";

            // draw hearts
            if (Tools.getInstance().getGameStat() != null
                    && Tools.getInstance().getGameStat().isQuestionsEnabled() == false) {
                String hearts = "";
                for (int i = 0; i < Tools.getInstance().getGameStat().getLives(); i++) {
                    hearts += "\u2665" + "  ";
                }
                twoDim[1][3] = hearts;
            }
            if (timing != null) {
                twoDim[2][1] = "Time:";
                twoDim[2][2] = timing;

            }
            if (questions != null) {
                twoDim[3][1] = questions;
            }

            if (options != null) {
                String[] splittingOptions = options.split("#");
                for (int k = 10; k <= 40; k++) {
                    twoDim[4][k] = "_";

                    twoDim[9][k] = "_";
                }

                twoDim[5][13] = splittingOptions[1];
                twoDim[6][13] = splittingOptions[2];
                twoDim[7][13] = splittingOptions.length > 3 ? splittingOptions[3] : "";
                twoDim[8][13] = splittingOptions.length > 4 ? splittingOptions[4] : "";

            }

        }

        // final output
        // attempt is to save two dim values into hashmap and check if someting changes
        // in array..
        // and only draw in cases changes are detected.
        // Funcionality is not finished. At the moment it has not achived that goal.
        {
            // String[][] prevstate = Tools.getInstance().getSnapshot();
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {

                    if (twoDimValues.get(String.valueOf(row) + String.valueOf(column)) == null || !twoDimValues
                            .get(String.valueOf(row) + String.valueOf(column)).equals(twoDim[row][column])) {
                        twoDimValues.put((String.valueOf(row) + String.valueOf(column)), twoDim[row][column]);
                        System.out.print(twoDimValues.get(String.valueOf(row) + String.valueOf(column)));

                    } else {
                        System.out.print(twoDimValues.get(String.valueOf(row) + String.valueOf(column)));
                    }

                }

                System.out.println("");
            }

        }

    }

    // this function is called when we want updated our console screen
    private void move() {
        moveCursorBeginning();
        output();

    }

    private void move(String... strData) {

        if (strData.length == 1) {
            moveCursorBeginning();
            output(strData[0]);

        } else {
            moveCursorBeginning();
            output(strData[0], strData[1]);

        }
    }

    public void moveTime(String time) {
        moveCursorBeginning();
        outputTime(time);
    }

    // for reference A cursor up, B down, C and D right/left
    // we are using 15 lines + 1 our input
    synchronized private void moveCursorBeginning() {
        for (int k = 0; k < 15; k++) {
            System.out.print("\u001B[A");
        }
    }

    public void leaveGameToMainMenu() {

        try {

            // if(Tools.getInstance().getGameStat().getScore()!=0)
            highScore.evalueteTop(Tools.getInstance().getGameStat().getScore());
            Tools.getInstance().getGameStat().getTiming().stopLoop();

            Tools.getInstance().getGameStat().getChildThread().join();
            
        } catch (InterruptedException e) {
            // System.out.println( e);
        }

        Tools.getInstance().getGameStat().setTiming(null);
        Tools.getInstance().getGameStat().setChildThread(null);

        Tools.getInstance().getGameStat().setScore(0);
        Tools.getInstance().setGameStat(null);

        System.out.print("\033[H\033[2J");
        System.out.flush();
        output();

    }

}