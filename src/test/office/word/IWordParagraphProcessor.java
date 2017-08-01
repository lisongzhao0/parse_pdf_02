package test.office.word;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 * Created by zhaolisong on 01/08/2017.
 */
public interface IWordParagraphProcessor {
    String parseParagraph(XWPFParagraph paragraph, String font, String fontSize, String color, String bold, String italic, String endReturn, String returnReplacement);
}
