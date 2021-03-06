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


// import modules
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;


// editor class name
public class Editor extends JTextArea {
	
	private float fontsize = 12.0f;
	private static float font_change_rate = 4.0f;
	

	public void increasefont(){
		// increase font size
		fontsize += font_change_rate ;
		applychanges();
	}
	public void decreasefont(){
		// decrease font size
		fontsize -= font_change_rate;
		applychanges();
	}


	private void applychanges(){
		// apply font change
		setFont(getFont().deriveFont(fontsize));
		
	}
	// Constructor
	public Editor(int w, int h){

		super(w, h);
		
		// Configurations
		setVisible(true);
		setFocusable(true);

	}

	/* 
	

	USEFUL Methods

	copy()
	cut()
	getSelectedText()
	getSelectionEnd
	getSelectionStart
	getText
	getText <-- Specific Portion
	paste
	replaceSelection
	select
	selectAll
	setText
	updateUI

	*/


	// main function trigger 
	public static void main(String[] args){
		
		// Create Window Frame
		JFrame obj1 = new JFrame("CodeRail Text");

		// Window Configuration
		obj1.setSize(300, 400);
		obj1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// Create Text Area Object
		Editor obj = new Editor(200, 300);
		obj1.add(obj);
		
		obj1.setVisible(true);
		obj1.setFocusable(true);
		
		
	}

}


