package com.happy.gene.pdf.generate.elements;

/**
 * This is subclass of Area
 *
 * this class defines the content can cross PDF page
 *
 * so y is not fixed
 * Created by zhaolisong on 27/06/2017.
 */
public class CrossPageArea extends Area implements ICloneable {

    private float topY, bottomY;
    private float lastY;

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

    public void clone(Object dest) {
        if (null==dest) { return; }
        CrossPageArea area = (CrossPageArea) dest;

        super.clone(area);
        area.lastY = this.lastY;
        area.topY = this.topY;
        area.bottomY = this.bottomY;
    }
    public Object newInstance() {
        return new CrossPageArea();
    }

    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("CrossPageArea@").append(hashCode()).append("[ ");
        msg.append("topY=").append(topY).append(", ");
        msg.append("bottomY=").append(bottomY).append(", ");
        msg.append("lastY=").append(lastY).append("");
        msg.append(" ]  <<<<<  ").append(super.toString());
        return msg.toString();
    }
}
