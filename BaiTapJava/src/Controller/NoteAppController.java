package Controller;


import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.event.ActionListener;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

import Model.NoteAppModel;
import View.NoteAppView;

public class NoteAppController implements ActionListener {
    NoteAppView noteAppView;
    NoteAppModel noteAppModel;
    boolean reloadCalled = false;
    JFileChooser fileChoose = new JFileChooser();
	private Object noteAppController;

  
    private void reloadTree() {
        DefaultTreeModel treeModel = (DefaultTreeModel) noteAppView.folderTree.getModel();
        treeModel.reload(); // Hoặc bạn có thể reload từ node gốc: treeModel.reload(rootNode);
    }
    public NoteAppController(NoteAppView noteAppView) {
        this.noteAppView = noteAppView;
        this.noteAppModel = new NoteAppModel(noteAppView);
    }

    private void addChildren(DefaultMutableTreeNode parent, File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
                parent.add(node);
                if (file.isDirectory()) {
                    addChildren(node, file);
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Open")) {
            int returnVal = fileChoose.showOpenDialog(this.noteAppView);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChoose.getSelectedFile();
                String fileName = file.getName();

                if (fileName.endsWith(".txt")) {
                    try {
                        List<String> allText = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
                        String result = "";
                        for (String line : allText) {
                            result += line;
                            result += "\n";
                        }
                        this.noteAppView.textArea.setText(result);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        } else if (command.equals("Open Folder")) {
        	String path = JOptionPane.showInputDialog("Nhập đường dẫn của thư mục:");
            if (path != null && !path.isEmpty()) {
                noteAppModel.printDirectory(path);
            } else {
                System.out.println("Đường dẫn không hợp lệ.");
            }
   
        } else if (command.equals("Save")) {
            noteAppModel.saveFile();
        } else if (command.equals("New")) {
            noteAppModel.newFile();
        } else if (command.equals("Search")) {
        }
   
    }
}
