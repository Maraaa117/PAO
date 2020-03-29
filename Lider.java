package objects;

import java.util.List;

public class Lider extends Angajat {

    private List<Proiect> proiect;

    public Lider(String nume, String prenume, Integer experienta, Float salariu, List<Proiect> proiect) {
        super(nume, prenume, experienta, salariu);
        this.proiect = proiect;
    }

    public List<Proiect> getProiect() {
        return proiect;
    }

    public void setProiect(List<Proiect> proiect) {
        this.proiect = proiect;
    }
}
