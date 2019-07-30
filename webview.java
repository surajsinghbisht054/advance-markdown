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

class webview extends JFrame implements ActionListener,DocumentListener
{
		//creating ADMark class object
		private ADMark markdownEngine;
		
		//creating panel 
		private JPanel buttonPanel = new JPanel(); 		//for storing button
		private JPanel viewportPanel = new JPanel();	//for storing content
		
		//creating buttons
		private JButton htmlButton = new JButton("HTML VIEW");
		private JButton markdownButton = new JButton("MARKDOWN VIEW");
		

		//creating componets of viewport panel
		private JTextArea markdown = new JTextArea(40,80);		//for markdown coading 
		private JTextArea html = new JTextArea(40,80);			//for html coading
		private JFXPanel web = new JFXPanel();					//for webview
		private WebView webView;								//webview object	
		
		//creating scroll bar for all components
		private JScrollPane scrollAll;
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

		markdownEngine = new ADMark();
		markdownContent= markdownEngine.input_data;
		htmlContent = markdownEngine.output_data;
		
		//adding scroll bar to every component
		scrollAll = new JScrollPane(viewportPanel);
		scrollhtml = new JScrollPane(html);
		scrollweb = new JScrollPane(web);
		scrollmarkdown = new JScrollPane(markdown);

		//config panels
		//web.setPreferredSize(new Dimension(900,600));
		markdown.setPreferredSize(new Dimension(50,200));
		html.setPreferredSize(new Dimension(50,200));
		markdown.setTabSize(1);
		html.setTabSize(1);

		//config
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
		viewportPanel.add(scrollweb,BorderLayout.CENTER);

		

		
		//initializing web view content
		Platform.runLater(()->{
				    webView = new WebView();
				  
				    web.setScene(new Scene(webView,800,945));
				    webView.getEngine().loadContent(htmlContent,"text/html");
			    });
		
		
		//initializing content of markdown and html text area
		html.setText(htmlContent);
		markdown.setText(markdownContent);
		
		
		//adding document listener
		html.getDocument().addDocumentListener(this);
		markdown.getDocument().addDocumentListener(this);

		
		
		//adding button listener
		markdownButton.addActionListener(this);
		htmlButton.addActionListener(this);
		

		//adding component to main frame
		add(buttonPanel,BorderLayout.NORTH);
		add(scrollAll,BorderLayout.CENTER);		//here scrollAll for html,markdown,web panels
		
		setVisible(true);
		revalidate();
		repaint();
	}

	/*					document listener 					*/
	public void changedUpdate(DocumentEvent documentEvent) {
			
			try{
				liveHtmlContent = markdownEngine.ADMark(markdown.getText());
				html.setText(liveHtmlContent);

				Platform.runLater(()->{
					    webView = new WebView();
					  
					    web.setScene(new Scene(webView,900,947));
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
					  
					    web.setScene(new Scene(webView,800,947));
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
					  
					    web.setScene(new Scene(webView,800,947));
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
				  
				    web.setScene(new Scene(webView,800,947));
				    webView.getEngine().loadContent(liveHtmlContent,"text/html");
				    
			    });
				
				viewportPanel.remove(scrollhtml);
				viewportPanel.add(scrollmarkdown);
				
				
				this.revalidate();
				this.repaint();
				
			}

			if(e.getSource() == htmlButton)
			{	
				System.out.println("html view");

				liveHtmlContent=html.getText();
								
				Platform.runLater(()->{
				    webView = new WebView();
				  
				    web.setScene(new Scene(webView,800,947));
				    webView.getEngine().loadContent(liveHtmlContent,"text/html");
			    });
		
				viewportPanel.remove(scrollmarkdown);
				viewportPanel.add(scrollhtml);

					
				this.revalidate();
				this.repaint();

				
			}

			
		}


	public static void main(String args[])
	{	

			webview obj = new webview();
	}
}

