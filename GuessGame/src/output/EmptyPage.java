package output;

import interfaces.Imenu;

public class EmptyPage  implements Imenu {

    @Override
    public void createPage(String[][] screen) {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 50; column++) {
                screen[row][column] = " ";
            }

        }
    }

}
