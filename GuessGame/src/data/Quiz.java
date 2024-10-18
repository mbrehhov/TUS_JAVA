package data;


import interfaces.Imainfunct;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;



public class Quiz implements  Imainfunct{

//we want to make sure resources are only opened once and then closed before closing app
// make singlton for Files like questions_java and answers_java
//to access the same file repeateadly. 

    @Override
    public String read() {
        String line = "None";
        String str = "None";
        try {
            RandomAccessFile file = Tools.getInstance().getJavaQuestions();
		//	file.getChannel();
            file.seek(0);
            
            Random rand = new Random();
            int maxLines = 3; 
            int n = rand.nextInt(maxLines); ///start from 0 not including max, so need to increment n 
            n++;
            int counter = 0;
            while ((str = file.readLine()) != null) {
				

                counter++;
                if(counter == n)
                {
                    line = str;
                    break;
                }
        	}

		} catch (IOException e) {
			e.printStackTrace();
		}
      return line;  

    }

    @Override
    public int verify(int linenr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verify'");
    }

    @Override
    public String options(int linenr) {
        String line = "None";
        String str = "None";
        try {
            RandomAccessFile file = Tools.getInstance().getJavaOptions();
		//	file.getChannel();
            file.seek(0);
            
            int counter = 0;
            while ((str = file.readLine()) != null) {
			
                counter++;
                if(counter == linenr)
                {
                    line = str;
                    break;
                }
        	}

		} catch (IOException e) {
			e.printStackTrace();
		}
      return line; 
    }

    @Override
    public int score(boolean correctness) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'score'");
    }

    

}
