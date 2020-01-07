#
# =================================================================================
#                                  README Section
# =================================================================================
#
# Author:
#        Suraj Singh Bisht
#        Surajsinghbisht054@gmail.com 
#        www.bitforestinfo.com
#        www.blaregroup.com
#
#


# help menu
function helpmenu(){
	echo "+                                                                      +"
	echo "           [ Hello, This is User manual of this script  ]     "
	echo "+                                                                      +"
	echo ""
	echo "                                                           tested in Ubuntu 18 Only"
	echo ""
	echo "[+]  To Simply Test ADMark Internal Working Modules, And Check" 
	echo "                   input_markdown.md HTML Markdown Conversion"
	echo "			         into output_html.html file. Then Simple Type"
	echo ""
	echo "                $0 test"
	echo ""
	echo "[+] Or If You Want To Run Live Preview Editor Then.."
	echo ""
	echo "               $0 prev"
	echo ""
	echo""
	echo "[+] Or If You Want to Install All Required Dependencies.. Automatically [ Only For Ubuntu ]"
	echo ""
	echo "               $0 setup"
	echo ""

}

function testrun(){
	echo "[+] Test Run Starting...."

	# Compile All Script
	javac ADMark/*.java


	# Execute
	java ADMark.ADMark

	# show
	firefox output_html.html 
}


function prevrun(){
	echo "[+] Preview Run Starting.... "

	# Path To Openjfx libx
	export PATH_TO_FX=/usr/share/openjfx/lib
	
	# Compile All Script
	javac ADMark/*.java
	
	# compile newwebview with openjfx
	javac --module-path $PATH_TO_FX --add-modules=javafx.swing,javafx.web execute.java

	
	#javac execute.java

	
	
	# To Run
	# java --module-path $PATH_TO_FX --add-modules=javafx.swing,javafx.web newwebview
	#java execute
	java --module-path $PATH_TO_FX --add-modules=javafx.swing,javafx.web execute
		
}

function dep_setup(){
	echo "[+] Installing All Dependencies..."
	echo "[+] Required Sudo Permission.. ?"
	sudo apt-get update
	echo "[-] Repo Updated.. Now Installing Libs"
	sudo apt-get install openjdk-11-*
	sudo apt-get install openjfx
	java --version
	
}

# Check if any command is passed
if [ $# == 0 ]
then
	helpmenu
elif [ $1 == "test" ]
then
	testrun

elif [ $1 == "prev" ]
then
	prevrun

elif [ $1 == "setup" ]
then
	dep_setup
	
else
	helpmenu
fi


