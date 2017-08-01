package com.happy.gene.pdf.generate.model.attributes;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class Alignment implements ICloneable {

    public static Alignment newInstance() {
        return new Alignment();
    }
    public static Alignment newInstance(int horizontal, int vertical) {
        return new Alignment(horizontal, vertical);
    }
    public static Alignment newInstance(String horizontal, String vertical) {
        return new Alignment(horizontal, vertical);
    }

    public enum H {
        LEFT, RIGHT, CENTER;

        public static H parse(String alignment) {
            if (LEFT.name().equalsIgnoreCase(alignment)) { return LEFT; }
            if (RIGHT.name().equalsIgnoreCase(alignment)) { return RIGHT; }
            if (CENTER.name().equalsIgnoreCase(alignment)) { return CENTER; }
            return null;
        }
        public static H parse(int alignment) {
            if (LEFT.ordinal()==alignment) { return LEFT; }
            if (RIGHT.ordinal()==alignment) { return RIGHT; }
            if (CENTER.ordinal()==alignment) { return CENTER; }
            return null;
        }
    }
    public enum V {
        TOP, BOTTOM, CENTER;

        public static V parse(String alignment) {
            if (TOP.name().equalsIgnoreCase(alignment)) { return TOP; }
            if (BOTTOM.name().equalsIgnoreCase(alignment)) { return BOTTOM; }
            if (CENTER.name().equalsIgnoreCase(alignment)) { return CENTER; }
            return null;
        }
        public static V parse(int alignment) {
            if (TOP.ordinal()==alignment) { return TOP; }
            if (BOTTOM.ordinal()==alignment) { return BOTTOM; }
            if (CENTER.ordinal()==alignment) { return CENTER; }
            return null;
        }
    }

    private H h = H.LEFT;
    private V v = V.CENTER;

    public Alignment() {}
    public Alignment(int horizontal, int vertical) {
        h = null==H.parse(horizontal)? h : H.parse(horizontal);
        v = null==V.parse(vertical)  ? v : V.parse(vertical);
    }
    public Alignment(String horizontal, String vertical) {
        h = null==H.parse(horizontal)? h : H.parse(horizontal);
        v = null==V.parse(vertical)  ? v : V.parse(vertical);
    }

    public H h() { return h; }
    public V v() { return v; }

    public Alignment h(H h) { this.h = h; return this; }
    public Alignment v(V v) { this.v = v; return this; }

    //====================================
    // ICloneable
    //====================================
    @Override
    public ICloneable clone(Object dest) {
        if (!(dest instanceof Alignment)) { return null; }

        ((Alignment) dest).h = h;
        ((Alignment) dest).v = v;

        return (ICloneable) dest;
    }
    @Override
    public ICloneable createBlank() {
        return new Alignment();
    }
}
