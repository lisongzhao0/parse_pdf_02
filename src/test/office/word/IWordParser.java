package test.office.word;

import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.Map;

/**
 * Created by zhaolisong on 31/07/2017.
 */
public interface IWordParser {

    String  getDocumentPath();
    void    setDocumentPath(String documentPath);

    void openDocument() throws Exception;
    int chapterIndex(IBodyElement bodyElement);
    Map<String, Object> parseDocument(Map<String, Object> result);
    void closeDocument() throws Exception;
}
