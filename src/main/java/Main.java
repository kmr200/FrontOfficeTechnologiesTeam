import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Threads in this solution are synchronized in a queue manner.
 * The Main class contains atomic variable called turn which will represent
 * which line is to be printed and which threads turn it is. The Main class
 * also contains "lock" Object using which all the threads will be synchronized.
 * When a thread prints a line, it will notify all the other threads about update
 * in turn and starts waiting until its own turn comes.
 */

public class Main {

    //Number of threads to be used
    private static final int threadCount = 6;

    //This number shows which thread turn it is and what line to print
    public static AtomicInteger turn = new AtomicInteger(1);
    //The empty lock object will be used to ensure thread safety in the Main class
    public static final Object lock = new Object();

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
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
