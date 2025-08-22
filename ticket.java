package parkhaus;

// OOP: Die Klasse Ticket kapselt die Eigenschaften eines Parktickets (Kapselung, SRP)
public class ticket {
    private int dauerMinuten; // OOP: Attribut, nur innerhalb der Klasse sichtbar
    private double betrag;

    // Konstruktor (OOP: Initialisierung, SRP)
    public ticket(int dauerMinuten, double minutenPreis) {
        this.dauerMinuten = dauerMinuten;
        this.betrag = dauerMinuten * minutenPreis;
    }

    // Getter (OOP: Kapselung, nur lesender Zugriff)
    public double getBetrag() {
        return betrag;
    }
}
