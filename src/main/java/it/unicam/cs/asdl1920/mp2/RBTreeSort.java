/**
 * 
 */
package it.unicam.cs.asdl1920.mp2;

import java.util.List;

/**
 * Algoritmo di ordinamento che usa un albero Rosso-Nero con molteplicità per
 * ordinare una lista di elementi.
 * 
 * @author Luca Tesei
 *
 */
public class RBTreeSort<E extends Comparable<E>>
        implements SortingAlgorithm<E> {

    public SortingAlgorithmResult<E> sort(List<E> l) {
        // Creo un albero Rosso-Nero vuoto
        RBTree<E> t = new RBTree<E>();
        // inizializzo il numero di confronti
        int countCompare = 0;
        // inserisco tutti gli elementi della lista nell'albero Rosso-Nero
        // accumulando il numero di confronti effettuati
        for (E el : l)
            countCompare += t.insert(el);
        // restituisco come risultato la lista ottenuta come visita in-order
        // dell'albero e il numero di confronti accumulato
        SortingAlgorithmResult<E> r = new SortingAlgorithmResult<E>(
                t.inOrderVisit(), countCompare);
        return r;
    }

    public String getName() {
        return "RBTreeSort";
    }

}
