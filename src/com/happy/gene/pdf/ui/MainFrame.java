package com.happy.gene.pdf.ui;

import com.happy.gene.pdf.ui.toolbar.ToolBar;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zhaolisong on 20/09/2017.
 */
public class MainFrame extends JFrame {

    public static void main(String[] args) {
        MainFrame frame = new MainFrame("GeneReport");
        frame.init(args);
        frame.setVisible(true);
    }

    private MainFrame(String title) {
        super(title);
    }

    public void init(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension(800, 600);
        this.setLocation((int)(screenSize.getWidth() - windowSize.getWidth()) / 2, (int)(screenSize.getHeight()-windowSize.getHeight()) / 2);
        this.setPreferredSize(windowSize);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
        this.add(new ToolBar(), BorderLayout.EAST);
        this.pack();
    }



}
