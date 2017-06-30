package com.happy.gene.pdf.generate.elements;

/**
 * The origin point is at the left-bottom corner
 *
 * the class defines the Area to show content in PDF
 *
 * Created by zhaolisong on 27/06/2017.
 */
public class Area implements GroupItem, ICloneable {

    private float x = 0, y = 0, w = 0, h = 0;
    private float paddingL=0,paddingT=0,paddingR=0,paddingB=0;

    private int row, col;

    // PDF relative parameters
    private int     zOrder;
    private boolean written;
    private boolean needNewPage;


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

    public boolean isNeedNewPage() {
        return needNewPage;
    }
    public void setNeedNewPage(boolean needNewPage) {
        this.needNewPage = needNewPage;
    }

    public void clone(Object dest) {
        if (null==dest) { return; }
        Area area = (Area) dest;
        area.x = this.x;
        area.y = this.y;
        area.w = this.w;
        area.h = this.h;
        area.paddingL=this.paddingL;
        area.paddingT=this.paddingT;
        area.paddingR=this.paddingR;
        area.paddingB=this.paddingB;
        area.written =this.written;
        area.row = this.row;
        area.col = this.col;
    }
    public Object newInstance() {
        return new Area();
    }

    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("Area@").append(hashCode()).append("[ ");
        msg.append("x=").append(x).append(", ");
        msg.append("y=").append(y).append(", ");
        msg.append("w=").append(w).append(", ");
        msg.append("h=").append(h).append(", ");
        msg.append("pL=").append(paddingL).append(", ");
        msg.append("pT=").append(paddingT).append(", ");
        msg.append("pR=").append(paddingR).append(", ");
        msg.append("pB=").append(paddingB).append(", ");
        msg.append("row=").append(row).append(", ");
        msg.append("col=").append(col).append(" ");
        msg.append("]");
        return msg.toString();
    }
}
