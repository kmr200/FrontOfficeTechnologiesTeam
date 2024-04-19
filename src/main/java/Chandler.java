public class Chandler implements Runnable {
    @Override
    public void run() {
        synchronized (Main.lock) {
            ChandlerWaitTurn(2);

            System.out.println("Chandler: Hey.");
            ChandlerGiveTurn(3);

            ChandlerWaitTurn(4);

            System.out.println("Chandler: And this from the cry-for-help department. Are you wearing makeup?");
            ChandlerGiveTurn(5);

            ChandlerWaitTurn(6);

            System.out.println(
                    "Chandler: That's so funny, 'cause I was thinking you look more like Joey Tribbiani, man slash woman."
            );
            ChandlerGiveTurn(7);

            ChandlerWaitTurn(11);

            System.out.println("Chandler: Do you know which one you're gonna be?");
            ChandlerGiveTurn(12);

            ChandlerWaitTurn(13);

            System.out.println("Chandler: Good luck, man. I hope you get it.");
            ChandlerGiveTurn(14);
        }
    }

    private void ChandlerWaitTurn(int turn) {
        while (Main.turn.get() != turn) {
            try {
                Main.lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void ChandlerGiveTurn(int turn) {
        Main.turn.set(turn);
        Main.lock.notifyAll();
    }
}
