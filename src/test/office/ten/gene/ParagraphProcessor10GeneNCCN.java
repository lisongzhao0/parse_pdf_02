package test.office.ten.gene;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.List;

/**
 * Created by zhaolisong on 01/08/2017.
 */
public class ParagraphProcessor10GeneNCCN extends DefaultParagraphProcessor {
    @Override
    public String parseParagraph(XWPFParagraph paragraph, String font, String fontSize, String color, String bold, String italic, String endReturn, String returnReplacement) {
        if (null==paragraph || null == paragraph.getText()) { return ""; }

        List<XWPFRun>       runs        = null;
        XWPFRun             run         = null;
        XWPFRun             preRun      = null;
        StringBuilder       part2C      = new StringBuilder();

        runs = paragraph.getRuns();
        for (int i = 0, count = null == runs ? 0 : runs.size(); i < count; i++) {
            preRun = run;
            run = runs.get(i);

            String style = null;
            String stylePrefix1 = "<style ";
            String stylePrefix2 = ">";
            String styleSuffix = "</style>";

            if (null==font && null==fontSize && null==color && null==bold && null==italic) {
                style = run.text();
            }
            else {
                if (null != font && !"".equals(font)) {
                    stylePrefix1 += " " + font;
                }
                if (null != fontSize && !"".equals(fontSize)) {
                    stylePrefix1 += " " + fontSize;
                }
                if (null != color && !"".equals(color)) {
                    stylePrefix1 += " " + color;
                }

                bold = null == bold ? "" : bold;
                italic = null == italic ? "" : italic;

                if (run.isBold() && run.isItalic()) {
                    style = stylePrefix1 + " " + bold + " " + italic + stylePrefix2 + run.text() + styleSuffix;
                } else if (run.isBold()) {
                    style = stylePrefix1 + " " + bold + stylePrefix2 + run.text() + styleSuffix;
                } else if (run.isItalic()) {
                    style = stylePrefix1 + " " + italic + stylePrefix2 + run.text() + styleSuffix;
                } else {
                    style = run.text();
                }
            }
            if (null!=preRun && null!=run && (preRun.isBold() || preRun.isItalic()) && (!run.isBold() && !run.isItalic())) {
                part2C.append("<br/>");
            }
            if (style == null) { continue; }
            style = style.replaceAll("[\\n]", returnReplacement);
            part2C.append(style);
        }
        part2C.append(endReturn);

        return part2C.toString();
    }
}
