package test.office.ten.gene;

import test.office.utility.DateUtil;
import org.apache.poi.xwpf.usermodel.*;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultDocument;
import org.dom4j.tree.DefaultElement;
import test.office.word.IWordParser;

import java.io.*;
import java.util.*;


/**
 * Created by zhaolisong on 26/07/2017.
 */
public class TenGeneWordParser implements IWordParser {

    public static TenGeneWordParser newInstance() { return new TenGeneWordParser(); }
    public static TenGeneWordParser newInstance(String documentPath, String temporaryPath) { return new TenGeneWordParser(documentPath, temporaryPath); }

    public static final int USER_INFO               = 0x01;
    public static final int DIAGNOSES_FAMILY_HIS    = 0x02;
    public static final int PROGRAM_CONTENT         = 0x04;
    public static final int CHECK_RESULT            = 0x08;
    public static final int TARGET_DRUG_SUMMARY     = 0x10;
    public static final int GENE_SITE_CHECKED       = 0x20;
    public static final int CANCER_GENE_SUMMARY     = 0x40;
    public static final int FDA_NCCN_SUMMARY        = 0x80;
    public static final int REFERENCES              = 0x100;

    private String       documentPath;
    private String       temporaryPath;
    private InputStream  is;
    private XWPFDocument wordDoc;

    private DefaultParagraphProcessor defaultParagraphProcessor = new DefaultParagraphProcessor();

    public TenGeneWordParser() {}
    public TenGeneWordParser(String documentPath, String temporaryPath) { setDocumentPath(documentPath); setTemporaryPath(temporaryPath); }

    @Override public String getDocumentPath() { return this.documentPath; }
    @Override public void setDocumentPath(String documentPath) { this.documentPath = documentPath; }
    @Override public String getTemporaryPath() { return temporaryPath; }
    @Override public void setTemporaryPath(String temporaryPath) { this.temporaryPath = temporaryPath; }
    @Override public void openDocument() throws Exception {
        this.is      = new FileInputStream(documentPath);
        this.wordDoc = new XWPFDocument(is);
    }
    @Override public void closeDocument() throws Exception { this.is.close(); }
    @Override public int chapterIndex(IBodyElement bodyEle) {
        if (!(bodyEle instanceof XWPFParagraph)) { return -1; }

        XWPFParagraph paragraph = (XWPFParagraph) bodyEle;

        if (null!=paragraph.getStyle() && paragraph.getText().contains("样本提供者信息")) {
            return USER_INFO;
        }
        else if (null!=paragraph.getStyle() && paragraph.getText().contains("临床诊断及肿瘤家族史调查")) {
            return DIAGNOSES_FAMILY_HIS;
        }
        else if (null!=paragraph.getStyle() && paragraph.getText().contains("项目内容")) {
            return PROGRAM_CONTENT;
        }
        else if (null!=paragraph.getStyle() && paragraph.getText().contains("检测结果汇总")) {
            return CHECK_RESULT;
        }
        else if (null!=paragraph.getStyle() && paragraph.getText().contains("靶向药物简述")) {
            return TARGET_DRUG_SUMMARY;
        }
        else if (null!=paragraph.getStyle() && paragraph.getText().contains("具体检出位点")) {
            return GENE_SITE_CHECKED;
        }
        else if (null!=paragraph.getStyle() && paragraph.getText().contains("疾病与基因简介")) {
            return CANCER_GENE_SUMMARY;
        }
        else if (paragraph.getText().contains("FDA") && paragraph.getText().contains("NCCN指导意见")) {
            return FDA_NCCN_SUMMARY;
        }
        else if (paragraph.getText().contains("参考文献")) {
            return REFERENCES;
        }

        return -1;
    }
    public boolean isChapter(int chapterIndex) {
        if (chapterIndex<0) { return false; }


        return (chapterIndex==USER_INFO) ||
                (chapterIndex==DIAGNOSES_FAMILY_HIS) ||
                (chapterIndex==PROGRAM_CONTENT) ||
                (chapterIndex==CHECK_RESULT) ||
                (chapterIndex== TARGET_DRUG_SUMMARY) ||
                (chapterIndex== GENE_SITE_CHECKED) ||
                (chapterIndex==CANCER_GENE_SUMMARY) ||
                (chapterIndex==FDA_NCCN_SUMMARY) ||
                (chapterIndex==REFERENCES);
    }

    @Override public Map<String, Object> parseDocument(Map<String, Object> result) {
        if (null==result) { result = new HashMap<>(); }

        List<IBodyElement>  allDocThing = new ArrayList<>();
        ListIterator        allThing    = wordDoc.getBodyElements().listIterator();
        while(allThing.hasNext()) { allDocThing.add((IBodyElement) allThing.next()); }


        String reportDate = null;
        int currentChapter = 0;
        for (int iBE = 0, iCount = allDocThing.size(); iBE < iCount; iBE ++) {
            IBodyElement bodyEle = allDocThing.get(iBE);
            if (bodyEle instanceof XWPFParagraph && ((XWPFParagraph) bodyEle).getText().trim().startsWith("报告日期：")) {
                reportDate = ((XWPFParagraph) bodyEle).getText().trim().substring(((XWPFParagraph) bodyEle).getText().trim().indexOf("报告日期：")+5).trim();
                result.put("reportDate", reportDate);
            }
            currentChapter = chapterIndex(bodyEle);
            if (isChapter(currentChapter)) {
                if (currentChapter==USER_INFO) {
                    iBE = parseUserInfo(allDocThing, iBE+1, result);
                    if (isChapter(chapterIndex(allDocThing.get(iBE)))) {
                        iBE --;
                    }
                }
                else if (currentChapter==DIAGNOSES_FAMILY_HIS) {
                    iBE = parseDiagnosesAndFamilyHis(allDocThing, iBE+1, result);
                    if (isChapter(chapterIndex(allDocThing.get(iBE)))) {
                        iBE --;
                    }
                }
                else if (currentChapter==PROGRAM_CONTENT) {
                    iBE = parseProgramContent(allDocThing, iBE+1, result);
                    if (isChapter(chapterIndex(allDocThing.get(iBE)))) {
                        iBE --;
                    }
                }
                else if (currentChapter==CHECK_RESULT) {
                    iBE = parseCheckResult(allDocThing, iBE+1, result);
                    if (isChapter(chapterIndex(allDocThing.get(iBE)))) {
                        iBE --;
                    }
                }
                else if (currentChapter==TARGET_DRUG_SUMMARY) {
                    iBE = parseTargetDrugSummary(allDocThing, iBE+1, result);
                    if (isChapter(chapterIndex(allDocThing.get(iBE)))) {
                        iBE --;
                    }
                }
                else if (currentChapter==GENE_SITE_CHECKED) {
                    iBE = parseGeneSiteChecked(allDocThing, iBE+1, result);
                    if (isChapter(chapterIndex(allDocThing.get(iBE)))) {
                        iBE --;
                    }
                }
                else if (currentChapter==CANCER_GENE_SUMMARY) {
                    iBE = parseGeneCancerSummary(allDocThing, iBE+1, result);
                    if (isChapter(chapterIndex(allDocThing.get(iBE)))) {
                        iBE --;
                    }
                }
                else if (currentChapter==FDA_NCCN_SUMMARY) {
                    iBE = parseFDA_NCCN(allDocThing, iBE+1, result);
                    if (isChapter(chapterIndex(allDocThing.get(iBE)))) {
                        iBE --;
                    }
                }
                else if (currentChapter==REFERENCES) {
                    iBE = parseReferences(allDocThing, iBE+1, result);
                    if (isChapter(chapterIndex(allDocThing.get(iBE)))) {
                        iBE --;
                    }
                }
            }
        }
        return result;
    }

    /** chapter 1 */
    public int parseUserInfo(List<IBodyElement> allDocThing, int currentIndex, Map<String, Object> result) {
        if (null==allDocThing || null==result) { return currentIndex; }


        String      name            = null;
        String      sampleNumb      = null;
        String      geneCheckDate   = null;

        XWPFTable table          = null;
        XWPFTableRow row         = null;
        List<XWPFTableCell> cols = null;
        XWPFTableCell colContent = null;
        StringBuilder partTableC = new StringBuilder();

        for (int iBE = currentIndex, iCount = allDocThing.size(); iBE < iCount; iBE ++) {
            // record the scanned index
            currentIndex = iBE;

            IBodyElement bodyEle = allDocThing.get(iBE);
            if (isChapter(chapterIndex(bodyEle))) { break; }

            if (!(bodyEle instanceof XWPFTable)) { continue; }

            table = (XWPFTable) bodyEle;
            int rowSize = table.getNumberOfRows();

            partTableC.append("<table id='userReportInfo'>\n<headers></headers>\n<rows>\n");
            for (int iR = 0; iR < rowSize; iR++) {
                row = table.getRow(iR);
                cols = row.getTableCells();
                for (int iC = 0, iColSize = null == cols ? 0 : cols.size(); iC < iColSize; iC++) {
                    colContent = cols.get(iC);
                    if (null == colContent || null == colContent.getText()) { continue; }

                    if (2==iC) { continue; }

                    if (colContent.getText().contains("姓名")) {
                        name =  cols.get(iC+1).getText();
                    }
                    if (colContent.getText().contains("样本编号")) {
                        sampleNumb =  cols.get(iC+1).getText();
                    }
                    if (colContent.getText().contains("接收日期")) {
                        geneCheckDate = cols.get(iC+1).getText();
                    }

                    String              strPara     = "";
                    List<XWPFParagraph> cellContent = colContent.getParagraphs();
                    for (int iPara = 0, cPara = cellContent.size(); iPara < cPara; iPara ++) {
                        strPara += defaultParagraphProcessor.parseParagraph(cellContent.get(iPara), null, null, null, null, "italic='true'", iPara+1==cPara ? "" : "<br/>", "<br/>");
                    }
                    partTableC.append("<cell")
                            .append("  row='").append(iR)
                            .append("' column='").append(iC>2 ? iC-1 : iC)
                            .append("' value=''><![CDATA[").append(strPara)
                            .append("]]></cell>\n");
                }
            }
            partTableC.append("</rows>\n</table>");
        }
        result.put("userReportInfo", partTableC.toString());
        result.put("name",           (null!=name && "".equals(name.trim())) ? "　" : name);
        result.put("sampleNumb",     (null!=sampleNumb && "".equals(sampleNumb.trim())) ? "　" : sampleNumb);
        result.put("geneCheckDate",     (null!=geneCheckDate && "".equals(geneCheckDate.trim())) ? "　" : geneCheckDate);
        return currentIndex;
    }

    /** chapter 2 */
    public int parseDiagnosesAndFamilyHis(List<IBodyElement> allDocThing, int currentIndex, Map<String, Object> result) {
        if (null==allDocThing || null==result) { return currentIndex; }


        XWPFParagraph paragraph  = null;
        XWPFTable table      = null;
        XWPFTableRow row        = null;
        List<XWPFTableCell> cols = null;
        XWPFTableCell colTitle   = null;
        XWPFTableCell colContent = null;
        StringBuilder partTableC = new StringBuilder();

        for (int iBE = currentIndex, iCount = allDocThing.size(); iBE < iCount; iBE ++) {
            // record the scanned index
            currentIndex = iBE;

            IBodyElement bodyEle = allDocThing.get(iBE);
            if (isChapter(chapterIndex(bodyEle))) { break; }

            if (!(bodyEle instanceof XWPFTable)) { continue; }

            table = (XWPFTable) bodyEle;
            int rowSize = table.getNumberOfRows();

            partTableC.append("<table id='diagnosesAndFamilyHistory'>\n<headers></headers>\n<rows>\n");
            for (int iR = 0; iR < rowSize; iR++) {
                row = table.getRow(iR);
                cols = row.getTableCells();
                for (int iC = 0, iColSize = null == cols ? 0 : cols.size(); iC < iColSize; iC++) {
                    colContent = cols.get(iC);
                    if (null == colContent || null == colContent.getText()) { continue; }

                    if (2==iC) { continue; }

                    String              strPara     = "";
                    List<XWPFParagraph> cellContent = colContent.getParagraphs();
                    for (int iPara = 0, cPara = cellContent.size(); iPara < cPara; iPara ++) {
                        strPara += defaultParagraphProcessor.parseParagraph(cellContent.get(iPara), null, null, null, null, "italic='true'", iPara+1==cPara ? "" : "<br/>", "<br/>");
                    }
                    partTableC.append("<cell")
                            .append("  row='").append(iR)
                            .append("' column='").append(iC>2 ? iC-1 : iC)
                            .append("' value=''><![CDATA[").append(strPara)
                            .append("]]></cell>\n");
                }
            }
            partTableC.append("</rows>\n</table>");
        }
        result.put("diagnosesAndFamilyHistory", partTableC.toString());
        return currentIndex;
    }

    /** chapter 3 */
    public int parseProgramContent(List<IBodyElement> allDocThing, int currentIndex, Map<String, Object> result) {
        if (null==allDocThing || null==result) { return currentIndex; }

        XWPFParagraph paragraph     = null;
        List<XWPFRun> runs          = null;
        XWPFRun       run           = null;
        StringBuilder programContent  = new StringBuilder();

        for (int iBE = currentIndex, iCount = allDocThing.size(); iBE < iCount; iBE ++) {
            // record the scanned index
            currentIndex = iBE;

            IBodyElement bodyEle = allDocThing.get(iBE);
            if (isChapter(chapterIndex(bodyEle))) { break; }


            if (bodyEle instanceof XWPFParagraph) {
                paragraph = (XWPFParagraph) bodyEle;
                if (null==paragraph.getText() || "".equals(paragraph.getText().trim())) { continue; }

                String style = defaultParagraphProcessor.parseParagraph(paragraph, null, null, null, "bold='true'", "italic='true'", "<br/>", "<br/>");
                if (style.startsWith("1")) { style = style.replace("1. ", "· "); }
                if (style.startsWith("2")) { style = style.replace("2. ", "· "); }
                if (style.startsWith("3")) { style = style.replace("3. ", "· "); }
                if (style.startsWith("4")) { style = style.replace("4. ", "· "); }
                if (style.startsWith("5")) { style = style.replace("5. ", "· "); }
                if (style.startsWith("6")) { style = style.replace("6. ", "· "); }
                if (style.startsWith("7")) { style = style.replace("7. ", "· "); }
                if (style.startsWith("临")) { style = style.replace("临", "· 临"); }

                programContent.append(style);
            }
        }
        result.put("programContent", programContent.toString());
        return currentIndex;
    }

    /** chapter 4 */
    public int parseCheckResult(List<IBodyElement> allDocThing, int currentIndex, Map<String, Object> result) {
        if (null==allDocThing || null==result) { return currentIndex; }


        XWPFParagraph       paragraph   = null;
        StringBuilder       part1C      = new StringBuilder();
        StringBuilder       part2C      = new StringBuilder();
        StringBuilder       part3C      = new StringBuilder();

        XWPFTable           table       = null;
        XWPFTableRow        row         = null;
        List<XWPFTableCell> cols        = null;
        XWPFTableCell       colContent  = null;
        StringBuilder       partTableC  = new StringBuilder();

        boolean part1     = true;

        for (int iBE = currentIndex, iCount = allDocThing.size(); iBE < iCount; iBE ++) {
            // record the scanned index
            currentIndex = iBE;

            IBodyElement bodyEle = allDocThing.get(iBE);
            if (isChapter(chapterIndex(bodyEle))) { break; }


            if (bodyEle instanceof XWPFParagraph) {
                if (part1) {
                    paragraph = (XWPFParagraph) bodyEle;
                    if (null == paragraph.getText()) { continue; }

                    String style = defaultParagraphProcessor.parseParagraph(paragraph, null, null, "font_color='#607b3f'", "font='fzltchjw'", "italic='true'", "<br/>", "<br/>");
                    part1C.append(style);
                }
                else {
                    paragraph = (XWPFParagraph) bodyEle;
                    if (null == paragraph.getText()) { continue; }

                    if (paragraph.getText().contains("用药提示")) {
                        String style = defaultParagraphProcessor.parseParagraph(paragraph, null, null, null, "font='fzltchjw'", "italic='true'", "<br/>", "<br/>");
                        part2C.append(style);
                    }
                    else {
                        if (paragraph.getText().trim().equals("\n")) { continue; }
                        if (paragraph.getText().trim().contains("注：")) {
                            String style = defaultParagraphProcessor.parseParagraph(paragraph, null, null, null, null, null, "", "<br/>");
                            part3C.append(style);
                            continue;
                        }
                        String style = defaultParagraphProcessor.parseParagraph(paragraph, null, null, "font_color='#607b3f'", "font='fzltchjw'", "italic='true'", "\n", "<br/>");
                        part2C.append(style);
                    }
                }
            }
            else if (bodyEle instanceof XWPFTable) {
                part1 = false;

                table = (XWPFTable) bodyEle;
                int rowSize = table.getNumberOfRows();

                partTableC.append("<table id='checkResultPartTable'>\n<headers></headers>\n<rows>\n");
                for (int iR = 3; iR < rowSize; iR++) {
                    row = table.getRow(iR);
                    cols = row.getTableCells();
                    for (int iC = 0, iColSize = null == cols ? 0 : cols.size(); iC < iColSize; iC++) {
                        colContent = cols.get(iC);
                        if (null == colContent || null == colContent.getText()) { continue; }

                        String              strPara     = "";
                        List<XWPFParagraph> cellContent = colContent.getParagraphs();
                        for (int iPara = 0, cPara = cellContent.size(); iPara < cPara; iPara ++) {
                            strPara += defaultParagraphProcessor.parseParagraph(cellContent.get(iPara), null, null, null, null, "italic='true'", iPara+1==cPara ? "" : "<br/>", "<br/>");
                        }
                        partTableC.append("<cell")
                                .append("  row='").append(iR-3)
                                .append("' column='").append(iC)
                                .append("' value=''><![CDATA[").append(strPara)
                                .append("]]></cell>\n");
                    }
                }
                partTableC.append("</rows>\n</table>");
            }
        }
        result.put("checkResultPart1", "".equals(part1C.toString().trim()) ? "　" : part1C.toString());
        part1C.setLength(0);

        result.put("checkResultPartTable", partTableC.toString());

        result.put("checkResultPart2", "".equals(part2C.toString().trim()) ? "　" : part2C.toString());
        part2C.setLength(0);

        result.put("checkResultPart3", "".equals(part3C.toString().trim()) ? "　" : part3C.toString());
        part3C.setLength(0);

        return currentIndex;
    }

    /** chapter 5 */
    public int parseTargetDrugSummary(List<IBodyElement> allDocThing, int currentIndex, Map<String, Object> result) {
        if (null==allDocThing || null==result) { return currentIndex; }



        XWPFTable           table       = null;
        XWPFTableRow        row         = null;
        List<XWPFTableCell> cols        = null;
        XWPFTableCell       colContent  = null;
        StringBuilder       partDrugS   = new StringBuilder();
        StringBuilder       drugTable   = new StringBuilder();

        boolean isTargetDrugSummary = false;
        boolean isTargetDrug        = false;

        for (int iBE = currentIndex, iCount = allDocThing.size(); iBE < iCount; iBE ++) {
            // record the scanned index
            currentIndex = iBE;

            IBodyElement bodyEle = allDocThing.get(iBE);
            if (isChapter(chapterIndex(bodyEle))) { break; }

            if (!(bodyEle instanceof XWPFTable)) { continue; }

            table = (XWPFTable) bodyEle;
            int rowSize = table.getNumberOfRows();
            if (rowSize<1) { continue; }
            row = table.getRow(0);
            int colSize = row.getTableCells().size();
            if (colSize<1) { continue; }
            isTargetDrug        = table.getRow(0).getCell(0).getText().contains("靶向药物");
            isTargetDrugSummary = table.getRow(0).getCell(0).getText().contains("药物简介");

            if (isTargetDrug) {
                drugTable.append("<table id='targetDrug'>\n<headers>\n");
                for (int iR = 0; iR < rowSize; iR++) {
                    row = table.getRow(iR);
                    if (1==iR) {
                        drugTable.append("</headers>\n<rows>\n");
                    }
                    cols = row.getTableCells();
                    for (int iC = 1, iColSize = null == cols ? 0 : cols.size(); iC < iColSize; iC++) {
                        colContent = cols.get(iC);
                        if (null == colContent || null == colContent.getText()) { continue; }

                        if (0 == iR) {
                            drugTable.append("<header")
                                    .append("  column='").append(iC-1)
                                    .append("' value=''><![CDATA[").append(colContent.getText())
                                    .append("]]></header>\n");
                        }
                        else {
                            String              strPara     = "";
                            List<XWPFParagraph> cellContent = colContent.getParagraphs();
                            for (int iPara = 0, cPara = cellContent.size(); iPara < cPara; iPara ++) {
                                strPara += defaultParagraphProcessor.parseParagraph(cellContent.get(iPara), null, null, null, null, null, iPara+1==cPara ? "" : "<br/>", "<br/>");
                            }
                            drugTable.append("<cell")
                                    .append("  row='").append(iR-1)
                                    .append("' column='").append(iC-1)
                                    .append("' value=''><![CDATA[").append(strPara)
                                    .append("]]></cell>\n");
                        }

                    }
                }
                drugTable.append("</rows>\n</table>");
            }
            if (isTargetDrugSummary) {
                partDrugS.append("<style font='fzltchjw' font_color='#000000' font_size='10'>药物简介：</style><br/>");
                colContent = row.getCell(1);

                String strPara = "";
                List<XWPFParagraph> cellContent = colContent.getParagraphs();
                for (int iPara = 0, cPara = cellContent.size(); iPara < cPara; iPara ++) {
                    strPara += defaultParagraphProcessor.parseParagraph(cellContent.get(iPara), null, null, "font_color='#607b3f' char_spacing='1.1'", "bold='true'", null, iPara+1==cPara ? "" : "<br/>", "<br/>");
                }
                partDrugS.append(strPara);
            }
        }
        result.put("targetDrugTitle", "靶向药物：");

        result.put("targetDrug", drugTable.toString());

        result.put("targetDrugSummary", partDrugS.toString());

        return currentIndex;
    }

    /** chapter 6 */
    public int parseGeneSiteChecked(List<IBodyElement> allDocThing, int currentIndex, Map<String, Object> result) {
        if (null==allDocThing || null==result) { return currentIndex; }

        XWPFTable           table       = null;
        XWPFTableRow        row         = null;
        List<XWPFTableCell> cols        = null;
        XWPFTableCell       colContent  = null;
        StringBuilder       partDrugS   = new StringBuilder();
        StringBuilder       drugTable   = new StringBuilder();


        for (int iBE = currentIndex, iCount = allDocThing.size(); iBE < iCount; iBE ++) {
            // record the scanned index
            currentIndex = iBE;

            IBodyElement bodyEle = allDocThing.get(iBE);
            if (isChapter(chapterIndex(bodyEle))) { break; }

            if (!(bodyEle instanceof XWPFTable)) { continue; }

            table = (XWPFTable) bodyEle;
            int rowSize = table.getNumberOfRows();
            if (rowSize<1) { continue; }
            row = table.getRow(0);
            int colSize = row.getTableCells().size();
            if (colSize<1) { continue; }

            drugTable.append("<table id='geneSiteChecked'>\n<headers>\n");
            for (int iR = 0; iR < rowSize; iR++) {
                row = table.getRow(iR);
                if (1==iR) {
                    drugTable.append("</headers>\n<rows>\n");
                }
                cols = row.getTableCells();
                for (int iC = 0, iColSize = null == cols ? 0 : cols.size(); iC < iColSize; iC++) {
                    colContent = cols.get(iC);
                    if (null == colContent || null == colContent.getText()) { continue; }

                    if (0 == iR) {
                        drugTable.append("<header")
                                .append("  column='").append(iC)
                                .append("' value=''><![CDATA[").append(colContent.getText())
                                .append("]]></header>\n");
                    }
                    else {
                        String              strPara     = "";
                        List<XWPFParagraph> cellContent = colContent.getParagraphs();
                        for (int iPara = 0, cPara = cellContent.size(); iPara < cPara; iPara ++) {
                            strPara += defaultParagraphProcessor.parseParagraph(cellContent.get(iPara), null, null, null, null, null, iPara+1==cPara ? "" : "<br/>", "<br/>");
                        }
                        drugTable.append("<cell")
                                .append("  row='").append(iR)
                                .append("' column='").append(iC)
                                .append("' value=''><![CDATA[").append(strPara)
                                .append("]]></cell>\n");
                    }

                }
            }
            drugTable.append("</rows>\n</table>");
        }
        result.put("geneSiteChecked", drugTable.toString());

        return currentIndex;
    }

    /** chapter 7 */
    public int parseGeneCancerSummary(List<IBodyElement> allDocThing, int currentIndex, Map<String, Object> result) {
        if (null==allDocThing || null==result) { return currentIndex; }


        XWPFParagraph       paragraph   = null;
        StringBuilder       part1C      = new StringBuilder();
        StringBuilder       part2C      = new StringBuilder();
        StringBuilder       partTableC2 = new StringBuilder();

        XWPFTable           table       = null;
        XWPFTableRow        row         = null;
        List<XWPFTableCell> cols        = null;
        XWPFTableCell       colContent  = null;

        boolean part1     = true;

        for (int iBE = currentIndex, iCount = allDocThing.size(); iBE < iCount; iBE ++) {
            // record the scanned index
            currentIndex = iBE;

            IBodyElement bodyEle = allDocThing.get(iBE);
            if (isChapter(chapterIndex(bodyEle))) { break; }


            if (bodyEle instanceof XWPFParagraph) {
                paragraph = (XWPFParagraph) bodyEle;
                if (null == paragraph.getText()) { continue; }

                if (paragraph.getText().contains("本次检测的基因简介如下")) {
                    String style = defaultParagraphProcessor.parseParagraph(paragraph, null, "font_size='12'", null, "font='fzltchjw'", "italic='true'", "<br/>", "");
                    part2C.append(style).append("<br/>");
                }
                else {
                    String style = defaultParagraphProcessor.parseParagraph(paragraph, null, null, "font_color='#607b3f'", "font='fzltchjw'", "italic='true'", "<br/>", "");
                    part1C.append(style);
                }
            }
            else if (bodyEle instanceof XWPFTable) {
                table = (XWPFTable) bodyEle;
                int rowSize = table.getNumberOfRows();

                row = table.getRow(0);
                if (row.getTableCells().size()>2) {
                    partTableC2.append("<table id='checkResultPartDrugTable'>\n<headers>\n");
                    for (int iR = 0; iR < rowSize; iR++) {
                        row = table.getRow(iR);
                        if (1==iR) {
                            partTableC2.append("</headers>\n<rows>\n");
                        }
                        cols = row.getTableCells();
                        for (int iC = 0, iColSize = null == cols ? 0 : cols.size(); iC < iColSize; iC++) {
                            colContent = cols.get(iC);
                            if (null == colContent || null == colContent.getText()) { continue; }

                            if (0 == iR) {
                                partTableC2.append("<header")
                                        .append("  column='").append(iC)
                                        .append("' value=''><![CDATA[").append(colContent.getText())
                                        .append("]]></header>\n");
                            }
                            else {
                                String              strPara     = "";
                                List<XWPFParagraph> cellContent = colContent.getParagraphs();
                                for (int iPara = 0, cPara = cellContent.size(); iPara < cPara; iPara ++) {
                                    strPara += (new ParagraphProcessor10GeneNCCN()).parseParagraph(cellContent.get(iPara), null, null, null, null, null, "", "");
                                }
                                partTableC2.append("<cell")
                                        .append("  row='").append(iR)
                                        .append("' column='").append(iC)
                                        .append("' value=''><![CDATA[").append(strPara)
                                        .append("]]></cell>\n");
                            }

                        }
                    }
                    partTableC2.append("</rows>\n</table>");
                    continue;
                }
                else {
                    for (int iR = 0; iR < rowSize; iR++) {
                        row = table.getRow(iR);
                        cols = row.getTableCells();


                        colContent = cols.get(0); // 基因检测title
                        String strPara = "";
                        List<XWPFParagraph> cellContent = colContent.getParagraphs();
                        for (int iPara = 0, cPara = cellContent.size(); iPara < cPara; iPara++) {
                            XWPFParagraph cellSubPara = cellContent.get(iPara);
                            if ("".equals(cellSubPara.getText().trim())) {
                                continue;
                            }
                            String tmp = defaultParagraphProcessor.parseParagraph(cellContent.get(iPara), null, "font_size='10'", "font_color='#607b3f'", "bold='true'", "italic='true'", "", "");
                            strPara += tmp;
                        }
                        part2C.append(strPara).append("<br/>");


                        colContent = cols.get(1); // 基因检测简介
                        strPara = "";
                        cellContent = colContent.getParagraphs();
                        for (int iPara = 0, cPara = cellContent.size(); iPara < cPara; iPara++) {
                            XWPFParagraph cellSubPara = cellContent.get(iPara);
                            if ("".equals(cellSubPara.getText().trim())) { continue;}
                            String tmp = defaultParagraphProcessor.parseParagraph(cellSubPara, null, null, "font_color='#607b3f'", "bold='true'", "italic='true'", "<br/>", "<br/>");
                            strPara += tmp;
                        }
                        part2C.append(strPara).append("<br/>");
                    }
                    break;
                }
            }
        }

        // parse sign
        for (int iBE = currentIndex+1, iCount = allDocThing.size(); iBE < iCount; iBE ++) {
            // record the scanned index
            currentIndex = iBE;

            IBodyElement bodyEle = allDocThing.get(iBE);
            if (isChapter(chapterIndex(bodyEle))) { break; }

            if (!(bodyEle instanceof XWPFTable)) { continue; }

            table = (XWPFTable) bodyEle;
            int rowSize = table.getNumberOfRows();

            for (int iR = 0; iR < rowSize; iR++) {
                row = table.getRow(iR);
                cols = row.getTableCells();

                for (int iC = 0; iC < cols.size(); iC++) {
                    colContent = cols.get(iC);
                    if (colContent.getText().contains("检验者")) {
                        colContent = cols.get(iC+1);
                        //将图片写入本地文件
                        String filePath = saveImage(colContent, "jianYanSign");
                        result.put("jianYanSign", filePath);
                    }
                    if (colContent.getText().contains("核对者")) {
                        colContent = cols.get(iC+1);
                        //将图片写入本地文件
                        String filePath = saveImage(colContent, "heDuiSign");
                        result.put("heDuiSign", filePath);
                    }
                    if (colContent.getText().contains("审核者")) {
                        colContent = cols.get(iC+1);
                        //将图片写入本地文件
                        String filePath = saveImage(colContent, "shenHeSign");
                        result.put("shenHeSign", filePath);
                    }
                }
            }
        }
        result.put("geneCancerSummary1", part1C.toString());
        part1C.setLength(0);

        if (!"".equals(partTableC2.toString())) { part2C.insert(0, "<br/>"); }
        result.put("geneCancerSummary2", part2C.toString());
        part2C.setLength(0);

        result.put("checkResultPartDrugTable", partTableC2.toString());

        return currentIndex;
    }

    /** chapter 8 */
    public int parseFDA_NCCN(List<IBodyElement> allDocThing, int currentIndex, Map<String, Object> result) {
        if (null==allDocThing || null==result) { return currentIndex; }


        XWPFTable           table       = null;
        XWPFTableRow        row         = null;
        List<XWPFTableCell> cols        = null;
        XWPFTableCell       colContent  = null;
        StringBuilder       fdaNccnDesc = new StringBuilder();
        StringBuilder       fdaNccnTable= new StringBuilder();


        for (int iBE = currentIndex, iCount = allDocThing.size(); iBE < iCount; iBE ++) {
            // record the scanned index
            currentIndex = iBE;

            IBodyElement bodyEle = allDocThing.get(iBE);
            if (isChapter(chapterIndex(bodyEle))) { break; }

            if (!(bodyEle instanceof XWPFTable)) { continue; }

            table = (XWPFTable) bodyEle;
            int rowSize = table.getNumberOfRows();

            int     currentR    = 0;
            boolean isTableEnd  = false;

            fdaNccnTable.append("<table id='fdaNccnTable'>\n<headers>\n");
            for (currentR = 0; currentR < rowSize; currentR++) {
                row = table.getRow(currentR);
                if (1==currentR) {
                    fdaNccnTable.append("</headers>\n<rows>\n");
                }
                cols = row.getTableCells();
                for (int iC = 0, iColSize = null == cols ? 0 : cols.size(); iC < iColSize; iC++) {
                    colContent = cols.get(iC);
                    if (null == colContent || null == colContent.getText()) { continue; }

                    if (0 == currentR) {
                        fdaNccnTable.append("<header")
                                .append("  column='").append(iC)
                                .append("' value=''><![CDATA[").append(colContent.getText())
                                .append("]]></header>\n");
                    }
                    else {
                        if (colContent.getText().contains("获批适应症")) { isTableEnd = true; break; }

                        String              strPara     = "";
                        List<XWPFParagraph> cellContent = colContent.getParagraphs();
                        for (int iPara = 0, cPara = cellContent.size(); iPara < cPara; iPara ++) {
                            strPara += defaultParagraphProcessor.parseParagraph(cellContent.get(iPara), null, null, null, null, null, iPara+1==cPara ? "" : "<br/>", "<br/>");
                        }
                        fdaNccnTable.append("<cell")
                                .append("  row='").append(currentR)
                                .append("' column='").append(iC)
                                .append("' value=''><![CDATA[").append(strPara)
                                .append("]]></cell>\n");
                    }
                }
                if (isTableEnd) { break; }
            }
            fdaNccnTable.append("</rows>\n</table>");

            // 获批适应症
            fdaNccnDesc.append("<style font='fzltchjw' font_color='#000000' font_size='13'>获批适应症：</style><br/><br/>");
            for (;  currentR < rowSize; currentR++) {
                row = table.getRow(currentR);
                cols = row.getTableCells();

                colContent      = cols.get(0);
                if (colContent.getText().contains("药物简介")) { break; }

                colContent      = cols.get(1);
                String strPara  = "";
                List<XWPFParagraph> cellContent = colContent.getParagraphs();
                for (int iPara = 0, cPara = cellContent.size(); iPara < cPara; iPara ++) {
                    XWPFParagraph cellSubPara = cellContent.get(iPara);
                    if ("".equals(cellSubPara.getText().trim())) { continue; }
                    String tmp = (new ParagraphProcessor10GeneNCCN()).parseParagraph(cellContent.get(iPara), null, "font_size='10' char_spacing='1.1'", "font_color='#607b3f'", "bold='true'", "italic='true'", "", "<br/>");
                    strPara += tmp;
                }
                fdaNccnDesc.append(strPara).append("<br/>");
            }

            // 药物简介
            fdaNccnDesc.append("<br/>");
            fdaNccnDesc.append("<style font='fzltchjw' font_color='#000000' font_size='13'>药物简介：</style><br/><br/>");
            for (;  currentR < rowSize; currentR++) {
                row = table.getRow(currentR);
                cols = row.getTableCells();
                if (cols.size()<2) { continue; }

                colContent      = cols.get(1);
                String strPara  = "";
                List<XWPFParagraph> cellContent = colContent.getParagraphs();
                for (int iPara = 0, cPara = cellContent.size(); iPara < cPara; iPara ++) {
                    XWPFParagraph cellSubPara = cellContent.get(iPara);
                    if ("".equals(cellSubPara.getText().trim())) { continue; }
                    String tmp = (new ParagraphProcessor10GeneNCCN()).parseParagraph(cellContent.get(iPara), null, "font_size='10' char_spacing='1.1'", "font_color='#607b3f'", "bold='true'", "italic='true'", "", "<br/>");
                    strPara += tmp;
                }
                fdaNccnDesc.append(strPara).append("<br/>");
            }
        }

        result.put("fdaNccnTable", fdaNccnTable.toString());
        fdaNccnTable.setLength(0);

        result.put("fdaNccnDescription", fdaNccnDesc.toString());
        fdaNccnDesc.setLength(0);

        return currentIndex;
    }

    /** chapter 9 */
    public int parseReferences(List<IBodyElement> allDocThing, int currentIndex, Map<String, Object> result) {
        if (null==allDocThing || null==result) { return currentIndex; }

        XWPFParagraph       paragraph   = null;
        StringBuilder       part1C      = new StringBuilder();

        int index = 1;
        for (int iBE = currentIndex, iCount = allDocThing.size(); iBE < iCount; iBE ++) {
            // record the scanned index
            currentIndex = iBE;

            IBodyElement bodyEle = allDocThing.get(iBE);
            if (isChapter(chapterIndex(bodyEle))) { break; }

            if (!(bodyEle instanceof XWPFParagraph)) { continue; }

            paragraph = (XWPFParagraph) bodyEle;
            if (null == paragraph.getText()) { continue; }

            if (defaultParagraphProcessor.containChinese(paragraph.getText())) {
                part1C.append(index++).append(". <style font='fzltxh_gbk'>").append(paragraph.getText()).append("</style><br/>");
            }
            else {
                part1C.append(index++).append(". ").append(paragraph.getText()).append("<br/>");
            }
        }
        result.put("appendixReferences", part1C.toString());
        part1C.setLength(0);

        return currentIndex;
    }

    public DefaultDocument toXml(Map<String, Object> parameters) throws DocumentException {
        DefaultDocument document    = new DefaultDocument();
        document.setXMLEncoding("UTF-8");
        if (null==parameters) {
            return null;
        }


        // root element
        Element     elePdf                      = new DefaultElement("pdf");
        document.add(elePdf);

        // data element
        Element     eleData                     = new DefaultElement("data");
        elePdf.add(eleData);

        String key = null;

        // report date and geneCheckDate
        key = "reportDate";
        addParameter(eleData, key, null==parameters.get(key) ? "　" : (String)parameters.get(key));
        key = "geneCheckDate";
        addParameter(eleData, key, reformatDate((String)parameters.get(key)));

        // user info
        key = "userReportInfo";
        addTable(eleData, (String)parameters.get(key));
        key = "name";
        addParameter(eleData, key, (String)parameters.get(key));
        key = "sampleNumb";
        addParameter(eleData, key, (String)parameters.get(key));

        // diagnoses
        key = "diagnosesAndFamilyHistory";
        addTable(eleData, (String)parameters.get(key));

        // program content
        key = "programContent";
        addParameter(eleData, key, (String)parameters.get(key));

        // check result
        key = "checkResultPart1";
        addParameter(eleData, key, (String)parameters.get(key));
        key = "checkResultPartTable";
        addTable(eleData, (String)parameters.get(key));
        key = "checkResultPart2";
        addParameter(eleData, key, (String)parameters.get(key));
        key = "checkResultPart3";
        addParameter(eleData, key, (String)parameters.get(key));

        // target drug summary
        key = "targetDrugSummary";
        if (parameters.containsKey(key) && null!=parameters.get(key)) {
            addParameter(eleData, key, (String) parameters.get(key));
            key = "targetDrug";
            addTable(eleData, (String) parameters.get(key));
            key = "targetDrugTitle";
            addParameter(eleData, key, (String) parameters.get(key));

            addParameter(eleData, "isPositive",       "true");
            addParameter(eleData, "fiveChapterName",  "五、靶向药物简述");
            addParameter(eleData, "sixChapterName",   "六、具体检出位点");
            addParameter(eleData, "sevenChapterName", "七、疾病与基因简介");
        }
        else {
            addParameter(eleData, "isPositive",       "false");
            addParameter(eleData, "fiveChapterName",  "");
            addParameter(eleData, "sixChapterName",   "五、具体检出位点");
            addParameter(eleData, "sevenChapterName", "六、疾病与基因简介");
        }

        // detail gene site checked
        key = "geneSiteChecked";
        addTable(eleData, (String)parameters.get(key));

        // gene cancer summary
        key = "geneCancerSummary1";
        addParameter(eleData, key, (String)parameters.get(key));
        key = "geneCancerSummary2";
        addParameter(eleData, key, (String)parameters.get(key));
        key = "checkResultPartDrugTable";
        if (parameters.containsKey(key) && null!=parameters.get(key) && !"".equals(((String) parameters.get(key)).trim())) {
            addParameter(eleData, "isCheckResultPartDrugVisible", "true");
            addTable(eleData, (String) parameters.get(key));
        }
        else {
            addParameter(eleData, "isCheckResultPartDrugVisible", "false");
        }
        key = "jianYanSign";
        addParameter(eleData, key, null==parameters.get(key) ? "" : (String)parameters.get(key));
        key = "heDuiSign";
        addParameter(eleData, key, null==parameters.get(key) ? "" : (String)parameters.get(key));
        key = "shenHeSign";
        addParameter(eleData, key, null==parameters.get(key) ? "" : (String)parameters.get(key));


        // FDA NCCN
        key = "fdaNccnTable";
        addTable(eleData, (String)parameters.get(key));
        key = "fdaNccnDescription";
        addParameter(eleData, key, (String)parameters.get(key));

        // references
        key = "appendixReferences";
        addParameter(eleData, key, (String)parameters.get(key));


        return document;
    }

    public void addParameter(Element root, String key, String value) {
        Element eleParam    = new DefaultElement("parameter");
        eleParam.addAttribute("id", key).addAttribute("value", "").addCDATA(value);
        root.add(eleParam);
    }

    public void addTable(Element root, String tableXml) throws DocumentException {
        DefaultDocument document = (DefaultDocument) DocumentHelper.parseText(tableXml);
        root.add(document.getRootElement());
    }

    public String reformatDate(String date) {
        DateUtil dateUtil = DateUtil.newInstance();
        if (date.contains("/")) {
            Date dDate = dateUtil.getTime(date, "MM/dd/yyyy");
            if (null==dDate) { return "　"; }
            return dateUtil.timeToString(dDate, "yyyy-MM-dd");
        }
        return date;
    }

    public String saveImage(XWPFTableCell cell, String picName) {
        List<XWPFParagraph> cellContent = cell.getParagraphs();
        XWPFPicture picture = cellContent.get(0).getRuns().get(0).getEmbeddedPictures().get(0);
        //将图片写入本地文件
        byte[] picbyte = picture.getPictureData().getData();
        String filePath = getTemporaryPath()+picName;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            fos.write(picbyte);
        }
        catch (Exception e) { e.printStackTrace(); }
        finally {
            if (null!=fos) {
                try { fos.close(); } catch (IOException e) { e.printStackTrace(); }
            }
        }
        return filePath;
    }
    public static void main(String[] args) throws Exception {
        TenGeneWordParser tenGeneWordParser = TenGeneWordParser.newInstance("/Users/zhaolisong/Downloads/10gene 组织阳性 sample.docx", "/Users/zhaolisong/Downloads/");
        tenGeneWordParser.openDocument();
        Map<String, Object> result = tenGeneWordParser.parseDocument(null);
        tenGeneWordParser.closeDocument();


        Set<String> keys = result.keySet();
        int iii = 0;
        for (String key : keys) {
            if (iii++ % 2 == 0) {
                System.out.println(key + "::==::\n" + result.get(key) + "\n\n");
            }
            else {
                System.err.println(key + "::==::\n" + result.get(key) + "\n\n");
            }
        }
    }

}
