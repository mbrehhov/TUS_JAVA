package output;

import data.Quiz;
import data.Stats;
import data.Timing;
import data.Tools;
import java.util.HashMap;
import java.util.Random;

public class ConsoleMenu {

    static String[][] twoDim = null;
    static HashMap<String, String> twoDimValues = new HashMap<String, String>();
    private int rows = 15;
    private int columns = 50;

    public void execute() {

        Timing time = null;
        Thread genericT = null; 
       
        output();

        // Scanner saveInput = null;
        char selection;
        boolean exit = false;
        while (exit != true) {

            try {

                selection = (new String(System.console().readPassword()).toLowerCase()).charAt(0); 
                
                switch (selection) {
                    case '1' -> {
                        //starting new game 
                        Stats stats = Tools.getInstance().getGameStat();
                         
                        if (stats==null || stats.isQuestionsEnabled())
                        {
                            Tools.getInstance().setGameStat(new Stats());
                            Tools.getInstance().getGameStat().setQuestionsEnabled(false);
                            getQuestionOption(new Quiz());
                            
                            time = new Timing(this);
                            
                            genericT = new Thread(time);
                            genericT.start();
                            

                        }
     

                    }
                    case '2' -> {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        output();

                        time.stopLoop();
                        genericT.join();
                        time = null;
                        //print stats into array or somewhere
                        // null stats
                        Tools.getInstance().setGameStat(null);
                        
                        //maybe need to disable new questions..  I'll leave this option for future usecase. 

                    }

                    case '3' -> {
                        exit = true;
                        time.stopLoop();
                        genericT.join();
                        time = null;
                        // on exit we can clear console.
                        // https://stackoverflow.com/questions/10241217/how-to-clear-console-in-java
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                    }
                    case '4' -> move();
                 
                 // answers for quiz
                    case 'a' -> {
                        String data = "selected Adddddddddddddddddddddddddddddddddddddddddddddddds";
                        move(data);
                    }
                    case 'b' -> {
                        String data = "selected B";
                        move(data);
                    }

                    case 'c' -> {
                        String data = "selected C";
                        move(data);
                    }

                    case 'd' -> {
                        String data = "selected D";
                        move(data);
                    }

                    default -> returnCursorOnePostion();
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
                       String question =  quiz.read();
                       int linenr = Integer.parseInt(String.valueOf(question.charAt(0)));  //add some validations here later
                       String options =  quiz.options(linenr);
                    
                       move(question,options);
    }

    private void returnCursorOnePostion() {
        System.out.print("\u001B[A"); // we moved next line, bringing coursor back
    }

    private void output() {
        output(true);
        Random rand = new Random();
        int random = rand.nextInt(9);
        printMenu();

        drawArray(random, "OOP Asingnment \nstudent nr. A00325954" + random,null,null,false);
     
    }
    private void output(boolean reinit) {
        int random = 1;
        drawArray(random, null ,null,null,reinit);

    }
    private void output(String data) {
        int random = 1;
        printMenu();

        drawArray(random, data + random,null,null,false);

    }

    private void outputTime(String time) {
        int random = 1;
        drawArray(random, null, null,time,false);
        //printMenu();
        printQuestionMenu();

    }
    private void output(String question, String options) {
        int random = 1;
        printQuestionMenu();
        drawArray(random, question, options,null,false);
        //printMenu();
        

    }
    private void printMenu() {

        twoDim[10][1] =  "#########################";
        twoDim[11][1] =  "[1] new game";
        twoDim[12][1] =  "[4] test app";
        twoDim[13][1] =  "[3] exit";
        twoDim[14][1] =  "#########################";
    }

    private void printQuestionMenu() {

        twoDim[10][1] =  "                         ";
        twoDim[11][1] =  "             ";
        twoDim[12][1] =  "             ";
        twoDim[13][1] =  "[2] leave";
        twoDim[14][1] =  "#########################";
        
    }

    private void drawArray(int value, String questions, String options,String timing, boolean nullDim) {

        if (nullDim) twoDim = null;

        if (twoDim == null) {
            twoDim = new String[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    twoDim[row][column] = " ";
                }

            }
        } else

        {

            //draw hearts
            if(Tools.getInstance().getGameStat()!=null&& Tools.getInstance().getGameStat().isQuestionsEnabled()==false)
            {
                String hearts = "";
                for(int i = 0; i<Tools.getInstance().getGameStat().getLives(); i++)
                {
                    hearts += "\u2665";
                }
                twoDim[1][3] = hearts;
            }
          

            if (timing!=null)
            {
                twoDim[2][1] = "Time:";
                twoDim[2][2] = timing;
                

            }
            if (questions != null)
                twoDim[3][1] = questions;


              
            if (options != null)
            {
                String[] splittingOptions = options.split("#");
                for(int k = 10; k <=40 ;k++)
                {
                    twoDim[4][k] = "_";

                    twoDim[9][k] ="_";
                }
                twoDim[5][13] =splittingOptions[1];
                twoDim[6][13] =splittingOptions[2];
                twoDim[7][13] =splittingOptions[3];
                twoDim[8][13] =splittingOptions[4];
                
                
    
            }
               
            }
       


        // final output
        //check with previous state and see if differ
        //idea is to try to avoid unnececsarry prints...
        //becouse copyes 2 dim arrays each time is sort of resrouce heavy..
        //better to use some sort of hashmap that record value only.. like string  to string ("1-1" -> "value")

        //if(Tools.getInstance().getSnapshot()!=null)
        {
            //String[][] prevstate = Tools.getInstance().getSnapshot();
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                   
               
                    if(twoDimValues.get(String.valueOf(row)+String.valueOf(column))==null||!twoDimValues.get(String.valueOf(row)+String.valueOf(column)).equals(twoDim[row][column]))
                    {
                       twoDimValues.put((String.valueOf(row)+String.valueOf(column)),twoDim[row][column]);
                       System.out.print(twoDimValues.get(String.valueOf(row)+String.valueOf(column)));
                       
                      // System.out.print("differe row" +row + " differ column" + column);
                           // System.out.print("*");
                    }
                     else
                     {
                          System.out.print(twoDimValues.get(String.valueOf(row)+String.valueOf(column)));
                           
                       
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
    private void move(boolean reinitArray) {
        moveCursorBeginning();
        output(reinitArray);

    }
    public void moveTime(String time)
    {
        moveCursorBeginning();
        outputTime(time);
    }
    private void move(String data) {
        moveCursorBeginning();
        output(data);

    }
    private void move(String question, String options) {
        moveCursorBeginning();
        output(question,options);

    }
    // for reference A cursor up, B down, C and D right/left
    // we are using 15 lines + 1 our input

    synchronized private void moveCursorBeginning() {
        for (int k = 0; k < 15; k++)
            System.out.print("\u001B[A");
    }

}
