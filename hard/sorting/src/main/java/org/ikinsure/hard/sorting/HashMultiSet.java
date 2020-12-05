package org.ikinsure.hard.sorting;

import java.util.*;

public class HashMultiSet<E> implements MultiSet<E> {

    private Map<E, Integer> map;

    public HashMultiSet(List<E> list) {
        this.map = new LinkedHashMap<>();
        list.forEach(this::add);
    }

    @Override
    public void add(E elem) {
        map.putIfAbsent(elem, 0);
        map.compute(elem, (a, v) -> ++v);
    }

    @Override
    public void remove(E elem) {
        map.computeIfPresent(elem, (a, v) -> --v);
        if (map.get(elem) == null || map.get(elem) < 1) {
            map.remove(elem);
        }
    }

    /**
     * for project purpose, TODO: need to make more generic
     * @param comparator for comparing then keys
     */
    public void sort(Comparator<E> comparator) {
        List<Map.Entry<E, Integer>> list = new ArrayList<>(map.entrySet());
        Comparator<Map.Entry<E, Integer>> valComp = Map.Entry.comparingByValue();
        Comparator<Map.Entry<E, Integer>> keyComp = Map.Entry.comparingByKey(comparator);
        list.sort(valComp.thenComparing(keyComp));
        Map<E, Integer> result = new LinkedHashMap<>();
        list.forEach(e -> result.put(e.getKey(), e.getValue()));
        this.map = result;
    }

    @Override
    public void union(MultiSet<E> other) {
        other.toSet().forEach(e -> map.put(e, Math.max(other.getMultiplicity(e), map.getOrDefault(e, 1))));
    }

    @Override
    public void intersect(MultiSet<E> other) {
        Set<E> uniq = other.toSet();
        uniq.retainAll(map.keySet());
        Map<E, Integer> result = new HashMap<>();
        uniq.forEach(e -> result.put(e, Math.min(other.getMultiplicity(e), map.getOrDefault(e, 0))));
        map = result;
    }

    public Map<E, Integer> getMap() {
        return map;
    }

    @Override
    public int getMultiplicity(E elem) {
        return map.getOrDefault(elem, 0);
    }

    @Override
    public boolean contains(E elem) {
        return map.containsKey(elem);
    }

    @Override
    public int numberOfUniqueElements() {
        return map.size();
    }

    @Override
    public int size() {
        return map.values().stream().reduce(0, Integer::sum);
    }

    @Override
    public Set<E> toSet() {
        return new HashSet<>(map.keySet());
    }
}
