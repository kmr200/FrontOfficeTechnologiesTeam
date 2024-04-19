import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static AtomicInteger turn = new AtomicInteger(0);
    public static final Object lock = new Object();

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        Joey joey = new Joey();
        Chandler chandler = new Chandler();
        Monica monica = new Monica();
        Phoebe phoebe = new Phoebe();

        executorService.submit(joey);
        executorService.submit(chandler);
        executorService.submit(monica);
        executorService.submit(phoebe);

        executorService.shutdown();
    }
}
