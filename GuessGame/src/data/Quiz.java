package data;

import interfaces.Imainfunct;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class Quiz implements Imainfunct {

    @Override
    public String readFile(int linenr, RandomAccessFile file) {
        Imainfunct read = (k, l) -> readLine(k, l);
        return operate(linenr, read, file);
    }

    public String operate(int lineNr, Imainfunct function, RandomAccessFile f) {
        return function.readFile(lineNr, f);
    }

    private String readLine(int linenr, RandomAccessFile file) {
        String currentLine = null;
        // if line is -1 read random line
        // else read line from given file
        try {

            if (linenr == -1) {

                Random rand = new Random();
                int maxLines = 8;
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

        return secondsLeft * 1.5f;
    }
}
