import objects.*;

import util.SortLider;

import java.util.*;

public class Firma {
    Scanner in = new Scanner(System.in);
    Manager manager;

    public Firma(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void adaugaLider() {
        System.out.println("Nume: ");
        String nume = in.nextLine();
        System.out.println("Prenume: ");
        String prenume = in.nextLine();
        System.out.println("Experienta: ");
        Integer experienta = in.nextInt();
        System.out.println("Salariu: ");
        Float salariu = in.nextFloat();
        in.nextLine();
        Lider liderNou = new Lider(nume, prenume, experienta, salariu, new ArrayList<>());
        manager.getSubalterni().add(liderNou);
    }

    public void adaugaProiect() {
        System.out.println("Numarul liderului:");
        Integer nr = in.nextInt();
        in.nextLine();
        if (nr > manager.getSubalterni().size() - 1)
            System.out.println("Nu exista liderul cu indexul cautat.");
        else {
            manager.getSubalterni().get(nr).getProiect().add(new Proiect(new Echipa(new HashSet<>())));
        }
    }

    public void adaugaAngajat(int nr, int x) {
        System.out.println("Selecteaza tipul angajatului: ");
        System.out.println("1. Programator");
        System.out.println("2. Designer");
        System.out.println("3. Vanzator");

        int numar = in.nextInt();
        in.nextLine();

        System.out.println("Nume: ");
        String nume = in.nextLine();
        System.out.println("Prenume: ");
        String prenume = in.nextLine();
        System.out.println("Experienta: ");
        Integer experienta = in.nextInt();
        System.out.println("Salariu: ");
        Float salariu = in.nextFloat();
        in.nextLine();

        switch (numar) {
            case 1:
                System.out.println("Specializare: ");
                String specializare = in.nextLine();
                Programator prog = new Programator(nume, prenume, experienta, salariu, specializare);
                manager.getSubalterni().get(nr).getProiect().get(x).getEchipa().getMembri().add(prog);
                break;
            case 2:
                System.out.println("Specializare: ");
                specializare = in.nextLine();
                Designer desig = new Designer(nume, prenume, experienta, salariu, specializare);
                manager.getSubalterni().get(nr).getProiect().get(x).getEchipa().getMembri().add(desig);
                break;
            case 3:
                System.out.println("Comision: ");
                Integer comision = in.nextInt();
                Vanzator vanz = new Vanzator(nume, prenume, experienta, salariu, comision);
                manager.getSubalterni().get(nr).getProiect().get(x).getEchipa().getMembri().add(vanz);
                break;
        }
    }

    public void adaugaMembri() {
        System.out.println("Numarul liderului: ");
        Integer nr = in.nextInt();
        if (nr > manager.getSubalterni().size() - 1)
            System.out.println("Nu exista liderul cu indexul cautat.");
        else {
            System.out.println("Numarul proiectului: ");
            Integer x = in.nextInt();
            if (x > manager.getSubalterni().get(nr).getProiect().size() - 1)
                System.out.println("Nu exista proiectul cu indexul cautat.");
            else {
                adaugaAngajat(nr, x);
                System.out.println("Doresti sa mai adaugi un angajat? (1 pentru da)");
                if (in.nextInt() == 1) {
                    adaugaAngajat(nr, x);
                }

            }
        }
    }

    public void schimbaManager() {
        System.out.println("Nume: ");
        String nume = in.nextLine();
        manager.setNume(nume);
        System.out.println("Prenume: ");
        String prenume = in.nextLine();
        manager.setPrenume(prenume);
        System.out.println("Experienta: ");
        Integer experienta = in.nextInt();
        manager.setExperienta(experienta);
        System.out.println("Salariu: ");
        Float salariu = in.nextFloat();
        manager.setSalariu(salariu);

    }

    public void maresteSalariu() {

        System.out.println("Indexul Liderului:");
        int x = in.nextInt();
        System.out.println("Salariul nou:");
        Float salariu = in.nextFloat();
        if (x > manager.getSubalterni().size() - 1)
            System.out.println("Nu exista liderul cu indexul cautat.");
        else if (manager.getSubalterni().get(x).getSalariu() < salariu)
            manager.getSubalterni().get(x).setSalariu(salariu);
        else
            System.out.println("Salariul actual este mai mare.");

    }

    public void micsoreazaSalariu() {
        System.out.println("Indexul Liderului:");
        int x = in.nextInt();
        System.out.println("Salariul nou:");
        Float salariu = in.nextFloat();
        if (x > manager.getSubalterni().size() - 1)
            System.out.println("Nu exista liderul cu indexul cautat.");
        else if (manager.getSubalterni().get(x).getSalariu() > salariu)
            manager.getSubalterni().get(x).setSalariu(salariu);
        else
            System.out.println("Salariul actual este mai mic.");

    }

    public void afiseazaLideri() {
        manager.getSubalterni().sort(new SortLider());
        for (Lider lider : manager.getSubalterni()) {
            System.out.println(lider.getNume());
        }
    }

    public void maiMultdeYani() {
        System.out.println("Indexul liderului: ");
        int z = in.nextInt();
        if (z > manager.getSubalterni().size() - 1)
            System.out.println("Nu exista liderul cautat.");
        else {
            System.out.println("Indexul proiectului: ");
            int x = in.nextInt();
            if (x > manager.getSubalterni().get(z).getProiect().size() - 1)
                System.out.println("Nu exista proiectul cu indexul cautat.");
            else {
                System.out.println("Experienta minima:");
                int y = in.nextInt();
                int k = 0;
                for (Angajat angajat : manager.getSubalterni().get(z).getProiect().get(x).getEchipa().getMembri())
                    if (angajat.getExperienta() >= y) {
                        System.out.println(angajat.getNume());
                        k = k + 1;
                    }
                if (k == 0)
                    System.out.println("Nu exista niciun angajat.");
            }
        }
    }

    public void comisionX() {
        System.out.println("Comisionul cerut: ");
        int x = in.nextInt();
        int k = 0;
        for (Lider lider : manager.getSubalterni())
            for (Proiect proiect : lider.getProiect())
                for (Angajat angajat : proiect.getEchipa().getMembri())
                    if (angajat instanceof Vanzator && ((Vanzator) angajat).getComision() == x) {
                        System.out.println(angajat.getNume());
                        k = k + 1;
                    }
        if (k == 0)
            System.out.println("Nu exista niciun angajat.");
    }

    public void afiseazaManager() {
        System.out.println(manager.getNume());
    }

    public void specializareProgramator() {
        System.out.println("Indexul liderului: ");
        int z = in.nextInt();
        if (z > manager.getSubalterni().size() - 1)
            System.out.println("Nu exista liderul cautat.");
        else {
            System.out.println("Indexul proiectului: ");
            int x = in.nextInt();
            if (x > manager.getSubalterni().get(z).getProiect().size() - 1)
                System.out.println("Nu exista proiectul cu indexul cautat.");
            else {
                for (Angajat angajat : manager.getSubalterni().get(z).getProiect().get(x).getEchipa().getMembri())
                    if (angajat instanceof Programator)
                        System.out.println(((Programator) angajat).getSpecializare());
            }
        }
    }

    public void schimbaSpecializare() {
        System.out.println("Indexul liderului: ");
        int z = in.nextInt();
        if (z > manager.getSubalterni().size() - 1)
            System.out.println("Nu exista liderul cautat.");
        else {
            System.out.println("Indexul proiectului: ");
            int x = in.nextInt();
            if (x > manager.getSubalterni().get(z).getProiect().size() - 1)
                System.out.println("Nu exista proiectul cu indexul cautat.");
            else {
                for (Angajat angajat : manager.getSubalterni().get(z).getProiect().get(x).getEchipa().getMembri())
                    if (angajat instanceof Programator) {
                        System.out.println("Scrie specializare:");
                        String y = in.nextLine();
                        ((Programator) angajat).setSpecializare(y);
                    }
            }
        }
    }

    public void schimbaComision() {
        System.out.println("Indexul liderului: ");
        int z = in.nextInt();
        if (z > manager.getSubalterni().size() - 1)
            System.out.println("Nu exista liderul cautat.");
        else {
            System.out.println("Indexul proiectului: ");
            int x = in.nextInt();
            if (x > manager.getSubalterni().get(z).getProiect().size() - 1)
                System.out.println("Nu exista proiectul cu indexul cautat.");
            else {
                for (Angajat angajat : manager.getSubalterni().get(z).getProiect().get(x).getEchipa().getMembri())
                    if (angajat instanceof Vanzator) {
                        System.out.println("Comision nou:");
                        int y = in.nextInt();
                        ((Vanzator) angajat).setComision(y);
                    }
            }
        }
    }
}
