package com.happy.gene.pdf.generate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaolisong on 28/06/2017.
 */
public class GridArea implements ICloneable {

    private int minR=0, maxR=0, minC=0, maxC=0;
    private List<IGridCell> items;

    //====================================
    // self
    //====================================
    public List<IGridCell> getItems() {
        return this.items;
    }
    public void setItems(List<IGridCell> items) {
        this.items = items;
        sort();
    }

    public void addItem(IGridCell item) {
        if (null==item) { return; }
        if (null==this.items) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
        sort();
    }
    public void addItem(List<IGridCell> items) {
        if (null==items || items.isEmpty()) { return; }
        if (null==items) {
            this.items = new ArrayList<>();
        }
        for (IGridCell i : items) {
            this.items.add(i);
        }
        sort();

    }

    public boolean removeItem(IGridCell item) {
        if (null==item)  { return true; }
        if (null==items) { return true; }
        boolean removed = items.remove(item);
        sort();
        return removed;
    }
    public IGridCell removeItem(int item) {
        if (null==items) {
            return null;
        }
        IGridCell removed = items.remove(item);
        sort();
        return removed;
    }

    public void sort() {
        if (null==this.items || this.items.isEmpty()) { return; }

        int minRow = Integer.MAX_VALUE; int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE; int maxCol = Integer.MIN_VALUE;

        String[]    keys = new String[this.items.size()];
        IGridCell[] vals = new IGridCell[this.items.size()];
        for (int idx = 0; idx < this.items.size(); idx ++) {
            IGridCell item = this.items.get(idx);
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

        List<IGridCell> sorted = new ArrayList<>();
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

    //====================================
    // ICloneable
    //====================================
    public void clone(Object dest) {
        if (null==dest) { return; }

        GridArea grid = (GridArea) dest;
        grid.minR = this.minR;
        grid.maxR = this.maxR;
        grid.minC = this.minC;
        grid.maxC = this.maxC;

        if (null!=items) {
            grid.items = new ArrayList<>();
            for (IGridCell i : this.items) {
                if (i instanceof ICloneable) {
                    Object newOne = ((ICloneable) i).createBlank();
                    ((ICloneable) i).clone(newOne);
                    grid.items.add((IGridCell) newOne);
                }
                else {
                    grid.items.add(i);
                }
            }
        }

    }
    public ICloneable createBlank() {
        return new GridArea();
    }
    public static Object newInstance() {
        return new GridArea();
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
            for (IGridCell i : items) {
                msg.append(i.toString()).append("; ");
            }
            msg.append(" ] ");
        }
        msg.append("]");

        return msg.toString();
    }
}
