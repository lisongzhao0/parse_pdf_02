package com.happy.gene.pdf.generate.elements;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class CircleDef extends AbstractDef {

    public static CircleDef newInstance() {
        return new CircleDef();
    }
    public static CircleDef newInstance(float dashOn, float dashOff) {
        return new CircleDef(dashOn, dashOff);
    }

    private Float dashOn  = null;
    private Float dashOff = null;

    public CircleDef() {}
    public CircleDef(float dashOn, float dashOff) {
        this.dashOn = dashOn; this.dashOff = dashOff;
    }

    public Float dashOn() { return dashOn; }
    public Float dashOff() { return dashOff; }

    public CircleDef dashOn(Float dashOn) {
        this.dashOn = dashOn;
        return this;
    }
    public CircleDef dashOff(Float dashOff) {
        this.dashOff = dashOff;
        return this;
    }

    @Override
    public CircleDef clone(Object dest) {
        if (!(dest instanceof CircleDef)) { return null; }
        super.clone(dest);

        ((CircleDef) dest).dashOn  = dashOn;
        ((CircleDef) dest).dashOff = dashOff;

        return (CircleDef) dest;
    }
    @Override
    public ICloneable createBlank() {
        return new CircleDef();
    }
}
