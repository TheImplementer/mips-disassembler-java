package org.bitbucket.theimplementer.mipsdisassembler.gui;

import net.emaze.dysfunctional.Casts;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.tuples.Pair;
import org.bitbucket.theimplementer.mipsdisassembler.ApplicationConfiguration;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuItem openMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem aboutMenuItem;
    private JSeparator fileMenuSeparator;
    private JTable contentTable;
    final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

    public MainFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Mips Disassembler");

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(new OpenFileActionListener(this));
        fileMenuSeparator = new JSeparator();
        exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(openMenuItem);
        fileMenu.add(fileMenuSeparator);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        helpMenu = new JMenu("?");
        aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        setLayout(new BorderLayout());

        contentTable = new JTable();
        contentTable.setPreferredScrollableViewportSize(new Dimension(300, 50));
        contentTable.setFillsViewportHeight(true);
        final JScrollPane scrollPane = new JScrollPane(contentTable);
        add(scrollPane);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(800, 600);
    }

    public void loadFile(File file) throws FileNotFoundException {
        final Delegate<List<Pair<Integer, Instruction>>, InputStream> mipsDisassembler = Casts.widen(context.getBean("mipsDisassembler"));
        final List<Pair<Integer, Instruction>> opcodesAndInstructions = mipsDisassembler.perform(new FileInputStream(file));
        updateContent(opcodesAndInstructions);
    }

    private void updateContent(java.util.List<Pair<Integer, Instruction>> opcodesAndInstructions) {
        final Object[][] newContent = new Object[opcodesAndInstructions.size()][2];
        for (int counter = 0; counter < opcodesAndInstructions.size(); counter++) {
            final Pair<Integer, Instruction> opcodeAndInstruction = opcodesAndInstructions.get(counter);
            newContent[counter][0] = String.format("%08X", opcodeAndInstruction.first().intValue());
            newContent[counter][1] = opcodeAndInstruction.second();
        }
        contentTable.setModel(new ContentTableModel(newContent));
        contentTable.getColumnModel().getColumn(0).setMaxWidth(100);
    }
}
