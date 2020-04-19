package util;

import objects.*;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class ServiciuDate {
    private static ServiciuDate INSTANTA = null;
    File auditCSV = new File("audit.csv");
    File lideriCSV = new File("lideri.csv");
    File proiecteCSV = new File("proiecte.csv");
    File echipeCSV = new File("echipe.csv");
    File angajatiCSV = new File("angajati.csv");

    private ServiciuDate() {

    }

    private void afisareEroare() {
        System.out.println("Eroare");
    }

    public static ServiciuDate getInstance() {
        if (INSTANTA == null)
            INSTANTA = new ServiciuDate();

        return INSTANTA;
    }

    public List<Lider> citesteLideri() {
        List<Lider> lista = new ArrayList();
        BufferedReader br = null;
        String linie = "";
        String virgulaCSV = ",";

        try {
            br = new BufferedReader(new FileReader(lideriCSV));
            while ((linie = br.readLine()) != null) {
                String[] randCitit = linie.split(virgulaCSV);
                lista.add(new Lider(randCitit[0], randCitit[1], Integer.valueOf(randCitit[2]), Float.valueOf(randCitit[3]), new ArrayList<Proiect>()));
            }
        } catch (Exception e) {
            afisareEroare();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    afisareEroare();
                }
            }
        }

        return lista;
    }

    public void citesteProiectele(Manager manager) {
        BufferedReader br = null;
        String linie = "";
        String virgulaCSV = ",";

        try {
            br = new BufferedReader(new FileReader(proiecteCSV));
            while ((linie = br.readLine()) != null) {
                String[] randCitit = linie.split(virgulaCSV);
                manager.getSubalterni().get(Integer.parseInt(randCitit[0])).getProiect().add(new Proiect(citesteEchipele(Integer.parseInt(randCitit[0]))));
            }
        } catch (Exception e) {
            afisareEroare();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    afisareEroare();
                }
            }
        }
    }

    private Echipa citesteEchipele(int index) {
        List<Echipa> echipe = new ArrayList<>();
        BufferedReader br = null;
        String linie = "";
        String virgulaCSV = ",";

        try {
            br = new BufferedReader(new FileReader(echipeCSV));
            while ((linie = br.readLine()) != null) {
                String[] randCitit = linie.split(virgulaCSV);
                if (Integer.parseInt(randCitit[0]) == index) {
                    echipe.add(new Echipa(citesteAngajatii(index)));
                }
            }
        } catch (Exception e) {
            afisareEroare();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    afisareEroare();
                }
            }
        }
        return echipe.get(0);
    }

    private Set<Angajat> citesteAngajatii(int index) {
        Set<Angajat> angajati = new HashSet<>();
        BufferedReader br = null;
        String linie = "";
        String virgulaCSV = ",";

        try {
            br = new BufferedReader(new FileReader(angajatiCSV));
            while ((linie = br.readLine()) != null) {
                String[] randCitit = linie.split(virgulaCSV);
                if (Integer.parseInt(randCitit[0]) == index) {
                    switch (randCitit[5]) {
                        case "des":
                            angajati.add(new Designer(randCitit[1], randCitit[2], Integer.parseInt(randCitit[3]), Float.parseFloat(randCitit[4]), randCitit[6]));
                            break;
                        case "pro":
                            angajati.add(new Programator(randCitit[1], randCitit[2], Integer.parseInt(randCitit[3]), Float.parseFloat(randCitit[4]), randCitit[6]));
                            break;
                        case "van":
                            angajati.add(new Vanzator(randCitit[1], randCitit[2], Integer.parseInt(randCitit[3]), Float.parseFloat(randCitit[4]), Integer.parseInt(randCitit[6])));
                            break;
                    }
                }
            }
        } catch (Exception e) {
            afisareEroare();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    afisareEroare();
                }
            }
        }
        return angajati;
    }

    public void scrieLideri(List<Lider> lideri) {
        try {
            FileWriter fileWriter = new FileWriter(lideriCSV, false);
            PrintWriter pw = new PrintWriter(fileWriter);
            lideri.forEach(pw::println);
            pw.close();
        } catch (IOException e) {
            afisareEroare();
        }
    }

    public void scrieData(Manager manager) {
        resertCSV();
        try {
            FileWriter fileWriter = new FileWriter(lideriCSV, true);
            PrintWriter pw = new PrintWriter(fileWriter);
            for (int i = 0; i < manager.getSubalterni().size(); i++) {
                pw.println(manager.getSubalterni().get(i).toString() + "," + i);
                scrieProiecte(i, manager.getSubalterni().get(i).getProiect());
            }
            pw.close();
        } catch (IOException e) {
            afisareEroare();
        }
    }

    private void scrieProiecte(int index, List<Proiect> proiects) {
        try {
            FileWriter fileWriter = new FileWriter(proiecteCSV, true);
            PrintWriter pw = new PrintWriter(fileWriter);
            for (int i = 0; i < proiects.size(); i++) {
                pw.println(index + "," + i);
                scrieEchipe(index, i, proiects.get(i).getEchipa());
            }
            pw.close();
        } catch (IOException e) {
            afisareEroare();
        }
    }

    private void scrieEchipe(int indexLider, int index, Echipa echipa) {
        try {
            FileWriter fileWriter = new FileWriter(echipeCSV, true);
            PrintWriter pw = new PrintWriter(fileWriter);
            pw.println(index + "," + index);
            scrieAngajatii(indexLider, index, echipa.getMembri());
            pw.close();
        } catch (IOException e) {
            afisareEroare();
        }
    }

    private void scrieAngajatii(int indexLider, int index, Set<Angajat> angajati) {
        try {
            FileWriter fileWriter = new FileWriter(angajatiCSV, true);
            PrintWriter pw = new PrintWriter(fileWriter);
            for (Angajat angajat : angajati) {
                if (angajat instanceof Programator)
                    pw.println(indexLider + "," + index + "," + ((Programator)angajat).toString());
                else if (angajat instanceof Designer)
                    pw.println(indexLider + "," + index + "," + ((Designer)angajat).toString());
                else
                    pw.println(indexLider + "," + index + "," + ((Vanzator)angajat).toString());

            }
            pw.close();
        } catch (IOException e) {
            afisareEroare();
        }
    }

    private void resertCSV() {
        angajatiCSV.delete();
        echipeCSV.delete();
        proiecteCSV.delete();
        lideriCSV.delete();
    }

    public String convertToCSV(String[] data) {
        return String.join(",", data);
    }

    public void scrieAudit(String actiune) {
        List<String[]> afisare = new ArrayList<>();
        afisare.add(new String[]{actiune, new Timestamp(new Date().getTime()).toLocalDateTime().toString()});

        try {
            FileWriter fileWriter = new FileWriter(auditCSV, true);
            PrintWriter pw = new PrintWriter(fileWriter);
            afisare.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
            pw.close();
        } catch (IOException e) {
            afisareEroare();
        }
    }
}
