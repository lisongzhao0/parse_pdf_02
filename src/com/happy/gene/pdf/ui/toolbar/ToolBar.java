package com.happy.gene.pdf.ui.toolbar;

import com.happy.gene.pdf.ui.ToggleButtonFactory;
import com.happy.gene.pdf.ui.layout.VFlowLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zhaolisong on 20/09/2017.
 */
public class ToolBar extends JPanel {


    public ToolBar() { super(); init(); }

    private void init() {
        this.invalidate();

        this.setPreferredSize(new Dimension(40, 500));

        ButtonGroup itemGroup = new ButtonGroup();

        this.setLayout(new VFlowLayout(2, 2));
        ItemCategory shape = new ItemCategory("形状");
        shape.addItem(ToggleButtonFactory.getItemButton(0, null, "圆圈", "circle", itemGroup));
        shape.addItem(ToggleButtonFactory.getItemButton(0, null, "方形", "rect", itemGroup));
        shape.addItem(ToggleButtonFactory.getItemButton(0, null, "路径", "path", itemGroup));
        shape.init();
        this.add(shape);

        ItemCategory group = new ItemCategory("组合");
        group.addItem(ToggleButtonFactory.getItemButton(0, null, "章节", "chapter", itemGroup));
        group.addItem(ToggleButtonFactory.getItemButton(0, null, "区域", "area", itemGroup));
        group.addItem(ToggleButtonFactory.getItemButton(0, null, "方格", "grid", itemGroup));
        group.addItem(ToggleButtonFactory.getItemButton(0, null, "模板", "template", itemGroup));
        group.init();
        this.add(group);

        ItemCategory element = new ItemCategory("元素");
        element.addItem(ToggleButtonFactory.getItemButton(0, null, "图片", "image", itemGroup));
        element.addItem(ToggleButtonFactory.getItemButton(0, null, "段落", "paragraph", itemGroup));
        element.init();
        this.add(element);

        this.validate();
    }
}
