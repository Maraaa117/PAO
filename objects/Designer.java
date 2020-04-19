package objects;

public class Designer extends Angajat {
    private String specializare;

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public Designer(String nume, String prenume, Integer experienta, Float salariu, String specializare) {
        super(nume, prenume, experienta, salariu);
        this.specializare = specializare;
    }

    @Override
    public String toString() {
        return getNume() + "," + getPrenume() + "," + getExperienta() + "," + getSalariu() + "," + "des" + "," + getSpecializare();
    }
}
