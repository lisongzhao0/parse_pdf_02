package com.happy.gene.pdf.generate.elements.pdfelement;

/**
 * Created by zhaolisong on 14/07/2017.
 */
public abstract class AShape {
    private Float   borderWidth = 0.1f;
    private String  borderColor = "#000000";
    private String  fillColor = "#000000";
    private Float   opacity = 1.0f;

    public Float getBorderWidth() {
        return null==borderWidth ? 1f : borderWidth;
    }
    public void setBorderWidth(Float borderWidth) {
        this.borderWidth = borderWidth;
    }

    public String getBorderColor() {
        return null==borderColor || "".equals(borderColor) ? "#000000" : borderColor;
    }
    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getFillColor() {
        return null==fillColor || "".equals(fillColor) ? "#000000" : fillColor;
    }
    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public Float getOpacity() {
        return null==opacity ? 1f : opacity;
    }
    public void setOpacity(Float opacity) {
        this.opacity = opacity;
    }
}
