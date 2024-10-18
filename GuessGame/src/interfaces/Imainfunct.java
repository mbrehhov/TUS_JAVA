package interfaces;

public interface Imainfunct {

    String read();

    int verify(int linenr);

    String options(int linenr);

    int score(boolean correctness);

}
