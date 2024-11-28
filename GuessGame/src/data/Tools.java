package data;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

//Bill Pugh singleton
public class Tools {
    private RandomAccessFile javaQuestions;
    private RandomAccessFile javaAnswer;
    private RandomAccessFile javaOptions;
    private String filePostfix="java";

    private static Stats gameStat;

    public Stats getGameStat() {
        return gameStat;
    }

    public static void setGameStat(Stats gameStat) {
        Tools.gameStat = gameStat;
    }

    public void reAssign()
    {
        closeAll(); 

        String filepathJavaQuestions = System.getProperty("user.dir") + "/java/questions_"+filePostfix;
        String filepathJavaOptions = System.getProperty("user.dir") + "/java/options_"+filePostfix;
        String filepathJavaAnswers = System.getProperty("user.dir") + "/java/answers_"+filePostfix;

        try {
            
            javaQuestions = new RandomAccessFile(filepathJavaQuestions, "r");
            javaAnswer = new RandomAccessFile(filepathJavaAnswers, "r");
            javaOptions = new RandomAccessFile(filepathJavaOptions, "r");

        } catch (FileNotFoundException e) {
            javaQuestions = null;
            javaAnswer = null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
   
    }
    private Tools() {
          
        reAssign();
    }

    public void setFilePostfix(String filePostfix) {
        this.filePostfix = filePostfix;
    }

    private static class ToolsHolder {

        private static final Tools singleton = new Tools();
    }

    public static Tools getInstance() {
        return ToolsHolder.singleton;
    }

    public RandomAccessFile getJavaQuestions() {

        return javaQuestions;
    }

    public RandomAccessFile getJavaAnswer() {
        return javaAnswer;
    }

    public RandomAccessFile getJavaOptions() {
        return javaOptions;
    }

    public void closeAll()
    {
        try {

            if(this.getJavaQuestions()!=null) this.getJavaQuestions().close();  
            if(this.getJavaOptions()!=null) this.getJavaOptions().close();
            if(this.getJavaAnswer()!=null) this.getJavaAnswer().close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}