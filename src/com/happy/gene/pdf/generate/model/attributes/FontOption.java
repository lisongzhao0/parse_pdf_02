package com.happy.gene.pdf.generate.model.attributes;


import com.happy.gene.pdf.generate.ICloneable;
import com.itextpdf.kernel.font.PdfFont;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class FontOption implements ICloneable
{

    public static FontOption newInstance() { return new FontOption(); }
    public static FontOption newInstance(String defaultFont) { return new FontOption(defaultFont); }

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

    public FontOption() {}
    public FontOption(String defaultFont) { this.fontName = defaultFont; }

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

    public FontOption fontName(String fontName){ this.fontName = fontName; return this; }
    public FontOption fontSize(Float fontSize) { this.fontSize = fontSize; return this; }
    public FontOption textRise(Float textRise) { this.textRise = textRise; return this; }
    public FontOption lineLeading(Float lineLeading) { this.lineLeading = lineLeading; return this; }
    public FontOption charSpacing(Float charSpacing) { this.charSpacing = charSpacing; return this; }
    public FontOption wordSpacing(Float wordSpacing) { this.wordSpacing = wordSpacing; return this; }
    public FontOption bold(boolean bold) { this.bold = bold; return this; }
    public FontOption italic(boolean italic) { this.italic = italic; return this; }
    public FontOption pdfFont(PdfFont pdfFont) { this.pdfFont = pdfFont; return this; }
    public FontOption underline(boolean underline) { this.underline = underline; return this; }


    //====================================
    // ICloneable
    //====================================
    @Override
    public ICloneable clone(Object dest)
    {
        if (!(dest instanceof FontOption)) { return null; }

        ((FontOption) dest).fontName    = fontName;
        ((FontOption) dest).pdfFont     = pdfFont;
        ((FontOption) dest).fontSize    = fontSize;
        ((FontOption) dest).lineLeading = lineLeading;
        ((FontOption) dest).charSpacing = charSpacing;
        ((FontOption) dest).wordSpacing = wordSpacing;
        ((FontOption) dest).textRise    = textRise;
        ((FontOption) dest).bold        = bold;
        ((FontOption) dest).italic      = italic;
        ((FontOption) dest).underline   = underline;

        return (ICloneable) dest;
    }

    @Override
    public ICloneable createBlank() { return new FontOption(); }
}
