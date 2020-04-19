package objects;

import java.util.List;
import java.util.Set;

public class Echipa {
    private Set<Angajat> membri;

    public Echipa(Set<Angajat> membri) {
        this.membri = membri;
    }

    public Set<Angajat> getMembri() {
        return membri;
    }

    public void setMembri(Set<Angajat> membri) {
        this.membri = membri;
    }

}
