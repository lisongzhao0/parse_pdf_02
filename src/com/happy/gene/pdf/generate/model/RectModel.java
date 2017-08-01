package com.happy.gene.pdf.generate.model;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class RectModel extends AbstractModel {

    public static RectModel newInstance() {
        return new RectModel();
    }

    public RectModel() {}

    @Override public ICloneable createBlank() { return new RectModel(); }
    @Override public RectModel clone(Object dest) {
        if (!(dest instanceof RectModel)) { return null; }
        super.clone(dest);
        return (RectModel) dest;
    }
}
