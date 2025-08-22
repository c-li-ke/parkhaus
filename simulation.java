package parkhaus;

import java.util.Scanner;
import java.util.Random;

// Die Simulation steuert das Parkhaus (OOP: Steuernde Klasse, SRP aus SOLID)
public class simulation {

    // Startet die Simulation (OOP: Methode, SRP)
    public void start() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int gesamtPlaetze = 5;
        int freiePlaetze = random.nextInt(gesamtPlaetze + 1);
        // OOP: Parkhaus-Objekt wird erzeugt (Kapselung, SRP)
        parkhaus parkhaus = new parkhaus(gesamtPlaetze, freiePlaetze, 0.25);

        System.out.println("Willkommen im Parkhaus!");
        boolean running = true;
        while (running) {
            parkhaus.anzeigen();
            System.out.println("Was möchten Sie tun?");
            System.out.println("1: Auto ins Parkhaus fahren");
            System.out.println("2: Ticket für ein Auto bezahlen");
            System.out.println("3: Auto das Parkhaus verlassen lassen");
            System.out.println("4: Simulation beenden");
            System.out.print("Ihre Wahl: ");
            int wahl = scanner.nextInt();
            scanner.nextLine();

            switch (wahl) {
                case 1:
                    parkhaus.autoEinfahren();
                    break;
                case 2:
                    if (parkhaus.getAutosImParkhaus() <= 0) {
                        System.out.println("Es befindet sich kein Auto im Parkhaus.");
                        break;
                    }
                    System.out.print("Wie viele Minuten war das Auto im Parkhaus? ");
                    int min = scanner.nextInt();
                    scanner.nextLine();
                    // OOP: Ticket-Objekt wird erzeugt (Kapselung, SRP)
                    ticket ticket = new ticket(min, parkhaus.getMinutenPreis());
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
                            parkhaus.verlassen(ticket); // OCP: Methode nimmt Ticket entgegen
                            leavingMenu = false;
                        } else if (wahl2 == 2) {
                            System.out.println("Die Simulation ist beendet.");
                            System.exit(0);
                        } else {
                            System.out.println("Ungültige Eingabe.");
                        }
                    }
                    break;
                case 3:
                    parkhaus.verlassenOhneTicket(scanner);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
        scanner.close();
        System.out.println("Die Simulation ist beendet.");
    }

    // Einstiegspunkt der Applikation (OOP: statische Methode, SRP)
    public static void main(String[] args) {
        new simulation().start();
    }
}
