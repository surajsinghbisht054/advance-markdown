package ADMark;

/*

    Author
            Suraj Singh Bisht
            Surajsinghbisht054@gmail.com
            www.bitforestinfo.com
            www.blaregroup.com

            Himanshu Sharma
            himanshusharma2930@gmail.com
            
    ===============================================================
                Advance MarkDown To HTML Generating Engine
    ===============================================================


    Reference Docs:
        https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
        https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        https://www.markdownguide.org/extended-syntax/
        https://daringfireball.net/projects/markdown/syntax#header
        

*/


import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class LineNumberColumn extends JTextArea {
    
    private Editor txtarea;
    private int numberline = 0;

    public LineNumberColumn(Editor editor) {
        super(editor.getLineCount(), 3);
        this.txtarea = editor;
        this.setVisible(true);
        this.setFocusable(true);
    }

    public void UpdateLineNumbers() {
        int n = this.txtarea.getLineCount();
        int n2 = 0;
        // System.out.println("[+] Rows Update " + n);
        if (this.numberline != n) {
            if (this.numberline > n) {
                n2 = this.numberline - n;
                this.setText("");
                this.numberline = 0;
                this.UpdateLineNumbers();
            } else {
                n2 = n - this.numberline;
                for (int i = 1; i <= n2; ++i) {
                    this.append(this.numberline + i + "\n");
                }
            }
            this.numberline = n;
        }
    }

    public void update_line_number_configurations() {
        this.setFont(this.txtarea.getFont());
        this.setEditable(false);
        this.setBackground(new Color(226, 230, 231));
        this.setForeground(new Color(34, 47, 62));
        this.setMargin(new Insets(5, 5, 5, 5));
        this.txtarea.setMargin(new Insets(5, 5, 5, 5));
    }

    // Main function
    public static void main(String args[]){
    	JFrame root = new JFrame("Main Test Window");
    	Editor txt = new Editor(50, 100);
    	LineNumberColumn clm = new LineNumberColumn(txt);

    	txt.getDocument().addDocumentListener(new DocumentListener(){

		    public void changedUpdate(DocumentEvent documentEvent) {
		    	clm.update_line_number_configurations();

		        };

		    public void removeUpdate(DocumentEvent documentEvent) {
		    	clm.UpdateLineNumbers();
		        };

		    public void insertUpdate(DocumentEvent documentEvent) {
		    	clm.UpdateLineNumbers();
		        };
    	});
    	clm.update_line_number_configurations();
    	JScrollPane frame = new JScrollPane(txt);
    	frame.setRowHeaderView(clm);
    	root.add(frame);
    	root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	root.setVisible(true);
    	root.setFocusable(true);
    }
}
