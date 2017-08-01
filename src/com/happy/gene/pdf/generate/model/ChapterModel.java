package com.happy.gene.pdf.generate.model;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 26/07/2017.
 */
public class ChapterModel extends AreaModel {

    public static ChapterModel newInstance() { return new ChapterModel(); }

    public ChapterModel() {}

    @Override public ICloneable createBlank() { return new ChapterModel(); }
    @Override public ICloneable clone(Object dest) {
        if (!(dest instanceof ChapterModel)) { return null; }
        super.clone(dest);

        return (ICloneable) dest;
    }
}
