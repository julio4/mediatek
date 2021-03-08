package persistance.modèle.filters;

import persistance.modèle.PDocument;

import java.util.Comparator;

public class IdSorter implements Comparator<PDocument> {
    @Override
    public int compare(PDocument o1, PDocument o2) {
        return o1.getId() - o2.getId();
    }
}
