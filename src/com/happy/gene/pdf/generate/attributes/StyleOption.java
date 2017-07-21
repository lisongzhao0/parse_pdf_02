package com.happy.gene.pdf.generate.attributes;

import com.happy.gene.pdf.generate.ICloneable;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class StyleOption implements ICloneable {

    public static StyleOption newInstance() {
        return new StyleOption();
    }

    private String foreground;
    private String background;
    private String fill;
    private float  opacity  = 1.0f;
    private float  lineWidth= 0.0f;

    public StyleOption() {}

    public Color foreground() { return parseColor(foreground, null); }
    public Color background() { return parseColor(background, null); }
    public Color fill() { return parseColor(fill, null); }
    public float opacity() { return opacity; }
    public float lineWidth() { return lineWidth; }

    public StyleOption foreground(String color) {
        foreground = color;
        return this;
    }
    public StyleOption background(String color) {
        background = color;
        return this;
    }
    public StyleOption fill(String color) {
        fill = color;
        return this;
    }
    public StyleOption opacity(float opacity) {
        this.opacity = opacity<0.0f ? 0.0f : (opacity>1.0f ? 1.0f : opacity);
        return this;
    }
    public StyleOption lineWidth(float width) {
        lineWidth = width<0.0f ? 0.00000000000001f : width;
        return this;
    }

    public Color parseColor(String color, Color defaultColor) {
        if (null==color || "".equalsIgnoreCase(color.trim())) {
            return defaultColor;
        }
        color = color.trim().toUpperCase();
        if (color.startsWith("#")) {
            int intColor = 0xffffff;
            try { intColor = Integer.parseInt(color.substring(1), 16); }
            catch (Exception ex) {
                return defaultColor;
            }
            return new DeviceRgb(
                    (intColor >> 16) & 0xFF,
                    (intColor >> 8)  & 0xFF,
                    (intColor >> 0)  & 0xFF
            );
        }
        return defaultColor;
    }
    public String getRandomColor() {
        Double random = Math.random() * 0xffffff;
        String resColor = "#"+Integer.toHexString(random.intValue());
        return resColor;
    }

    //====================================
    // ICloneable
    //====================================
    @Override
    public ICloneable clone(Object dest) {
        if (!(dest instanceof StyleOption)) { return null; }

        ((StyleOption) dest).foreground = foreground;
        ((StyleOption) dest).background = background;
        ((StyleOption) dest).fill       = fill;
        ((StyleOption) dest).opacity    = opacity;
        ((StyleOption) dest).lineWidth  = lineWidth;

        return (ICloneable) dest;
    }
    @Override
    public ICloneable createBlank() {
        return new StyleOption();
    }
}
