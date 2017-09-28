package com.happy.gene.pdf.ui.toolbar;

import com.happy.gene.pdf.ui.layout.VFlowLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaolisong on 20/09/2017.
 */
public class ItemCategory extends JPanel {

    private String              title;
    private List<JToggleButton> items;

    public ItemCategory() { this(null, null);}
    public ItemCategory(String title) { this(title, null); }
    public ItemCategory(String title, List<JToggleButton> items) {
        super();
        this.title = title;
        this.items = items;
    }

    public void setTitle(String title) { this.title = title; }
    public void setItems(List<JToggleButton> items) { this.items = items; }
    public void addItem(JToggleButton item) {
        if (null==this.items) { this.items = new ArrayList<>(); }
        this.items.add(item);
    }

    public void init() {
        this.invalidate();
        this.removeAll();
        this.setLayout(new BorderLayout(2, 2));

        this.setSize(new Dimension(50, 158));
        this.setPreferredSize(new Dimension(50, 158));

        // title
        JLabel jTitle = new JLabel(title);
        this.add(jTitle, BorderLayout.NORTH);

        // item
        JPanel jPanel = new JPanel(new VFlowLayout(2, 2));
        for (JToggleButton it : items) {
            jPanel.add(it);
        }
        this.add(jPanel, BorderLayout.CENTER);

        this.validate();
    }
}
