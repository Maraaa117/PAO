package objects;

public class Vanzator extends Angajat {
    private Integer comision;

    public Integer getComision() {
        return comision;
    }

    public void setComision(Integer comision) {
        this.comision = comision;
    }

    public Vanzator(String nume, String prenume, Integer experienta, Float salariu, Integer comision) {
        super(nume, prenume, experienta, salariu);
        this.comision = comision;
    }
}
