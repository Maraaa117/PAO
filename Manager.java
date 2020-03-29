package objects;

import java.util.List;
import java.util.Set;

public class Manager extends Angajat {
    private List<Lider> subalterni;

    public Manager(String nume, String prenume, Integer experienta, Float salariu, List<Lider> subalterni) {
        super(nume, prenume, experienta, salariu);
        this.subalterni = subalterni;
    }

    public List<Lider> getSubalterni() {
        return subalterni;
    }

    public void setSubalterni(List<Lider> subalterni) {
        this.subalterni = subalterni;
    }
}
