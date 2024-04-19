public class Chandler implements Runnable {
    @Override
    public void run() {
        synchronized (Main.lock) {
            ChandlerWaitTurn(1);

            System.out.println("Chandler: Hey.");
            ChandlerGiveTurn(2);

            ChandlerWaitTurn(3);

            System.out.println("Chandler: And this from the cry-for-help department. Are you wearing makeup?");
            ChandlerGiveTurn(4);

            ChandlerWaitTurn(5);

            System.out.println(
                    "Chandler: That's so funny, 'cause I was thinking you look more like Joey Tribbiani, man slash woman."
            );
            ChandlerGiveTurn(6);

            ChandlerWaitTurn(10);

            System.out.println("Chandler: Do you know which one you're gonna be?");
            ChandlerGiveTurn(11);

            ChandlerWaitTurn(12);

            System.out.println("Chandler: Good luck, man. I hope you get it.");
            ChandlerGiveTurn(13);
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
