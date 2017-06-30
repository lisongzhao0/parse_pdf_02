package com.happy.gene.pdf.generate.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaolisong on 28/06/2017.
 */
public class Group implements ICloneable {

    private int minR=0, maxR=0, minC=0, maxC=0;
    private List<GroupItem> items;

    public List<GroupItem> getItems() {
        return this.items;
    }
    public void setItems(List<GroupItem> items) {
        this.items = items;
        sort();
    }

    public void addItem(GroupItem item) {
        if (null==item) { return; }
        if (null==this.items) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
        sort();
    }
    public void addItem(List<GroupItem> items) {
        if (null==items || items.isEmpty()) { return; }
        if (null==items) {
            this.items = new ArrayList<>();
        }
        for (GroupItem i : items) {
            this.items.add(i);
        }
        sort();

    }

    public boolean removeItem(GroupItem item) {
        if (null==item)  { return true; }
        if (null==items) { return true; }
        boolean removed = items.remove(item);
        sort();
        return removed;
    }
    public GroupItem removeItem(int item) {
        if (null==items) {
            return null;
        }
        GroupItem removed = items.remove(item);
        sort();
        return removed;
    }

    public void sort() {
        if (null==this.items || this.items.isEmpty()) { return; }

        int minRow = Integer.MAX_VALUE; int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE; int maxCol = Integer.MIN_VALUE;

        String[]    keys = new String[this.items.size()];
        GroupItem[] vals = new GroupItem[this.items.size()];
        for (int idx = 0; idx < this.items.size(); idx ++) {
            GroupItem item = this.items.get(idx);
            int row = item.getRow();
            int col = item.getCol();
            minRow = minRow > row ? row : minRow;
            if (minRow > row) { minRow = row; }
            maxRow = maxRow < row ? row : maxRow;
            if (maxRow < row) { maxRow = row; }
            minCol = minCol > col ? col : minCol;
            if (minCol > col) { minCol = col; }
            maxCol = maxCol > col ? col : maxCol;
            if (maxCol < col) { maxCol = col; }

            keys[idx] = row+"_"+col;
            vals[idx] = item;
        }

        this.minR = minRow; this.maxR = maxRow;
        this.minC = minCol; this.maxC = maxCol;

        List<GroupItem> sorted = new ArrayList<>();
        for (int r = minRow; r <= maxRow; r ++) {
            for (int c = minCol; c <= maxCol; c ++) {
                String tmpKey = r+"_"+c;
                for (int idx = 0; idx < keys.length; idx ++) {
                    String key = keys[idx];
                    if (null == vals[idx]) { continue; }
                    if (!tmpKey.equals(key)) { continue; }
                    sorted.add(vals[idx]);
                }
            }
        }

        this.items = sorted;
    }

    public int getMinR() {
        return minR;
    }
    public void setMinR(int minR) {
        this.minR = minR;
    }

    public int getMaxR() {
        return maxR;
    }
    public void setMaxR(int maxR) {
        this.maxR = maxR;
    }

    public int getMinC() {
        return minC;
    }
    public void setMinC(int minC) {
        this.minC = minC;
    }

    public int getMaxC() {
        return maxC;
    }
    public void setMaxC(int maxC) {
        this.maxC = maxC;
    }

    public void clone(Object dest) {
        if (null==dest) { return; }

        Group group = (Group) dest;
        group.minR = this.minR;
        group.maxR = this.maxR;
        group.minC = this.minC;
        group.maxC = this.maxC;

        if (null!=items) {
            group.items = new ArrayList<>();
            for (GroupItem i : this.items) {
                if (i instanceof ICloneable) {
                    Object newOne = ((ICloneable) i).newInstance();
                    ((ICloneable) i).clone(newOne);
                    group.items.add((GroupItem) newOne);
                }
                else {
                    group.items.add(i);
                }
            }
        }

    }
    public Object newInstance() {
        return new CrossPageArea();
    }

    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("Group@").append(hashCode()).append("[ ");
        msg.append("minR=").append(minR).append(", ");
        msg.append("maxR=").append(maxR).append(", ");
        msg.append("minC=").append(minC).append(", ");
        msg.append("maxC=").append(maxC).append(", ");
        if (null==items || items.isEmpty()) {
            msg.append("[ ], ");
        }
        else {
            msg.append("[ ");
            for (GroupItem i : items) {
                msg.append(i.toString()).append("; ");
            }
            msg.append(" ] ");
        }
        msg.append("]");

        return msg.toString();
    }
}
