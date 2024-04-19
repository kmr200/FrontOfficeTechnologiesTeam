public class Phoebe implements Runnable {
    @Override
    public void run() {
        synchronized (Main.lock) {
            PhoebeWaitTurn(3);

            System.out.println("Phoebe: Hey.");
            PhoebeGiveTurn(4);

            PhoebeWaitTurn(7);
            System.out.println("Phoebe: What were you modeling for?");
            PhoebeGiveTurn(8);

            PhoebeWaitTurn(10);
            System.out.println("Phoebe: You know, the asthma guy was really cute.");
            PhoebeGiveTurn(11);
        }
    }

    private void PhoebeWaitTurn(int turn) {
        while (Main.turn.get() != turn) {
            try {
                Main.lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void PhoebeGiveTurn(int turn) {
        Main.turn.set(turn);
        Main.lock.notifyAll();
    }
}
