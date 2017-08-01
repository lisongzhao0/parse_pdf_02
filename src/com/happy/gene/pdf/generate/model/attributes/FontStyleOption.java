package com.happy.gene.pdf.generate.model.attributes;


import com.happy.gene.pdf.generate.ICloneable;
import com.itextpdf.kernel.font.PdfFont;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class FontStyleOption implements ICloneable {

    public static FontStyleOption newInstance() {
        return new FontStyleOption();
    }
    public static FontStyleOption newInstance(String defaultFont) {
        return new FontStyleOption(defaultFont);
    }

    private String  fontName    = "";
    private Float   fontSize    = 9.0f;
    private Float   lineLeading = 1.2f;
    private Float   charSpacing = null;
    private Float   wordSpacing = null;
    private Float   textRise    = null;
    private boolean bold        = false;
    private boolean italic      = false;
    private boolean underline   = false;
    private PdfFont pdfFont     = null;

    public FontStyleOption() {}
    public FontStyleOption(String defaultFont) { this.fontName = defaultFont; }

    public String fontName() { return fontName; }
    public float fontSize() { return fontSize; }
    public Float lineLeading() { return lineLeading; }
    public Float charSpacing() { return charSpacing; }
    public Float wordSpacing() { return wordSpacing; }
    public Float textRise() { return textRise; }
    public boolean bold() { return bold; }
    public boolean italic() { return italic; }
    public boolean underline() { return underline; }
    public PdfFont pdfFont() { return pdfFont; }

    public FontStyleOption fontName(String fontName) {
        this.fontName = fontName;
        return this;
    }
    public FontStyleOption fontSize(Float size) {
        this.fontSize = size;
        return this;
    }
    public FontStyleOption lineLeading(Float lineLeading) {
        this.lineLeading = lineLeading;
        return this;
    }
    public FontStyleOption charSpacing(Float charSpacing) {
        this.charSpacing = charSpacing;
        return this;
    }
    public FontStyleOption wordSpacing(Float wordSpacing) {
        this.wordSpacing = wordSpacing;
        return this;
    }
    public FontStyleOption textRise(Float textRise) {
        this.textRise = textRise;
        return this;
    }
    public FontStyleOption bold(boolean bold) {
        this.bold = bold;
        return this;
    }
    public FontStyleOption italic(boolean italic) {
        this.italic = italic;
        return this;
    }
    public FontStyleOption underline(boolean underline) {
        this.underline = underline;
        return this;
    }
    public FontStyleOption pdfFont(PdfFont pdfFont) {
        this.pdfFont = pdfFont;
        return this;
    }


    //====================================
    // ICloneable
    //====================================
    @Override
    public ICloneable clone(Object dest) {
        if (!(dest instanceof FontStyleOption)) { return null; }

        ((FontStyleOption) dest).fontName    = fontName;
        ((FontStyleOption) dest).pdfFont     = pdfFont;
        ((FontStyleOption) dest).fontSize    = fontSize;
        ((FontStyleOption) dest).lineLeading = lineLeading;
        ((FontStyleOption) dest).charSpacing = charSpacing;
        ((FontStyleOption) dest).wordSpacing = wordSpacing;
        ((FontStyleOption) dest).textRise    = textRise;
        ((FontStyleOption) dest).bold        = bold;
        ((FontStyleOption) dest).italic      = italic;
        ((FontStyleOption) dest).underline   = underline;

        return (ICloneable) dest;
    }
    @Override
    public ICloneable createBlank() {
        return new FontStyleOption();
    }
}
