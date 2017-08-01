package com.happy.gene.pdf.generate.model;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class CircleModel extends AbstractModel {

    public static CircleModel newInstance() {
        return new CircleModel();
    }
    public static CircleModel newInstance(Float dashOn, Float dashOff) {
        return new CircleModel(dashOn, dashOff);
    }

    private Float dashOn  = null;
    private Float dashOff = null;

    public CircleModel() {}
    public CircleModel(float dashOn, float dashOff) {
        this.dashOn = dashOn; this.dashOff = dashOff;
    }

    public Float dashOn() { return dashOn; }
    public Float dashOff() { return dashOff; }

    public CircleModel dashOn(Float dashOn) {
        this.dashOn = dashOn;
        return this;
    }
    public CircleModel dashOff(Float dashOff) {
        this.dashOff = dashOff;
        return this;
    }

    @Override public ICloneable createBlank() {
        return new CircleModel();
    }
    @Override public CircleModel clone(Object dest) {
        if (!(dest instanceof CircleModel)) { return null; }
        super.clone(dest);

        ((CircleModel) dest).dashOn  = dashOn;
        ((CircleModel) dest).dashOff = dashOff;

        return (CircleModel) dest;
    }
}
