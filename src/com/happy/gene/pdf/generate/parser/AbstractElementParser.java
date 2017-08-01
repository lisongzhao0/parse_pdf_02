package com.happy.gene.pdf.generate.parser;

import com.happy.gene.pdf.generate.model.AbstractModel;

/**
 * Created by zhaolisong on 25/07/2017.
 */
public abstract class AbstractElementParser implements IElementParser {

    @Override
    public void setModelAttributes(AbstractModel def, Object... args) {
        if (null==def) { return; }

        def.index(attributeIndex(args));
        def.name(attributeName(args));
        def.grid(attributeGridOption(args));
        def.alignment(attributeAlignment(args));
        def.dimension(attributeDimension(args));
        def.fontStyle(attributeFontStyle(args));
        def.margin(attributeMargin(args));
        def.position(attributePosition(args));
        def.style(attributeStyle(args));
        def.pageOption(attributePageOption(args));
    }
}
