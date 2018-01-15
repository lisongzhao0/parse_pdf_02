package com.happy.gene.pdf.generate.parser;

import com.happy.gene.pdf.generate.model.*;
import com.happy.gene.pdf.generate.model.attributes.*;
import com.happy.gene.util.NumberUtil;
import com.happy.gene.util.StringUtil;
import org.dom4j.Element;

import java.util.List;

/**
 * Created by zhaolisong on 25/07/2017.
 */
public class XmlDefaultElementParser extends AbstractElementParser {

    public static final XmlDefaultElementParser newInstance() { return new XmlDefaultElementParser(); }

    private NumberUtil numberUtil = NumberUtil.newInstance();
    private StringUtil stringUtil = StringUtil.newInstance();

    private XmlDefaultElementParser() {}

    @Override
    public int attributeIndex(Object... args)
    {
        if (null==args || args.length==0) { return -1; }
        if (args[0] instanceof Element)
        {
            String index = ((Element) args[0]).attributeValue("index");
            if (numberUtil.isInteger(index))
            {
                return numberUtil.parseInteger(index);
            }
        }
        return -1;
    }

    @Override
    public String attributeName(Object... args)
    {
        if (null==args || args.length==0) { return null; }
        if (args[0] instanceof Element)
        {
            return ((Element) args[0]).attributeValue("name");
        }
        return null;
    }

    @Override
    public GridOption attributeGridOption(Object... args)
    {
        if (null==args || args.length==0) { return null; }
        if (args[0] instanceof Element)
        {
            String row     = ((Element) args[0]).attributeValue("row");
            String col     = ((Element) args[0]).attributeValue("col");
            String rowspan = ((Element) args[0]).attributeValue("rowspan");
            String colspan = ((Element) args[0]).attributeValue("colspan");

            Integer irow     = numberUtil.isInteger(row)    ? numberUtil.parseInteger(row)      : null;
            Integer icol     = numberUtil.isInteger(col)    ? numberUtil.parseInteger(col)      : null;
            Integer irowspan = numberUtil.isInteger(rowspan)? numberUtil.parseInteger(rowspan)  : null;
            Integer icolspan = numberUtil.isInteger(colspan)? numberUtil.parseInteger(colspan)  : null;

            return GridOption.newInstance(irow, icol, irowspan, icolspan);
        }
        return null;
    }

    @Override
    public Alignment attributeAlignment(Object... args)
    {
        if (null==args || args.length==0) { return null; }
        if (args[0] instanceof Element)
        {
            String hAlign = ((Element) args[0]).attributeValue("h_align");
            String vAlign = ((Element) args[0]).attributeValue("v_align");

            return Alignment.newInstance(hAlign, vAlign);
        }
        return null;
    }

    @Override
    public Dimension attributeDimension(Object... args)
    {
        if (null==args || args.length==0) { return null; }
        if (args[0] instanceof Element)
        {
            String width  = ((Element) args[0]).attributeValue("width");
            String height = ((Element) args[0]).attributeValue("height");
            String radius = ((Element) args[0]).attributeValue("radius");

            float fWidth  = numberUtil.isFloat(width)  ? numberUtil.parseFloat(width)  : 0f;
            float fHeight = numberUtil.isFloat(height) ? numberUtil.parseFloat(height) : 0f;
            float fRadius = numberUtil.isFloat(radius) ? numberUtil.parseFloat(radius) : 0f;

            return Dimension.newInstance(fWidth, fHeight, fRadius);
        }
        return null;
    }

    @Override
    public FontOption attributeFontStyle(Object... args)
    {
        if (null==args || args.length==0) { return null; }
        if (args[0] instanceof Element)
        {
            String name         = ((Element) args[0]).attributeValue("font");
            String size         = ((Element) args[0]).attributeValue("font-size");
            String lineLeading  = ((Element) args[0]).attributeValue("font-height");
            String charSpacing  = ((Element) args[0]).attributeValue("char-spacing");
            String wordSpacing  = ((Element) args[0]).attributeValue("word-spacing");
            String textRise     = ((Element) args[0]).attributeValue("text-rise");
            String boldItalicUnderline = ((Element) args[0]).attributeValue("font-style");
            boldItalicUnderline = stringUtil.isEmpty(boldItalicUnderline) ? "" : boldItalicUnderline.trim().toLowerCase();


            FontOption fontStyle = FontOption.newInstance();
            fontStyle.fontName(name)
                    .fontSize(numberUtil.isFloat(size) ? numberUtil.parseFloat(size) : fontStyle.fontSize())
                    .textRise(numberUtil.isFloat(textRise) ? numberUtil.parseFloat(textRise) : fontStyle.textRise())
                    .lineLeading(numberUtil.isFloat(lineLeading) ? numberUtil.parseFloat(lineLeading) : fontStyle.lineLeading())
                    .charSpacing(numberUtil.isFloat(charSpacing) ? numberUtil.parseFloat(charSpacing) : fontStyle.charSpacing())
                    .wordSpacing(numberUtil.isFloat(wordSpacing) ? numberUtil.parseFloat(wordSpacing) : fontStyle.wordSpacing())
                    .bold(!stringUtil.isEmpty(boldItalicUnderline) ? boldItalicUnderline.contains("bold") : fontStyle.bold())
                    .italic(!stringUtil.isEmpty(boldItalicUnderline) ? boldItalicUnderline.contains("italic") : fontStyle.italic())
                    .underline(!stringUtil.isEmpty(boldItalicUnderline) ? boldItalicUnderline.contains("underline") : fontStyle.underline());


            return fontStyle;
        }
        return null;
    }

    @Override
    public Margin attributeMargin(Object... args)
    {
        if (null==args || args.length==0) { return null; }
        if (args[0] instanceof Element)
        {
            String left   = ((Element) args[0]).attributeValue("margin-left");
            String top    = ((Element) args[0]).attributeValue("margin-top");
            String right  = ((Element) args[0]).attributeValue("margin-right");
            String bottom = ((Element) args[0]).attributeValue("margin-bottom");
            String margin = ((Element) args[0]).attributeValue("margin-trbl");
            if (stringUtil.isEmpty(margin))
            {
                margin = ((Element) args[0]).attributeValue("margin");
            }

            Float fLeft   = numberUtil.isFloat(left)  ? numberUtil.parseFloat(left)  : 0f;
            Float fTop    = numberUtil.isFloat(top)   ? numberUtil.parseFloat(top)   : 0f;
            Float fRight  = numberUtil.isFloat(right) ? numberUtil.parseFloat(right) : 0f;
            Float fBottom = numberUtil.isFloat(bottom)? numberUtil.parseFloat(bottom): 0f;

            Margin eMargin = Margin.newInstance();
            if (!stringUtil.isEmpty(margin)) { eMargin.TRBL(margin); }
            else {
                eMargin.margin(fTop, fRight, fBottom, fLeft);
            }

            return eMargin;
        }
        return null;
    }

    @Override
    public Padding attributePadding(Object... args)
    {
        if (null==args || args.length==0) { return null; }
        if (args[0] instanceof Element)
        {
            String left   = ((Element) args[0]).attributeValue("padding-left");
            String top    = ((Element) args[0]).attributeValue("padding-top");
            String right  = ((Element) args[0]).attributeValue("padding-right");
            String bottom = ((Element) args[0]).attributeValue("padding-bottom");
            String padding= ((Element) args[0]).attributeValue("padding-trbl");
            if (stringUtil.isEmpty(padding))
            {
                padding = ((Element) args[0]).attributeValue("padding");
            }

            Float fLeft   = numberUtil.isFloat(left)  ? numberUtil.parseFloat(left)  : 0f;
            Float fTop    = numberUtil.isFloat(top)   ? numberUtil.parseFloat(top)   : 0f;
            Float fRight  = numberUtil.isFloat(right) ? numberUtil.parseFloat(right) : 0f;
            Float fBottom = numberUtil.isFloat(bottom)? numberUtil.parseFloat(bottom): 0f;

            Padding ePadding = Padding.newInstance();
            if (!stringUtil.isEmpty(padding)) { ePadding.TRBL(padding); }
            else { ePadding.padding(fTop, fRight, fBottom, fLeft); }

            return ePadding;
        }
        return null;
    }

    @Override
    public Position attributePosition(Object... args) {
        if (null==args || args.length==0) { return null; }
        if (args[0] instanceof Element) {
            String x = ((Element) args[0]).attributeValue("x");
            String y = ((Element) args[0]).attributeValue("y");
            String yTop = ((Element) args[0]).attributeValue("y-top");
            String yBottom = ((Element) args[0]).attributeValue("y-bottom");

            Float fX = numberUtil.isFloat(x) ? numberUtil.parseFloat(x)  : null;
            Float fY = numberUtil.isFloat(y) ? numberUtil.parseFloat(y)   : null;
            Float fYTop = numberUtil.isFloat(yTop) ? numberUtil.parseFloat(yTop) : null;
            Float fYBottom = numberUtil.isFloat(yBottom) ? numberUtil.parseFloat(yBottom): null;

            Position position = Position.newInstance();
            position.X(null==fX ? 0f : fX);
            position.Y(null==fY ? 0f : fY);
            position.topY(fYTop);
            position.bottomY(fYBottom);

            return position;
        }
        return null;
    }

    @Override
    public StyleOption attributeStyle(Object... args) {
        if (null==args || args.length==0) { return null; }
        if (args[0] instanceof Element) {
            String foreground= ((Element) args[0]).attributeValue("foreground");
            String background= ((Element) args[0]).attributeValue("background");
            String fill      = ((Element) args[0]).attributeValue("fill");
            String opacity   = ((Element) args[0]).attributeValue("opacity");
            String lineWidth = ((Element) args[0]).attributeValue("line-width");
            String visible   = ((Element) args[0]).attributeValue("visible");
            String dashOn    = ((Element) args[0]).attributeValue("dash-on");
            String dashOff   = ((Element) args[0]).attributeValue("dash-off");

            Float fOpacity   = numberUtil.isFloat(opacity)  ? numberUtil.parseFloat(opacity)  : 1.0f;
            Float fLineWidth = numberUtil.isFloat(lineWidth)? numberUtil.parseFloat(lineWidth): 0.0f;
            Float lDashOn  = numberUtil.isFloat(dashOn)  ? numberUtil.parseFloat(dashOn) : null;
            Float lDashOff = numberUtil.isFloat(dashOff) ? numberUtil.parseFloat(dashOff): null;

            StyleOption styleOption = StyleOption.newInstance();
            styleOption.foreground(foreground);
            styleOption.background(background);
            styleOption.fill(fill);
            styleOption.opacity(fOpacity);
            styleOption.lineWidth(fLineWidth);
            styleOption.visible(visible);
            styleOption.dashOn(lDashOn);
            styleOption.dashOff(lDashOff);

            return styleOption;
        }
        return null;
    }

    @Override
    public PageOption attributePageOption(Object... args) {
        if (null==args || args.length==0) { return null; }
        if (args[0] instanceof Element) {
            String inWhichCatalog       = ((Element) args[0]).attributeValue("in-which-catalog");
            String generateNewPage      = ((Element) args[0]).attributeValue("generate-new-page");
            String startCountPage       = ((Element) args[0]).attributeValue("start-count-page");
            String startCountPageAt     = ((Element) args[0]).attributeValue("start-count-page-at");
            String fixPageAt            = ((Element) args[0]).attributeValue("fix-page-at");
            String repaintAfterAllPaint = ((Element) args[0]).attributeValue("repaint_after_all_paint");

            Boolean bGenerateNewPage    = Boolean.parseBoolean(generateNewPage);
            Boolean bStartCountPage     = Boolean.parseBoolean(startCountPage);
            Integer iStartCountPageAt   = numberUtil.isInteger(startCountPageAt) ? numberUtil.parseInteger(startCountPageAt) : 1;
            Integer iFixPageAt          = numberUtil.isInteger(fixPageAt)        ? numberUtil.parseInteger(fixPageAt)        : null;
            Boolean bRepaintAfterAllPaint   = Boolean.parseBoolean(repaintAfterAllPaint);

            PageOption pageOption = PageOption.newInstance();
            pageOption.inWhichCatalog(inWhichCatalog);
            pageOption.generateNewPage(bGenerateNewPage);
            pageOption.startCountPage(bStartCountPage);
            pageOption.startCountPageAt(iStartCountPageAt);
            pageOption.fixPageAt(iFixPageAt);
            pageOption.repaintAfterAllPaint(bRepaintAfterAllPaint);

            return pageOption;
        }
        return null;
    }

    @Override
    public AbstractModel parseModel(Object... args) {
        if (null==args || args.length==0) { return null; }
        if (args[0] instanceof Element) {
            Element element = (Element) args[0];

            String eleName = element.getName();
            if ("area".equalsIgnoreCase(eleName)) {
                String repeat       = element.attributeValue("repeat");
                String repeatIndex  = element.attributeValue("repeat-index");
                String startY       = element.attributeValue("y-start");

                Boolean bRepeat     = Boolean.parseBoolean(repeat);
                Integer iRepeatIndex= numberUtil.isInteger(repeatIndex)  ? numberUtil.parseInteger(repeatIndex) : null;
                Float   fStartY     = numberUtil.isFloat(startY) ? numberUtil.parseFloat(startY): null;

                AreaModel area = AreaModel.newInstance(bRepeat, iRepeatIndex, fStartY);
                setModelAttributes(area, args);

                Object[] newArgs = new Object[args.length];
                for (int i = 0; i < args.length; i ++) {
                    newArgs[i] = args[i];
                }
                List<Element> children = element.elements();
                for (Element tmp : children) {
                    newArgs[0] = tmp;
                    area.component(parseModel(newArgs));
                }
                return area;
            }
            else if ("circle".equalsIgnoreCase(eleName)) {
                CircleModel circle = CircleModel.newInstance();
                setModelAttributes(circle, args);
                return circle;
            }
            else if ("grid_area".equalsIgnoreCase(eleName)) {
                String colSize = element.attributeValue("col-size");

                Integer lColSize = numberUtil.isInteger(colSize) ? numberUtil.parseInteger(colSize) : 0;

                GridAreaModel gridArea = GridAreaModel.newInstance(lColSize);
                setModelAttributes(gridArea, args);

                Object[] newArgs = new Object[args.length];
                for (int i = 0; i < args.length; i ++) {
                    newArgs[i] = args[i];
                }
                List<Element> children = element.elements();
                for (Element tmp : children) {
                    newArgs[0] = tmp;
                    gridArea.component(parseModel(newArgs));
                }

                return gridArea;
            }
            else if ("image".equalsIgnoreCase(eleName)) {
                String id    = element.attributeValue("id");
                String url   = element.attributeValue("url");
                String scale = element.attributeValue("scale");

                Float lScale  = numberUtil.isFloat(scale)  ? numberUtil.parseFloat(scale) : null;

                ImageModel image = ImageModel.newInstance(id, url, lScale);
                setModelAttributes(image, args);
                return image;
            }
            else if ("chapter".equalsIgnoreCase(eleName)) {
                ChapterModel page = ChapterModel.newInstance();
                setModelAttributes(page, args);

                Object[] newArgs = new Object[args.length];
                for (int i = 0; i < args.length; i ++) {
                    newArgs[i] = args[i];
                }
                List<Element> children = element.elements();
                for (Element tmp : children) {
                    newArgs[0] = tmp;
                    page.component(parseModel(newArgs));
                }

                return page;
            }
            else if ("paragraph".equalsIgnoreCase(eleName)) {
                String value = element.attributeValue("value");

                ParagraphModel paragraph = ParagraphModel.newInstance(value);
                setModelAttributes(paragraph, args);

                return paragraph;
            }
            else if ("path".equalsIgnoreCase(eleName)) {
                String points  = element.attributeValue("path");
                String shading = element.attributeValue("shading");

                PathModel path = PathModel.newInstance(points, shading);
                setModelAttributes(path, args);
                return path;
            }
            else if ("rect".equalsIgnoreCase(eleName)) {
                RectModel rect = RectModel.newInstance();
                setModelAttributes(rect, args);
                return rect;
            }
            else if ("templates".equalsIgnoreCase(eleName)) {
                TemplateModel templates = TemplateModel.newInstance();
                setModelAttributes(templates, args);


                Object[] newArgs = new Object[args.length];
                for (int i = 0; i < args.length; i ++) {
                    newArgs[i] = args[i];
                }
                List<Element> children = element.elements();
                for (Element tmp : children) {
                    newArgs[0] = tmp;
                    AbstractModel condition = parseModel(newArgs);
                    if (condition instanceof TemplateModel.ConditionModel) {
                        templates.condition((TemplateModel.ConditionModel) condition);
                    }
                }

                return templates;
            }
            else if ("condition".equalsIgnoreCase(eleName)) {
                TemplateModel.ConditionModel condition = TemplateModel.ConditionModel.newInstance();
                setModelAttributes(condition, args);

                Object[] newArgs = new Object[args.length];
                for (int i = 0; i < args.length; i ++) {
                    newArgs[i] = args[i];
                }
                List<Element> children = element.elements();
                for (Element tmp : children) {
                    newArgs[0] = tmp;
                    condition.component(parseModel(newArgs));
                }

                return condition;
            }
        }
        return null;
    }

}
