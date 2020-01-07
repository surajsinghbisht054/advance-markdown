// import modules
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.UIManager;
import ADMark.*;
import java.net.URI;
import javax.swing.border.*;
import javafx.scene.*;
import javafx.scene.web.WebView;
import javafx.embed.swing.*;
import javafx.application.Platform;


/*
================================================================================
						Project Advance-Markdown
================================================================================

	Author:
			Suraj Singh Bisht
			surajsinghbisht054@gmail.com


			himanshu sharma
			himanshusharma2972@gmail.com

	================================================================================
									README
	================================================================================
	We have decided to follow, Divide and Conquer strategy. so, in this project, 
	ADMark folder is our source of module and class. we are going to implement
	various types of functionality and features in seperate class files. so, that
	Contributors of Project don't have to mess with complete project code to understand
	any specific functions.
	 
	This Combined Controls class play the role of connector or main control. basically,
	this create object of all CodeRail Subdirectory class and then, connect them.

	USEFUL Documentation:
		https://docs.oracle.com/javase/7/docs/api/javax/swing/JScrollPane.html


*/
class CombinedControls extends JFrame implements ActionListener,ItemListener, DocumentListener, CaretListener {
	/*

		JFrame : Class To Create Main Window Object

		ActionLIstener: Implementation

				Configurations Settings

	*/
	private static final String window_name = "Advance Markdown";
	private static  String title= "Untitled";
	private static final int window_width = 1500;
	private static final int window_height = 800;
	private static final boolean debug = false;
	private static boolean editing = false;
		


	// Markdown Module Objects
	private AddMenuBar menu;        // Menu Bar Module
	private Editor markdownobj;     // Editor Module For Markdown
	private UndoManager manager;    // Undo Manager
	private FileManager FileObj;    // FileManager Module Object
	private FileManager FileObjHtml;
	private JScrollPane markdownScroller; // Scrollbar Pane
	private SmallPopWindows PopUpDialog; // pop up Dialog Box Windows
	private StatusBar statusbarObj; // Status Bar
	private LineNumberColumn clm;   // Line Numbers
	private JPanel webviewPanel;	//webviewPanel
	
	/* new 	modificatoin for adding webview */
	private JPanel MainContainer = new JPanel();	//Contains main screen
	private Editor htmlviewobj;             // Editor Module for HTML
	private JFXPanel web = new JFXPanel();	// For Webview
	private WebView webView;				// Webview Object	
	private JScrollPane webviewScroller;	//Scroller for WebView
	private JPanel header = new JPanel();   // For Containing Labels and buttons 
	private ADMark markdownEngine;			//creating ADMark class object for using Engine 
	private String markdownContent; 		// contains markdown file Content
	private String htmlContent;				// contains html file Content
	private String liveHtmlContent; 		//for live content change
	private int switchButtonStatus=1;  				//initialize window to webview
	private JButton switchWindowViewButton = new JButton(" Switch To HTML VIEW ");
	private JLabel markdownlabel = new JLabel("  MarkDown View  ");
	private JLabel htmllabel = new JLabel("  Html View  ");
	private JLabel webviewlabel = new JLabel("  Browser View  ");
	
	

	/*******/


	// constructor
	CombinedControls(){

		/* 
		This Class Specially Created To Handle Listener Implementation and Generate Calls To Required
		Methods.

			[https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html] 

		*/
		super(title+" - "+window_name);

		


		/* 

		Create TextArea Object [CodeRail.editor] 
			
			1. Creating Engine for Trasfomation 
			2. Creating Html view
			3. Creating Web View
			4. Creating Header Panel & Its Component
			5. Create Markdown Object
			6. Create Line Number Object
			7. Create ScrollPane Object and Add Editor Object
			8. Add Line Number Object in Scroll Pane
			9. Connect Event Listeners to Markdown Class
			10. Initialise Configurations
		*/
		
		//Creating Object of ADMark to trasform markdown to html
		markdownEngine = new ADMark();
		markdownContent= markdownEngine.input_data;
		htmlContent = markdownEngine.output_data;

		/*Config Editor for htmlview*/
		htmlviewobj = new Editor(getSize().width/20,0);		//Markdown TextArea
		htmlviewobj.setText(htmlContent);
		htmlviewobj.setEditable(false);
		
		/* Congif Panels and Scroller for WebView */
		webviewPanel = new JPanel();
		webviewPanel.setLayout(new BorderLayout());
		webviewPanel.add(web,BorderLayout.CENTER);
		webviewPanel.setBorder(new LineBorder(Color.BLACK,2,false));
		webviewScroller = new JScrollPane(webviewPanel);  //Adding Scroller for webviewPanel
		
		//header panel 
		header.setBackground(new Color(111, 30, 81));
		header.setLayout(new BorderLayout(400,10));
		header.setBorder(new LineBorder(new Color(67,120,210),5,false));

		//config button for Header Panel
		switchWindowViewButton.setForeground(Color.WHITE);
		switchWindowViewButton.setBackground(new Color(25, 42, 86));
		switchWindowViewButton.setPreferredSize(new Dimension(60,35));
		switchWindowViewButton.setFont(new Font("",Font.BOLD,20));
		switchWindowViewButton.setBorder(new LineBorder(Color.RED,3,true));

		//config Labels for Header Panel
		htmllabel.setFont(new Font("",Font.BOLD,20));
		htmllabel.setForeground(Color.WHITE);
		htmllabel.setBackground(new Color(25, 42, 86));
		//htmllabel.setBorder(new LineBorder(Color.RED,5,true));
		webviewlabel.setFont(new Font("",Font.BOLD,20));
		webviewlabel.setForeground(Color.WHITE);
		webviewlabel.setBackground(new Color(25, 42, 86));
		//webviewlabel.setBorder(new LineBorder(Color.RED,5,true));
		markdownlabel.setFont(new Font("",Font.BOLD,20));
		markdownlabel.setForeground(Color.WHITE);		
		markdownlabel.setBackground(new Color(25, 42, 86));
		//markdownlabel.setBorder(new LineBorder(Color.RED,5,true));
		
		

		
		

		  
		//Cofig Editor for Markdown
		markdownobj = new Editor(getSize().width/20,0);		//Markdown TextArea
		markdownobj.setText(markdownContent);				//Setting Intial text Demo
		clm = new LineNumberColumn(markdownobj);			//Line Number 
		markdownScroller = new JScrollPane();		//Scroll bar for markdown area
		
		/*  Setting Viewport For Markdown Scroller */
		markdownScroller.setViewportView(markdownobj);		
		markdownScroller.setRowHeaderView(clm);
		markdownScroller.setBorder(new LineBorder(Color.BLACK,2,false));   //Setting Border

		
		markdownobj.getDocument().addDocumentListener(this);	//adding Listener for Markdown Textarea
		
		
		//this method is used for making panel responsive to window
		super.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                htmlviewobj.setColumns(getSize().width/25);
        		markdownobj.setColumns(getSize().width/22);
        		revalidate();
        		repaint();

            }
        });

		
		/* set font for JTextArea Object */
		Font font_family = new Font("Courier", Font.BOLD,16);
		//Font font_family = new Font("Garuda", Font.BOLD,16);
		markdownobj.setFont(font_family);
		htmlviewobj.setFont(font_family);
		
		/* Undo manager to track change and undo changes */
		manager = new UndoManager();
		markdownobj.getDocument().addUndoableEditListener(manager);

		/* Create Menu Bar Object [CodeRail.AddMenuBar] */
		menu = new AddMenuBar();
		menu.setBackground(new Color(142, 68, 173));
		menu.setPreferredSize(new Dimension(100,45));
		menu.setMargin(new Insets(10,10,10,10));
		
		/* File Manager Module Object */
		FileObj = new FileManager(markdownobj);
		

		/* Status bar */
		statusbarObj = new StatusBar(markdownobj);
		

		//Adding Items To Header Panel
		header.add(markdownlabel,BorderLayout.WEST);
		header.add(switchWindowViewButton,BorderLayout.CENTER);
		header.add(webviewlabel,BorderLayout.EAST);

		//Main Contains for HTMl,MARKDOWN,WEBVIEW
		MainContainer.setLayout(new BorderLayout());
		MainContainer.add(header,BorderLayout.NORTH);
		MainContainer.add(markdownScroller,BorderLayout.WEST);
		MainContainer.add(webviewScroller,BorderLayout.CENTER);

		//initializing web view content
		Platform.setImplicitExit(false);
		Platform.runLater(()->{
				    webView = new WebView();
				  
				    web.setScene(new Scene(webView,800,945));
				    webView.getEngine().loadContent(htmlContent,"text/html");
			    });

		/* Window Configuration  */  
		setLayout(new BorderLayout());
		setSize(window_width, window_height); // Set Size
		setMinimumSize(new Dimension(1300, 730));	//set minimum
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Close Operation
		add(menu,BorderLayout.NORTH);           // Menu
		add(MainContainer,BorderLayout.CENTER);	//adding MainContainer
		add(statusbarObj,BorderLayout.SOUTH);   // Status Bar
		setJMenuBar(menu);                      // Menu
		setVisible(true);
		setFocusable(true);
		clm.update_line_number_configurations();


		/*
		Here, First we implemented ActionListener into self class.
		and then, register this self listener classes with menu bar
		object. Whenever user generate any type of event through menu
		bar, this actionListener Automatically get call.

		Then, actionPerfomed Method get called. we use if else condition
		to filter specific menu bar calls.

		*/
		registerListener();

	}
	@Override
	public void caretUpdate(CaretEvent caretEvent) {
		try{
			int offset = caretEvent.getMark();
			int line = markdownobj.getLineOfOffset(offset);
			statusbarObj.LineStatusUpdate(line+1, offset - markdownobj.getLineStartOffset(line));
		}catch(Exception error){
			System.out.println(error);

		}
		
	}


	public void changedUpdate(DocumentEvent documentEvent) {
		// System.out.println("Document Change Update");
		clm.update_line_number_configurations();
		editing = true;
		System.out.println("work");		
		//updating webview
			try{
				liveHtmlContent = markdownEngine.ADMark(markdownobj.getText());
				htmlviewobj.setText(liveHtmlContent);
				htmlviewobj.setCaretPosition(0);
				Platform.runLater(()->{
					   webView.getEngine().loadContent(liveHtmlContent,"text/html");
					    
				    });
			}
			catch(Exception i){}

		};

	public void removeUpdate(DocumentEvent documentEvent) {
		// System.out.println("remove Update");
		clm.UpdateLineNumbers();
		statusbarObj.UpdateStatus();
		editing = true;
System.out.println("work");
		//updating webview
			try{
				liveHtmlContent = markdownEngine.ADMark(markdownobj.getText());
				//System.out.println(markdownobj.getText());
				htmlviewobj.setText(liveHtmlContent);
				htmlviewobj.setCaretPosition(0);
			
				Platform.runLater(()->{
					  webView.getEngine().loadContent(liveHtmlContent,"text/html");
					    
				    });
			}
			catch(Exception k){}
		};

	public void insertUpdate(DocumentEvent documentEvent) {
		clm.UpdateLineNumbers();
		statusbarObj.UpdateStatus();
		editing = true;
System.out.println("work");
		//updating webview
			try{
				liveHtmlContent = markdownEngine.ADMark(markdownobj.getText());
				//System.out.println(markdownobj.getText());
				htmlviewobj.setText(liveHtmlContent);
				htmlviewobj.setCaretPosition(0);
				//System.out.println(""+markdown.getCaretPosition());
				Platform.runLater(()->{
					  webView.getEngine().loadContent(liveHtmlContent,"text/html");
					    
				    });
				
			}
			catch(Exception l){}
		};

	public void registerListener(){
		/*
		Register Self Class ActionListener With Menu Bar Objects.
		So, Whenever User Generate Event this class actionPerform
		actomatically get call.

		*/
		
		//adding button listener
		switchWindowViewButton.addActionListener(this);

		// menu file items
		menu.menu_file_new.addActionListener(this);
		menu.menu_file_open.addActionListener(this);
		menu.menu_file_savemarkdown.addActionListener(this);
		menu.menu_file_savehtml.addActionListener(this);
		menu.menu_file_saveas.addActionListener(this);
		menu.menu_file_print.addActionListener(this);
		menu.menu_file_exit.addActionListener(this);


		// menu edit
		menu.menu_edit_undo.addActionListener(this);
		menu.menu_edit_redo.addActionListener(this);
		menu.menu_edit_cut.addActionListener(this);
		menu.menu_edit_copy.addActionListener(this);
		menu.menu_edit_paste.addActionListener(this);
		menu.menu_edit_delete.addActionListener(this);
		menu.menu_edit_selectall.addActionListener(this);
		menu.menu_edit_find.addActionListener(this);
		menu.menu_edit_replace.addActionListener(this);
		menu.menu_edit_goto.addActionListener(this);

		// menu view
		menu.theme_a.addActionListener(this);
		menu.theme_b.addActionListener(this);
		menu.theme_c.addActionListener(this);
		menu.theme_d.addActionListener(this);
		menu.theme_e.addActionListener(this);
		menu.theme_f.addActionListener(this);
		menu.theme_g.addActionListener(this);
		menu.theme_h.addActionListener(this);
		menu.theme_0.addActionListener(this);
		menu.language_c.addActionListener(this);
		menu.language_cplus.addActionListener(this);
		menu.language_csharp.addActionListener(this);
		menu.language_css.addActionListener(this);
		menu.language_html.addActionListener(this);
		menu.language_java.addActionListener(this);
		menu.language_javascript.addActionListener(this);
		menu.language_plain.addActionListener(this);
		menu.language_php.addActionListener(this);
		menu.language_python.addActionListener(this);
		menu.menu_view_statusbar.addItemListener(this);
		menu.menu_view_cursorline.addItemListener(this);
		menu.menu_view_linenumber.addItemListener(this);

		// menu font
		menu.menu_font_wordwrap.addActionListener(this);
		menu.menu_font_font.addActionListener(this);
		menu.menu_font_font_size_large.addActionListener(this);
		menu.menu_font_font_size_small.addActionListener(this);

		// menu help
		menu.menu_help_topic.addActionListener(this);
		menu.menu_help_about.addActionListener(this);

		// Override Close Operation
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				exit();
			}
		});

		markdownobj.addCaretListener(this);
		
	}

	private void exit(){
		System.out.println("[-] Safe Protocol to Exit..");

		// if any changes made
		if(editing){

			int ask = JOptionPane.showConfirmDialog(null, 
					"Do you want to Save The Changes");

			// Yes, Save Changes and Close
			if (ask==JOptionPane.YES_OPTION) {
				FileObj.SaveFile();
				System.exit(0);
									
			// Don't Save Changes But Close 
			}else if (ask==JOptionPane.NO_OPTION) {
				System.exit(0);

			// Don't Do anything
			}else{

			}


		}else{
			System.exit(0);
		}
		
	}

	//adding action to checkbox menu like status bar,linenumber,cursor
	public void itemStateChanged(ItemEvent e) 
	{
		// for status bar state change
		if (e.getSource()==menu.menu_view_statusbar) {
			System.out.println("[+] Status Bar State Changed");

			if(e.getStateChange()==1)
			{
				statusbarObj.setVisible(true);
			}
			else{ 
				statusbarObj.setVisible(false);
			}
		}
		// for line number state change
		else if (e.getSource()==menu.menu_view_linenumber) {
			System.out.println("[+] Line Number State Changed");

			if(e.getStateChange()==1)
			{
				clm.setVisible(true);
			}
			else{ 
				clm.setVisible(false);
			}
		}

		// for cursor highlight
		else if (e.getSource()==menu.menu_view_cursorline){
			System.out.println("[+] Cursor Line State Changed");

			if(e.getStateChange()==1)
			{
				//statusbarObj.setVisible(true);
			}
			else{ 
				//statusbarObj.setVisible(false);
			}   
		}
		
	}
	
	public void actionPerformed(ActionEvent e){
		if (debug) {
			System.out.println(e.getSource());
							
		}
		try{
			CustomActionPerformed(e);

		}catch (Exception error){
			System.out.println("[-] Error Catch During CustomActionPerformed Function Call");
			error.printStackTrace();
			System.out.println(error);
		}
	}

	private void CustomActionPerformed(ActionEvent e) throws Exception{
		/*
		This method catch events.

			USEFUL JTextArea Supported Functions Methods

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
		//switch button action
		if(e.getSource() == switchWindowViewButton)
			{
				if(switchButtonStatus == 0)
				{
					System.out.println("Browser view");

					//changing status of button and text
					switchButtonStatus = 1;
					switchWindowViewButton.setText(" Switch To HTML VIEW ");

					
					MainContainer.remove(webviewScroller);
					webviewPanel.remove(htmlviewobj);
					MainContainer.remove(markdownScroller);
					MainContainer.remove(header);
					header.remove(htmllabel);
					header.remove(webviewlabel);

					header.add(markdownlabel,BorderLayout.WEST);
					header.add(webviewlabel,BorderLayout.EAST);
					MainContainer.add(header,BorderLayout.NORTH);
					MainContainer.add(markdownScroller,BorderLayout.WEST);
					MainContainer.add(webviewScroller,BorderLayout.CENTER);
					webviewPanel.add(web);
				
					
					
					this.revalidate();
					this.repaint();


				}
				else if(switchButtonStatus == 1)
				{
					System.out.println("html view");

					//changing status of button and text
					switchButtonStatus = 0;
					switchWindowViewButton.setText(" Switch To Browser View ");

					//liveHtmlContent=htmlviewobj.getText();
					//htmlviewobj.setText(liveHtmlContent);
					
					MainContainer.remove(markdownScroller);
					MainContainer.remove(webviewScroller);
					webviewPanel.remove(web);
					MainContainer.remove(header);
					header.remove(webviewlabel);

					System.out.println(""+Platform.isImplicitExit());

					header.add(htmllabel,BorderLayout.EAST);
					MainContainer.add(header,BorderLayout.NORTH);
					MainContainer.add(markdownScroller,BorderLayout.WEST);
					MainContainer.add(webviewScroller,BorderLayout.CENTER);
					webviewPanel.add(htmlviewobj);
						
					this.revalidate();
					this.repaint();

				}
			}

		// menu file items
		if (e.getSource()==menu.menu_file_new){
			if (debug) {
				System.out.println("[-] new file");

			}
			/*FileObj.SaveFile();*/
			title="Untitled";
			FileObj.NewFile();
			setTitle(title+" -"+window_name);
		}
		
		else if (e.getSource()==menu.menu_file_open){
			if (debug) {
				System.out.println("[-] open file");

			}
			FileObj.OpenFileChooser();
			clm.UpdateLineNumbers();
			statusbarObj.UpdateStatus();
			title=FileObj.Path;
			setTitle(title+" -"+window_name);
			//htmlviewobj.setText(markdownEngine.ADMark(markdownobj.getText()));
			//markdownobj.setCaretPosition(0);
			htmlviewobj.setCaretPosition(0);
		}
		
		else if (e.getSource()==menu.menu_file_savemarkdown){
			if (debug) {
				System.out.println("[-] save markdown file");

			}
			FileObj.SaveFile();
			title=FileObj.Path;
			setTitle(title+" -"+window_name);

		}

		else if(e.getSource()==menu.menu_file_savehtml){
			if (debug) {
				System.out.println("[-] save html file");

			}
			FileObj.html = true;
			FileObj.exportable_data = htmlviewobj.getText();
			FileObj.SaveAsFile();
				
		}

		else if (e.getSource()==menu.menu_file_saveas){
			if (debug) {
				System.out.println("[-] save as file");

			}
			FileObj.SaveAsFile();
			title=FileObj.Path;
			setTitle(title+" -"+window_name);
		}
		
		else if (e.getSource()==menu.menu_file_print){
			if (debug) {
				System.out.println("[-] print file");

			}
		}
		
		else if (e.getSource()==menu.menu_file_exit){
			if (debug) {
				System.out.println("[-] exit");

			}

			exit();
		}



		// menu edit
		else if (e.getSource()==menu.menu_edit_undo){
			if (debug) {
				System.out.println("[-] undo");

			}
			manager.undo();
		}
		
		else if (e.getSource()==menu.menu_edit_redo){
			if (debug) {
				System.out.println("[-] redo");

			}
			manager.redo();
		}
		
		else if (e.getSource()==menu.menu_edit_cut){
			if (debug) {
				System.out.println("[-] cut");

			}
			markdownobj.cut();
		}
		
		else if (e.getSource()==menu.menu_edit_copy){
			if (debug) {
				System.out.println("[-] copy");

			}
			markdownobj.copy();
		}
		
		else if (e.getSource()==menu.menu_edit_paste){
			if (debug) {
				System.out.println("[-] paste");

			}
			markdownobj.paste();
		}
		
		else if (e.getSource()==menu.menu_edit_delete){
			if (debug) {
				System.out.println("[-] delete");

			}
			markdownobj.setText("");

		}

		else if (e.getSource()==menu.menu_edit_selectall){
			if (debug) {
				System.out.println("[-] selectall");

			}
			markdownobj.selectAll();
		}
		
		else if (e.getSource()==menu.menu_edit_find){
			if (debug) {
				System.out.println("[-] find");
				
			}
			PopUpDialog = new SmallPopWindows(this, markdownobj, clm, 0);
			
		}
		
		else if (e.getSource()==menu.menu_edit_replace){
			if (debug) {
				System.out.println("[-] replace");

			}
			PopUpDialog = new SmallPopWindows(this, markdownobj, clm, 1);
		}
		
		
		else if (e.getSource()==menu.menu_edit_goto){
			if (debug) {
				System.out.println("[-] goto");

			}
			try{
				int l = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Line Number"));
				System.out.println(l);
				markdownobj.setCaretPosition(markdownobj.getLineStartOffset(l)-1);
			}catch(Exception error){
				System.out.println(error);
			}
		}

		// Default
		else if (e.getSource()==menu.theme_0){
			if (debug) {
				System.out.println("[-] theme selected");
				

			}

			markdownobj.setBackground(Color.WHITE);
			markdownobj.setForeground(Color.BLACK);
			menu.setBackground(new Color(142, 68, 173));
			markdownobj.setCaretColor(Color.BLACK);
			clm.setBackground(new Color(226, 230, 231));
			clm.setForeground(new Color(34, 47, 62));
			statusbarObj.setBackground(new Color(226, 230, 231));
			statusbarObj.setcomponentcolor(34,47,62);
		}
		// Dark
		else if (e.getSource()==menu.theme_a){
			if (debug) {
				System.out.println("[-] theme selected");

			}

			markdownobj.setBackground(new Color(61, 61, 61));
			markdownobj.setForeground(new Color(236, 240, 241));
			menu.setBackground(new Color(142, 68, 173));
			markdownobj.setCaretColor(new Color(236, 240, 241));
			clm.setBackground(new Color(75, 75, 75));
			clm.setForeground(new Color(236, 240, 241));
			statusbarObj.setBackground(new Color(75, 75, 75));
			statusbarObj.setcomponentcolor(236,240,241);
		}
		// Light
		else if (e.getSource()==menu.theme_b){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			markdownobj.setBackground(new Color(236, 240, 241));
			markdownobj.setForeground(new Color(34, 47, 62));
			menu.setBackground(new Color(211, 84, 0));
			markdownobj.setCaretColor(new Color(34, 47, 62));
			clm.setBackground(new Color(226, 230, 231));
			clm.setForeground(new Color(34, 47, 62));
			statusbarObj.setBackground(new Color(226, 230, 231));
			statusbarObj.setcomponentcolor(34,47,62);
		}
		// Cool
		else if (e.getSource()==menu.theme_c){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			markdownobj.setBackground(new Color(25, 42, 86));
			markdownobj.setForeground(new Color(236, 240, 241));
			menu.setBackground(new Color(44, 62, 80));
			markdownobj.setCaretColor(new Color(236,240,241));
			clm.setBackground(new Color(15,32,76));
			clm.setForeground(new Color(236, 240, 241));
			statusbarObj.setBackground(new Color(15, 32, 86));
			statusbarObj.setcomponentcolor(236,240,241);
		}
		// Vim
		else if (e.getSource()==menu.theme_d){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			markdownobj.setBackground(new Color(111, 30, 81));
			markdownobj.setForeground(new Color(247, 241, 227));
			menu.setBackground(new Color(179, 55, 113));
			markdownobj.setCaretColor(new Color(247, 241, 227));
			clm.setBackground(new Color(101, 20, 71));
			clm.setForeground(new Color(247, 241, 227));
			statusbarObj.setBackground(new Color(101, 20, 71));
			statusbarObj.setcomponentcolor(247, 241, 227);
		}
		// Ocean
		else if (e.getSource()==menu.theme_e){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			markdownobj.setBackground(new Color(154, 236, 219));
			markdownobj.setForeground(new Color(44, 58, 71));
			menu.setBackground(new Color(130, 88, 159));
			markdownobj.setCaretColor(new Color(44, 58, 71));
			clm.setBackground(new Color(144, 226, 209));
			clm.setForeground(new Color(44, 58, 71));
			statusbarObj.setBackground(new Color(144, 226, 209));
			statusbarObj.setcomponentcolor(44, 58, 71);
		}
		// Arc
		else if (e.getSource()==menu.theme_f){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			markdownobj.setBackground(new Color(72, 84, 96));
			markdownobj.setForeground(new Color(248, 239, 186));
			menu.setBackground(new Color(44, 58, 71));
			markdownobj.setCaretColor(new Color(248, 239, 186));
			clm.setBackground(new Color(62, 74, 86));
			clm.setForeground(new Color(248, 239, 186));
			statusbarObj.setBackground(new Color(62, 74, 86));
			statusbarObj.setcomponentcolor(248, 239, 186);

		}
		// Pro
		else if (e.getSource()==menu.theme_g){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			markdownobj.setBackground(new Color(44, 58, 71));
			markdownobj.setForeground(new Color(255, 121, 63));
			menu.setBackground(new Color(24, 44, 97));
			markdownobj.setCaretColor(new Color(130, 88, 159));
			clm.setBackground(new Color(34, 48, 61));
			clm.setForeground(new Color(255, 121, 63));
			statusbarObj.setBackground(new Color(34, 48, 61));
			statusbarObj.setcomponentcolor(255, 121, 63);
		}
		// MAC
		else if (e.getSource()==menu.theme_h){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			markdownobj.setBackground(new Color(236, 240, 241));
			markdownobj.setForeground(new Color(44, 62, 80));
			menu.setBackground(new Color(44, 58, 71));
			markdownobj.setCaretColor(new Color(44, 62, 80));
			clm.setBackground(new Color(226, 230, 231));
			clm.setForeground(new Color(44, 62, 80));
			statusbarObj.setBackground(new Color(226, 230, 231));
			statusbarObj.setcomponentcolor(44, 62, 80);
		}

		else if (e.getSource()==menu.language_plain){
			if (debug) {
				System.out.println("[-] plain language selected");

			}
			

		}

		else if (e.getSource()==menu.language_c){
			if (debug) {
				System.out.println("[-] c language selected");

			}

		}

		else if (e.getSource()==menu.language_cplus){
			if (debug) {
				System.out.println("[-]  c++ language selected");

			}

		}

		else if (e.getSource()==menu.language_csharp){
			if (debug) {
				System.out.println("[-] csharp language selected");

			}

		}

		else if (e.getSource()==menu.language_css){
			if (debug) {
				System.out.println("[-] css language selected");

			}

		}

		else if (e.getSource()==menu.language_html){
			if (debug) {
				System.out.println("[-] html language selected");

			}

		}

		else if (e.getSource()==menu.language_java){
			if (debug) {
				System.out.println("[-] java language selected");

			}

		}

		else if (e.getSource()==menu.language_javascript){
			if (debug) {
				System.out.println("[-] javascript language selected");

			}

		}

		else if (e.getSource()==menu.language_php){
			if (debug) {
				System.out.println("[-] php language selected");

			}

		}

		else if (e.getSource()==menu.language_python){
			if (debug) {
				System.out.println("[-] python language selected");

			}

		}

		else if (e.getSource()==menu.menu_view_statusbar){
			if (debug) {
				System.out.println("[-] view statusbar");

			}

		}

		else if (e.getSource()==menu.menu_view_cursorline){
			if (debug) {
				System.out.println("[-] highlight cursor line");

			}
		}


		else if (e.getSource()==menu.menu_view_linenumber){
			if (debug) {
				System.out.println("[-] show line number");

			}
		}


		// menu font
		else if (e.getSource()==menu.menu_font_wordwrap){
			if (debug) {
				System.out.println("[-] view wordwrap");

			}


			PopUpDialog = new SmallPopWindows(this, markdownobj,clm,3);
		}
		else if (e.getSource()==menu.menu_font_font){
			if (debug) {
				System.out.println("[-] font");

			}
		}
		else if (e.getSource()==menu.menu_font_font_size_large){
			if (debug) {
				System.out.println("[-] font size large");

			}
			markdownobj.increasefont();
			clm.update_line_number_configurations();
		}
		else if (e.getSource()==menu.menu_font_font_size_small){
			if (debug) {
				System.out.println("[-] font size small");


			}
			markdownobj.decreasefont();
			clm.update_line_number_configurations();
		}

		// menu help
		else if (e.getSource()==menu.menu_help_topic){
			if (debug) {
				System.out.println("[-] help topic");

			}
				if (Desktop.isDesktopSupported()) 
				{
				  try {
						Desktop.getDesktop().browse(new URI("https://www.blaregroup.com/coderail-text-editor/"));
				  }
				  catch (Exception m) {}
				} 
		}
		else if (e.getSource()==menu.menu_help_about){
			if (debug) {
				System.out.println("[-] About");
			}
			PopUpDialog = new SmallPopWindows(this, markdownobj,clm, 2);
		}
	

	}
}


// main class to run Coderail classes
class execute{


	public static void main(String[] args){
		CombinedControls markdownobj = new CombinedControls();
		System.out.println("Starting CodeRail...");
		
	}

}
