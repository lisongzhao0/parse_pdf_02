package com.happy.gene.pdf.generate.model.attributes;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class Dimension implements ICloneable {

    public static final Float H_UNDEFINE = Float.MAX_VALUE;

    public static Dimension newInstance() { return new Dimension(); }
    public static Dimension newInstance(float width, float height, float radius) { return new Dimension(width, height, radius); }

    private Float w = 0.0f;
    private Float h = 0.0f;
    private Float r = 0.0f;

    public Dimension() {}
    public Dimension(float width, float height, float radius) {
        this.w = width; this.h = height; this.r = radius;
    }

    public Float W() { return w; }
    public Float H() { return h; }
    public Float R() { return r; }
    public boolean isHeigtUndefine() { return Float.MAX_VALUE==h; }

    public Dimension W(Float width)  { this.w = width;  return this; }
    public Dimension H(Float height) { this.h = height; return this; }
    public Dimension R(Float radius) { this.r = radius; return this; }

    //====================================
    // ICloneable
    //====================================
    @Override
    public ICloneable clone(Object dest) {
        if (!(dest instanceof Dimension)) { return null; }

        ((Dimension) dest).w = w;
        ((Dimension) dest).h = h;
        ((Dimension) dest).r = r;

        return (ICloneable) dest;
    }
    @Override
    public ICloneable createBlank() {
        return new Dimension();
    }

}
