package com.happy.gene.pdf.generate.model.attributes;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class PageOption implements ICloneable
{

    public static PageOption newInstance() { return new PageOption(); }
    public static PageOption newInstance(String inWhichCatalog, boolean generateNewPage, boolean startCountPage, Integer startCountPageAt, Integer fixPageAt, Boolean repaintAfterAllPaint)
    {
        return new PageOption(inWhichCatalog, generateNewPage, startCountPage, startCountPageAt, fixPageAt, repaintAfterAllPaint);
    }

    private String  inWhichCatalog       = null;
    private Boolean generateNewPage      = false;
    private Boolean startCountPage       = false;
    private Integer startCountPageAt     = 1;
    private Integer fixPageAt            = null;
    private Boolean repaintAfterAllPaint = false;

    // variable
    private Integer startPage       = null;
    private Integer endPage         = null;
    private Integer startPageInPdf  = null;
    private Integer endPageInPdf = null;

    public PageOption() {}
    public PageOption(String inWhichCatalog,  Boolean generateNewPage,
                      Boolean startCountPage, Integer startCountPageAt,
                      Integer fixPageAt, Boolean repaintAfterAllPaint)
    {
        this.inWhichCatalog       = inWhichCatalog;
        this.generateNewPage      = generateNewPage;
        this.startCountPage       = startCountPage;
        this.startCountPageAt     = startCountPageAt;
        this.fixPageAt            = fixPageAt;
        this.repaintAfterAllPaint = repaintAfterAllPaint;
    }


    public String  inWhichCatalog() { return inWhichCatalog; }
    public Boolean generateNewPage() { return generateNewPage; }
    public Boolean startCountPage() { return startCountPage; }
    public Integer startCountPageAt() { return startCountPageAt; }
    public Integer fixPageAt() { return fixPageAt; }
    public Boolean repaintAfterAllPaint() { return repaintAfterAllPaint; }



    public PageOption inWhichCatalog(String inWhichCatalog) { this.inWhichCatalog = inWhichCatalog; return this; }
    public PageOption generateNewPage(Boolean generateNewPage) { this.generateNewPage = generateNewPage; return this; }
    public PageOption startCountPage(Boolean startCountPage) { this.startCountPage = startCountPage; return this; }
    public PageOption startCountPageAt(Integer startCountPageAt) { this.startCountPageAt = startCountPageAt; return this; }
    public PageOption fixPageAt(Integer fixPageAt) { this.fixPageAt = fixPageAt; return this; }
    public PageOption repaintAfterAllPaint(Boolean repaintAfterAllPaint) { this.repaintAfterAllPaint = repaintAfterAllPaint; return this; }

    //====================================
    // variable
    //====================================
    public Integer startPage() { return startPage; }
    public Integer endPage() { return endPage; }
    public Integer startPageInPdf() { return startPageInPdf; }
    public Integer endPageInPdf() { return endPageInPdf; }


    public PageOption startPage(Integer startPage) { this.startPage = startPage; return this; }
    public PageOption endPage(Integer endPage) { this.endPage = endPage; return this; }
    public PageOption startPageInPdf(Integer startPageInPdf) { this.startPageInPdf = startPageInPdf; return this; }
    public PageOption endPageInPdf(Integer endPageInPdf) { this.endPageInPdf = endPageInPdf; return this; }

    //====================================
    // ICloneable
    //====================================
    @Override
    public ICloneable clone(Object dest)
    {
        if (!(dest instanceof PageOption)) { return null; }

        ((PageOption) dest).inWhichCatalog       = inWhichCatalog;
        ((PageOption) dest).generateNewPage      = generateNewPage;
        ((PageOption) dest).startCountPage       = startCountPage;
        ((PageOption) dest).startCountPageAt     = startCountPageAt;
        ((PageOption) dest).fixPageAt            = fixPageAt;
        ((PageOption) dest).repaintAfterAllPaint = repaintAfterAllPaint;

        return (ICloneable) dest;
    }

    @Override
    public ICloneable createBlank() { return new PageOption(); }
}
