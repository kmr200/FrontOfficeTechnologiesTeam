public class Monica implements Runnable{
    @Override
    public void run() {
        synchronized (Main.lock) {
            MonicaTurnWait(9);

            System.out.println(
                    "Monica: Oh, wow, so you're gonna be one of those \"healthy, healthy, healthy guys\"?"
            );
            MonicaGiveTurn();
        }
    }

    /**
     * The private method *TurnWait has the same functionality across all
     * runnable thread classes.
     * Method locks current thread until its turn comes
     * as described in the Main class.
     * @param turn
     * turn parameter being magical number should not be
     * a big problem since it represents the line to be printed
     */
    private void MonicaTurnWait(int turn) {
        while (Main.turn.get() != turn) {
            try {
                Main.lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * *GiveTurn has the same functionality across all the runnable thread classes. Method
     * updates the value of the atomicInteger turn contained in the Main class and notifies
     * other threads about changes in turn.
     */
    private void MonicaGiveTurn() {
        Main.turn.set(Main.turn.get() + 1);
        Main.lock.notifyAll();
    }
}
