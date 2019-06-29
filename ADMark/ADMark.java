package ADMark;

/*

	Author
			Suraj Singh Bisht
			Surajsinghbisht054@gmail.com
			www.bitforestinfo.com
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
import java.lang.System;
import java.io.*;
import java.util.regex.*;












/* Still UnderProcess */

class ADMarkProcessor{

    /* Expressions */
    String FOLLOWED_BY_NEWLINE  = "(?<=\n)";
    String COMMENTS_EXPRESSION  = "(/\\*[\\s\\S]+?\\*/)"; // Comment
    String HEADINGS_EXPRESSION  = "(#+ [^\n]+)";         // Heading
    
    
    String LINEBREAK_EXPRESSION = "(-{1,}.+\n)";                   // Line Break
    String HORI_LINE_EXPRESSION = "(={1,}.+\n)";                  // Horizontal Line
    
    String UL_LIST_EXPRESSION = "((\\s*[\\-\\+\\*]+ .+\n)+)";    // Unordered List
    String OL_LIST_EXPRESSION = "((\\s*\\d+\\. .+\n)+)";        // Ordered List
    
    String CODES_EXPRESSION = "(```[\\s\\S]+?```)";               // Code Snippet
    String TABLE_EXPRESSION = "(.+)\n([-:\\| ]{3,})(\n.+)+";   // Table
    String BLOCKQ_EXPRESSION = "(( &gt;|&gt;)+ .+\n)";         // Blockquote
    String ICODE_EXPRESSION = "(( {4,}.+\n)+)";                  // Internal Codes
    String SOURCE_EXPRESSION = "(\\.{5,})([\\s\\S]+?)(\\.{5,})";   // External HTML Codes Added
    
    /* Combining All Expression */
    String EXP = String.join("|", 
                    COMMENTS_EXPRESSION, 
                    FOLLOWED_BY_NEWLINE + CODES_EXPRESSION,
                    FOLLOWED_BY_NEWLINE + HEADINGS_EXPRESSION,
                    FOLLOWED_BY_NEWLINE + LINEBREAK_EXPRESSION,
                    FOLLOWED_BY_NEWLINE + HORI_LINE_EXPRESSION,
                    FOLLOWED_BY_NEWLINE + OL_LIST_EXPRESSION,
                    FOLLOWED_BY_NEWLINE + UL_LIST_EXPRESSION,
                    FOLLOWED_BY_NEWLINE + TABLE_EXPRESSION,
                    FOLLOWED_BY_NEWLINE + BLOCKQ_EXPRESSION,
                    FOLLOWED_BY_NEWLINE + ICODE_EXPRESSION,
                    SOURCE_EXPRESSION
                    );


    /* Debug */
    boolean debug = false;
    String output_html = "";
    
    /* 
     Work Under Progress
     
    AMDHTMLObject converter = new AMDHTMLObject();
    
    */
    
    
    /* Constructor */
    ADMarkProcessor(String markdown){
    
        /* Print Expression */
        if(debug){
            System.out.println(EXP);
        }

        ProcessSyntax(escapeHtml(markdown));

    }
    
    
    /* 
    
    Call Specific Method According to markdown syntax type
    
    */
    private void ProcessElement(String data){
        
        // System.out.println(String.format("--%s--", data));
        
        /* Heading Expression */
        if (data.matches(HEADINGS_EXPRESSION)){
            output_html += process_heading(data);
           
        /* Code Snippet */
        }else if (data.matches(CODES_EXPRESSION)){
            output_html += process_codes(data);
        
        /* Line Break */
        }else if(data.matches(LINEBREAK_EXPRESSION)){
            output_html += process_linebreak(data);
            
            
        /* Horizontal Line */  
        }else if(data.matches(HORI_LINE_EXPRESSION)){
            output_html += process_horizontalline(data);
        
        /* Ordered List Processing */
        }else if(data.matches(OL_LIST_EXPRESSION)){
            output_html += process_orderlist(data);
        
        /* Unordered List */
        }else if(data.matches(UL_LIST_EXPRESSION)){
            output_html += process_unorderlist(data);
        
        /* Table processor */
        }else if(data.matches(TABLE_EXPRESSION)){
            output_html += process_tablecontent(data);
        
        /* Blockquote processor */
        }else if(data.matches(BLOCKQ_EXPRESSION)){
            output_html += process_blockquote(data);
            
        }else if(data.matches(ICODE_EXPRESSION)){
            output_html += process_icode(data);
            
        }else if(data.matches(SOURCE_EXPRESSION)){
            output_html += process_htmlcodes(data);
        }else {
            output_html += String.format("\n<!-- %s -->\n", data);
        }
    }
    
    /* Split Patterns */
    private void ProcessSyntax(String data){
        
        /* compile pattern */
        Pattern pattern = Pattern.compile(EXP);
        
        /* match patterns */
        Matcher match = pattern.matcher(data);
        
        /* Extract Data */
        while(match.find()){
            ProcessElement(data.substring(match.start(), match.end()));
        }
    }
    
    
    /* 
    =========================================================
           MarkDown Syntax Processing Methods
    =========================================================
    
    */
    
    private String process_heading(String data){
        if(debug){
            System.out.println(String.format("<h2> %s </h2>", data));
        }
        return String.format("<h2> %s </h2>", data);
    }
    
    
    private String process_linebreak(String data){
        if(debug){
            System.out.println(String.format("<br /> %s", data));
        }
        return String.format("<br /> \n");
        
    }
    private String process_horizontalline(String data){
        if(debug){
            System.out.println(String.format("<hr /> %s", data));
        }
        
        return String.format("\n<hr /> \n");
    }
    private String process_codes(String data){
        if(debug){
            System.out.println(String.format("<code> %s </code>", data));
        }
        return String.format("\n<code> %s </code>\n", data);
    }
    
    private String process_orderlist(String data){
        if(debug){
            System.out.println(String.format("<ol> %s </ol>", data));
        }
        return String.format("\n<ol> %s </ol>\n", data);
    }
    
    private String process_unorderlist(String data){
    if(debug){
            System.out.println(String.format("<ul> %s </ul>", data));
        }
        return String.format("\n<ul> %s </ul>\n", data);
    }
    
    private String process_tablecontent(String data){
    if(debug){
        System.out.println(String.format("<data> %s </data>", data));
        }
        
        return String.format("\n<table> %s </table>\n", data);
    }
    
    private String process_blockquote(String data){
    if(debug){
        System.out.println(String.format("<blockquote> %s </blockquote>", data));
        
        }
        
        return String.format("\n<blockquote> %s </blockquote>\n", data);
        
    }
    
    private String process_icode(String data){
    if(debug){
        System.out.println(String.format("<code> %s </code>", data));
        }
        
        return String.format("\n<code> %s </code>\n", data);
    }
    
    private String process_htmlcodes(String unsafe){
        unsafe = unsafe.replaceAll("&amp;", "&");
        unsafe = unsafe.replaceAll("&lt;", "<");
        unsafe = unsafe.replaceAll("&gt;", ">");
        unsafe = unsafe.replaceAll("&quot;", "\"");
        unsafe = unsafe.replaceAll("&#039;", "\'");
        
    if(debug){
        
        System.out.println(String.format("%s", unsafe));
        }
        
         return unsafe;
    }
    
    
    /* Escape HTML */
    private String escapeHtml(String unsafe) {
        unsafe = unsafe.replaceAll("&", "&amp;");
        unsafe = unsafe.replaceAll("<", "&lt;");
        unsafe = unsafe.replaceAll(">", "&gt;");
        unsafe = unsafe.replaceAll("\"", "&quot;");
        unsafe = unsafe.replaceAll("\'", "&#039;");
        unsafe = unsafe.replaceAll("\t", "    ");
        return unsafe;
    }
    public String getHTML(){
        return output_html;
    }
}


















public class ADMark{

/* Global Variable Configuration */
	private static final String input_file = "./tmp/input_markdown.md";  // Input File Path
	private static final String output_file = "./tmp/output_html.html";  // Output File Path
	private String input_data; // Input Markdown Text
	private String output_data; // Output HTML Source Code

	/* Constructor */
	public ADMark(){

		// Get Input
		input_data = ReadFileData(input_file);
		ADMarkProcessor obj = new ADMarkProcessor(input_data);
		output_data = obj.getHTML(); 
		// Write Output
		WriteFileData(output_file, output_data);
	}
	/* Write Data To File */
	private void WriteFileData(String path, String content){
		try{
			FileWriter writer = new FileWriter(path);
			writer.write(content);
			writer.flush();
			writer.close();

		}catch(Exception error){
			System.out.println(error);
			error.printStackTrace();
		}
	}

	/* Read Data from File */
	private String ReadFileData(String path){
		String content=""; // Content
		String line;
		try{
			BufferedReader reader = new BufferedReader(new FileReader(path));
			while(true){
				line=reader.readLine();
				if (line==null) {
					break;
				}
				content = String.format("%s%s\n", content, line);
			}

		}catch(Exception error){
			System.out.println(error);
			error.printStackTrace();
		}
		return content;
	}



	/* Main Trigger Function */
	public static void main(String[] args){
		ADMark obj = new ADMark();
	}

}