public class App {
    public static void main(String[] args) throws Exception {
        int numCassieri = 2; // Numero di cassieri disponibili
        long tempoAttesa = 5000; // Tempo di attesa in millisecondi (5 secondi)
        Sportello sportello = new Sportello(numCassieri, tempoAttesa);
        Cliente[] clienti = new Cliente[10];

        // Creazione di 10 clienti
        for (int i = 0; i < clienti.length; i++) {
            clienti[i] = new Cliente("Cliente " + (i + 1), sportello);
            clienti[i].start();
        }

        for (Cliente c : clienti) {
            c.join();
        }

        System.out.println("Terminato");


    }
}
