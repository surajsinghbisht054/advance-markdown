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
import java.awt.event.*;
import java .awt.*;


// AddMenuBar Class
public class AddMenuBar extends JMenuBar {
	// menu items
	private JMenu menu_file;
	private JMenu menu_edit;
	private JMenu menu_view;
	private JMenu menu_font;
	private JMenu menu_help;


	// menu file items
	public JMenuItem menu_file_new;
	public JMenuItem menu_file_open;
	public JMenuItem menu_file_savemarkdown;
	public JMenuItem menu_file_savehtml;
	public JMenuItem menu_file_saveas;
	public JMenuItem menu_file_print;
	public JMenuItem menu_file_exit;


	// menu edit
	public JMenuItem menu_edit_undo;
	public JMenuItem menu_edit_redo;
	public JMenuItem menu_edit_cut;
	public JMenuItem menu_edit_copy;
	public JMenuItem menu_edit_paste;
	public JMenuItem menu_edit_delete;
	public JMenuItem menu_edit_selectall;
	public JMenuItem menu_edit_find;
	public JMenuItem menu_edit_replace;
	public JMenuItem menu_edit_goto;

	// menu view
	public JMenu menu_view_theme;
	public JMenuItem theme_a;
	public JMenuItem theme_b;
	public JMenuItem theme_c;
	public JMenuItem theme_d;
	public JMenuItem theme_e;
	public JMenuItem theme_f;
	public JMenuItem theme_g;
	public JMenuItem theme_h;
	public JMenuItem theme_0;
	public JMenu menu_view_language;
	public JMenuItem language_plain;
	public JMenuItem language_html;
	public JMenuItem language_css;
	public JMenuItem language_javascript;
	public JMenuItem language_java;
	public JMenuItem language_python;
	public JMenuItem language_c;
	public JMenuItem language_cplus;
	public JMenuItem language_csharp;
	public JMenuItem language_php;
	public JMenuItem menu_view_statusbar;
	public JMenuItem menu_view_linenumber;
	public JMenuItem menu_view_cursorline;

	// menu font
	public JMenuItem menu_font_wordwrap;
	public JMenu menu_font_font;
	public JMenuItem menu_font_font_size_large;
	public JMenuItem menu_font_font_size_medium;
	public JMenuItem menu_font_font_size_small;

	// menu help
	public JMenuItem menu_help_topic;
	public JMenuItem menu_help_about;

	// Constructor
	public AddMenuBar(){
		initUI();
	}

	// initialise method
	private void initUI(){

		menu_file = new JMenu("File");
		menu_edit = new JMenu("Edit");
		menu_view = new JMenu("Format");
		menu_font = new JMenu("View");
		menu_help = new JMenu("Help");


		//add Mnemonic to menu (used with alt key)
		menu_file.setMnemonic('F');
		menu_edit.setMnemonic('E');
		menu_view.setMnemonic('M');
		menu_font.setMnemonic('V');
		menu_help.setMnemonic('H');

		// Menu Item Created
		menu_file_open 	= new JMenuItem("Open");
		menu_file_new 	= new JMenuItem("New");
		menu_file_savemarkdown 	= new JMenuItem("Save MarkDown");
		menu_file_savehtml = new JMenuItem("Save Html");
		menu_file_saveas= new JMenuItem("Save As");
		menu_file_print = new JMenuItem("Print");
		menu_file_exit 	= new JMenuItem("Exit");
		

		// menu edit
		menu_edit_undo 		= new JMenuItem("Undo");
		menu_edit_redo 		= new JMenuItem("Redo");
		menu_edit_cut 		= new JMenuItem("Cut");
		menu_edit_copy 		= new JMenuItem("Copy");
		menu_edit_paste 	= new JMenuItem("Paste");
		menu_edit_delete 	= new JMenuItem("Delete");
		menu_edit_selectall = new JMenuItem("Select All");
		menu_edit_find 		= new JMenuItem("Find");
		menu_edit_replace 	= new JMenuItem("Replace");
		menu_edit_goto 		= new JMenuItem("Goto");

		// menu view
		menu_view_theme = new JMenu("Theme");
		
		theme_0 = new JMenuItem("Default");
		theme_a = new JMenuItem("Dark");
		theme_b = new JMenuItem("Light");
		theme_c = new JMenuItem("Pico");
		theme_d = new JMenuItem("Vim");
		theme_e = new JMenuItem("Ocean");
		theme_f = new JMenuItem("Arc");
		theme_g = new JMenuItem("Pro");
		theme_h = new JMenuItem("Mac");

		// Sub menu option
		menu_view_language  = new JMenu("Language");
		
		language_plain 		= new JMenuItem("Plain");
		language_c          = new JMenuItem("C");
		language_cplus      = new JMenuItem("C++");
		language_csharp     = new JMenuItem("CSharp");
		language_css        = new JMenuItem("CSS");
		language_html       = new JMenuItem("HTML");
		language_java       = new JMenuItem("JAVA");
		language_javascript = new JMenuItem("JAVASCRIPT");
		language_php        = new JMenuItem("PHP");
		language_python     = new JMenuItem("PYTHON");
		menu_view_statusbar = new JCheckBoxMenuItem("Status Bar", true);
		
		menu_view_linenumber= new JCheckBoxMenuItem("Line Number", true);
		menu_view_cursorline= new JCheckBoxMenuItem("Cursor Line", true);

		// menu font
		menu_font_wordwrap  = new JMenuItem("Word wrap");

		menu_font_font 		= new JMenu("Font");

		//sub menus of font_font menu
		menu_font_font_size_large =new JMenuItem("Large");
		menu_font_font_size_small =new JMenuItem("Small");

		// menu help
		menu_help_topic = new JMenuItem("Topic");
		menu_help_about = new JMenuItem("About");



		//adding shortcut keys to menu items
		// Menu Item Shotcut keys
		menu_file_open.setAccelerator(KeyStroke.getKeyStroke('O',ActionEvent.CTRL_MASK));
		menu_file_new.setAccelerator(KeyStroke.getKeyStroke('N',ActionEvent.CTRL_MASK));
		menu_file_savemarkdown.setAccelerator(KeyStroke.getKeyStroke('S',ActionEvent.CTRL_MASK));
		menu_file_saveas.setAccelerator(KeyStroke.getKeyStroke('S',ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		menu_file_print.setAccelerator(KeyStroke.getKeyStroke('P',ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
		menu_file_exit.setAccelerator(KeyStroke.getKeyStroke('Q',ActionEvent.CTRL_MASK));
		
		
		// menu edit shortcut keys
		menu_edit_undo.setAccelerator(KeyStroke.getKeyStroke('Z',ActionEvent.CTRL_MASK));
		menu_edit_redo.setAccelerator(KeyStroke.getKeyStroke('Z',ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		menu_edit_cut.setAccelerator(KeyStroke.getKeyStroke('X',ActionEvent.CTRL_MASK));
		menu_edit_delete.setAccelerator(KeyStroke.getKeyStroke('X', ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		menu_edit_copy.setAccelerator(KeyStroke.getKeyStroke('C',ActionEvent.CTRL_MASK));
		menu_edit_paste.setAccelerator(KeyStroke.getKeyStroke('V',ActionEvent.CTRL_MASK));
		menu_edit_selectall.setAccelerator(KeyStroke.getKeyStroke('A',ActionEvent.CTRL_MASK));
		menu_edit_find.setAccelerator(KeyStroke.getKeyStroke('F',ActionEvent.CTRL_MASK));
		menu_edit_replace.setAccelerator(KeyStroke.getKeyStroke('R',ActionEvent.CTRL_MASK));
		menu_edit_goto.setAccelerator(KeyStroke.getKeyStroke('G',ActionEvent.CTRL_MASK));

		//menuitem font shortcut keys
		menu_font_font_size_large.setAccelerator(KeyStroke.getKeyStroke(107,ActionEvent.CTRL_MASK));
		menu_font_font_size_small.setAccelerator(KeyStroke.getKeyStroke(109,ActionEvent.CTRL_MASK));

		// Help Menu
		menu_help_topic.setAccelerator(KeyStroke.getKeyStroke(121, 0));
		menu_help_about.setAccelerator(KeyStroke.getKeyStroke(123, 0));

		//menu file
		menu_file.add(menu_file_open );
		menu_file.add(menu_file_new);
		menu_file.addSeparator();
		menu_file.add(menu_file_savemarkdown);
		menu_file.add(menu_file_savehtml);
		menu_file.add(menu_file_saveas);
		menu_file.add(menu_file_print);
		menu_file.addSeparator();
		menu_file.add(menu_file_exit);
	

		// menu edit
		menu_edit.add(menu_edit_undo);
		menu_edit.add(menu_edit_redo);
		menu_edit.addSeparator();
		menu_edit.add(menu_edit_cut);
		menu_edit.add(menu_edit_copy);
		menu_edit.add(menu_edit_paste);
		menu_edit.addSeparator();
		menu_edit.add(menu_edit_delete);
		menu_edit.add(menu_edit_goto);
		menu_edit.add(menu_edit_selectall);
		menu_edit.addSeparator();
		menu_edit.add(menu_edit_find);
		menu_edit.addSeparator();
		menu_edit.add(menu_edit_replace);
		
		// menu view
		menu_view_theme.add(theme_0);
		menu_view_theme.add(theme_a);
		menu_view_theme.add(theme_b);
		menu_view_theme.add(theme_c);
		menu_view_theme.add(theme_d);
		menu_view_theme.add(theme_e);
		menu_view_theme.add(theme_f);
		menu_view_theme.add(theme_g);
		menu_view_theme.add(theme_h);
		menu_view.add(menu_view_theme);
		menu_view.addSeparator();
		menu_view_language.add(language_plain);
		menu_view_language.add(language_c);
		menu_view_language.add(language_cplus);
		menu_view_language.add(language_csharp);
		menu_view_language.add(language_css);
		menu_view_language.add(language_html);
		menu_view_language.add(language_java);
		menu_view_language.add(language_javascript);
		menu_view_language.add(language_php);
		menu_view_language.add(language_python);
		menu_view.add(menu_view_language);
		menu_view.addSeparator();
		menu_view.add(menu_view_statusbar);
		menu_view.add(menu_view_linenumber);
		menu_view.add(menu_view_cursorline);

		// menu font
		menu_font.add(menu_font_wordwrap);
		menu_font_font.add(menu_font_font_size_large);
		menu_font_font.add(menu_font_font_size_small);
		menu_font.add(menu_font_font);


		// menu help
		menu_help.add(menu_help_topic);
		menu_help.add(menu_help_about);




		// Add menu to bar
		add(menu_file);
		add(menu_edit);
		add(menu_view);
		add(menu_font);
		add(menu_help);

		//setting menu size
		menu_file.setFont(new Font("",Font.BOLD,25));
		menu_edit.setFont(new Font("",Font.BOLD,25));
		menu_view.setFont(new Font("",Font.BOLD,25));
		menu_font.setFont(new Font("",Font.BOLD,25));
		menu_help.setFont(new Font("",Font.BOLD,25));

		//adding color menu 
		menu_file.setForeground(Color.white);
		menu_edit.setForeground(Color.white);
		menu_view.setForeground(Color.white);
		menu_font.setForeground(Color.white);
		menu_help.setForeground(Color.white);

		

			/****Config JMenu Item*****/
		Font font_family = new Font("", Font.BOLD,20);
		// Menu Item 
		menu_file_open.setFont(font_family);
		menu_file_new.setFont(font_family);
		menu_file_savemarkdown.setFont(font_family);
		menu_file_savehtml.setFont(font_family);
		menu_file_saveas.setFont(font_family);
		menu_file_print.setFont(font_family);
		menu_file_exit.setFont(font_family);
		
		
		// menu edit
		menu_edit_undo.setFont(font_family);
		menu_edit_redo.setFont(font_family);
		menu_edit_cut.setFont(font_family);
		menu_edit_delete.setFont(font_family);
		menu_edit_copy.setFont(font_family);
		menu_edit_paste.setFont(font_family);
		menu_edit_selectall.setFont(font_family);
		menu_edit_find.setFont(font_family);
		menu_edit_replace.setFont(font_family);
		menu_edit_goto.setFont(font_family);

		//format menu
		menu_view_theme.setFont(font_family);
		menu_view_language.setFont(font_family);
		menu_view_statusbar.setFont(font_family);
		menu_view_linenumber.setFont(font_family);
		menu_view_cursorline.setFont(font_family);
		language_plain.setFont(font_family);
		language_c.setFont(font_family); 
		language_cplus.setFont(font_family);
		language_csharp.setFont(font_family);
		language_css.setFont(font_family);
		language_html.setFont(font_family);
		language_java.setFont(font_family); 
		language_javascript.setFont(font_family);
		language_php.setFont(font_family); 
		language_python.setFont(font_family);
		
		
		//view menu
		menu_font_wordwrap.setFont(font_family);
		menu_font_font.setFont(font_family);

		//menuitem font
		menu_font_font_size_large.setFont(font_family);
		menu_font_font_size_small.setFont(font_family);

		// Help Menu
		menu_help_topic.setFont(font_family);
		menu_help_about.setFont(font_family);
				
			/***** End of MenuCongfig *****/


		//adding color to theme
		theme_0.setBackground(Color.WHITE);
		theme_0.setForeground(Color.BLACK);
		theme_a.setBackground(new Color(61, 61, 61));
		theme_a.setForeground(new Color(236, 240, 241));
		theme_b.setBackground(new Color(236, 240, 241));
		theme_b.setForeground(new Color(34, 47, 62));
		theme_c.setBackground(new Color(25, 42, 86));
		theme_c.setForeground(new Color(236, 240, 241));
		theme_d.setBackground(new Color(111, 30, 81));
		theme_d.setForeground(new Color(247, 241, 227));
		theme_e.setBackground(new Color(154, 236, 219));
		theme_e.setForeground(new Color(44, 58, 71));
		theme_f.setBackground(new Color(72, 84, 96));
		theme_f.setForeground(new Color(248, 239, 186));
		theme_g.setBackground(new Color(44, 58, 71));
		theme_g.setForeground(new Color(255, 121, 63));
		theme_h.setBackground(new Color(236, 240, 241));
		theme_h.setForeground(new Color(44, 62, 80));

		//increase size of theme sub menu
		theme_a.setPreferredSize(new Dimension(100,35));
		theme_b.setPreferredSize(new Dimension(100,35));
		theme_c.setPreferredSize(new Dimension(100,35));
		theme_d.setPreferredSize(new Dimension(100,35));
		theme_e.setPreferredSize(new Dimension(100,35));
		theme_f.setPreferredSize(new Dimension(100,35));
		theme_g.setPreferredSize(new Dimension(100,35));
		theme_h.setPreferredSize(new Dimension(100,35));


		
	}

	
}
