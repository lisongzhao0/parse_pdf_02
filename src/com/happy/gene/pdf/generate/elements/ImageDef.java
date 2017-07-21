package com.happy.gene.pdf.generate.elements;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class ImageDef extends AbstractDef {

    public static ImageDef newInstance() {
        return new ImageDef();
    }
    public static ImageDef newInstance(String id, String url, String scale) {
        return new ImageDef(id, url, scale);
    }

    private String id;
    private String url;
    private String scale;

    public ImageDef() {}
    public ImageDef(String id, String url, String scale) {
        this.id = id; this.url = url; this.scale = scale;
    }

    public String id() { return id; }
    public String url() { return url; }
    public String scale() { return scale; }

    public ImageDef id(String id) {
        this.id = id;
        return this;
    }
    public ImageDef url(String url) {
        this.url = url;
        return this;
    }
    public ImageDef scale(String scale) {
        this.scale = scale;
        return this;
    }

    @Override
    public ImageDef clone(Object dest) {
        if (!(dest instanceof ImageDef)) { return null; }
        super.clone(dest);

        ((ImageDef) dest).id    = id;
        ((ImageDef) dest).url   = url;
        ((ImageDef) dest).scale = scale;

        return (ImageDef) dest;
    }
    @Override
    public ICloneable createBlank() {
        return new ImageDef();
    }
}
