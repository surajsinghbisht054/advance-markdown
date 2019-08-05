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
		private JButton switchWindowViewButton = new JButton(" Switch To HTML VIEW ");
		

		//creating componets of viewport panel
		private JPanel header = new JPanel();
		private JLabel markdownlabel = new JLabel("  MarkDown View  ");
		private JLabel htmllabel = new JLabel("  Html View  ");
		private JLabel webviewlabel = new JLabel("  Browser View  ");
		private JTextArea markdown;		//for markdown coading 
		private JTextArea html;			//for html coading
		private JPanel browserView = new JPanel();
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

		private int switchButtonStatus=1;

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
        browserView.add(web);

		markdownEngine = new ADMark();
		markdownContent= markdownEngine.input_data;
		htmlContent = markdownEngine.output_data;
		
		//adding scroll bar to every component
		//scrollAll = new JScrollPane(viewportPanel);
		scrollhtml = new JScrollPane(html);
		//scrollweb = new JScrollPane(browserView);
		scrollmarkdown = new JScrollPane(markdown);

		//config panels
		markdown.setTabSize(1);
		html.setTabSize(1);
		html.setFont(new Font("Courier", Font.BOLD,18));
		markdown.setFont(new Font("Courier", Font.BOLD,18));
		html.setMargin(new Insets(0,10,10,0));
		html.setEditable(false);
		markdown.setMargin(new Insets(0,10,10,0));
		web.setBorder(new LineBorder(Color.BLACK,1,false));
		

		//adding button on buttonpanel
		buttonPanel.add(switchWindowViewButton);
				
		//header panel for vieport panel
		htmllabel.setFont(new Font("",Font.BOLD,26));
		htmllabel.setForeground(Color.WHITE);
		htmllabel.setBackground(Color.RED);
		htmllabel.setBorder(new LineBorder(Color.RED,5,true));
		webviewlabel.setFont(new Font("",Font.BOLD,26));
		webviewlabel.setForeground(Color.WHITE);
		webviewlabel.setBackground(Color.RED);
		webviewlabel.setBorder(new LineBorder(Color.RED,5,true));
		markdownlabel.setFont(new Font("",Font.BOLD,26));
		markdownlabel.setForeground(Color.WHITE);		
		markdownlabel.setBackground(Color.RED);
		markdownlabel.setBorder(new LineBorder(Color.RED,5,true));
		header.setBackground(Color.BLACK);
		header.setLayout(new BorderLayout());
		header.setBorder(new LineBorder(new Color(67,120,210),5,false));
		header.add(markdownlabel,BorderLayout.WEST);
		header.add(webviewlabel,BorderLayout.EAST);



		//setting layout of viewport panel to box layout 
		viewportPanel.setLayout(new BorderLayout());
		viewportPanel.add(header,BorderLayout.NORTH);
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
		switchWindowViewButton.setForeground(Color.WHITE);
		switchWindowViewButton.setBackground(Color.BLACK);
		switchWindowViewButton.setPreferredSize(new Dimension(400,40));
		switchWindowViewButton.setFont(new Font("",Font.BOLD,23));
		switchWindowViewButton.setBorder(new LineBorder(Color.RED,3,true));

		//adding document listener
		html.getDocument().addDocumentListener(this);
		markdown.getDocument().addDocumentListener(this);


		
		
		//adding button listener
		switchWindowViewButton.addActionListener(this);
		
		//config panels
		buttonPanel.setBackground(Color.BLACK);
		buttonPanel.setBorder(new LineBorder(new Color(67,120,210),5,false));
		viewportPanel.setBorder(new LineBorder(new Color(67,120,210),5,false));

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
			
			if(e.getSource() == switchWindowViewButton)
			{
				if(switchButtonStatus == 0)
				{
					System.out.println("Browser view");

					//changing status of button and text
					switchButtonStatus = 1;
					switchWindowViewButton.setText(" Switch To HTML VIEW ");



					liveHtmlContent=html.getText();
					
					Platform.runLater(()->{

					    webView = new WebView();
					  	
					    web.setScene(new Scene(webView));
					    webView.getEngine().loadContent(liveHtmlContent,"text/html");
					    
				    });
					
					
					viewportPanel.remove(scrollhtml);
					viewportPanel.remove(scrollmarkdown);
					viewportPanel.remove(header);
					header.remove(htmllabel);
					header.remove(webviewlabel);

					header.add(markdownlabel,BorderLayout.WEST);
					header.add(webviewlabel,BorderLayout.EAST);
					viewportPanel.add(header,BorderLayout.NORTH);
					viewportPanel.add(scrollmarkdown,BorderLayout.WEST);
					viewportPanel.add(web,BorderLayout.CENTER);
				
					
					
					this.revalidate();
					this.repaint();


				}
				else if(switchButtonStatus == 1)
				{
					System.out.println("html view");

					//changing status of button and text
					switchButtonStatus = 0;
					switchWindowViewButton.setText(" Switch To Browser View ");

					liveHtmlContent=html.getText();
					
					viewportPanel.remove(scrollmarkdown);
					viewportPanel.remove(web);
					viewportPanel.remove(header);
					header.remove(webviewlabel);


					header.add(htmllabel,BorderLayout.EAST);
					viewportPanel.add(header,BorderLayout.NORTH);
					viewportPanel.add(scrollmarkdown,BorderLayout.WEST);
					viewportPanel.add(scrollhtml,BorderLayout.CENTER);
						
					this.revalidate();
					this.repaint();

				}
			}
			
		}


	public static void main(String args[])
	{	

			webview obj = new webview();
	}
}

