package interfaces;

import java.io.RandomAccessFile;

@FunctionalInterface
public interface Imainfunct {

    String readFile(int linenr,RandomAccessFile f);
}
