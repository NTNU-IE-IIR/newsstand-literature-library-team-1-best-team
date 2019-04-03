package no.ntnu.trygvew.GUI;

import no.ntnu.trygvew.LiteratureStockRegister;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TestGUI{

    public TestGUI(){
        JFrame frame = new JFrame("Tabbed Pane Sample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LiteratureStockRegister lsr = new LiteratureStockRegister("Data/inventory.json");
        JTable tbl = TableMaker.makeAllLitratureTable(lsr.getStock());

        JTableHeader header = tbl.getTableHeader();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.add(tbl, BorderLayout.CENTER);
        frame.add(panel);
        frame.setSize(700, 700);
        frame.setVisible(true);
    }


    private static void makeTable(){

    }
}
