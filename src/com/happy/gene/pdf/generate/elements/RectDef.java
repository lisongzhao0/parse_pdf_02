package com.happy.gene.pdf.generate.elements;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class RectDef extends AbstractDef {

    public static RectDef newInstance() {
        return new RectDef();
    }
    public static RectDef newInstance(float x1, float y1, float x2, float y2) {
        return new RectDef(x1, y1, x2, y2);
    }

    private float x1 = 0.0f;
    private float y1 = 0.0f;
    private float x2 = 0.0f;
    private float y2 = 0.0f;

    public RectDef() {}
    public RectDef(float x1, float y1, float x2, float y2) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
    }

    public float x1() { return x1; }
    public float y1() { return y1; }
    public float x2() { return x2; }
    public float y2() { return y2; }

    public RectDef x1(float x1) {
        this.x1 = x1;
        return this;
    }
    public RectDef y1(float y1) {
        this.y1 = y1;
        return this;
    }
    public RectDef x2(float x2) {
        this.x2 = x2;
        return this;
    }
    public RectDef y2(float y2) {
        this.y2 = y2;
        return this;
    }

    @Override
    public RectDef clone(Object dest) {
        if (!(dest instanceof RectDef)) { return null; }
        super.clone(dest);

        ((RectDef) dest).x1 = x1;
        ((RectDef) dest).y1 = y1;
        ((RectDef) dest).x2 = x2;
        ((RectDef) dest).y2 = y2;

        return (RectDef) dest;
    }
    @Override
    public ICloneable createBlank() {
        return new RectDef();
    }
}
