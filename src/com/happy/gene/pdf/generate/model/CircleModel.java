package com.happy.gene.pdf.generate.model;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class CircleModel extends AbstractModel {

    public static CircleModel newInstance() {
        return new CircleModel();
    }

    public CircleModel() {}

    @Override public ICloneable createBlank() {
        return new CircleModel();
    }
    @Override public CircleModel clone(Object dest) {
        if (!(dest instanceof CircleModel)) { return null; }
        super.clone(dest);

        return (CircleModel) dest;
    }
}
