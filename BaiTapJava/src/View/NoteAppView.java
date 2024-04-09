package View;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JLabel;

import Controller.NoteAppController;
import Model.NoteAppModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.JTable;
import javax.swing.JTextField;

public class NoteAppView extends JFrame {
    private NoteAppModel model;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JTextArea textArea;
	public Object displayLabel;
	public final JTextField txtSearch = new JTextField();
	public JTree folderTree;
	public JTree tree;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    NoteAppView frame = new NoteAppView();
                    frame.setVisible(true);
                } catch (Exception e) {    
                    e.printStackTrace();
                }
            }
        });
    }

    public NoteAppView() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 562);
        
        ActionListener action = new NoteAppController(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu MenuFile = new JMenu("File");
        menuBar.add(MenuFile);

        JMenuItem MenuSaveItem = new JMenuItem("Save");
        MenuSaveItem.addActionListener(action);
        MenuFile.add(MenuSaveItem);

        JMenuItem MenuNewItem = new JMenuItem("New");
        MenuNewItem.addActionListener(action);
        MenuFile.add(MenuNewItem);

        JMenuItem MenuOpenItem = new JMenuItem("Open");
        MenuOpenItem.addActionListener(action);
        
        JMenuItem MenuOpenFolder = new JMenuItem("Open Folder");
        MenuOpenFolder.addActionListener(action);
        MenuFile.add(MenuOpenFolder);
        MenuFile.add(MenuOpenItem);
        contentPane.setLayout(null);
        contentPane.setLayout(null);

        textArea = new JTextArea();

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(5, 5, 496, 493);
        contentPane.add(scrollPane);
        
        txtSearch.setText("search \r\n");
        scrollPane.setColumnHeaderView(txtSearch);
        txtSearch.setColumns(10);
        
    }
    public String getText() {
        return textArea.getText();
    }
    public JTextArea getTextArea() {
    	return textArea;
    }
}
