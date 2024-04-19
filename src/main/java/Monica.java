public class Monica implements Runnable{
    @Override
    public void run() {
        synchronized (Main.lock) {
            MonicaWaitTurn(8);

            System.out.println(
                    "Monica: Oh, wow, so you're gonna be one of those \"healthy, healthy, healthy guys\"?"
            );
            MonicaGiveTurn(9);
        }
    }

    private void MonicaWaitTurn(int turn) {
        while (Main.turn.get() != turn) {
            try {
                Main.lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void MonicaGiveTurn(int turn) {
        Main.turn.set(turn);
        Main.lock.notifyAll();
    }
}
