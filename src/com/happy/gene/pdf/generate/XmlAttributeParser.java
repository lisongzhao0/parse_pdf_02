package com.happy.gene.pdf.generate;

import com.happy.gene.pdf.generate.attributes.*;
import com.happy.gene.pdf.generate.attributes.Dimension;
import com.happy.gene.util.NumberUtil;
import com.happy.gene.util.StringUtil;
import org.dom4j.Element;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class XmlAttributeParser {

    public static XmlAttributeParser newInstance() {
        return new XmlAttributeParser();
    }

    private StringUtil stringUtil = StringUtil.newInstance();
    private NumberUtil numberUtil = NumberUtil.newInstance();

    public Alignment getAlignment(Element ele) {
        if (null==ele) { return Alignment.newInstance(); }
        return Alignment.newInstance(
                ele.attributeValue("align_h"),
                ele.attributeValue("align_v")
        );
    }

    public Dimension getDimension(Element ele) {
        Dimension dimension = Dimension.newInstance();
        if (null != ele) {
            String width  = ele.attributeValue("width");
            String height = ele.attributeValue("height");
            String radius = ele.attributeValue("radius");
            if (numberUtil.isFloat(radius)) { dimension.R(numberUtil.parseFloat(radius)); }
            if (numberUtil.isFloat(width))  { dimension.W(numberUtil.parseFloat(width));  }
            if (numberUtil.isFloat(height)) { dimension.H(numberUtil.parseFloat(height)); }
            if (!numberUtil.isFloat(height) && "undefine".equalsIgnoreCase(height)) {
                dimension.H(Dimension.H_UNDEFINE);
            }
        }
        return dimension;
    }

    public FontStyleOption getFontStyle(Element ele) {
        FontStyleOption fontStyle = FontStyleOption.newInstance();
        if (null==ele) { return fontStyle; }

        String fontName    = ele.attributeValue("font");
        String fontSize    = ele.attributeValue("font-size");
        String textRise    = ele.attributeValue("text-rise");
        String lineLeading = ele.attributeValue("line-leading");
        String charSpacing = ele.attributeValue("char-space");
        String wordSpacing = ele.attributeValue("word-space");
        String bold        = ele.attributeValue("bold");
        String italic      = ele.attributeValue("italic");
        String underline   = ele.attributeValue("underline");

        fontStyle.fontName(fontName)
                .fontSize(numberUtil.isFloat(fontSize) ? numberUtil.parseFloat(fontSize) : fontStyle.fontSize())
                .textRise(numberUtil.isFloat(textRise) ? numberUtil.parseFloat(textRise) : fontStyle.textRise())
                .lineLeading(numberUtil.isFloat(lineLeading) ? numberUtil.parseFloat(lineLeading) : fontStyle.lineLeading())
                .charSpacing(numberUtil.isFloat(charSpacing) ? numberUtil.parseFloat(charSpacing) : fontStyle.charSpacing())
                .wordSpacing(numberUtil.isFloat(wordSpacing) ? numberUtil.parseFloat(wordSpacing) : fontStyle.wordSpacing())
                .bold(Boolean.parseBoolean(bold))
                .italic(Boolean.parseBoolean(italic))
                .underline(Boolean.parseBoolean(underline));

        return fontStyle;
    }

    public Margin getMargins(Element ele) {
        Margin margins = Margin.newInstance();
        if (null==ele) { return margins; }

        String TRBL    = ele.attributeValue("margins");
        if (!stringUtil.isEmpty(TRBL)) {
            margins = Margin.newInstance(TRBL);
        }

        String left    = ele.attributeValue("margins-left");
        String top     = ele.attributeValue("margins-top");
        String right   = ele.attributeValue("margins-right");
        String bottom  = ele.attributeValue("margins-bottom");

        margins.top(numberUtil.isFloat(top) ? numberUtil.parseFloat(top)  : margins.top())
               .left(numberUtil.isFloat(left) ? numberUtil.parseFloat(left) : margins.left())
               .right(numberUtil.isFloat(right) ? numberUtil.parseFloat(right) : margins.right())
               .bottom(numberUtil.isFloat(bottom) ? numberUtil.parseFloat(bottom) : margins.bottom());

        return margins;
    }

    public PageOption getPageOption(Element ele) {
        PageOption pageOption = PageOption.newInstance();
        if (null==ele) { return pageOption; }

        Boolean pageNumberStart   = false;
        Integer pageNumberStartAt = 1;
        boolean useNewPage        = false;
        boolean recordPageNumber  = false;
        String  inWhichCatalog    = ele.attributeValue("catalog");
        Integer fixPageNumber     = null;
        String pageNumber        = ele.attributeValue("catalog");


        String catalog = ele.attributeValue("catalog");
        String top     = ele.attributeValue("page-top");
        String right   = ele.attributeValue("margins-right");
        String bottom  = ele.attributeValue("margins-bottom");

        pageOption.inWhichCatalog(numberUtil.isFloat(top) ? numberUtil.parseFloat(top)  : pageOption.top())
                .left(numberUtil.isFloat(left) ? numberUtil.parseFloat(left) : pageOption.left())
                .right(numberUtil.isFloat(right) ? numberUtil.parseFloat(right) : pageOption.right())
                .bottom(numberUtil.isFloat(bottom) ? numberUtil.parseFloat(bottom) : pageOption.bottom());

        return pageOption;

    }

    public Position getPosition(Element ele) {
        Position position = Position.newInstance();
        if (null==ele) { return position; }

        String X       = ele.attributeValue("x");
        String Y       = ele.attributeValue("y");
        String topY    = ele.attributeValue("y-top");
        String bottomY = ele.attributeValue("y-bottom");

        position.X(numberUtil.isFloat(X) ? numberUtil.parseFloat(X) : position.X())
                .Y(numberUtil.isFloat(Y) ? numberUtil.parseFloat(Y) : position.Y())
                .topY(numberUtil.isFloat(topY) ? numberUtil.parseFloat(topY) : position.topY())
                .bottomY(numberUtil.isFloat(bottomY) ? numberUtil.parseFloat(bottomY) : position.bottomY());

        return position;
    }

}
