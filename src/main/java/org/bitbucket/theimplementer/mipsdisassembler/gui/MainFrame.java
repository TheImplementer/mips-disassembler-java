package org.bitbucket.theimplementer.mipsdisassembler.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import org.bitbucket.theimplementer.mipsdisassembler.InstructionOffsetDisplacer;
import net.emaze.dysfunctional.Casts;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.tuples.Pair;
import org.bitbucket.theimplementer.mipsdisassembler.ApplicationConfiguration;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.OpcodeAndInstruction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import net.emaze.dysfunctional.Applications;
import net.emaze.dysfunctional.options.Box;
import org.bitbucket.theimplementer.mipsdisassembler.PsxExeLoader;
import org.bitbucket.theimplementer.mipsdisassembler.PsxExecutable;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu searchMenu;
    private JMenu helpMenu;
    private JMenuItem openMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem searchMenuItem;
    private JMenuItem searchAgainMenuItem;
    private JMenuItem aboutMenuItem;
    private JSeparator fileMenuSeparator;
    private JTable contentTable;
    private Box<String> lastSearchedString;
    final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

    public MainFrame() {
        initComponents();
        registerEvents();
    }

    private void initComponents() {
        setTitle("Mips Disassembler");
        final String applicationLaf = context.getEnvironment().getProperty("application.laf");
        try {
            UIManager.setLookAndFeel(applicationLaf);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        openMenuItem = new JMenuItem("Open");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK, true));
        fileMenuSeparator = new JSeparator();
        exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(openMenuItem);
        fileMenu.add(fileMenuSeparator);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        searchMenu = new JMenu("Search");
        searchMenuItem = new JMenuItem("Search");
        searchMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK, true));
        searchMenu.add(searchMenuItem);
        searchAgainMenuItem = new JMenuItem("Search again");
        searchAgainMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0, true));
        searchMenu.add(searchAgainMenuItem);
        menuBar.add(searchMenu);


        helpMenu = new JMenu("?");
        aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        setLayout(new BorderLayout());

        contentTable = new JTable();
        contentTable.setPreferredScrollableViewportSize(new Dimension(300, 50));
        contentTable.setFillsViewportHeight(true);
        contentTable.setDragEnabled(false);
        contentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lastSearchedString = new Box<>();

        final JScrollPane scrollPane = new JScrollPane(contentTable);
        add(scrollPane);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(800, 600);
    }

    private void registerEvents() {
        openMenuItem.addActionListener(new OpenFileActionListener(this));
        searchMenuItem.addActionListener(new SearchInstructionActionListener(contentTable, lastSearchedString));
        searchAgainMenuItem.addActionListener(new SearchAgainInstructionActionListener(contentTable, lastSearchedString));
    }

    public void loadFile(File file) throws FileNotFoundException {
        final Delegate<List<Pair<Integer, OpcodeAndInstruction>>, InputStream> mipsDisassembler = Casts.widen(context.getBean("mipsDisassembler"));
        final PsxExecutable psxExecutable = new PsxExeLoader().load(file);
        final List<Pair<Integer, OpcodeAndInstruction>> offsetsAndInstructions = mipsDisassembler.perform(new ByteArrayInputStream(psxExecutable.getTextSection()));
        updateContent(Applications.map(offsetsAndInstructions, new InstructionOffsetDisplacer()));
    }

    private void updateContent(List<Pair<Integer, OpcodeAndInstruction>> offsetsAndInstructions) {
        final Object[][] newContent = new Object[offsetsAndInstructions.size()][3];
        for (int counter = 0; counter < offsetsAndInstructions.size(); counter++) {
            final Pair<Integer, OpcodeAndInstruction> offsetAndInstruction = offsetsAndInstructions.get(counter);
            final OpcodeAndInstruction opcodeAndInstruction = offsetAndInstruction.second();
            newContent[counter][0] = String.format("%08X", offsetAndInstruction.first().intValue());
            newContent[counter][1] = String.format("%08X", opcodeAndInstruction.getOpcode().intValue());
            newContent[counter][2] = opcodeAndInstruction.getInstruction();
        }
        contentTable.setModel(new ContentTableModel(newContent));
        contentTable.getColumnModel().getColumn(0).setMaxWidth(100);
        contentTable.getColumnModel().getColumn(1).setMaxWidth(100);
        contentTable.getColumnModel().getColumn(0).setResizable(false);
        contentTable.getColumnModel().getColumn(1).setResizable(false);
        contentTable.getColumnModel().getColumn(2).setResizable(false);
    }
}
