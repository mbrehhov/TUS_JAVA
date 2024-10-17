package output;

import data.Quiz;
import java.util.Random;

public class ConsoleMenu {

    static String[][] twoDim = null;

    public void execute() {
        int questionsLeft = 10;
        boolean questionOpen = true;
        Quiz quiz = new Quiz();

        output();

        // Scanner saveInput = null;
        char selection;
        boolean exit = false;
        while (exit != true) {

            try {

                selection = (new String(System.console().readPassword()).toLowerCase()).charAt(0); 
                
                switch (selection) {
                    case '1' -> {
                        quiz.read();
                        
                        questionsLeft = 10;
                        questionOpen = true;
                    }

                    case '3' -> {
                        exit = true;
                        // on exit we can clear console.
                        // https://stackoverflow.com/questions/10241217/how-to-clear-console-in-java
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                    }
                    case '4' -> move();
                 
                 // answers for quiz
                    case 'a' -> {
                        String data = "selected A";
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

    private void returnCursorOnePostion() {
        System.out.print("\u001B[A"); // we moved next line, bringing coursor back
    }

    private void output() {
        Random rand = new Random();
        int random = rand.nextInt(9);
        drawArray(random, "First Testing Question " + random);
        printMenu();

    }

    private void output(String data) {
        int random = 1;
        drawArray(random, data + random);
        printMenu();

    }

    private void printMenu() {

        System.out.println("#########################");

        System.out.println("[1] new game");
        System.out.println("[4] test app"); // later change
        System.out.println("[3] exit");

        System.out.println("#########################");

    }

    private void drawArray(int value, String newQuestion) {
        // arrays are stored as objects in heap, we can access them during game loop and
        // modify values.

        if (twoDim == null) {
            twoDim = new String[10][100];
            for (int row = 0; row < 10; row++) {
                for (int column = 0; column < 100; column++) {
                    twoDim[row][column] = " ";
                }

            }
        } else

        {

            if (newQuestion != null)
                twoDim[4][9] = newQuestion;

        }

        // final output
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 100; column++) {
                System.out.print(twoDim[row][column]);
            }

            System.out.println("");
        }

    }

    // this function is called when we want updated our console screen
    private void move() {
        moveCursorBeginning();
        output();

    }

    private void move(String data) {
        moveCursorBeginning();
        output(data);

    }
    // for reference A cursor up, B down, C and D right/left
    // we are using 15 lines + 1 our input

    private void moveCursorBeginning() {
        for (int k = 0; k < 16; k++)
            System.out.print("\u001B[A");
    }

}
