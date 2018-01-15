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

    private SetUtil       setUtil     = SetUtil.newInstance();
    private NumberUtil    numberUtil  = NumberUtil.newInstance();
    private StringUtil    stringUtil  = StringUtil.newInstance();

    private AbstractModel parent;
    private int           index;
    private String        name;

    private StyleOption   style;
    private Margin        margin;
    private Padding       padding;
    private Position      position;
    private Alignment     alignment;
    private Dimension     dimension;
    private FontOption    fontStyle;
    private PageOption    pageOption;
    private GridOption    gridOption;

    public AbstractModel parent() { return parent; }
    public int           index() { return index; }
    public String        name() { return name; }
    public StyleOption   style() { return style; }
    public Margin        margin() { return margin; }
    public Padding       padding() { return padding; }
    public Position      position() { return position; }
    public Alignment     alignment() { return alignment; }
    public Dimension     dimension() { return dimension; }
    public FontOption    fontStyle() { return fontStyle; }
    public PageOption    pageOption() { return pageOption; }

    public AbstractModel parent(AbstractModel parent) { this.parent = parent; return this; }
    public AbstractModel index(int index) { this.index = index; return this; }
    public AbstractModel name(String name) { this.name = name; return this; }
    public AbstractModel style(StyleOption style) { this.style = style; return this; }
    public AbstractModel margin(Margin margin) { this.margin = margin; return this; }
    public AbstractModel padding(Padding padding) { this.padding = padding; return this; }
    public AbstractModel position(Position position) { this.position = position; return this; }
    public AbstractModel alignment(Alignment alignment) { this.alignment = alignment; return this; }
    public AbstractModel dimension(Dimension dimension) { this.dimension = dimension; return this; }
    public AbstractModel fontStyle(FontOption fontStyle) { this.fontStyle = fontStyle; return this; }
    public AbstractModel pageOption(PageOption pageOption) { this.pageOption = pageOption; return this; }
    public AbstractModel center(Position center)
    {
        if (null==dimension) { position = center; return this; }

        position = (Position) center.clone(new Position());
        position.X(position.X()-dimension.W()/2);
        position.Y(position.Y()-dimension.H()/2);

        return this;
    }

    /**
     * the outter size of an area.
     *
     *                            w
     *   ------------------------------------------------------
     *   |                   margin-top                       |
     *   |        ------------------------------------        |
     *   |        |         padding-top              |        |
     * h |        |        ------------------        |        |
     *   |        |padding-|  inner-bound   |padding-| margin-|
     *   |margin- |  left  | (has content)  |  right |  right |
     *   |  left  |        ------------------        |        |
     *   |        |          padding-bottom          |        |
     *   |        ------------------------------------        |
     *   |                  margin-bottom                     |
     * (x,y)---------------------------------------------------
     *
     * The origin point PDF ^
     * is at the            |
     * left-bottom corner   |
     *                    (0,0)---->
     * @return [originX, originY, width, height, ]
     */
    public float[] outterBoundaryPdf()
    {
        if (null==position || null==dimension) { return null; }

        float x = position.X()  - (null==margin ? 0.0f : margin.left());
        float y = position.Y()  - (null==margin ? 0.0f : margin.bottom());
        float w = dimension.W() + (null==margin ? 0.0f : (margin.left() + margin.right()));
        float h = dimension.H() + (null==margin ? 0.0f : (margin.top() + margin.bottom()));

        return new float[]{x, y, w, h};
    }

    /**
     * the outter size of an area.
     *
     *   -------------------------------------------------------
     *   |                    margin-top                       |
     *   |                         w                           |
     *   |         ------------------------------------        |
     *   |         |         padding-top              |        |
     *   |         |        ------------------        |        |
     *   |       h |padding-|  inner-bound   |padding-| margin-|
     *   |margin-  |  left  | (has content)  |  right |  right |
     *   |  left   |        ------------------        |        |
     *   |         |          padding-bottom          |        |
     *   |       (x,y)---------------------------------        |
     *   |                   margin-bottom                     |
     *   -------------------------------------------------------
     *
     * The origin point PDF ^
     * is at the            |
     * left-bottom corner   |
     *                    (0,0)---->
     * @return [originX, originY, width, height, ]
     */
    public float[] boundaryPdf()
    {
        if (null==position || null==dimension) { return null; }

        float x = position.X();
        float y = position.Y();
        float w = dimension.W();
        float h = dimension.H();

        return new float[]{x, y, w, h};
    }
    /**
     *
     *   -------------------------------------------------------
     *   |                    margin-top                       |
     *   |         ------------------------------------        |
     *   |         |          padding-top             |        |
     *   |         |                w                 |        |
     *   |         |        ------------------        |        |
     *   |         |        |                |        |        |
     *   | margin- |      h |  inner-bound   |        | margin-|
     *   |  left   |padding-| (has content)  |padding-|  right |
     *   |         |  left  |                |  right |        |
     *   |         |        |                |        |        |
     *   |         |      (x,y)---------------        |        |
     *   |         |          padding-bottom          |        |
     *   |         ------------------------------------        |
     *   |                    margin-bottom                    |
     *   -------------------------------------------------------
     *
     * The origin point PDF ^
     * is at the            |
     * left-bottom corner   |
     *                    (0,0)---->
     *
     * the class defines the Position to show content in PDF
     */
    public float[] innerBoundaryPdf()
    {
        if (null==position || null==dimension) { return null; }

        float x = position.X()  + (null==padding ? 0.0f : padding.left());
        float y = position.Y()  + (null==padding ? 0.0f : padding.bottom());
        float w = dimension.W();
        float h = dimension.H();

        return new float[]{x, y, w, h};
    }
    /**
     * the outter size of an area as 2D.
     *                            w
     * (x,y)---------------------------------------------------
     *   |                   margin-top                       |
     *   |        ------------------------------------        |
     *   |        |         padding-top              |        |
     * h |        |        ------------------        |        |
     *   |        |padding-|  inner-bound   |padding-| margin-|
     *   |margin- |  left  | (has content)  |  right |  right |
     *   |  left  |        ------------------        |        |
     *   |        |          padding-bottom          |        |
     *   |        ------------------------------------        |
     *   |                  margin-bottom                     |
     *   ------------------------------------------------------
     * The origin 2D    (0,0)---->
     * point is at the    |
     * left-top corner    |
     *                    v
     * @return [originX, originY, width, height, ]
     */
    public float[] outterBoundary2D()
    {
        if (null==position || null==dimension) { return null; }

        float x = position.X()  - (null==margin ? 0.0f : margin.left());
        float y = position.Y()  - (null==margin ? 0.0f : margin.bottom());
        float w = dimension.W() + (null==margin ? 0.0f : (margin.left() + margin.right()));
        float h = dimension.H() + (null==margin ? 0.0f : (margin.top() + margin.bottom()));

        y = y + h;
        return new float[]{x, y, w, h};
    }

    /**
     * the size of an area as 2D.
     *
     *   -------------------------------------------------------
     *   |                    margin-top                       |
     *   |                         w                           |
     *   |       (x,y)---------------------------------        |
     *   |         |         padding-top              |        |
     *   |         |        ------------------        |        |
     *   |       h |padding-|  inner-bound   |padding-| margin-|
     *   |margin-  |  left  | (has content)  |  right |  right |
     *   |  left   |        ------------------        |        |
     *   |         |          padding-bottom          |        |
     *   |         ------------------------------------        |
     *   |                   margin-bottom                     |
     *   -------------------------------------------------------
     *
     * The origin 2D    (0,0)---->
     * point is at the    |
     * left-top corner    |
     *                    v
     * @return [originX, originY, width, height, ]
     */
    public float[] boundary2D()
    {
        if (null==position || null==dimension) { return null; }

        float x = position.X();
        float y = position.Y();
        float w = dimension.W();
        float h = dimension.H();

        y = y + h;
        return new float[]{x, y, w, h};
    }

    /**
     * the inner size of an area as 2D.
     *
     *   -------------------------------------------------------
     *   |                    margin-top                       |
     *   |         ------------------------------------        |
     *   |         |          padding-top             |        |
     *   |         |                w                 |        |
     *   |         |      (x,y)---------------        |        |
     *   |         |        |                |        |        |
     *   | margin- |      h |  inner-bound   |        | margin-|
     *   |  left   |padding-| (has content)  |padding-|  right |
     *   |         |  left  |                |  right |        |
     *   |         |        |                |        |        |
     *   |         |        ------------------        |        |
     *   |         |          padding-bottom          |        |
     *   |         ------------------------------------        |
     *   |                    margin-bottom                    |
     *   -------------------------------------------------------
     *
     *
     * The origin 2D    (0,0)---->
     * point is at the    |
     * left-top corner    |
     *                    v
     * @return [originX, originY, width, height, ]
     */
    public float[] innerBoundary2D()
    {
        if (null==position || null==dimension) { return null; }

        float x = position.X()  + (null==padding ? 0.0f : padding.left());
        float y = position.Y()  + (null==padding ? 0.0f : padding.bottom());
        float w = dimension.W() - (null==padding ? 0.0f : (padding.left() + padding.right()));
        float h = dimension.H() - (null==padding ? 0.0f : (padding.top() + padding.bottom()));

        y = y + h;
        return new float[]{x, y, w, h};
    }


    //===================================================
    // Grids
    //===================================================
    public int row() { return null==gridOption ? GridOption.DEFAULT_ROW : gridOption.row(); }
    public int col() { return null==gridOption ? GridOption.DEFAULT_COL : gridOption.col(); }
    public int rowspan() { return null==gridOption ? GridOption.DEFAULT_ROWSPAN : gridOption.rowspan(); }
    public int colspan() { return null==gridOption ? GridOption.DEFAULT_COLSPAN : gridOption.colspan(); }

    public AbstractModel grid(GridOption gridOption) { this.gridOption = gridOption; return this; }
    public AbstractModel grid(Integer row, Integer col)
    {
        if (null==gridOption) { gridOption = new GridOption(); }
        gridOption.grid(row, col);
        return this;
    }
    public AbstractModel grid(Integer row, Integer col, Integer rowspan, Integer colspan)
    {
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


    @Override public ICloneable clone(Object dest)
    {
        if (!(dest instanceof AbstractModel)) { return null; }

        ((AbstractModel) dest).parent = parent;
        ((AbstractModel) dest).index = index;
        ((AbstractModel) dest).name  = null==name ? null : name;

        ((AbstractModel) dest).style      = null==style     ? null : (StyleOption) style.clone(StyleOption.newInstance());
        ((AbstractModel) dest).margin     = null==margin    ? null : (Margin)      margin.clone(Margin.newInstance());
        ((AbstractModel) dest).padding    = null==padding   ? null : (Padding)     padding.clone(Margin.newInstance());
        ((AbstractModel) dest).position   = null==position  ? null : (Position)    position.clone(Position.newInstance());
        ((AbstractModel) dest).alignment  = null==alignment ? null : (Alignment)   alignment.clone(Alignment.newInstance());
        ((AbstractModel) dest).dimension  = null==dimension ? null : (Dimension)   dimension.clone(Dimension.newInstance());
        ((AbstractModel) dest).fontStyle  = null==fontStyle ? null : (FontOption)  fontStyle.clone(FontOption.newInstance());
        ((AbstractModel) dest).pageOption = null==pageOption? null : (PageOption)  pageOption.clone(PageOption.newInstance());
        ((AbstractModel) dest).gridOption = null==gridOption? null : (GridOption)  gridOption.clone(GridOption.newInstance());


        return (ICloneable) dest;
    }
}
