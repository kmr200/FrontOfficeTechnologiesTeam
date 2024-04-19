public class Joey implements Runnable {

    @Override
    public void run() {
        synchronized (Main.lock) {
            System.out.println("Joey: Hey, hey.");
            JoeyGiveTurn();

            JoeyTurnWait(5);

            System.out.println("Joey: Yes, I am. As of today, I am officially Joey Tribbiani, actor slash model.");
            JoeyGiveTurn();

            JoeyTurnWait(8);

            System.out.println("Joey: You know those posters for the City Free Clinic?");
            JoeyGiveTurn();

            JoeyTurnWait(12);

            System.out.println("Joey: No, but I hear lyme disease is open, so... (crosses fingers)");
            JoeyGiveTurn();

            JoeyTurnWait(14);

            System.out.println("Joey: Thanks.");
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
    private void JoeyTurnWait(int turn) {
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
    private void JoeyGiveTurn() {
        Main.turn.set(Main.turn.get() + 1);
        Main.lock.notifyAll();
    }
}
