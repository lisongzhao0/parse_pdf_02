package com.happy.gene.pdf.generate.model;

import com.happy.gene.pdf.generate.ICloneable;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class ImageModel extends AbstractModel {

    public static ImageModel newInstance() {
        return new ImageModel();
    }
    public static ImageModel newInstance(String id, String url, Float scale) {
        return new ImageModel(id, url, scale);
    }

    private String id;
    private String url;
    private Float  scale;

    public ImageModel() {}
    public ImageModel(String id, String url, Float scale) {
        this.id = id; this.url = url; this.scale = scale;
    }

    public String id() { return id; }
    public String url() { return url; }
    public Float scale() { return scale; }

    public ImageModel id(String id) {
        this.id = id;
        return this;
    }
    public ImageModel url(String url) {
        this.url = url;
        return this;
    }
    public ImageModel scale(Float scale) {
        this.scale = scale;
        return this;
    }

    @Override public ICloneable createBlank() { return new ImageModel(); }
    @Override public ImageModel clone(Object dest) {
        if (!(dest instanceof ImageModel)) { return null; }
        super.clone(dest);

        ((ImageModel) dest).id    = id;
        ((ImageModel) dest).url   = url;
        ((ImageModel) dest).scale = scale;

        return (ImageModel) dest;
    }
}
