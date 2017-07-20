package com.happy.gene.pdf.generate;

/**
 * Created by zhaolisong on 28/06/2017.
 */
public interface ICloneable {
    void clone(Object dest);
    ICloneable createBlank();
}
