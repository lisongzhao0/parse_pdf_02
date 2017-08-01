package com.happy.gene.pdf.generate.model;

import com.happy.gene.pdf.generate.ICloneable;
import com.happy.gene.pdf.generate.IGridCell;
import com.happy.gene.pdf.generate.IIndex;
import com.happy.gene.pdf.generate.model.attributes.*;
import com.happy.gene.util.NumberUtil;
import com.happy.gene.util.SetUtil;
import com.happy.gene.util.StringUtil;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public abstract class AbstractModel implements ICloneable, IIndex, IGridCell {

    private SetUtil         setUtil     = SetUtil.newInstance();
    private NumberUtil      numberUtil  = NumberUtil.newInstance();
    private StringUtil      stringUtil  = StringUtil.newInstance();

    private AbstractModel   parent;
    private int             index;
    private String          name;

    private GridOption      gridOption;
    private Alignment       alignment;
    private Dimension       dimension;
    private FontStyleOption fontStyle;
    private Margin          margin;
    private Position        position;
    private StyleOption     style;
    private PageOption      pageOption;

    public AbstractModel parent() { return parent; }
    public int index() { return index; }
    public String name() { return name; }
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
    /**
     * the outter size of an area.
     *
     *-----------------------------------
     *|          margin-top             |
     *|        -----------------        |
     *|margin- |  inner-bound  | margin-|
     *|  left  | (has content) |  right |
     *|        -----------------        |
     *|         margin-bottom           |
     *-----------------------------------
     *
     * The origin 2D    (0,0)---->
     * point is at the    |
     * left-top corner    |
     *                    v
     * @return [originX, originY, width, height, ]
     */
    public float[] outterBoundary2D() {
        float[] innerBoundary2D = innerBoundary2D();
        if (null==innerBoundary2D) {
            return null;
        }
        innerBoundary2D[0] = innerBoundary2D[0]-margin.left();
        innerBoundary2D[1] = innerBoundary2D[1]+margin.top();
        innerBoundary2D[2] = innerBoundary2D[2]+margin.left()+ margin.right();
        innerBoundary2D[3] = innerBoundary2D[3]+margin.top() + margin.bottom();
        return innerBoundary2D;
    }
    /**
     * The origin 2D    (0,0)---->
     * point is at the    |
     * left-top corner    |
     *                    v
     * @return [originX, originY, width, height, ]
     */
    public float[] innerBoundary2D() {
        if (null==position || null==dimension) {
            return null;
        }
        float x = position.X();
        float y = position.Y();
        float w = dimension.W();
        float h = dimension.H();

        y = y + h;
        return new float[]{x, y, w, h};
    }

    public AbstractModel parent(AbstractModel parent) { this.parent = parent; return this; }
    public AbstractModel index(int index) { this.index = index; return this; }
    public AbstractModel name(String name) { this.name = name; return this; }
    public AbstractModel alignment(Alignment alignment) { this.alignment = alignment; return this; }
    public AbstractModel dimension(Dimension dimension) { this.dimension = dimension; return this; }
    public AbstractModel fontStyle(FontStyleOption fontStyle) { this.fontStyle = fontStyle; return this; }
    public AbstractModel margin(Margin margin) { this.margin = margin; return this; }
    public AbstractModel position(Position position) { this.position = position; return this; }
    public AbstractModel style(StyleOption style) { this.style = style; return this; }
    public AbstractModel pageOption(PageOption pageOption) { this.pageOption = pageOption; return this; }


    //===================================================
    // Grids
    //===================================================
    public int row() { return null==gridOption ? GridOption.DEFAULT_ROW : gridOption.row(); }
    public int col() { return null==gridOption ? GridOption.DEFAULT_COL : gridOption.col(); }
    public int rowspan() { return null==gridOption ? GridOption.DEFAULT_ROWSPAN : gridOption.rowspan(); }
    public int colspan() { return null==gridOption ? GridOption.DEFAULT_COLSPAN : gridOption.colspan(); }

    public AbstractModel grid(GridOption gridOption) { this.gridOption = gridOption; return this; }
    public AbstractModel grid(Integer row, Integer col) {
        if (null==gridOption) { gridOption = new GridOption(); }
        gridOption.grid(row, col);
        return this;
    }
    public AbstractModel grid(Integer row, Integer col, Integer rowspan, Integer colspan) {
        if (null==gridOption) { gridOption = new GridOption(); }
        this.gridOption.grid(row, col, rowspan, colspan);
        return this;
    }

    //===================================================
    //       utilities
    //===================================================
    public SetUtil setUtil() { return setUtil; }
    public NumberUtil numberUtil() { return numberUtil; }
    public StringUtil stringUtil() { return stringUtil; }


    @Override public ICloneable clone(Object dest) {
        if (!(dest instanceof AbstractModel)) { return null; }

        ((AbstractModel) dest).parent = parent;
        ((AbstractModel) dest).index = index;
        ((AbstractModel) dest).name  = null==name ? null : name;

        ((AbstractModel) dest).alignment  = null==alignment ? null : (Alignment)       alignment.clone(Alignment.newInstance());
        ((AbstractModel) dest).dimension  = null==dimension ? null : (Dimension)       dimension.clone(Dimension.newInstance());
        ((AbstractModel) dest).fontStyle  = null==fontStyle ? null : (FontStyleOption) fontStyle.clone(FontStyleOption.newInstance());
        ((AbstractModel) dest).margin     = null== margin   ? null : (Margin)          margin.clone(Margin.newInstance());
        ((AbstractModel) dest).position   = null==position  ? null : (Position)        position.clone(Position.newInstance());
        ((AbstractModel) dest).style      = null==style     ? null : (StyleOption)     style.clone(StyleOption.newInstance());
        ((AbstractModel) dest).pageOption = null==pageOption? null : (PageOption)      pageOption.clone(PageOption.newInstance());

        return (ICloneable) dest;
    }
}
