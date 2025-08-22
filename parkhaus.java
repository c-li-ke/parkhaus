package parkhaus;

// OOP: Die Klasse Parkhaus kapselt alle Eigenschaften und Methoden eines Parkhauses (Kapselung, SRP)
public class parkhaus {
    private int gesamtPlaetze;      // OOP: Attribut, nur innerhalb der Klasse sichtbar (Kapselung)
    private int freiePlaetze;
    private double minutenPreis;
    private int autosImParkhaus;

    // Konstruktor (OOP: Initialisierung, SRP)
    public parkhaus(int gesamtPlaetze, int freiePlaetze, double minutenPreis) {
        this.gesamtPlaetze = gesamtPlaetze;
        this.freiePlaetze = freiePlaetze;
        this.minutenPreis = minutenPreis;
        this.autosImParkhaus = gesamtPlaetze - freiePlaetze;
    }

    // Ein Auto fährt ins Parkhaus (OOP: Methode, SRP)
    public boolean autoEinfahren() {
        if (freiePlaetze > 0) {
            freiePlaetze--;
            autosImParkhaus++;
            System.out.println("Ein Auto fährt ins Parkhaus und zieht ein Ticket.");
            return true;
        } else {
            System.out.println("Das Parkhaus ist voll. Kein Platz verfügbar.");
            return false;
        }
    }

    // Ein Auto verlässt das Parkhaus (OOP: Methode, SRP)
    public void autoVerlassen() {
        if (autosImParkhaus <= 0) {
            System.out.println("Es befindet sich kein Auto im Parkhaus.");
            return;
        }
        autosImParkhaus--;
        freiePlaetze++;
        System.out.println("Das Auto verlässt das Parkhaus.");
    }

    // Ein Auto verlässt das Parkhaus nach Bezahlung (OCP: nimmt Ticket entgegen)
    public void verlassen(ticket ticket) {
        autoVerlassen();
    }

    // Ein Auto will ohne Ticket raus (OCP: Methode nimmt Scanner entgegen)
    public void verlassenOhneTicket(java.util.Scanner scanner) {
        if (autosImParkhaus <= 0) {
            System.out.println("Es befindet sich kein Auto im Parkhaus.");
            return;
        }
        System.out.println("Das Auto kann das Parkhaus nicht verlassen, ohne das Ticket zu bezahlen!");
        System.out.print("Wie viele Minuten war das Auto im Parkhaus? ");
        int min = scanner.nextInt();
        scanner.nextLine();
        // OOP: Ticket-Objekt wird erzeugt (Kapselung, SRP)
        ticket ticket = new ticket(min, minutenPreis);
        System.out.println("Ticket wird für " + min + " Minuten bezahlt.");
        System.out.println("Bitte zahlen Sie: CHF " + String.format("%.2f", ticket.getBetrag()));
        // Nach dem Bezahlen: Nur noch Option zu verlassen oder Simulation zu beenden
        boolean leavingMenu = true;
        while (leavingMenu) {
            System.out.println("Was möchten Sie tun?");
            System.out.println("1: Auto das Parkhaus verlassen lassen");
            System.out.println("2: Simulation beenden");
            System.out.print("Ihre Wahl: ");
            int wahl2 = scanner.nextInt();
            scanner.nextLine();
            if (wahl2 == 1) {
                verlassen(ticket);
                leavingMenu = false;
            } else if (wahl2 == 2) {
                System.out.println("Die Simulation ist beendet.");
                System.exit(0);
            } else {
                System.out.println("Ungültige Eingabe.");
            }
        }
    }

    // Anzeige der freien Plätze (OOP: Methode, SRP)
    public void anzeigen() {
        System.out.println("=== Parkhaus-Display ===");
        System.out.println("Freie Plätze: " + freiePlaetze + "/" + gesamtPlaetze);
        System.out.println("Autos im Parkhaus: " + autosImParkhaus);
        System.out.println("==================================");
    }

    // Getter (OOP: Kapselung, nur lesender Zugriff)
    public int getAutosImParkhaus() {
        return autosImParkhaus;
    }

    public double getMinutenPreis() {
        return minutenPreis;
    }
}
