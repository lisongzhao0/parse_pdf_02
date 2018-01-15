package com.happy.gene.pdf.generate.model.attributes;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class Padding extends Margin
{
    public static final Margin margin = new Margin();

    public static Padding newInstance() { return new Padding(); }
    public static Padding newInstance(float top, float right, float bottom, float left) { return new Padding(top, right, bottom, left); }
    public static Padding newInstance(String marginsTRBL) { return new Padding(margin.TRBL(marginsTRBL)); }

    public Padding() { super(); }
    public Padding(float top, float right, float bottom, float left) { super(top, right, bottom, left); }
    public Padding(Margin margin)
    {
        if (null==margin) { return; }
        top(margin.top());
        left(margin.left());
        right(margin.right());
        bottom(margin.bottom());
    }


    public Padding padding(float top, float right, float bottom, float left) { margin(top, right, bottom, left); return this; }

    //====================================
    // ICloneable
    //====================================
    @Override
    public ICloneable clone(Object dest)
    {
        if (!(dest instanceof Padding)) { return null; }

        ((Padding) dest).top(top());
        ((Padding) dest).left(left());
        ((Padding) dest).right(right());
        ((Padding) dest).bottom(bottom());

        return (ICloneable) dest;
    }

    @Override
    public ICloneable createBlank() { return new Padding(); }

    public static void main(String[] args) {
        Padding padding = Padding.newInstance("15 20 25 30");
        System.out.println();
    }
}
