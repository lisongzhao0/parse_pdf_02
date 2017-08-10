package com.happy.gene.pdf.generate;

/**
 * Created by zhaolisong on 09/08/2017.
 */
public interface I2DTranslation {

    void translate2D(float deltaX, float deltaY);
    void translatePdf(float deltaX, float deltaY);

    void scaleToFit2D(float width, float height);
    void scaleToFitPdf(float width, float height);
    void scaleToFit2D(float size);
    void scaleToFitPdf(float size);


}
