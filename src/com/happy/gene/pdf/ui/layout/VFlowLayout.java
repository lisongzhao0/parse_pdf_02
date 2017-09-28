package com.happy.gene.pdf.ui.layout;

import java.awt.*;

/**
 * Created by zhaolisong on 20/09/2017.
 */
public class VFlowLayout extends FlowLayout {

    private static final long serialVersionUID = 1L;

    public static final int TOP    = 0;
    public static final int MIDDLE = 1;
    public static final int BOTTOM = 2;

    int hgap;
    int vgap;
    boolean hfill;
    boolean vfill;

    /**
     * Construct a new VerticalFlowLayout with a top alignment, and the fill
     * to edge flag set.
     */
    public VFlowLayout() {
        this(TOP, 5, 5, true, false);
    }

    /**
     * Construct a new VerticalFlowLayout with horizontal and vertical gap.
     *
     * @param hgap  the horizontal gap variable
     * @param vgap  the vertical gap variable
     */
    public VFlowLayout(int hgap, int vgap) {
        this(TOP, hgap, vgap, true, false);
    }

    /**
     * Construct a new VerticalFlowLayout with fill type.
     *
     * @param hfill the fill to edge flag
     * @param vfill the vertical fill in pixels.
     */
    public VFlowLayout(boolean hfill, boolean vfill) {
        this(TOP, 5, 5, hfill, vfill);
    }

    /**
     * Construct a new VerticalFlowLayout with an alignment value.
     *
     * @param align the alignment value
     */
    public VFlowLayout(int align) {
        this(align, 5, 5, true, false);
    }

    /**
     * Construct a new VerticalFlowLayout.
     *
     * @param align the alignment value
     * @param hgap  the horizontal gap variable
     * @param vgap  the vertical gap variable
     * @param hfill the fill to edge flag
     * @param vfill true if the panel should vertically fill.
     */
    public VFlowLayout(int align, int hgap, int vgap, boolean hfill, boolean vfill) {
        setAlignment(align);
        this.hgap = hgap;
        this.vgap = vgap;
        this.hfill = hfill;
        this.vfill = vfill;
    }


    /**
     * Set true to fill vertically.
     *
     * @param vfill true to fill vertically.
     */
    public void setVerticalFill(boolean vfill) {
        this.vfill = vfill;
    }

    /**
     * Returns true if the layout vertically fills.
     *
     * @return true if vertically fills the layout using the specified.
     */
    public boolean getVerticalFill() {
        return vfill;
    }

    /**
     * Set to true to enable horizontally fill.
     *
     * @param hfill true to fill horizontally.
     */
    public void setHorizontalFill(boolean hfill) {
        this.hfill = hfill;
    }

    /**
     * Returns true if the layout horizontally fills.
     *
     * @return true if horizontally fills.
     */
    public boolean getHorizontalFill() {
        return hfill;
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        Dimension tarsiz = new Dimension(0, 0);

        for (int i = 0; i < parent.getComponentCount(); i ++) {
            Component m = parent.getComponent(i);
            if (m.isVisible()) {
                Dimension d = m.getPreferredSize();
                tarsiz.width = Math.max(tarsiz.width, d.width);

                if (i > 0) {
                    tarsiz.height += vgap;
                }

                tarsiz.height += d.height;
            }
        }

        Insets insets = parent.getInsets();
        tarsiz.width += insets.left + insets.right + hgap * 2;
        tarsiz.height+= insets.top + insets.bottom + vgap * 2;

        return tarsiz;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        Dimension tarsiz = new Dimension(0, 0);

        for (int i = 0; i < parent.getComponentCount(); i++) {
            Component m = parent.getComponent(i);

            if (m.isVisible()) {
                Dimension d = m.getMinimumSize();
                tarsiz.width = Math.max(tarsiz.width, d.width);

                if (i > 0) {
                    tarsiz.height += vgap;
                }

                tarsiz.height += d.height;
            }
        }

        Insets insets = parent.getInsets();
        tarsiz.width += insets.left + insets.right + hgap * 2;
        tarsiz.height+= insets.top + insets.bottom + vgap * 2;

        return tarsiz;
    }

    @Override
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int maxheight = parent.getSize().height - (insets.top + insets.bottom + vgap * 2);
        int maxwidth  = parent.getSize().width - (insets.left + insets.right  + hgap * 2);
        int numcomp = parent.getComponentCount();
        int x = insets.left + hgap, y = 0;
        int colw = 0, start = 0;


        for (int i = 0; i < numcomp; i ++) {
            Component m = parent.getComponent(i);
            m.invalidate();
            m.validate();
        }

        for (int i = 0; i < numcomp; i ++) {
            Component m = parent.getComponent(i);

            if (m.isVisible()) {
                Dimension d = m.getPreferredSize();

                if ((this.vfill) && (i == (numcomp - 1))) {
                    d.height = Math.max(maxheight - y, m.getPreferredSize().height);
                }

                if (this.hfill) {
                    m.setSize(maxwidth, d.height);
                    d.width = maxwidth;
                }
                else {
                    m.setSize(d.width, d.height);
                }

                if (y + d.height > maxheight) {
                    placethem(parent, x, insets.top + vgap, colw, maxheight - y, start, i);
                    y = d.height;
                    x += hgap + colw;
                    colw = d.width;
                    start = i;
                }
                else {
                    if (y > 0) {
                        y += vgap;
                    }

                    y += d.height;
                    colw = Math.max(colw, d.width);
                }
            }
        }

        placethem(parent, x, insets.top + vgap, colw, maxheight - y, start, numcomp);
    }

    private void placethem(Container parent, int x, int y, int width, int height, int first, int last) {
        int align = getAlignment();

        if (align == MIDDLE) {
            y += height / 2;
        }
        if (align == BOTTOM) {
            y += height;
        }
        for (int i = first; i < last; i ++) {
            Component m = parent.getComponent(i);
            Dimension md = m.getSize();

            if (m.isVisible()) {
                int px = x + (width - md.width) / 2;
                m.setLocation(px, y);
                y += vgap + md.height;
            }
        }
    }
}
