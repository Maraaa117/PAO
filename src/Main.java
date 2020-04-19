import objects.*;
import util.ServiciuDate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static void afisare(Scanner in, Firma firma) {
        System.out.println("Selecteaza optiunea dorita:");
        System.out.println("1. Adauga Lider");
        System.out.println("2. Adauga Proiect");
        System.out.println("3. Adauga membri");
        System.out.println("4. Schimba Manager");
        System.out.println("5. Mareste Salariul Liderului X");
        System.out.println("6. Micsoreaza Salariul Liderului X");
        System.out.println("7. Vanzatorii cu comisionul X");
        System.out.println("8. Oamenii din proiectul X care au mai mult de Y ani");
        System.out.println("9. Afiseaza specializarea programatorului din proiectul X");
        System.out.println("10. Schimba specializarea programatorului din proiectul X");
        System.out.println("11. Afiseaza managerul");
        System.out.println("12. Afiseaza lideri");
        System.out.println("13. Schimba comisionul vanzatorului din proiectul X");

        switch (in.nextInt()) {
            case 1:
                firma.adaugaLider();
                ServiciuDate.getInstance().scrieAudit("Lider adaugat");
                break;
            case 2:
                firma.adaugaProiect();
                break;
            case 3:
                firma.adaugaMembri();
                break;
            case 4:
                firma.schimbaManager();
                break;
            case 5:
                firma.maresteSalariu();
                ServiciuDate.getInstance().scrieAudit("Salariu Marit");
                break;
            case 6:
                firma.micsoreazaSalariu();
                break;
            case 7:
                firma.comisionX();
                break;
            case 8:
                firma.maiMultdeYani();
                break;
            case 9:
                firma.specializareProgramator();
                break;
            case 10:
                firma.schimbaSpecializare();
                break;
            case 11:
                firma.afiseazaManager();
                break;
            case 12:
                firma.afiseazaLideri();
                ServiciuDate.getInstance().scrieAudit("Lideri Afisati");
                break;
            case 13:
                firma.schimbaComision();
                break;
        }

        ServiciuDate.getInstance().scrieData(firma.manager);

        afisare(in, firma);

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Manager manager = new Manager("Popescu", "Andrei", 10, 4500f, new ArrayList<>());
        manager.getSubalterni().add(new Lider("Ionescu", "Marius", 8, 3000f, new ArrayList<>()));
        manager.getSubalterni().get(0).getProiect().add(new Proiect(new Echipa(new HashSet<>())));
        manager.getSubalterni().get(0).getProiect().get(0).getEchipa().getMembri().add(new Designer("Crisan", "Marcela", 5, 2000f, "Mobile"));
        manager.getSubalterni().get(0).getProiect().get(0).getEchipa().getMembri().add(new Programator("Anton", "Valeriu", 7, 2800f, "FrontEnd"));
        manager.getSubalterni().get(0).getProiect().get(0).getEchipa().getMembri().add(new Vanzator("Diaconu", "Florin", 4, 1850f, 15));

        manager.getSubalterni().add(new Lider("Blagescu", "Marian", 9, 3300f, new ArrayList<>()));
        manager.getSubalterni().get(1).getProiect().add(new Proiect(new Echipa(new HashSet<>())));
        manager.getSubalterni().get(1).getProiect().get(0).getEchipa().getMembri().add(new Designer("Abel", "Casian", 7, 2900f, "Web"));
        manager.getSubalterni().get(1).getProiect().get(0).getEchipa().getMembri().add(new Programator("Antel", "Valentina", 7, 2800f, "BackEnd"));
        manager.getSubalterni().get(1).getProiect().get(0).getEchipa().getMembri().add(new Vanzator("Dias", "Alina", 2, 1500f, 80));

        manager.getSubalterni().get(1).getProiect().add(new Proiect(new Echipa(new HashSet<>())));
        manager.getSubalterni().get(1).getProiect().get(1).getEchipa().getMembri().add(new Designer("Asec", "Crina", 12, 5000f, "Web"));
        manager.getSubalterni().get(1).getProiect().get(1).getEchipa().getMembri().add(new Programator("Ocis", "Andreea", 9, 3300f, "IA"));
        manager.getSubalterni().get(1).getProiect().get(1).getEchipa().getMembri().add(new Vanzator("Nemus", "Erika", 5, 2700f, 60));


        Firma firma = new Firma(manager);
        manager.setSubalterni(ServiciuDate.getInstance().citesteLideri());
        ServiciuDate.getInstance().citesteProiectele(manager);

        afisare(in, firma);


    }
}
