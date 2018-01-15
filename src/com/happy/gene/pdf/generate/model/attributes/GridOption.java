package com.happy.gene.pdf.generate.model.attributes;

import com.happy.gene.pdf.generate.ICloneable;
import com.happy.gene.pdf.generate.IGridCell;

/**
 * Created by zhaolisong on 26/07/2017.
 */
public class GridOption implements ICloneable, IGridCell
{

    public static final int DEFAULT_ROW = 0;
    public static final int DEFAULT_COL = 0;
    public static final int DEFAULT_ROWSPAN = 1;
    public static final int DEFAULT_COLSPAN = 1;

    public static GridOption newInstance() {
        return new GridOption();
    }
    public static GridOption newInstance(Integer row, Integer col) { return new GridOption(row, col); }
    public static GridOption newInstance(Integer row, Integer col, Integer rowspan, Integer colspan) { return new GridOption(row, col, rowspan, colspan); }

    private int row = 0, rowspan = 1;
    private int col = 0, colspan = 1;

    public GridOption() {}
    public GridOption(Integer row, Integer col) { row(row); col(col); }
    public GridOption(Integer row, Integer col, Integer rowspan, Integer colspan) { row(row); col(col); rowspan(rowspan); colspan(colspan); }

    public int row() { return row; }
    public int col() { return col; }
    public int rowspan() { return rowspan; }
    public int colspan() { return colspan; }

    public GridOption row(Integer row) { this.row = null==row ? 0 : (row<0 ? 0 : row); return this; }
    public GridOption col(Integer col) { this.col = null==col ? 0 : (col<0 ? 0 : col); return this; }
    public GridOption rowspan(Integer rowspan) { this.rowspan = null==rowspan ? 1 : (rowspan<1 ? 1 : rowspan); return this; }
    public GridOption colspan(Integer colspan) { this.colspan = null==colspan ? 1 : (colspan<1 ? 1 : colspan); return this; }
    public GridOption grid(Integer row, Integer col) { grid(row, col, DEFAULT_ROWSPAN, DEFAULT_COLSPAN); return this; }
    public GridOption grid(Integer row, Integer col, Integer rowspan, Integer colspan) { row(row); col(col); rowspan(rowspan); colspan(colspan); return this; }


    //====================================
    // ICloneable
    //====================================
    @Override
    public ICloneable clone(Object dest)
    {
        if (!(dest instanceof GridOption)) { return null; }

        ((GridOption) dest).row     = row;
        ((GridOption) dest).col     = col;
        ((GridOption) dest).rowspan = rowspan;
        ((GridOption) dest).colspan = colspan;

        return (ICloneable) dest;
    }

    @Override
    public ICloneable createBlank() { return new GridOption(); }

}
