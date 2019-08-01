import ADMark.*;
/*

	Author
			Suraj Singh Bisht
			Surajsinghbisht054@gmail.com
			www.bitforestinfo.com
			www.blaregroup.com
			
			Himanshu Sharma 
			himanshusharma2972@gmail.com
			www.blaregroup.com

	===============================================================
				Advance MarkDown To HTML Generating Engine
	===============================================================


	Reference Docs:
		https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
		https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        https://www.markdownguide.org/extended-syntax/
        https://daringfireball.net/projects/markdown/syntax#header
		

*/
import javafx.scene.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import javafx.scene.web.WebView;
import javafx.embed.swing.*;
import javafx.application.Platform;
import java.awt.event.*; 
import java.lang.System;
import java.io.*;
import javax.swing.UIManager;
import javax.swing.border.*;

class webview extends JFrame implements ActionListener,DocumentListener
{
		//creating ADMark class object
		private ADMark markdownEngine;
		
		//creating panel 
		private JPanel buttonPanel = new JPanel(); 		//for storing button
		private JPanel viewportPanel = new JPanel();	//for storing content
		
		//creating buttons
		private JButton htmlButton = new JButton("  HTML VIEW  ");
		private JButton markdownButton = new JButton("  MARKDOWN VIEW  ");
		

		//creating componets of viewport panel
		private JTextArea markdown;		//for markdown coading 
		private JTextArea html;			//for html coading
		private JFXPanel web = new JFXPanel();					//for webview
		private WebView webView;								//webview object	
		
		//creating scroll bar for all components
		//private JScrollPane scrollAll;
		private JScrollPane scrollhtml;
		private JScrollPane scrollweb;
		private JScrollPane scrollmarkdown;

		private String markdownContent; // Content
		private String htmlContent;
		
		private String liveHtmlContent;

	public webview()
	{	

		super("Markdown-Html Engine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1900,1000);
		setMinimumSize(new Dimension(1666, 727));

		//this method is used for making panel responsive to window
		super.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
               //System.out.println("Size Changed to "+getSize().height+","+getSize().width);
                markdown.setColumns(getSize().width/23);
        		html.setColumns(getSize().width/23);
            }
        });

        markdown = new JTextArea(1,getSize().width/20);
        html = new JTextArea(1,getSize().width/20);

		markdownEngine = new ADMark();
		markdownContent= markdownEngine.input_data;
		htmlContent = markdownEngine.output_data;
		
		//adding scroll bar to every component
		//scrollAll = new JScrollPane(viewportPanel);
		scrollhtml = new JScrollPane(html);
		scrollweb = new JScrollPane(web);
		scrollmarkdown = new JScrollPane(markdown);

		//config panels
		markdown.setTabSize(1);
		html.setTabSize(1);
		html.setFont(new Font("Courier", Font.BOLD,18));
		markdown.setFont(new Font("Courier", Font.BOLD,18));
		html.setMargin(new Insets(0,10,10,0));
		markdown.setMargin(new Insets(0,10,10,0));

		//adding button on buttonpanel
		buttonPanel.add(htmlButton);
		buttonPanel.add(markdownButton);
		

		//setting layout of viewport panel to box layout 
		viewportPanel.setLayout(new BorderLayout());
		viewportPanel.add(scrollmarkdown,BorderLayout.WEST);
		viewportPanel.add(web,BorderLayout.CENTER);

		

		
		//initializing web view content
		Platform.runLater(()->{
				    webView = new WebView();
				  
				    web.setScene(new Scene(webView,800,945));
				    webView.getEngine().loadContent(htmlContent,"text/html");
			    });
		
		
		//initializing content of markdown and html text area
		html.setText(htmlContent);
		markdown.setText(markdownContent);
		
		//config button
		htmlButton.setFont(new Font("",Font.BOLD,20));
		htmlButton.setBorder(new LineBorder(Color.RED,3,true));
		htmlButton.setBackground(new Color(0,85,130));
		htmlButton.setForeground(Color.WHITE);
		htmlButton.setPreferredSize(new Dimension(200,40));
		markdownButton.setPreferredSize(new Dimension(250,40));
		markdownButton.setFont(new Font("",Font.BOLD,20));
		markdownButton.setBorder(new LineBorder(Color.RED,3,true));
		markdownButton.setBackground(new Color(0,85,130));
		markdownButton.setForeground(Color.WHITE);

		//adding document listener
		html.getDocument().addDocumentListener(this);
		markdown.getDocument().addDocumentListener(this);

		
		
		//adding button listener
		markdownButton.addActionListener(this);
		htmlButton.addActionListener(this);
		
		//config panels
		buttonPanel.setBackground(new Color(31, 35, 64));
		buttonPanel.setBorder(new LineBorder(new Color(67,120,210),5,false));
		viewportPanel.setBorder(new LineBorder(new Color(67,120,210),10,false));

		//adding component to main frame
		add(buttonPanel,BorderLayout.NORTH);
		add(viewportPanel,BorderLayout.CENTER);		//here scrollAll for html,markdown,web panels
		
		setVisible(true);
		revalidate();
		repaint();
	}

	/*=========			document listener 	==============*/
	public void changedUpdate(DocumentEvent documentEvent) {
			
			try{
				liveHtmlContent = markdownEngine.ADMark(markdown.getText());
				html.setText(liveHtmlContent);

				Platform.runLater(()->{
					    webView = new WebView();
					  
					    web.setScene(new Scene(webView));
					    webView.getEngine().loadContent(liveHtmlContent,"text/html");
					    
				    });
			}
			catch(Exception i){}

		};

	public void removeUpdate(DocumentEvent documentEvent) {
			
			try{
				liveHtmlContent = markdownEngine.ADMark(markdown.getText());
				html.setText(liveHtmlContent);
			
				Platform.runLater(()->{
					    webView = new WebView();
					  
					    web.setScene(new Scene(webView));
					    webView.getEngine().loadContent(liveHtmlContent,"text/html");
					    
				    });
			}
			catch(Exception k){}	
		};

	public void insertUpdate(DocumentEvent documentEvent) {
			
			try{
				liveHtmlContent = markdownEngine.ADMark(markdown.getText());
				html.setText(liveHtmlContent);
			
				Platform.runLater(()->{
					    webView = new WebView();
					  
					    web.setScene(new Scene(webView));
					    webView.getEngine().loadContent(liveHtmlContent,"text/html");
					    
				    });
			}
			catch(Exception l){}
		};




	//handling action on button
	public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==markdownButton)
			{
				
				System.out.println("markdown view");

				liveHtmlContent=html.getText();
				
				Platform.runLater(()->{
				    webView = new WebView();
				  
				    web.setScene(new Scene(webView));
				    webView.getEngine().loadContent(liveHtmlContent,"text/html");
				    
			    });
				
				viewportPanel.remove(scrollhtml);
				viewportPanel.remove(web);
				viewportPanel.add(scrollmarkdown,BorderLayout.WEST);
				viewportPanel.add(web,BorderLayout.CENTER);
				
				this.revalidate();
				this.repaint();
				
			}

			if(e.getSource() == htmlButton)
			{	
				System.out.println("html view");

				liveHtmlContent=html.getText();
								
				Platform.runLater(()->{
				    webView = new WebView();
				  
				    web.setScene(new Scene(webView));
				    webView.getEngine().loadContent(liveHtmlContent,"text/html");
			    });
		
				viewportPanel.remove(scrollmarkdown);
				viewportPanel.remove(web);
				viewportPanel.add(scrollhtml,BorderLayout.WEST);
				viewportPanel.add(web,BorderLayout.CENTER);
					
				this.revalidate();
				this.repaint();

				
			}

			
		}


	public static void main(String args[])
	{	

			webview obj = new webview();
	}
}

