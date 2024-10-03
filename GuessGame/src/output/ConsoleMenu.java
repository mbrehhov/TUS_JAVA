package output;

import java.util.Scanner;

public class ConsoleMenu {
       
    public void execute()
    {
        Scanner input  =null;
        int selection;
        boolean exit = false;

        while(exit!=true)
        {
            printmenu();
            
            try  {
                 input = new Scanner(System.in);
                selection = input.nextInt();
                if(selection == 3) exit = true;

                System.out.println(selection);   
            }
            catch (Exception e)
            {
                System.out.println("wrong option try again");
            }
            
            
        }
        if(input != null) input.close();

        
      
    }

    private void printmenu()
    {
            
            System.out.println("#########################");
            System.out.println("[1] new game");
            System.out.println("[2] see score");
            System.out.println("[3] exit");
            System.out.println("#########################");        
    }

}
