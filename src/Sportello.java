public class Sportello {
    private final int NUMCASSIERI;
    private Cassiere[] cassieri;
    private int cassieriOccupati;
    private long tempoAttesa;

    public Sportello(int numCassieri, long tempoAttesa) {
        this.NUMCASSIERI = numCassieri;
        this.cassieri = new Cassiere[NUMCASSIERI];
        this.cassieriOccupati = 0;
        this.tempoAttesa = tempoAttesa;
        for (int i = 0; i < NUMCASSIERI; i++) {
            cassieri[i] = new Cassiere(false);
        }
    }

    public synchronized boolean entra(Cliente cliente) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        while (cassieriOccupati == NUMCASSIERI) {
            wait(tempoAttesa - (System.currentTimeMillis() - startTime));
            return false; // Timeout dopo N secondi
        }
        for (int i = 0; i < NUMCASSIERI; i++) {
            if (!cassieri[i].isOccupato()) {
                cassieri[i].setOccupato(true);
                cliente.setCassiere(cassieri[i]);
                cassieriOccupati++;
                break;
            }
        }
        return true;
    }

    public synchronized void esci(Cliente cliente) {
        if (cliente.getCassiere() != null) {
            cliente.getCassiere().setOccupato(false);
            cliente.setCassiere(null);
            cassieriOccupati--;
            notifyAll();
        }
    }

}
