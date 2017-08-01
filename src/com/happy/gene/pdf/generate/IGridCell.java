package com.happy.gene.pdf.generate;

/**
 * Created by zhaolisong on 28/06/2017.
 */
public interface IGridCell {
    int row();
    int col();
    int rowspan();
    int colspan();

    IGridCell grid(Integer row, Integer col);
    IGridCell grid(Integer row, Integer col, Integer rowspan, Integer colspan);
}
