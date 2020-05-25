package objects;

public class Angajat {
    private String nume;
    private String prenume;
    private Integer experienta;
    private Float salariu;

    public Angajat() {

    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Integer getExperienta() {
        return experienta;
    }

    public void setExperienta(Integer experienta) {
        this.experienta = experienta;
    }

    public Float getSalariu() {
        return salariu;
    }

    public void setSalariu(Float salariu) {
        this.salariu = salariu;
    }

    public Angajat(String nume, String prenume, Integer experienta, Float salariu) {
        this.nume = nume;
        this.prenume = prenume;
        this.experienta = experienta;
        this.salariu = salariu;
    }
}
