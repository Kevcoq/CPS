package filesprios;

import java.util.Set;

public interface IFilesPrio<T> {

    /* Observators */
    public int size();
    public boolean empty();
    public Set<Integer> activePrios();
    public boolean isActivePrio(int n);
    public int maxPrio();
    public int sizePrio(int n);
    //\pre : sizePrio(i) > 0
    public T getPrio(int n);
    //\pre : size() > 0
    public T get();
    //\pre : activePrios().contains(i) && 0 < k < sizePrio(i)
    public T getElem(int i, int k);

    /* Invariants */
    //\inv : (min) size() == somme des éléments de activePrios()
    //\inv : (min) empty() : (size() == 0)
    //\inv : (min) isActivePrio(i) == \exists i \in activePrios()
    //\inv : (min) maxPrio() == max(activePrios()) \with max(E) := \exists x \in E \\union {0} -> \forall y \in E, x >= y
    //\inv : (min) getPrio(i) == getElem(i, 1)
    //\inv : (min) get() == getPrio(maxPrio())
    //\inv : (utile) \forall i \in activePrios(), sizePrio(i) > 0
    //\inv : (utile) \forall i \not \in activePrios(), sizePrio(i) == 0
    //\inv : (utile) \forall i \in activePrios(), \forall k \in [1..sizePrio(i)], getElem(i, k) != null

    /* Constructors */
    //\post : size() == 0
    public void init();

    /* Operators */
    //\pre : i >= 0 && e != null
    //\post : isActivePrio(i) => activePrios() == activePrios()@pre
    //\post : \not isActivePrio(i) => activePrio() == activePrio()@pre \\union {i}
    //\post : sizePrio(i) == sizePrio(i)@pre + 1
    //\post : \forall j \in activePrios() \{i}, sizePrio(j) == sizePrio(j)@pre
    //\post : getPrio(i) == e
    //\post : \forall k \in [2..sizePrio(i)@pre + 1], getElem(i, k) == getElem(i, k-1)@pre
    //\post : \forall j \in activePrios()@pre \{i}, \forall k \in [1..sizePrio(j)@pre], getElem(j, k) == getElem(j, k)@pre
    public void putPrio(int i, T e);

    //\pre : e != null
    //\def : put(e) == putPrio(e, maxPrio())
    public void put(T e);

    //\pre : sizePrio(i) > 0
    //\post : sizePrio(i)@pre > 1 => activePrios() = activePrios()@pre
    //\post : sizePrio(i)@pre = 1 => activePrios() = activePrios()@pre \{i}
    //\post : sizePrio(i) = sizePrio(i)@pre - 1
    //\post : \forall j \in activePrios()@pre \{i}, sizePrio(j) = sizePrio(j)@pre
    //\post : \forall k \in [1..sizePrio(i)@pre -1], getElem(i,k) = getElem(i,k)@pre
    //\post : \forall i \in activePrios()@pre \{i}, \forall k \in [1..sizePrio(j)@pre], getElem(j,k) = getElem(j,k)@pre
    public void removePrio(int i);

    //\pre : size(i) > 0
    //\def : remove() == removePrio(maxPrio())
    public void remove();

}


