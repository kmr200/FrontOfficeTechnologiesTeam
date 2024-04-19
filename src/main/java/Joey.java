public class Joey implements Runnable {
    @Override
    public void run() {
        synchronized (Main.lock) {
            System.out.println("Joey: Hey, hey.");
            JoeyGiveTurn(1);

            JoeyTurnWait(4);

            System.out.println("Joey: Yes, I am. As of today, I am officially Joey Tribbiani, actor slash model.");
            JoeyGiveTurn(5);

            JoeyTurnWait(7);

            System.out.println("Joey: You know those posters for the City Free Clinic?");
            JoeyGiveTurn(8);

            JoeyTurnWait(11);

            System.out.println("Joey: No, but I hear lyme disease is open, so... (crosses fingers)");
            JoeyGiveTurn(12);

            JoeyTurnWait(13);

            System.out.println("Joey: Thanks.");
        }
    }

    private void JoeyTurnWait(int turn) {
        while (Main.turn.get() != turn) {
            try {
                Main.lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void JoeyGiveTurn(int turn) {
        Main.turn.set(turn);
        Main.lock.notifyAll();
    }
}
