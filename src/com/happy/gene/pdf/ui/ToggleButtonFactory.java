package com.happy.gene.pdf.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zhaolisong on 20/09/2017.
 */
public class ToggleButtonFactory {

    public static final int Type_Circle = 0x001;

    public static JToggleButton getItemButton(int type, Icon icon, String text, String tooltip, ButtonGroup buttonGroup) {
        JToggleButton item = new JToggleButton();

        if (null!=text && !"".equals(text.trim())) { item.setText(text); }
        if (null!=tooltip && !"".equals(tooltip.trim())) { item.setToolTipText(tooltip); }

        if (null!=buttonGroup) { buttonGroup.add(item); }

        item.setPreferredSize(new Dimension(40, 40));
        item.setSize(new Dimension(40, 40));

        return item;
    }
}
