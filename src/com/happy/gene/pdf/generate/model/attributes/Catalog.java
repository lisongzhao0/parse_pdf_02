package com.happy.gene.pdf.generate.model.attributes;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 26/07/2017.
 */
public class Catalog implements ICloneable
{

    public static Catalog newInstance() { return new Catalog(); }
    public static Catalog newInstance(String catalog) { return new Catalog(catalog); }

    private String  catalog    = null;
    private Integer pageNumber = null;

    public Catalog() {}
    public Catalog(String catalog) { this.catalog = catalog; }

    public String catalog() { return catalog; }
    public Integer pageNumber() { return pageNumber; }

    public Catalog catalog(String catalog)  { this.catalog = catalog;  return this; }
    public Catalog pageNumber(Integer pageNumber) { this.pageNumber = pageNumber; return this; }

    //====================================
    // ICloneable
    //====================================
    @Override
    public ICloneable clone(Object dest)
    {
        if (!(dest instanceof Catalog)) { return null; }

        ((Catalog) dest).catalog = catalog;

        return (ICloneable) dest;
    }

    @Override
    public ICloneable createBlank() {
        return new Catalog();
    }
}
