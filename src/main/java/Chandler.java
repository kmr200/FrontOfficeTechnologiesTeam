public class Chandler implements Runnable {
    @Override
    public void run() {
        synchronized (Main.lock) {
            ChandlerTurnWait(2);

            System.out.println("Chandler: Hey.");
            ChandlerGiveTurn();

            ChandlerTurnWait(4);

            System.out.println("Chandler: And this from the cry-for-help department. Are you wearing makeup?");
            ChandlerGiveTurn();

            ChandlerTurnWait(6);

            System.out.println(
                    "Chandler: That's so funny, 'cause I was thinking you look more like Joey Tribbiani, man slash woman."
            );
            ChandlerGiveTurn();

            ChandlerTurnWait(11);

            System.out.println("Chandler: Do you know which one you're gonna be?");
            ChandlerGiveTurn();

            ChandlerTurnWait(13);

            System.out.println("Chandler: Good luck, man. I hope you get it.");
            ChandlerGiveTurn();
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
    private void ChandlerTurnWait(int turn) {
        while (Main.turn.get() != turn) {
            try {
                Main.lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * *GiveTurn method has the same functionality across all the runnable thread classes. Method
     * updates the value of the atomicInteger turn contained in the Main class and notifies
     * other threads about changes in turn.
     */
    private void ChandlerGiveTurn() {
        Main.turn.set(Main.turn.get() + 1);
        Main.lock.notifyAll();
    }
}
