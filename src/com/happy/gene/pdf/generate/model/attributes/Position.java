package com.happy.gene.pdf.generate.model.attributes;


import com.happy.gene.pdf.generate.ICloneable;

/**
 * The origin point     ^
 * is at the            |
 * left-bottom corner   |
 *                    (0,0)---->
 *
 * the class defines the Position to show content in PDF
 *
 * Created by zhaolisong on 21/07/2017.
 */
public class Position implements ICloneable {

    public static Position newInstance() { return new Position(); }
    public static Position newInstance(float x, float y) { return new Position(x, y); }
    public static Position newInstance(float x, float y, float topY, float bottomY) { return new Position(x, y, topY, bottomY); }

    private float x       = 0.0f;
    private float y       = 0.0f;
    private Float topY    = null;
    private Float bottomY = null;

    public Position() {}
    public Position(float x, float y) {
        this(x, y, null, null);
    }
    public Position(float x, float y, Float topY, Float bottomY) {
        this.x = x; this.y = y; this.topY = topY; this.bottomY = bottomY;
    }

    public float X() { return x; }
    public float Y() { return y; }
    public Float topY() { return topY; }
    public Float bottomY() { return bottomY; }

    public Position X(float x) { this.x = x; return this; }
    public Position Y(float y) {
        this.y = y;
        if (null!=topY    && y > topY   ) { this.y = topY;    }
        if (null!=bottomY && y < bottomY) { this.y = bottomY; }
        return this;
    }
    public Position topY(Float topY) { this.topY = topY; return this; }
    public Position bottomY(Float bottomY) {this.bottomY = bottomY; return this; }
    //====================================
    // ICloneable
    //====================================
    @Override
    public ICloneable clone(Object dest) {
        if (!(dest instanceof Position)) { return null; }

        ((Position) dest).x = x;
        ((Position) dest).y = y;
        ((Position) dest).topY    = topY;
        ((Position) dest).bottomY = bottomY;

        return (ICloneable) dest;
    }
    @Override
    public ICloneable createBlank() {
        return new Position();
    }
}
