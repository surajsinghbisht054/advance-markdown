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
import java.util.*;
import java.util.Collections;


/*

    Tag Conversion HTML Processing Class Implementation CheckList

BaseClass ------------------------ Done
HeadingProcessor ----------------- Done
HorizontalLineProcessor ---------  Done
CodeProcessor -------------------- Done
ListProcessor -------------------- Done
BlockQuoteProcessor -------------- Done


*/




/* 
    Abstract Class To Handle Common Functionality To Convert Markdown into HTML tags 
    
*/

abstract class HTMLObject{
    
    protected String tag;         // To Store HTML tag variable
    protected String property[];  // To Store CSS and Other HTML Properties
    protected String content[];   // To Store Content
    protected String html;        // Output HTML Tag Variable
    protected String markdown;    // Input Markdown 
    
    public boolean debug = true;   // Debug Trigger
    
    
    /* Reset */
    public void reset(){
        tag = null;
        property = null;
        content = null;
        html = null;
        markdown = null;
    }
    
    
    /* Markdown */
    public void load_markdown(String _markdown, String _tag){
        markdown = _markdown;
        tag = _tag;
        if(debug){
            System.out.println(_markdown);
            System.out.println(_tag);
        }
        
    }
    
    /* Load HTML */
    public void load_html(String _html){
        html = _html;
        if(debug){
            System.out.println(_html);
        }
    }
    
    /* Get html */
    public String get_html(){
        parse_markdown();
        return String.format("\n\n%s\n\n", html);
    }
    
    /* get markdown */
    public String get_markdown(){
        return markdown;
    }    
    
    /* get matcher */
    protected Matcher getMatcher(String _exp, String _data){
        return Pattern.compile(_exp).matcher(_data);
    } 
        
    
    /* Group Extractor */
    protected void GroupDebug(String _exp, String _data){
    
        Matcher match = getMatcher(_exp, _data); //Heading Group 
        System.out.println(String.format("Group : %d", match.groupCount()));
        if(match.find()){
            for(int i=0; i<=match.groupCount(); i++){
                    System.out.println(String.format("Group %d %s",i, match.group(i)));
            }
        }
    }
    
    /* property parser */
    protected String[] property_parser(String data){        
        
        List<String> property = new ArrayList<String>();
        List<String> property_class = new ArrayList<String>();
        String property_id="";
        
        String[] args;
        
        if(debug){
            System.out.println(String.format("[Property Parser ] %s ", data));
        }
        
        if(data==null){
            
            args = new String[]{""};
        
        }else{
            data = data.replaceAll("&quot;", "\"");
            data = data.replaceAll("\\{|\\}", "");
            for(String line: data.split("( (?=(#|\\.))| (?=(\\S+\\=\".+\")))")){
                if(line.length()>0){
                    
                 
                if(line.charAt(0)=='#'){
                    // ID
                    property_id = line.replace("#", "");
                    
                }else if(line.charAt(0)=='.'){
                    line = line.replace(".", "");
                    property_class.add(line);
                
                }else{
                    
                    property.add(line);
                }   
                    
                }
            }
            property.add(String.format("id=\"%s\"", property_id));
            property.add(String.format("class=\"%s\"", String.join(" ", property_class)));
            Collections.reverse(property);
            args = property.toArray(new String[property.size()]);
            
        }
        return args;
        
    }
    
    /* to convert markdown to object */
    protected abstract void parse_markdown();
}








// Heading Processor 
class HeadingProcessor 
    
    extends HTMLObject{
    /*
    private String tag;
    private String property[];
    private String content[];
    private String html;
    private String markdown;
    */
    HeadingProcessor(){
        debug = false; 
    }
    public void parse_markdown(){
        // GroupDebug("(#+) (.+?)(\\{.+\\})?\n", markdown);
        Matcher match = getMatcher("(#+) (.+?)(\\{.+\\})?\n", markdown+"\n");
        
        if(match.find()){
            
            // Tag Parser
            tag = String.format(tag, match.group(1).length());
            
            // Content Parser
            content = new String[]{match.group(2)};
            
            // Property Parser 
            property = property_parser(match.group(3));
            
            // Html Generator
            html = String.format("<%s %s> %s </%s>", 
                                 tag,
                                 String.join(" ", property),
                                 String.join(" ", content),
                                 tag);
        }   
    }
}







/* Horizontal Line */

class HorizontalLineProcessor
    extends HTMLObject{
    /*
    private String tag;
    private String property[];
    private String content[];
    private String html;
    private String markdown;
    */
    
    HorizontalLineProcessor(){
        debug = false; 
    }
    
    public void parse_markdown(){
        // GroupDebug("(.*?)(\\{.+\\})?\n", markdown);
        
        Matcher match = getMatcher("(.*?)(\\{.+\\})?\n", markdown);
        
        if(match.find()){
            
            // Property Parser 
            property = property_parser(match.group(2));
            
            // Html Generator
            html = String.format("<%s %s />", 
                                 tag,
                                 String.join(" ", property)
                                 );
        } 
        
    }
}
    

    
/* Code Snippet Processor */

class CodeProcessor
    extends HTMLObject{
    /*
    private String tag;
    private String property[];
    private String content[];
    private String html;
    private String markdown;
    */
    
    CodeProcessor(){
        debug = false; 
    }
    
    public void parse_markdown(){
        //GroupDebug("(```)(.*?)\n([\\s\\S]+)(```)", markdown);
        
        Matcher match = getMatcher("(```)(.*?)\n([\\s\\S]+)(```)", markdown);
        
        if(match.find()){
            
            // Property Parser 
            property = property_parser(match.group(2));
            
            // Content Parser
            content = new String[]{match.group(3)};
            
            // Html Generator
            html = String.format("<%s %s> %s </%s>", 
                                 tag,
                                 String.join(" ", property),
                                 String.join(" ",content),
                                 tag
                                 );
        } 
        
    }
}
    

/* List Processor */

class ListProcessor
    extends HTMLObject{
    /*
    private String tag;
    private String property[];
    private String content[];
    private String html;
    private String markdown;
    */
    
    ListProcessor(){
        debug = false; 
    }
    
    public void parse_markdown(){
        int new_=0;
        int last_=0;
        int tab_=0;
        
        List<String> list_ = new ArrayList<String>(); // To Collect HTML Tags Set 
        List<Boolean> unorderlist = new ArrayList<Boolean>(); // List Type
        List<String> propertylist = new ArrayList<String>(); // Properties
        List<String> contentlist = new ArrayList<String>(); // Content List
        List<Integer> nodelist = new ArrayList<Integer>(); // Node Level
        Deque<String> tags = new ArrayDeque<String>();   // To Close ALL Opened Tags In Correct Order
        
        Matcher match;
        /*
        Parse All List Syntax and Store it According To Planned Structure.
        */
        for(String x: markdown.split("\n")){
            x+= "\n";
            match = getMatcher("(\\-|\\+|\\*|\\d)(.*?)(\\{.+\\})?\n", x);
            if(match.find()){
            
                if(match.group(1).matches("\\d")){
                    // System.out.println("OrderList");
                    unorderlist.add(false);
                }else{
                    // System.out.println("UnOrderList");
                    unorderlist.add(true);
                }
                nodelist.add(x.split("\\+|\\-|\\*|\\d")[0].length()/4);
                contentlist.add(match.group(2));
                propertylist.add(String.join(" ", property_parser(match.group(3))));
            }
        }
        
        /*
        Generate Tag
        */
        for(int i=0; i<contentlist.size(); i++){
            if(debug){
            
            System.out.println(String.format("[+] %dx %s - %s", 
                nodelist.get(i), 
                contentlist.get(i), 
                propertylist.get(i))
                );
        
            }

            tab_ = nodelist.get(i); 
            new_ = tab_;

            if( new_ < last_ ){

                // System.out.print("Parent | ");
                list_.add(tags.pop());
                list_.add(String.join("", Collections.nCopies(last_ - new_, "</li>\n")));
                list_.add(String.format("<li %s > %s ", propertylist.get(i) ,contentlist.get(i)));
                list_.add("</li>\n");

            }else if( new_ > last_){


                //System.out.print("Child  | ");
                list_.remove(list_.size()-1);
                
                if(unorderlist.get(i)==true){
                    list_.add(String.format("<ul %s >\n", propertylist.get(i)));
                    tags.push("</ul>\n"); // Keep Record of Pushed Tag
                
                }else{
                    list_.add(String.format("<ol %s >\n", propertylist.get(i)));
                    tags.push("</ol>\n");
                
                }
                
                list_.add(String.format("<li> %s ", contentlist.get(i)));
                list_.add("</li>\n");


            }else{
                // System.out.print("Node   | ");
                list_.add(String.format("<li %s> %s ",propertylist.get(i), contentlist.get(i)));
                list_.add("</li>\n");
            }
            //System.out.println(String.format("Tabs : %d | String : %s ",tab_,tmp[i]));
            last_ = new_;
        }
         
        /* Close if Any Tag is Open */
        while(!tags.isEmpty()){
            list_.add(tags.pop());
            
        }
        if(unorderlist.get(0)==true){
            html = String.format("<ul>\n %s \n</ul>\n", String.join("\n", list_));
        }else{
            html = String.format("<ol>\n %s \n</ol>\n", String.join("\n", list_));
        }
        
        
    }
 
}




/* BlockQuote Processor */

class BlockQuoteProcessor
    extends HTMLObject{
    /*
    private String tag;
    private String property[];
    private String content[];
    private String html;
    private String markdown;
    */
    
    BlockQuoteProcessor(){
        debug = false; 
    }
    
    public void parse_markdown(){
        
        
        /* Custom Debug Work */
        // System.out.println(String.format(">%s<",markdown));
        markdown = markdown.replaceAll("&gt;", ">");
        
        int new_=1;
        int last_=1;
        int tab_=1;
        
        List<String> list_ = new ArrayList<String>();
        List<String> propertylist = new ArrayList<String>(); // Properties
        List<String> contentlist = new ArrayList<String>(); // Content List
        List<Integer> nodelist = new ArrayList<Integer>(); // Node Level
        Deque<String> tags = new ArrayDeque<String>();
        
        Matcher match;
        // GroupDebug("(\\>+)(.*?)(\\{.+\\})?\n", markdown.split("\n")[2]+"\n");
        
        for(String x: markdown.split("\n")){
            x+= "\n";
            match = getMatcher("(>+)(.*?)(\\{.+\\})?\n", x);
            if(match.find()){
            
                nodelist.add(match.group(1).length());
                contentlist.add(match.group(2));
                propertylist.add(String.join(" ", property_parser(match.group(3))));
            }
        }
        
        
        for(int i=0; i<contentlist.size(); i++){
            if(debug){
            
            System.out.println(String.format("[+] %dx %s - %s", 
                nodelist.get(i), 
                contentlist.get(i), 
                propertylist.get(i)));
        
            }; 
            
            tab_ = nodelist.get(i); 
            new_ = tab_;
            
            // If Decreasing Node
            if( new_ < last_ ){

                // System.out.print("Parent | ");
                //list_.add("</blockquote>");
                //list_.add(String.join("", Collections.nCopies(last_ - new_, "</p>\n")));
                //list_.add(String.join("", Collections.nCopies(last_ - new_, "</blockquote>\n")));
                
                for(int k=0; k<last_-new_; k++){
                    list_.add(tags.pop());
                }
                list_.add(String.format("<p %s > %s </p>\n", propertylist.get(i) ,contentlist.get(i)));
                //list_.add("</p>\n");

            // Increasing Node
            }else if( new_ > last_){


                //System.out.print("Child  | ");
                //list_.remove(list_.size()-1);
                list_.add(String.join("", Collections.nCopies(new_-last_, String.format("<blockquote %s >\n", propertylist.get(i)))));                
                list_.add(String.format("<p> %s </p>\n", contentlist.get(i)));
                tags.push("</blockquote>");
                
            // Constent Node
            }else{
                // System.out.print("Node   | ");
                list_.add(String.format("<p %s> %s </p>",propertylist.get(i), contentlist.get(i)));
                //list_.add("</p>\n");
            }
            //System.out.println(String.format("Tabs : %d | String : %s ",tab_,tmp[i]));
            last_ = new_;
            
        }
        while(!tags.isEmpty()){
            list_.add(tags.pop());
            
        }
        html = String.format("<blockquote> %s </blockquote>\n", String.join("\n", list_));
        
    }
 
}


class TableProcessor 
  extends HTMLObject{
    
    /*
    private String tag;
    private String property[];
    private String content[];
    private String html;
    private String markdown;
    */
    
    // to stroe all generated html output tags
    List<String> htmltags = new ArrayList<String>();
    
    // To Store Heading titles
    List<String> heading = new ArrayList<String>();
    
    // To store row lines
    List<String> row = new ArrayList<String>();


    TableProcessor(){
        debug=false;
        
    }

    public void parse_markdown(){
        
        Matcher match = getMatcher("(.+)(\n[-:\\| ]{3,})([\\w\\W]+)",markdown);
        
        if(match.find()){
        
            // Table Heading Portion
            for(String r:match.group(1).split("\\|")){
                heading.add(r);
            }
            
            // Table All Row Portion
            for(String r:match.group(3).split("\n"))
                row.add(r);
            
        }
        
        /*
            Table Heading Processor
        */
        
        htmltags.add("<tr>");
        
        for(String r:heading){
        
            // Jump Blank 
            if(r.equals("")){
            continue;
            }
            
            // Add Table Heading tags
            htmltags.add(String.format("<th> %s </th>", r));
        }
        htmltags.add("</tr>");
        
        
        /*
            Table Row Processor
        */
        
        for(String r:row){
            
            // Jump blank rows
            if(r.equals("")){
                continue;
            }
            
            htmltags.add("<tr>");
            
            for(String dcell: r.split("\\|")){
            
            // Jump blank data cells
            if(dcell.equals("")){
                continue;
            }
            htmltags.add(String.format("<td> %s </td>", dcell)); 
            
            }

                       
            htmltags.add("</tr>");
        }
        
        html = String.format("<table> %s </table>", String.join("\n", htmltags));
        
        /*
    
        for(String ch:heading){
            System.out.println(ch);
        }
        for(String ch:row){
            System.out.println(ch);
        }
    */
    }
}

/* Still UnderProcess */

class ADMarkProcessor{

    /* Expressions */
    String FOLLOWED_BY_NEWLINE  = "(?<=\n)";
    String COMMENTS_EXPRESSION  = "(/\\*[\\s\\S]+?\\*/)"; // Comment
    String HEADINGS_EXPRESSION  = "(#+ [^\n]+)";         // Heading
    
    
    String LINEBREAK_EXPRESSION = "(-{2,}.*\n)";                   // Line Break
    String HORI_LINE_EXPRESSION = "(={2,}.*\n)";                  // Horizontal Line
    
    String UL_LIST_EXPRESSION = "((\\s*[\\-\\+\\*\\d]+ .+\n)+)";    // Unordered List
    String OL_LIST_EXPRESSION = "((\\s*\\d+\\. .+\n)+)";        // Ordered List
    
    String CODES_EXPRESSION = "(```[\\s\\S]+?```)";               // Code Snippet
    String TABLE_EXPRESSION = "(.+)\n([-:\\| ]{3,})(\n.+)+";   // Table
    String BLOCKQ_EXPRESSION = "(((&gt;)+ .+\n)+)";         // Blockquote
    String ICODE_EXPRESSION = "(( {4}.+\n)+)";                  // Internal Codes
    String SOURCE_EXPRESSION = "\n(\\.{5,})([\\s\\S]+?)(\\.{5,})";   // External HTML Codes Added
    String PARAGRAPH_EXPRESSION = "(.+)";
    
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
                    SOURCE_EXPRESSION,
                    PARAGRAPH_EXPRESSION
                    );


    /* Debug */
    boolean debug = false;
    String output_html = "";
    
    /* 
     Still Work Under Progress
    */ 
    HTMLObject converter;
    
    
    
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
            data = data.replaceAll("\n\\.\\.\\.\\.\\.", "");
            output_html += String.format("\n<!-- [ HTML CODE EMBED SECTION ] (START) -->\n %s \n<!--[ HTML CODE EMBED SECTION ] (END) -->\n", process_htmlcodes(data));
        
        }else if(data.matches(COMMENTS_EXPRESSION)){
            output_html += String.format("<!-- %s -->", data);
            
        }else {
            output_html += String.format("<p> %s </p>\n", data);
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
        // Heading
        converter = new HeadingProcessor();
        converter.load_markdown(data, "h%d");
        return converter.get_html();
    }
    
    
    private String process_linebreak(String data){
        if(debug){
            System.out.println(String.format("<br /> %s", data));
        }
        // Line Break
        converter = new HorizontalLineProcessor();
        converter.load_markdown(data, "br");
        return converter.get_html();
    }
    private String process_horizontalline(String data){
        if(debug){
            System.out.println(String.format("<hr /> %s", data));
        }
        // Horizontal Line
        converter = new HorizontalLineProcessor();
        converter.load_markdown(data, "hr");
        return converter.get_html();
     
    }
    private String process_codes(String data){
        if(debug){
            System.out.println(String.format("<code> %s </code>", data));
        }
        // Code
		converter = new CodeProcessor();
        converter.load_markdown(data, "pre");
        return converter.get_html();
    }
    
    private String process_orderlist(String data){
        if(debug){
            System.out.println(String.format("<ol> %s </ol>", data));
        }
        // List
        converter = new ListProcessor();
        converter.load_markdown(data, "ol");
        return converter.get_html();
    }
    
    private String process_unorderlist(String data){
    if(debug){
            System.out.println(String.format("<ul> %s </ul>", data));
        }
        // List
        converter = new ListProcessor();
        converter.load_markdown(data, "ul");
        return converter.get_html();
    }
    
    private String process_tablecontent(String data){
    if(debug){
        System.out.println(String.format("<data> %s </data>", data));
        }
        // Table Processor
        converter = new TableProcessor();
        converter.load_markdown(data, "table");
        return converter.get_html();
        //return String.format("\n<table> %s </table>\n", data);
    }
    
    private String process_blockquote(String data){
    if(debug){
        System.out.println(String.format("<blockquote> %s </blockquote>", data));
        
        }
        // Blockquote
        converter = new BlockQuoteProcessor();
        converter.load_markdown(data, "blockquote");
        return converter.get_html();
        //return String.format("\n<blockquote> %s </blockquote>\n", data);
        
    }
    
    private String process_icode(String data){
    if(debug){
        System.out.println(String.format("<pre> %s </pre>", data));
        }
        
        // Converting ICode to Code Syntax
        data = String.format("```\n%s```", data);
        data = data.replaceAll("\n    ", "\n");
        
        // Code
       	converter = new CodeProcessor();
        converter.load_markdown(data, "pre");
        return converter.get_html();
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
	private static final String input_file = "./input_markdown.md";  // Input File Path
	private static final String output_file = "./output_html.html";  // Output File Path
	public String input_data; // Input Markdown Text
	public String output_data; // Output HTML Source Code

	/* Constructor */
	public ADMark(){

		// Get Input
		input_data = ReadFileData(input_file);
		ADMarkProcessor obj = new ADMarkProcessor(input_data);
		output_data = obj.getHTML(); 
		// Write Output
		WriteFileData(output_file, output_data);
	}

	public String ADMark(String markdownContent){
		input_data = markdownContent;
		ADMarkProcessor obj = new ADMarkProcessor(input_data);
		output_data = obj.getHTML(); 

		// Write Output
		WriteFileData(output_file, output_data);
		return output_data;
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