package com.happy.gene.pdf.generate.model;

import com.happy.gene.pdf.generate.ICloneable;
import com.happy.gene.pdf.generate.IGridCell;
import com.sun.javafx.geom.Area;

import java.util.ArrayList;
import java.util.List;

/**
 * The origin point is at the left-bottom corner
 *
 * the class defines the Area to show content in PDF
 *
 * Created by zhaolisong on 27/06/2017.
 */
public class AreaModel extends AbstractModel {

    public static AreaModel newInstance() {
        return new AreaModel();
    }
    public static AreaModel newInstance(Boolean repeat, Integer repeatIndex, Float startY) {
        return new AreaModel(repeat, repeatIndex, startY);
    }

    // repeat
    protected Boolean repeat;
    protected int     repeatIndex = 0;
    // all components
    protected List<AbstractModel> components;
    // Cross page
    protected float startY;
    protected float finallyY;

    public AreaModel() {}
    public AreaModel(Boolean repeat, Integer repeatIndex, Float startY) {
        repeat(repeat); repeatIndex(repeatIndex); startY(startY);
    }

    //====================================
    // Repeat
    //====================================
    public boolean repeat() { return this.repeat; }
    public int repeatIndex() { return this.repeatIndex; }

    public AreaModel repeat(Boolean repeat) { this.repeat = null==repeat ? false : repeat; return this; }
    public AreaModel repeatIndex(Integer repeatIndex) { this.repeatIndex = null==repeatIndex ? 0 : repeatIndex; return this; }


    //====================================
    // Cross page
    //====================================
    public float startY() { return startY; }
    public float finallyY() { return finallyY; }

    public AreaModel startY(Float startY) { this.startY = null==startY ? 0.0f : startY; return this; }
    public AreaModel finallyY(float finallyY) { this.finallyY = finallyY; return this; }


    //====================================
    // Sub Components
    //====================================
    public List<AbstractModel> componentsLink() { return components; }
    public List<AbstractModel> components() {
        List<AbstractModel> componentsCopy = new ArrayList<>();
        if (null==components) { return componentsCopy; }

        for (AbstractModel tmp : components) {
            componentsCopy.add((AbstractModel) tmp.clone(tmp.createBlank()));
        }
        return components;
    }
    public int componentsSize() { return null==this.components ? 0 : this.components.size(); }
    public AbstractModel component(int index) { return index>=0 && index<componentsSize() ? this.components.get(index) : null; }

    public AreaModel component(AbstractModel component) {
        if (null==component)  { return this; }
        if (null==this.components) { this.components = new ArrayList<>(); }
        this.components.add(component);
        return this;
    }
    public AreaModel components(List<AbstractModel> components) { this.components = components; return this; }


    //====================================
    // ICloneable
    //====================================
    @Override public ICloneable createBlank() { return new AreaModel(); }
    @Override public ICloneable clone(Object dest) {
        if (!(dest instanceof AreaModel)) { return null; }
        super.clone(dest);

        ((AreaModel) dest).repeat     = repeat;
        ((AreaModel) dest).finallyY   = finallyY;
        ((AreaModel) dest).components = components();

        return (ICloneable) dest;
    }
}
