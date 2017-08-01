package com.happy.gene.pdf.generate.model;

import com.happy.gene.pdf.generate.ICloneable;
import com.happy.gene.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaolisong on 21/07/2017.
 */
public class PathModel extends AbstractModel {

    public static PathModel newInstance() { return new PathModel(); }
    public static PathModel newInstance(String path, String shading) {
        return new PathModel(path, shading);
    }

    private NumberUtil numberUtil = NumberUtil.newInstance();
    private List<Point> points;
    private Shading     shading;

    public PathModel() {}
    public PathModel(String path, String shading) {
        points(path);
        shading(shading);
    }

    public List<Point> pointsLink() { return points; }
    public List<Point> points() { return (new Point()).clone(points, null); }
    public Shading shadingLink() {
        return shading;
    }
    public Shading shading() {
        return null==shading ? null : shading.clone(shading.createBlank());
    }

    public PathModel points(String path) {
        this.points = new ArrayList<>();
        Point point = null;
        String[] ctrlPointsArray = null==path ? new String[]{} : path.split(" ");
        for (int i = 0; i < ctrlPointsArray.length; i ++) {
            String cp = ctrlPointsArray[i];
            if ("m".equalsIgnoreCase(cp) || "l".equalsIgnoreCase(cp)) {
                point = new Point();
                point.type = cp;
                point.x1 = numberUtil.isFloat(ctrlPointsArray[i+1]) ? Float.parseFloat(ctrlPointsArray[i+1]) : null;
                point.y1 = numberUtil.isFloat(ctrlPointsArray[i+2]) ? Float.parseFloat(ctrlPointsArray[i+2]) : null;
                this.points.add(point);
                i += 2;
                continue;
            }
            if ("c".equalsIgnoreCase(cp)) {
                point = new Point();
                point.type = cp;
                point.x1 = numberUtil.isFloat(ctrlPointsArray[i+1]) ? Float.parseFloat(ctrlPointsArray[i+1]) : null;
                point.y1 = numberUtil.isFloat(ctrlPointsArray[i+2]) ? Float.parseFloat(ctrlPointsArray[i+2]) : null;
                point.x2 = numberUtil.isFloat(ctrlPointsArray[i+3]) ? Float.parseFloat(ctrlPointsArray[i+3]) : null;
                point.y2 = numberUtil.isFloat(ctrlPointsArray[i+4]) ? Float.parseFloat(ctrlPointsArray[i+4]) : null;
                point.x3 = numberUtil.isFloat(ctrlPointsArray[i+5]) ? Float.parseFloat(ctrlPointsArray[i+5]) : null;
                point.y3 = numberUtil.isFloat(ctrlPointsArray[i+6]) ? Float.parseFloat(ctrlPointsArray[i+6]) : null;
                this.points.add(point);
                i += 6;
                continue;
            }
            if ("v".equalsIgnoreCase(cp)) {
                point = new Point();
                point.type = cp;
                point.x1 = numberUtil.isFloat(ctrlPointsArray[i+1]) ? Float.parseFloat(ctrlPointsArray[i+1]) : null;
                point.y1 = numberUtil.isFloat(ctrlPointsArray[i+2]) ? Float.parseFloat(ctrlPointsArray[i+2]) : null;
                point.x2 = numberUtil.isFloat(ctrlPointsArray[i+3]) ? Float.parseFloat(ctrlPointsArray[i+3]) : null;
                point.y2 = numberUtil.isFloat(ctrlPointsArray[i+4]) ? Float.parseFloat(ctrlPointsArray[i+4]) : null;
                this.points.add(point);
                i += 4;
                continue;

            }
            if ("y".equalsIgnoreCase(cp)) {
                point = new Point();
                point.type = cp;
                point.x1 = numberUtil.isFloat(ctrlPointsArray[i+1]) ? Float.parseFloat(ctrlPointsArray[i+1]) : null;
                point.y1 = numberUtil.isFloat(ctrlPointsArray[i+2]) ? Float.parseFloat(ctrlPointsArray[i+2]) : null;
                point.x2 = numberUtil.isFloat(ctrlPointsArray[i+3]) ? Float.parseFloat(ctrlPointsArray[i+3]) : null;
                point.y2 = numberUtil.isFloat(ctrlPointsArray[i+4]) ? Float.parseFloat(ctrlPointsArray[i+4]) : null;
                this.points.add(point);
                i += 4;
                continue;

            }
            if ("h".equalsIgnoreCase(cp)) {
                point = new Point();
                point.type = cp;
                this.points.add(point);
            }
        }

        return this;
    }
    public PathModel shading(String shading) {
        String[] shadingArray = null==shading ? new String[]{} : shading.split("[, ;]+");
        if (shadingArray.length>0) {
            this.shading = new Shading();
            this.shading.x1 = numberUtil.isFloat(shadingArray[0]) ? Float.parseFloat(shadingArray[0]) : null;
            this.shading.y1 = numberUtil.isFloat(shadingArray[1]) ? Float.parseFloat(shadingArray[1]) : null;
            this.shading.x2 = numberUtil.isFloat(shadingArray[3]) ? Float.parseFloat(shadingArray[3]) : null;
            this.shading.y2 = numberUtil.isFloat(shadingArray[4]) ? Float.parseFloat(shadingArray[4]) : null;
            this.shading.c1 = shadingArray[2];
            this.shading.c2 = shadingArray[5];
        }
        else {
            this.shading = null;
        }
        return this;
    }
    public PathModel translate(float deltaX, float deltaY) {
        if (null!=this.points) {
            for (Point pnt : this.points) {
                pnt.translate(deltaX, deltaY);
            }
        }
        if (null!=this.shading) {
            this.shading.translate(deltaX, deltaY);
        }
        return this;
    }

    @Override public ICloneable createBlank() {
        return new PathModel();
    }
    @Override public ICloneable clone(Object dest) {
        if (!(dest instanceof PathModel)) { return null; }
        super.clone(dest);

        ((PathModel) dest).points = points();
        ((PathModel) dest).shading = shading();

        return (ICloneable) dest;
    }

    public static class Point implements ICloneable {
        String type = "";
        Float x1 = null;
        Float y1 = null;
        Float x2 = null;
        Float y2 = null;
        Float x3 = null;
        Float y3 = null;

        public Point translate(float deltaX, float deltaY) {
            if ("m".equalsIgnoreCase(type) || "l".equalsIgnoreCase(type)) {
                x1 = null!=x1 ? x1 + deltaX : null;
                y1 = null!=y1 ? y1 + deltaY : null;
            }
            else if ("c".equalsIgnoreCase(type)) {
                x1 = null!=x1 ? x1 + deltaX : null;
                y1 = null!=y1 ? y1 + deltaY : null;
                x2 = null!=x2 ? x2 + deltaX : null;
                y2 = null!=y2 ? y2 + deltaY : null;
                x3 = null!=x3 ? x3 + deltaX : null;
                y3 = null!=y3 ? y3 + deltaY : null;
            }
            else if ("v".equalsIgnoreCase(type) || "y".equalsIgnoreCase(type)) {
                x1 = null!=x1 ? x1 + deltaX : null;
                y1 = null!=y1 ? y1 + deltaY : null;
                x2 = null!=x2 ? x2 + deltaX : null;
                y2 = null!=y2 ? y2 + deltaY : null;
            }
            else if ("h".equalsIgnoreCase(type)) {
                // do nothing
            }

            return this;
        }

        public List<Point> clone(List<Point> src, List<Point> dest) {
            if (null==src || src.isEmpty()) { return new ArrayList<>(); }
            if (null==dest) { dest = new ArrayList<>(); }
            for (Point tmp : src) {
                dest.add(tmp.clone(tmp.createBlank()));
            }
            return dest;
        }

        @Override public Point createBlank() { return new Point(); }
        @Override public Point clone(Object newOne) {
            if (!(newOne instanceof Point)) { return null; }

            ((Point) newOne).type = this.type;
            ((Point) newOne).x1 = null!=this.x1 ? x1.floatValue() : null;
            ((Point) newOne).x2 = null!=this.x2 ? x2.floatValue() : null;
            ((Point) newOne).x3 = null!=this.x3 ? x3.floatValue() : null;
            ((Point) newOne).y1 = null!=this.y1 ? y1.floatValue() : null;
            ((Point) newOne).y2 = null!=this.y2 ? y2.floatValue() : null;
            ((Point) newOne).y3 = null!=this.y3 ? y3.floatValue() : null;

            return (Point) newOne;
        }

    }

    public static class Shading implements ICloneable {
        Float  x1 = 0.0f;
        Float  y1 = 0.0f;
        String c1 = null;
        Float  x2 = 0.0f;
        Float  y2 = 0.0f;
        String c2 = null;

        public Shading translate(float deltaX, float deltaY) {
            x1 = null!=x1 ? x1 + deltaX : null;
            y1 = null!=y1 ? y1 + deltaY : null;
            x2 = null!=x2 ? x2 + deltaX : null;
            y2 = null!=y2 ? y2 + deltaY : null;
            return this;
        }

        @Override public Shading createBlank() { return new Shading(); }
        @Override public Shading clone(Object newOne) {
            if (!(newOne instanceof Shading)) { return null; }

            ((Shading) newOne).x1 = null!=this.x1 ? x1.floatValue() : null;
            ((Shading) newOne).x2 = null!=this.x2 ? x2.floatValue() : null;
            ((Shading) newOne).c1 = c1;
            ((Shading) newOne).y1 = null!=this.y1 ? y1.floatValue() : null;
            ((Shading) newOne).y2 = null!=this.y2 ? y2.floatValue() : null;
            ((Shading) newOne).c2 = c2;

            return (Shading) newOne;
        }
    }
}
