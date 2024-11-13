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
        
        return readLine(-1, Tools.getInstance().getJavaQuestions());        
    }

    @Override
    public String correctAnsw(int linenr) {
        return readLine(linenr, Tools.getInstance().getJavaAnswer()); 
    }

    @Override
    public String options(int linenr) {
      return readLine(linenr, Tools.getInstance().getJavaOptions()); 
    }

    private String readLine(int linenr, RandomAccessFile file) {
        String currentLine = null;
        // if line is -1 read random line
        // else read line from given file
        try {

            if (linenr == -1) {

                Random rand = new Random();
                int maxLines = 3;
                int n = rand.nextInt(maxLines); ///start from 0 not including max, so need to increment n 
                linenr = ++n;
            }

            file.seek(0);

            int counter = 0;
            while ((currentLine = file.readLine()) != null) {

                counter++;
                if (counter == linenr) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return currentLine;
    }

    static public float score(int secondsLeft) {
        
        return secondsLeft*1.5f; 
    }



  

    

}
