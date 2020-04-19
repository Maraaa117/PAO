package util;

import objects.Lider;

import java.util.Comparator;

public class SortLider implements Comparator<Lider> {

    @Override
    public int compare(Lider lider, Lider t1) {
        return lider.getNume().toLowerCase().compareTo(t1.getNume().toLowerCase());
    }
}
