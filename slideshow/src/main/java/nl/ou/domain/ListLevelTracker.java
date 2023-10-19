package nl.ou.domain;

import java.util.Stack;

public class ListLevelTracker {
    private final Stack<Integer> listLevels;

    public ListLevelTracker() {
        this.listLevels = new Stack<>();
        this.listLevels.push(0);
    }

    public int getCurrentListLevel() {
        return listLevels.peek();
    }

    public void incListLevel() {
        listLevels.push(listLevels.pop() + 1);
    }

    public void decListLevel() {
        listLevels.push(listLevels.pop() - 1);
    }

    public void saveAndResetListLevel() {
        listLevels.push(0);
    }

    public void restoreListLevel() {
        listLevels.pop();
    }
}
