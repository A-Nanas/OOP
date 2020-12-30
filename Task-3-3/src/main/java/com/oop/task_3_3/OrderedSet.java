package com.oop.task_3_3;

import java.util.*;

public class OrderedSet<T> {
    private final List<SetElement> setElements = new ArrayList<>();
    private final Map<T, Integer> aliasToId = new HashMap<>();

    private enum Colors {
        WHITE,
        GRAY,
        BLACK
    }

    private class SetElement {
        private final T name;
        private final List<Integer> relations = new ArrayList<>();
        private Colors color;
        private int depth;
        private int parentID;
        SetElement(T elem) {
            name = elem;
        }
        public void addNewRelation(int id) {
            this.relations.add(id);
        }
    }

    OrderedSet() {}

    OrderedSet(T[] elements) {
        for (T elem : elements) {
            if (aliasToId.containsKey(elem)) {
                throw new IllegalArgumentException("You can't have two identical aliases");
            }
            setElements.add(new SetElement(elem));
            aliasToId.put(elem, setElements.size() - 1);
        }
    }

    public void addNewRelation(T lesser, T bigger) {
        int lID = aliasToId.get(lesser);
        int bID = aliasToId.get(bigger);

        SetElement lesserElem = setElements.get(lID);
        lesserElem.addNewRelation(bID);
        checkStateSanity();
    }

    public List<T> findMaxElements() {
        List<T> res = new ArrayList<>();
        for (SetElement elem : setElements) {
            if (elem.parentID == -1) {
                res.add(elem.name);
            }
        }
        return res;
    }

    public T[] getTopologicalSorting() {
        int setSize = setElements.size();

        T[] res = (T[]) new Object[setSize];
        for (SetElement elem : setElements) {
            res[setSize - elem.depth - 1] = elem.name;
        }
        return res;
    }

    private int time = 0;

    private void checkStateSanity() {
        time = 0;
        for (SetElement elem : setElements) {
            elem.color = Colors.WHITE;
            elem.parentID = -1;
        }
        for (int i = 0; i < setElements.size(); i++) {
            SetElement elem = setElements.get(i);
            if (elem.color == Colors.WHITE) {
                dfs(i, -1);
            }
        }
    }

    private void dfs(int vertex, int parent) {
        SetElement curElement = setElements.get(vertex);
        curElement.color = Colors.GRAY;
        curElement.parentID = parent;

        for (int childID : curElement.relations) {
            SetElement childElem = setElements.get(childID);
            if (childElem.color == Colors.BLACK) {
                childElem.parentID = vertex;
            }
            if (childElem.color == Colors.GRAY) {
                throw new IllegalStateException("Transitivity failure: there is a cycle");
            }
            if (childElem.color == Colors.WHITE) {
                dfs(childID, vertex);
            }
        }

        curElement.color = Colors.BLACK;
        curElement.depth = time++;
    }
}