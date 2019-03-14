package practical4;

public class Letter {

    public static Letter A = new Letter('A');
    public static Letter B = new Letter('B');
    public static Letter C = new Letter('C');

    private final char letter;

    private Letter(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    public Letter getNextLetter() {
        if (this.equals(A)) return B;
        else if (this.equals(B)) return C;
        else return A;
    }
}