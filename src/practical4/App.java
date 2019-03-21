package practical4;

import java.util.List;

public class App {
    public static void main(String[] args) throws InterruptedException {

        List<Thread> printers = List.of(
                new Thread(new PrintLetter(Letter.A)::print),
                new Thread(new PrintLetter(Letter.B)::print),
                new Thread(new PrintLetter(Letter.C)::print)
        );

        printers.forEach(Thread::start);

        for (Thread printer : printers) {
            printer.join();
        }

    }
}
