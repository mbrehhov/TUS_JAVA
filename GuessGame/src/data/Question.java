package data;

public class Question {

    String name;
    String[] options;

    public Question(String name, String[] options) {

        this.name = name;
        this.options = options;
    }

    @Override
    public boolean equals(Object obj) {

        // if same object
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        // cast.
        Question maybeSame = (Question) obj;

        // comparing the state of argument with
        // the state of 'this' Object.
        boolean falseFlag = true;
        for (int i = 0; i < options.length; i++) {
            if (!options[i].equals(maybeSame.options[i])) {
                falseFlag = false;
                break;
            }

        }

        return (maybeSame.name.equals(this.name) && falseFlag == true);
    }

    @Override
    public int hashCode() {

        return this.name.hashCode();
    }
}
