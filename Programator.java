package objects;

public class Programator extends Angajat {
    private String specializare;

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public Programator(String nume, String prenume, Integer experienta, Float salariu, String specializare) {
        super(nume, prenume, experienta, salariu);
        this.specializare = specializare;
    }
}
