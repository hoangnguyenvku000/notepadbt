package Model;


import java.io.File;
import View.NoteAppView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

public class NoteAppModel {
	File file;
    private String fileName;
    private String content;
    private NoteAppView noteAppView;
    private JFileChooser fileChooser;

    public NoteAppModel(NoteAppView noteAppView) {
        this.fileName = "";
        this.content = "";
        this.noteAppView = noteAppView;
        this.fileChooser = new JFileChooser();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void saveFile() {
        int returnValue = fileChooser.showSaveDialog(noteAppView);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File file = fileChooser.getSelectedFile();
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write(content);
                writer.close();
                fileWriter.close();
                System.out.println("File saved successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	public void newFile() {
		File file = new File("D:\\Java\\Eclipse\\BaiTapJava\\New file");
		file.mkdir();
		JTextArea textArea = noteAppView.getTextArea(); 
	    textArea.setText("");
	}

	public void printDirectory(String path) {
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            printFullDirectory(directory, 0);
        } else {
            System.out.println("Đường dẫn không hợp lệ hoặc không phải là một thư mục.");
        }
    }

	public void printFullDirectory(File f, int bac) {
		 for (int i = 0; i < bac; i++) {
	            System.out.print("\t");
	        }
//	        System.out.print("|__");
//	        System.out.println(f.getName());
	        
	        if (f.isDirectory()) {
	        	System.out.print("|__");
	        	System.out.println(f.getName());
	            File[] mangCon = f.listFiles();
	            if (mangCon != null) { 
	                for (File fx : mangCon) {
	                    printFullDirectory(fx, bac+1);
	                }
	            }
	        }else {
	        	System.out.println("|--" + f.getName());
	        }
	    }
	}
