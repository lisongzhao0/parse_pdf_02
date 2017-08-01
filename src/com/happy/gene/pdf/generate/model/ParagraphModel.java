package com.happy.gene.pdf.generate.model;

import com.happy.gene.pdf.generate.ICloneable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaolisong on 25/07/2017.
 */
public class ParagraphModel extends AbstractModel {

    public static ParagraphModel newInstance() { return new ParagraphModel(); }
    public static ParagraphModel newInstance(String value) { return new ParagraphModel(value); }

    private String value;

    public ParagraphModel() {};
    public ParagraphModel(String value) {
        value(value);
    }

    public String value() { return value; }
    public ParagraphModel value(String value) { this.value = value; return this; }

    public String replaceBlankTag(String blankValue) {
        if (null==blankValue || blankValue.trim().length()==0) { return blankValue; }

        blankValue = blankValue.replace("<br/>", "\n");
        blankValue = blankValue.replace("&nbsp;", " ");
        blankValue = blankValue.replace("&tab;", "\t");
        blankValue = blankValue.replace('®', '\u00ae');

        return blankValue;
    }
    public List<String> allPhrases(String value) {
        if (null==value) {
            return null;
        }

        StringBuilder buffer  = new StringBuilder();
        List<String>  phrases = new ArrayList<>();
        for (int i = 0; i < value.length(); i ++) {
            buffer.append(value.charAt(i));
            if (buffer.lastIndexOf("<style")>0){
                phrases.add(buffer.substring(0, buffer.lastIndexOf("<style")));
                buffer.delete(0, buffer.lastIndexOf("<style"));
            }
            if (buffer.lastIndexOf("</style>")>0) {
                phrases.add(buffer.substring(0, buffer.lastIndexOf("</style>")+"</style>".length()));
                buffer.delete(0, buffer.lastIndexOf("</style>")+"</style>".length());
            }
        }
        if (buffer.length()>0) {
            if (phrases.size()<=0)
            { phrases.add(buffer.toString()); }
            else
            {
                String lastOne = phrases.get(phrases.size() - 1);
                if (lastOne.endsWith("</style>"))
                { phrases.add(buffer.toString()); }
                else
                {
                    phrases.remove(phrases.size() - 1);
                    phrases.add(lastOne+buffer.toString());
                }
            }
        }

        return phrases;
    }

    @Override public ICloneable createBlank() { return new ParagraphModel(); }
    @Override public ICloneable clone(Object dest) {
        if (!(dest instanceof ParagraphModel)) { return null; }
        super.clone(dest);

        ((ParagraphModel) dest).value = value;

        return (ICloneable) dest;
    }


    public static class StyleDef extends AbstractModel {

        private String value;

        public String value() { return value; }
        public StyleDef value(String value) { this.value = value; return this; }

        @Override public ICloneable createBlank() { return new StyleDef(); }
        @Override public ICloneable clone(Object dest) {
            if (!(dest instanceof StyleDef)) { return null; }
            super.clone(dest);

            ((StyleDef) dest).value = value;

            return (ICloneable) dest;
        }

    }
}
