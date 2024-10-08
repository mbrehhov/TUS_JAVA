package output;

import java.util.Scanner;
import java.io.Console;
import java.util.Random;
public class ConsoleMenu {

    static int[][] twoDim = null;

    public void execute() {

        // init 2 dim array

        // print single menu into console

        // start game loop

        // keep track of the rows

        // output cursor

        // inital output
        output();

        Scanner input = new Scanner(System.in);

        twoDim = new int[10][100];

        // Scanner saveInput = null;
        int selection = -1;
        boolean exit = false;
        String pwdString = null;
        while (exit != true) {

            try {

                pwdString = new String(System.console().readPassword()); // console readPassword wont show output on                                                          // screen
                selection = Integer.parseInt(pwdString); // hope user entered interger, if not exception catch , but user will be back here

                if (selection == 3) {

                    exit = true;
                    // on exit we can clear console.
                    // https://stackoverflow.com/questions/10241217/how-to-clear-console-in-java
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                }

                if (selection == 4) {
                    move();
                }

            } catch (Exception e) {
                System.out.print("\u001B[A"); // we moved next line, bringing coursor back
            }

        }

        input.close();
    }

   
    // trying bit different approach how to output data on console, instead creating new lines on each selection of user
    // program tries to reprint  existing print from top, by cursor manipulation. This way it creates illusion of rendering. 
    // plan is move cursor up and output updaed array ( updated information somehwere in the
    // middle) and  after that landing cursor back to original place. 
   

    private void output() {
        Random rand = new Random();
        int random = rand.nextInt(9);
        drawArray(twoDim, random);
        printMenu();

    }

    private void printMenu() {

        System.out.println("#########################");

        System.out.println("[1] new game");
        System.out.println("[4] test app");  //later change
        System.out.println("[3] exit");

        System.out.println("#########################");

    }

    private void drawArray(int[][] twoDim, int value) {
        // arrays are stored as objects in heap, we can access them during game loop and
        // modify values.

        if (twoDim == null) {
            twoDim = new int[10][100];
            for (int row = 0; row < 10; row++) {
                for (int column = 0; column < 100; column++) {

                    twoDim[row][column] = 0;
                }

            }
        } else

        {
            for (int column = 0; column < 100; column++) {

                twoDim[5][column] = value;

            }

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
        // for reference A cursor up, B down, C and D right/left
        // we are using 15 lines + 1 our input
        for (int k = 0; k < 16; k++)
            System.out.print("\u001B[A");
        output();

    }

}
