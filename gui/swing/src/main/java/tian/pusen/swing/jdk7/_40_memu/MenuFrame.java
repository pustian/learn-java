package tian.pusen.swing.jdk7._40_memu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuFrame extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new MenuFrame();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setSize(300, 200);
            }
        });
    }

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private Action saveAction;
//    private Action saveAsAction;
    private JCheckBoxMenuItem readonlyItem;
    private JPopupMenu popupMenu;

    class TestAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(getValue(Action.NAME) + " selected");
        }

        public TestAction(String name) {
            super(name);
        }
    }

    public MenuFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // File menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new TestAction("new"));

        JMenuItem openItem = fileMenu.add(new TestAction("Open"));
        openItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        fileMenu.addSeparator();

        saveAction = new TestAction("Save");
        JMenuItem saveItem = fileMenu.add(saveAction);
        saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));

        final Action saveAsAction = new TestAction("Save As");
        fileMenu.add(saveAsAction);
        fileMenu.addSeparator();

        fileMenu.add(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Edit menu
        readonlyItem = new JCheckBoxMenuItem("Read-only");
        readonlyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean saveOK = !readonlyItem.isSelected();
                saveAction.setEnabled(saveOK);
                saveAsAction.setEnabled(saveOK);
                System.out.println("readonlyItem is not selected " + saveOK);
            }
        });

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButtonMenuItem insertItem = new JRadioButtonMenuItem("Insert");
        insertItem.setSelected(true);
        JRadioButtonMenuItem overtypeItem = new JRadioButtonMenuItem("Overtype");
        buttonGroup.add(insertItem);
        buttonGroup.add(overtypeItem);

        Action cutAction = new TestAction("Cut");
        cutAction.putValue(Action.SMALL_ICON, new ImageIcon("cut.png"));
        Action copyAction = new TestAction("Copy");
        cutAction.putValue(Action.SMALL_ICON, new ImageIcon("copy.png"));
        Action pasteAction = new TestAction("Paste");
        cutAction.putValue(Action.SMALL_ICON, new ImageIcon("paste.png"));

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(cutAction);
        editMenu.add(copyAction);
        editMenu.add(pasteAction);

        JMenu optionMenu = new JMenu("Options");
        optionMenu.add(readonlyItem);
        optionMenu.addSeparator();
        optionMenu.add(insertItem);
        optionMenu.add(overtypeItem);

        editMenu.addSeparator();
        editMenu.add(optionMenu);

        // Help Menu
        JMenu helpMenu = new JMenu("help");
        helpMenu.setMnemonic('H');

        JMenuItem indexItem = new JMenuItem("index");
        indexItem.setMnemonic('I');
        helpMenu.add(indexItem);
        Action aboutAction = new TestAction("About");
        aboutAction.putValue(Action.MNEMONIC_KEY, new Integer('A'));
        helpMenu.add(aboutAction);

        // list menu at frame
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        // popupmenu
        popupMenu = new JPopupMenu();
        popupMenu.add(cutAction);
        popupMenu.add(copyAction);
        popupMenu.add(pasteAction);

        JPanel panel = new JPanel();
        panel.setComponentPopupMenu(popupMenu);
        add(panel);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }
}
