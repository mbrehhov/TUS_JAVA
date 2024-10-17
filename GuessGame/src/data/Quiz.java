package data;

import interfaces.Imainfunct;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Quiz implements  Imainfunct{

//we want to make sure resources are only opened once and then closed before closing app
// make singlton for Files like questions_java and answers_java
//to access the same file repeateadly. 

    @Override
    public void read() {
        String filepath =  System.getProperty("user.dir") + "/java/questions_java";

        try {
			RandomAccessFile file = new RandomAccessFile(filepath, "r");
			String str;

			while ((str = file.readLine()) != null) {
				System.out.println(str);
			}

			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        

    }

    @Override
    public int verify(int linenr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verify'");
    }

    @Override
    public String output(int linenr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'output'");
    }

    @Override
    public int score(boolean correctness) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'score'");
    }

    

}
