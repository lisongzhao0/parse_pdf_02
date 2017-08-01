package com.happy.gene.pdf.generate.model;

import com.happy.gene.pdf.generate.ICloneable;
import com.happy.gene.pdf.generate.IGridCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhaolisong on 28/06/2017.
 */
public class GridAreaModel extends AreaModel {

    public static GridAreaModel newInstance() {
        return new GridAreaModel();
    }
    public static GridAreaModel newInstance(Integer colSize) {
        return new GridAreaModel();
    }

    private int rowSize=0, colSize=0;

    public GridAreaModel() {}
    public GridAreaModel(Integer colSize) {
        colSize(colSize);
    }

    //====================================
    // self
    //====================================
    public void addItem(IGridCell model) {
        if (!(model instanceof AbstractModel)) { return; }
        if (null==this.components) { this.components = new ArrayList<>(); }
        this.components.add((AbstractModel) model);
        sort();
    }
    public void addItem(List<IGridCell> models) {
        if (setUtil().isListEmpty(models)) { return; }
        if (null==this.components) { this.components = new ArrayList<>(); }
        for (IGridCell model : models) {
            if (!(model instanceof AbstractModel)) { continue; }
            this.components.add((AbstractModel) model);
        }
        sort();

    }

    public boolean removeItem(IGridCell model) {
        if (null==model)  { return true; }
        if (null==components) { return true; }
        boolean removed = components.remove(model);
        sort();
        return removed;
    }
    public IGridCell removeItem(int item) {
        if (null==components) { return null; }
        IGridCell removed = components.remove(item);
        sort();
        return removed;
    }

    public void sort() {
        if (null==this.components || this.components.isEmpty()) { return; }

        String[]    keys = new String[this.components.size()];
        IGridCell[] vals = new IGridCell[this.components.size()];
        for (int idx = 0; idx < this.components.size(); idx ++) {
            IGridCell item = this.components.get(idx);
            int row = item.row();
            int col = item.col();
            int rowspan = item.rowspan();
            int colspan = item.colspan();

            int maxRowNumber = row + rowspan - 1;
            int maxColNumber = col + colspan - 1;
            if (maxRowNumber>0 && maxRowNumber> rowSize) { rowSize = maxRowNumber + 1; }
            if (maxColNumber>0 && maxColNumber> colSize) { colSize = maxColNumber + 1; }

            keys[idx] = row+"_"+col;
            vals[idx] = item;
        }

        Collections.sort(components, new Comparator<AbstractModel>() {
            @Override public int compare(AbstractModel o1, AbstractModel o2) {
                if (null==o1 && null==o2) { return 0; }
                if (null!=o1 && null==o2) { return 1; }
                if (null==o1 && null!=o2) { return -1;}
                int deltaRow = o1.row() - o2.row();
                int deltaCol = o1.col() - o2.col();
                if (deltaRow!=0) { return deltaRow; }
                if (deltaCol!=0) { return deltaCol; }
                return 0;
            }
        });
    }

    public int rowSize() { return this.rowSize; }
    public int colSize() { return this.colSize; }

    public GridAreaModel rowSize(Integer rowSize) { this.rowSize = (null==rowSize || 0 > rowSize) ? 0 : rowSize; return this; }
    public GridAreaModel colSize(Integer colSize) { this.colSize = (null==colSize || 0 > colSize) ? 0 : colSize; return this; }

    //====================================
    // ICloneable
    //====================================
    @Override public ICloneable createBlank() {
        return new GridAreaModel();
    }
    @Override public ICloneable clone(Object dest) {
        if (!(dest instanceof GridAreaModel)) { return null; }
        super.clone(dest);

        ((GridAreaModel) dest).rowSize = this.rowSize;
        ((GridAreaModel) dest).colSize = this.colSize;

        return ((GridAreaModel) dest);
    }
}
