public class Joey implements Runnable {
    @Override
    public void run() {
        synchronized (Main.lock) {
            System.out.println("Joey: Hey, hey.");
            JoeyGiveTurn(2);

            JoeyTurnWait(5);

            System.out.println("Joey: Yes, I am. As of today, I am officially Joey Tribbiani, actor slash model.");
            JoeyGiveTurn(6);

            JoeyTurnWait(8);

            System.out.println("Joey: You know those posters for the City Free Clinic?");
            JoeyGiveTurn(9);

            JoeyTurnWait(12);

            System.out.println("Joey: No, but I hear lyme disease is open, so... (crosses fingers)");
            JoeyGiveTurn(13);

            JoeyTurnWait(14);

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
