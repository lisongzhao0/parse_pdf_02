package com.happy.gene.pdf.generate.model.attributes;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class Margin implements ICloneable
{

    public static Margin newInstance() { return new Margin(); }
    public static Margin newInstance(float top, float right, float bottom, float left) { return new Margin(top, right, bottom, left); }
    public static Margin newInstance(String marginsTRBL) { Margin margin = newInstance(); return margin.TRBL(marginsTRBL); }

    private float top    = 0.0f;
    private float left   = 0.0f;
    private float right  = 0.0f;
    private float bottom = 0.0f;

    public Margin() {}
    public Margin(float top, float right, float bottom, float left) { this.top = top; this.right = right; this.bottom = bottom; this.left = left; }

    public float top() { return top; }
    public float left() { return left; }
    public float right() { return right; }
    public float bottom() { return bottom; }

    public Margin top(float top) { this.top = top; return this; }
    public Margin left(float left) { this.left = left; return this; }
    public Margin right(float right) { this.right = right; return this; }
    public Margin bottom(float bottom) { this.bottom = bottom; return this; }
    public Margin margin(float top, float right, float bottom, float left) { this.top = top; this.right = right; this.bottom = bottom; this.left = left; return this; }

    /** @param marginsTRBL  {TOP, RIGHT, BOTTOM, LEFT } */
    public Margin TRBL(String marginsTRBL)
    {
        if (null!=marginsTRBL && !marginsTRBL.trim().isEmpty())
        {
            String[] margins = marginsTRBL.split("[, ;]+");
            if (margins.length>=4)
            {
                try
                {
                    Float top    = Float.parseFloat(margins[0]);
                    Float right  = Float.parseFloat(margins[1]);
                    Float bottom = Float.parseFloat(margins[2]);
                    Float left   = Float.parseFloat(margins[3]);

                    this.top    = null==top   ? 0f : top;
                    this.right  = null==right ? 0f : right;
                    this.bottom = null==bottom? 0f : bottom;
                    this.left   = null==left  ? 0f : left;
                }
                catch (Exception ex)
                {}
            }
            else if (margins.length>=3)
            {
                try
                {
                    Float top        = Float.parseFloat(margins[0]);
                    Float leftRight  = Float.parseFloat(margins[1]);
                    Float bottom     = Float.parseFloat(margins[2]);

                    this.top    = null==top       ? 0f : top;
                    this.right  = null==leftRight ? 0f : leftRight;
                    this.bottom = null==bottom    ? 0f : bottom;
                    this.left   = null==leftRight ? 0f : leftRight;
                }
                catch (Exception ex)
                {}
            }
            else if (margins.length>=2)
            {
                try
                {
                    Float topBottom = Float.parseFloat(margins[0]);
                    Float leftRight = Float.parseFloat(margins[1]);

                    this.top    = null==topBottom ? 0f : topBottom;
                    this.right  = null==leftRight ? 0f : leftRight;
                    this.bottom = null==topBottom ? 0f : topBottom;
                    this.left   = null==leftRight ? 0f : leftRight;
                }
                catch (Exception ex)
                {}
            }
            else if (margins.length>=1)
            {
                try
                {
                    Float trbl = Float.parseFloat(margins[0]);

                    this.top    = null==trbl ? 0f : trbl;
                    this.right  = null==trbl ? 0f : trbl;
                    this.bottom = null==trbl ? 0f : trbl;
                    this.left   = null==trbl ? 0f : trbl;
                }
                catch (Exception ex)
                {}
            }
        }
        return this;
    }


    //====================================
    // ICloneable
    //====================================
    @Override
    public ICloneable clone(Object dest)
    {
        if (!(dest instanceof Margin)) { return null; }

        ((Margin) dest).top    = top;
        ((Margin) dest).left   = left;
        ((Margin) dest).right  = right;
        ((Margin) dest).bottom = bottom;

        return (ICloneable) dest;
    }

    @Override
    public ICloneable createBlank() { return new Margin(); }
}
