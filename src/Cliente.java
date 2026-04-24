public class Cliente extends Thread {
    private Cassiere cassiere;
    private Sportello sportello;

    public Cliente(String nome, Sportello sportello) {
        super(nome);
        this.sportello = sportello;
        cassiere = null;
    }

    public Cassiere getCassiere() {
        return cassiere;
    }

    public void setCassiere(Cassiere cassiere) {
        this.cassiere = cassiere;
    }

    public void run() {
        try {
            if (!sportello.entra(this)) {
                System.out.println(getName() + " ha lasciato lo sportello per timeout.");
            } else {
                System.out.println(getName() + " è servito dal cassiere.");
                try {
                    Thread.sleep((long) (Math.random() * 2000) + 2000); // Simula il tempo di servizio
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sportello.esci(this);
                System.out.println(getName() + " ha finito e ha lasciato lo sportello.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
