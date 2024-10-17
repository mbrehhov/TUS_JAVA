package data;

import java.io.File;

//Bill Pugh singleton
public class Tools {
    private File javaQuestions;
    private File javaAnsers;
    
    
    private Tools() {
    }

    public File getJavaQuestions() {
        return javaQuestions;
    }

    public File getJavaAnsers() {
        return javaAnsers;
    }

    private static class ToolsHolder {
        private static final Tools singleton = new Tools();
    }

    public static Tools getInstance() {
        return ToolsHolder.singleton;
    }



}
