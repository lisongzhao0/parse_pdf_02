package com.happy.gene.pdf.generate.parser;

import com.happy.gene.pdf.generate.model.AbstractModel;
import com.happy.gene.pdf.generate.model.attributes.*;

/**
 * Created by zhaolisong on 25/07/2017.
 */
public interface IElementParser {

    int attributeIndex(Object ... args);
    String attributeName(Object ... args);
    GridOption attributeGridOption(Object ... args);

    Alignment attributeAlignment(Object ... args);
    Dimension attributeDimension(Object ... args);
    FontStyleOption attributeFontStyle(Object ... args);
    Margin attributeMargin(Object ... args);
    Position attributePosition(Object ... args);
    StyleOption attributeStyle(Object ... args);
    PageOption attributePageOption(Object ... args);

    AbstractModel parseModel(Object ... args);
    void setModelAttributes(AbstractModel def, Object ... args);
}
