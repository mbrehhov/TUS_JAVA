package data;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

//Bill Pugh singleton
public class Tools {
    private  RandomAccessFile javaQuestions;
    private  RandomAccessFile javaAnswer;
    private  RandomAccessFile javaOptions;

    private static Stats gameStat;
    

    public static Stats getGameStat() {
        return gameStat;
    }

    public static void setGameStat(Stats gameStat) {
        Tools.gameStat = gameStat;
    }

    private Tools() {

        String filepathJavaQuestions = System.getProperty("user.dir") + "/java/questions_java";
        String filepathJavaOptions = System.getProperty("user.dir") + "/java/options_java";
        String filepathJavaAnswers = System.getProperty("user.dir") + "/java/answers_java";

        try {

            javaQuestions = new RandomAccessFile(filepathJavaQuestions, "r");
            javaAnswer = new RandomAccessFile(filepathJavaAnswers, "r");
            javaOptions = new RandomAccessFile(filepathJavaOptions, "r");

        } catch (FileNotFoundException e) {
            javaQuestions = null;
            javaAnswer = null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    private static class ToolsHolder {

        private static final Tools singleton = new Tools();
    }

    public static Tools getInstance() {
        return ToolsHolder.singleton;
    }



    public  RandomAccessFile getJavaQuestions() {

        return javaQuestions;
    }

    public  RandomAccessFile getJavaAnswer() {
        return javaAnswer;
    }

    public  RandomAccessFile getJavaOptions() {
        return javaOptions;
    }


}
