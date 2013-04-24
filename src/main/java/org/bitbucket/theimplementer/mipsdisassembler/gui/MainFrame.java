package org.bitbucket.theimplementer.mipsdisassembler.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
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

        searchMenu = new JMenu("Search");
        searchMenuItem = new JMenuItem("Search instruction");
        searchMenu.add(searchMenuItem);
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
        searchMenuItem.addActionListener(new SearchInstructionActionListener(contentTable, lastSearchedString));
        registerGlobalKeyEvent(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK), new SearchGlobalKeyAction(contentTable, lastSearchedString), "SEARCH");
    }

    private void registerGlobalKeyEvent(KeyStroke keyStroke, Action action, Object actionMapKey) {
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionMapKey);
        getRootPane().getActionMap().put(actionMapKey, action);
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
