package data;

import interfaces.Imainfunct;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * 
 * This is a **Quiz** class, implements functional interface.
 *
 * - readFile
 */
public class Quiz implements Imainfunct {
    
    @Override
    public String readFile(int linenr, RandomAccessFile file) {
        return operate(linenr, getLambda(), file);
    }
    
    public String operate(int lineNr, Imainfunct function, RandomAccessFile f) {
        return function.readFile(lineNr, f);
    }

    public Imainfunct getLambda()
    {
        return  (k, l) -> readLineFromFile(k, l);
    }

    // if line is -1 read random question else read line from given file
    private String readLineFromFile(int linenr, RandomAccessFile file) {
        String currentLine = null;
        try {

            if (linenr == -1) {

                Random rand = new Random();
                int maxLines = getMaxLines(file);
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
                            System.out.println(e);
                        }
                
                        return currentLine;
                    }
                
                    private int getMaxLines(RandomAccessFile file) {
                        int count = 0;
                        try {
                            file.seek(0);

                            while(file.readLine() != null)
                            count++;
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        return count;
                    }
                
                    static public float score(int secondsLeft) {

        return secondsLeft * 1.5f;
    }
}
