package practical4;

public class PrintLetter {

    public static final int COUNT = 5;

    private static Object lock = new Object();

    private static volatile Letter currentLetter = Letter.A;

    private Letter printedLetter;

    public PrintLetter(Letter letter) {
        this.printedLetter = letter;
    }

    public void print() {
        for (int i = 0; i < COUNT; i++) {
            synchronized (lock) {
                while (printedLetter != currentLetter) {
                    waitLetter();
                }
                System.out.print(printedLetter.getLetter());
                currentLetter = currentLetter.getNextLetter();
                lock.notifyAll();
            }
        }
    }

    private void waitLetter() {
        try {
            lock.wait();
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
    }
}