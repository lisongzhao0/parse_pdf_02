package com.happy.gene.pdf.generate.elements;

import com.happy.gene.pdf.generate.ICloneable;
import com.happy.gene.pdf.generate.IGridCell;

/**
 * The origin point is at the left-bottom corner
 *
 * the class defines the Area to show content in PDF
 *
 * Created by zhaolisong on 27/06/2017.
 */
public class AreaDef implements IGridCell, ICloneable {

    private float x = 0, y = 0, w = 0, h = 0;
    private float paddingL=0,paddingT=0,paddingR=0,paddingB=0;
    private float marginL=0,marginT=0,marginR=0,marginB=0;

    // Grid area cell
    private int row, col;

    // PDF relative parameters
    private int     zOrder;
    private boolean written;
    private boolean crossPageNeeded;

    // Cross page
    private float topY, bottomY;
    private float lastY;


    //====================================
    //  self
    //====================================
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }

    public float getW() {
        return w;
    }
    public void setW(float w) {
        this.w = w;
    }

    public float getH() {
        return h;
    }
    public void setH(float h) {
        this.h = h;
    }

    public float getMarginL() {
        return marginL;
    }
    public void setMarginL(float marginL) {
        this.marginL = marginL;
    }

    public float getMarginT() {
        return marginT;
    }
    public void setMarginT(float marginT) {
        this.marginT = marginT;
    }

    public float getMarginR() {
        return marginR;
    }
    public void setMarginR(float marginR) {
        this.marginR = marginR;
    }

    public float getMarginB() {
        return marginB;
    }
    public void setMarginB(float marginB) {
        this.marginB = marginB;
    }

    public float[] getMargin() {
        return new float[]{this.marginL, this.marginT, this.marginR, this.marginB};
    }
    public void setMargin(float[] margin) {
        if (null==margin && margin.length>=4) {
            this.marginL = margin[0];
            this.marginT = margin[1];
            this.marginR = margin[2];
            this.marginB = margin[3];
        }
    }

    public float getPaddingL() {
        return paddingL;
    }
    public void setPaddingL(float paddingL) {
        this.paddingL = paddingL;
    }

    public float getPaddingT() {
        return paddingT;
    }
    public void setPaddingT(float paddingT) {
        this.paddingT = paddingT;
    }

    public float getPaddingR() {
        return paddingR;
    }
    public void setPaddingR(float paddingR) {
        this.paddingR = paddingR;
    }

    public float getPaddingB() {
        return paddingB;
    }
    public void setPaddingB(float paddingB) {
        this.paddingB = paddingB;
    }

    public float[] getPadding() {
        return new float[]{this.paddingL, this.paddingT, this.paddingR, this.paddingB};
    }
    public void setPadding(float[] padding) {
        if (null==padding && padding.length>=4) {
            this.paddingL = padding[0];
            this.paddingT = padding[1];
            this.paddingR = padding[2];
            this.paddingB = padding[3];
        }
    }


    //====================================
    //  Grid area cell
    //====================================
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }

    //====================================
    //  PDF relative parameters
    //====================================
    public int getzOrder() {
        return zOrder;
    }
    public void setzOrder(int zOrder) {
        this.zOrder = zOrder;
    }

    public boolean isWritten() {
        return written;
    }
    public void setWritten(boolean written) {
        this.written = written;
    }

    public boolean isCrossPageNeeded() {
        return crossPageNeeded;
    }
    public void setCrossPageNeeded(boolean crossPageNeeded) {
        this.crossPageNeeded = crossPageNeeded;
    }

    //====================================
    // Cross page
    //====================================
    public float getTopY() {
        return topY;
    }
    public void setTopY(float topY) {
        this.topY = topY;
    }

    public float getBottomY() {
        return bottomY;
    }
    public void setBottomY(float bottomY) {
        this.bottomY = bottomY;
    }

    public float getLastY() {
        return lastY;
    }
    public void setLastY(float lastY) {
        this.lastY = lastY;
    }


    //====================================
    // ICloneable
    //====================================
    public void clone(Object dest) {
        if (null==dest) { return; }
        AreaDef area = (AreaDef) dest;

        // self
        area.x = this.x;
        area.y = this.y;
        area.w = this.w;
        area.h = this.h;
        area.paddingL=this.paddingL;
        area.paddingT=this.paddingT;
        area.paddingR=this.paddingR;
        area.paddingB=this.paddingB;
        area.marginL=this.marginL;
        area.marginT=this.marginT;
        area.marginR=this.marginR;
        area.marginB=this.marginB;

        // Grid area cell
        area.row = this.row;
        area.col = this.col;

        // PDF relative parameters
        area.zOrder = this.zOrder;
        area.written = this.written;
        area.crossPageNeeded = this.crossPageNeeded;

        // Cross page
        area.topY = this.topY;
        area.bottomY = this.bottomY;
        area.lastY = this.lastY;
    }
    public ICloneable createBlank() { return new AreaDef(); }
    public static Object newInstance() {
        return new AreaDef();
    }


    //====================================
    //
    //====================================
    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("Area@").append(hashCode()).append("[ ");
        msg.append("zOrder=").append(zOrder).append(", ");
        msg.append("written=").append(written).append(", ");
        msg.append("crossPageNeeded=").append(crossPageNeeded).append(", ");
        msg.append("x=").append(x).append(", ");
        msg.append("y=").append(y).append(", ");
        msg.append("w=").append(w).append(", ");
        msg.append("h=").append(h).append(", ");
        msg.append("pLTRB=[").append(paddingL).append(", ").append(paddingT).append(", ").append(paddingR).append(", ").append(paddingB).append("], ");
        msg.append("mLTRB=[").append(marginL).append(", ").append(marginT).append(", ").append(marginR).append(", ").append(marginB).append("], ");
        msg.append("row=").append(row).append(", ");
        msg.append("col=").append(col).append(", ");
        msg.append("yTopBottomLast=[ top=").append(topY).append(", bottom=").append(bottomY).append(", last=").append(lastY).append("] ");
        msg.append("]");
        return msg.toString();
    }
}
