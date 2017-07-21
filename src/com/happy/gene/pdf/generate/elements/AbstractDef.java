package com.happy.gene.pdf.generate.elements;

import com.happy.gene.pdf.generate.ICloneable;
import com.happy.gene.pdf.generate.IIndex;
import com.happy.gene.pdf.generate.attributes.*;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public abstract class AbstractDef implements ICloneable, IIndex {

    private int             index;
    private Alignment       alignment;
    private Dimension       dimension;
    private FontStyleOption fontStyle;
    private Margin          margin;
    private Position        position;
    private StyleOption     style;
    private PageOption      pageOption;

    public int index() { return index; }
    public Alignment alignment() { return alignment; }
    public Dimension dimension() { return dimension; }
    public FontStyleOption fontStyle() { return fontStyle; }
    public Margin margin() { return margin; }
    public Position position() { return position; }
    public StyleOption style() { return style; }
    public PageOption pageOption() { return pageOption; }
    public float[] outterBoundary() {
        float[] innerBoundary = innerBoundary();
        if (null==innerBoundary) {
            return null;
        }
        innerBoundary[0] = innerBoundary[0]-margin.left();
        innerBoundary[1] = innerBoundary[1]-margin.bottom();
        innerBoundary[2] = innerBoundary[2]+margin.left()+ margin.right();
        innerBoundary[3] = innerBoundary[3]+margin.top() + margin.bottom();
        return innerBoundary;
    }
    public float[] innerBoundary() {
        if (null==position || null==dimension) {
            return null;
        }
        float x = position.X();
        float y = position.Y();
        float w = dimension.W();
        float h = dimension.H();

        return new float[]{x, y, w, h};
    }


    public AbstractDef index(int index) { this.index = index; return this; }
    public AbstractDef alignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }
    public AbstractDef dimension(Dimension dimension) {
        this.dimension = dimension;
        return this;
    }
    public AbstractDef fontStyle(FontStyleOption fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }
    public AbstractDef margin(Margin margin) {
        this.margin = margin;
        return this;
    }
    public AbstractDef position(Position position) {
        this.position = position;
        return this;
    }
    public AbstractDef style(StyleOption style) {
        this.style = style;
        return this;
    }
    public AbstractDef pageOption(PageOption pageOption) {
        this.pageOption = pageOption;
        return this;
    }



    @Override
    public ICloneable clone(Object dest) {
        if (!(dest instanceof AbstractDef)) { return null; }

        ((AbstractDef) dest).alignment  = null==alignment ? null : (Alignment)       alignment.clone(Alignment.newInstance());
        ((AbstractDef) dest).dimension  = null==dimension ? null : (Dimension)       dimension.clone(Dimension.newInstance());
        ((AbstractDef) dest).fontStyle  = null==fontStyle ? null : (FontStyleOption) fontStyle.clone(FontStyleOption.newInstance());
        ((AbstractDef) dest).margin     = null== margin   ? null : (Margin)          margin.clone(Margin.newInstance());
        ((AbstractDef) dest).position   = null==position  ? null : (Position)        position.clone(Position.newInstance());
        ((AbstractDef) dest).style      = null==style     ? null : (StyleOption)     style.clone(StyleOption.newInstance());
        ((AbstractDef) dest).pageOption = null==pageOption? null : (PageOption)      pageOption.clone(PageOption.newInstance());

        return (ICloneable) dest;
    }
}
