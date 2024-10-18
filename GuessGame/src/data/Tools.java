package data;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

//Bill Pugh singleton
public class Tools {
    private static RandomAccessFile javaQuestions;
    private static RandomAccessFile javaAnswers;
    private static RandomAccessFile javaOptions;


    private Tools()  {

        //file initilized during building of singleton and those only once initialized  
        
        String filepathJavaQuestions =  System.getProperty("user.dir") + "/java/questions_java";
        String filepathJavaOptions =  System.getProperty("user.dir") + "/java/options_java";
        String filepathJavaAnswers =  System.getProperty("user.dir") + "/java/answers_java";
        
        //TODO 
        // loop 

        try {
        
             javaQuestions = new RandomAccessFile(filepathJavaQuestions, "r");
             javaAnswers = new RandomAccessFile(filepathJavaAnswers, "r");
             javaOptions = new RandomAccessFile(filepathJavaOptions, "r");

        } catch (FileNotFoundException  e) {
            javaQuestions = null;
            javaAnswers = null;
        }
          
    }

    public static RandomAccessFile getJavaQuestions()  {

        return javaQuestions;
    }

    public  static RandomAccessFile getJavaAnsers() {
        return javaAnswers;
    }


    public static RandomAccessFile getJavaOptions() {
        return javaOptions;
    }

    private static class ToolsHolder  {
        
        private static final Tools singleton = new Tools();
    }

    public static Tools getInstance()  {
        return ToolsHolder.singleton;
    }




}
