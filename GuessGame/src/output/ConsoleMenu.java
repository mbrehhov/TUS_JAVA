package output;

import java.util.Scanner;
import java.io.Console;
public class ConsoleMenu {
       
    public void execute()
    {
        //change 
        
        //init 2 dim array


        //print single menu into console


        // start game loop

        //keep track of the rows

        // output cursor




        printmenu();
        
        
        
        
        
        Scanner input = new Scanner(System.in);
        
        
        //Scanner saveInput = null; 
        int selection = 1;
        boolean exit = false;
        int currentposition = 10;
        String pwdString = null;
        while(exit!=true)
        {
            
            try  {
              
               pwdString = new String(System.console().readPassword());  //console readPassword wont show output on screen
              selection = Integer.parseInt(pwdString);   //hope user entered interger

              //System.out.println(testB);
            
              if(selection == 3) 
              {

                exit = true;
                //on exit we can clear console. 
                //https://stackoverflow.com/questions/10241217/how-to-clear-console-in-java
                System.out.print("\033[H\033[2J");
                System.out.flush();
                
            }
                moveup(--currentposition);

            }
            catch (Exception e)
            {
               System.out.print("\u001B[A");  // we moved next line, bringing coursor back
            }
            
            
        }
        
    input.close();    
    }

    private void printmenu()
    {
      
      
        // initial single menu
        //game loop

        //FOR TESTING

        //create signle 2 dim array
        int[][] twoDim = new int[10][100];
              
        for(int row = 0; row<10; row ++ )
        {
            for(int column = 0; column<100; column ++ )
            {
           //arrays are stored as objects in heap, we can access them during game loop and modify values. 

                twoDim[row][column] = 0;
            }
    
        }

        for(int row = 0; row<10; row ++ )
        {
            for(int column = 0; column<100; column ++ )
            {
         //idea is that instead of 3 textual line menu , menu will be linked with array objects. 
         // and we can access to menu elements by moving cursor up and down
         //yet not 100% sure, maybe removign this approach later
                System.out.print(twoDim[row][column]);
           
            }
    
            System.out.println("");
        }



            System.out.println("#########################");

                System.out.println("[1] new game");
                System.out.println("[2] see score");
                System.out.println("[3] exit");
                
            System.out.println("#########################");        
    
            
        }

    private void moveup(int i) {
       // A cursor up, B down, C and D right/left

        //just for testing
        //later count max how much you can go up or down. Also shoudl be generic function  - like move, not moveup or movedown

       for(int k=0; k<2;k++)
        System.out.print("\u001B[A");   
    }

}
