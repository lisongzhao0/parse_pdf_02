package com.happy.gene.pdf.generate.attributes;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class PageOption implements ICloneable {

    public static PageOption newInstance() {
        return new PageOption();
    }
    public static PageOption newInstance(boolean useNewPage, boolean recordPageNumber, Integer pageNumber, String inWhichCatalog) {
        return new PageOption(useNewPage, recordPageNumber, pageNumber, inWhichCatalog);
    }

    private Boolean pageNumberStart   = false;
    private Integer pageNumberStartAt = 1;
    private Boolean useWholePage      = false;
    private boolean useNewPage        = false;
    private boolean recordPageNumber  = false;
    private String  inWhichCatalog    = null;
    private Integer fixPageNumber     = null;
    // variable
    private Integer pageNumber        = null;

    public PageOption() {}
    public PageOption(boolean useNewPage, boolean recordPageNumber, Integer pageNumber, String inWhichCatalog) {
        this.useNewPage       = useNewPage;
        this.recordPageNumber = recordPageNumber;
        this.pageNumber       = pageNumber;
        this.inWhichCatalog   = inWhichCatalog;
    }


    public boolean pageNumberStart() { return pageNumberStart; }
    public Integer pageNumberStartAt() { return pageNumberStartAt; }
    public boolean useNewPage() { return useNewPage; }
    public boolean recordPageNumber() { return recordPageNumber; }
    public String  inWhichCatalog() { return inWhichCatalog; }
    public Integer fixPageNumber() { return fixPageNumber; }
    public Integer pageNumber() { return pageNumber; }

    public PageOption useNewPage(boolean useNewPage) {
        this.useNewPage = useNewPage;
        return this;
    }
    public PageOption recordPageNumber(boolean recordPageNumber) {
        this.recordPageNumber = recordPageNumber;
        return this;
    }
    public PageOption setInWhichCatalog(String inWhichCatalog) {
        this.inWhichCatalog = inWhichCatalog;
        return this;
    }
    public PageOption pageNumberStart(Boolean pageNumberStart) {
        this.pageNumberStart = pageNumberStart;
        return this;
    }
    public PageOption pageNumberStartAt(Integer pageNumberStartAt) {
        this.pageNumberStartAt = pageNumberStartAt;
        return this;
    }
    public PageOption fixPageNumber(Integer fixPageNumber) {
        this.fixPageNumber = fixPageNumber;
        return this;
    }
    public PageOption pageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    //====================================
    // ICloneable
    //====================================
    @Override
    public ICloneable clone(Object dest) {
        if (!(dest instanceof PageOption)) { return null; }

        ((PageOption) dest).useNewPage       = useNewPage;
        ((PageOption) dest).recordPageNumber = recordPageNumber;
        ((PageOption) dest).pageNumber       = pageNumber;
        ((PageOption) dest).inWhichCatalog   = inWhichCatalog;

        return (ICloneable) dest;
    }
    @Override
    public ICloneable createBlank() {
        return new PageOption();
    }
}
