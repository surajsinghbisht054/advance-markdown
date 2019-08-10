# Advance Markdown interpreter

**ADMark** is a Advance Markdown Interpreter Written in Java, Design and Developed By [BlareGroup](https://github.com/blaregroup) Members.

This Interpreter Also Support various type of other Markdown Interpreter Flavors Like
- Git MarkDown Syntax flavor
- Pandoc Syntax Flavor 
- ADMark Syntax

And To Produce Beautiful HTML Output This Interpreter Going To Use  

- Bootstrap Framework support
- Many useful extra features


### Currently, This Project is Developed By 
	- Suraj Singh Bisht
    - Himanshu Sharma


### How to test it?

Simply, Clone Repo And Run `./run.sh test`. All Parameter With Description


|Arguemnt | Description |
|----|----|
| setup | To Install All Required Dependencies.. Only Tested In Ubuntu |
| test |To Simply Test ADMark Internal Working Modules, And Check               input_markdown.md HTML Markdown Conversion into output_html.html file|
| prev | Run Live Markdown Preview Editor |



### Github Markdown Flavor Support Example
---

**Heading**
```
# H1
## H2
### H3
#### H4
##### H5
###### H6
```

**Line Break**

You can simply break line using `---`.
```
--- 
```

**Horizontal Line**

use `===` to add Horizontal Line.
```
===
```

**Ordered List**
```
Ordered

1. Lorem ipsum dolor sit amet
    2. Consectetur adipiscing elit
    3. Integer molestie lorem at massa

1. You can use sequential numbers...
1. ...or keep all the numbers as `1.`

```

**Un Ordered List**

```
+ Create a list by starting a line with `+`, `-`, or `*`
+ Sub-lists are made by indenting 2 spaces:
- Marker character change forces new list start:
 * Ac tristique libero volutpat at
 + Facilisis in pretium nisl aliquet
 - Nulla volutpat aliquam velit
+ Very easy!

```

**Table**
```
| Option | Description |
| ------ | ----------- |
| data   | path to data files to supply the data that will be passed into templates. |
| engine | engine to be used for processing templates. Handlebars is the default. |
| ext    | extension to be used for dest files. |

Right aligned columns

| Option | Description |
| ------:| -----------:|
| data   | path to data files to supply the data that will be passed into templates. |
| engine | engine to be used for processing templates. Handlebars is the default. |
| ext    | extension to be used for dest files. |


Markdown | Less | Pretty
--- | --- | ---
*Still* | `renders` | **nicely**
1 | 2 | 3

```

**Codes**

	```  <- Use before Starting
	
	Your Code Will Be Here
	
	``` <- Use After End

	And For Indent
	` Type here`




**BlockQuote**
```
> Something here
>> Yes, This is Blockquote.
> Or Here
```



**And We Are Still Working To Add Complete Support**



## Pandoc Inspired Custom Markdown Flavor Support Example
---

**User, can add extra properties to HTML element in MarkDown. For Example**
`## This Is Heading {Property elements for this heading} ` well, with the help of `{ .. }` user can easily add extra Html property elements into HTML object.

#### Property Usages Example
- `#this_for_id` Use this to add ID Element.
- `.this_for_class` Use this to add Class Elements. 
- `size="20px" style="font-family: Arial, bold;" ` Easily, Can Add Other HTML Element Directly.

#### Usages Approach
- `### this is a heading {#exampleid .heading .working}` Heading Property.
- `-- {#exampleid .heading .working}`
- `== {#exampleid .heading .working}`
- OrderList Example
	1. this is Order List {#exampleid .heading .working}
	2. something 
	3. something
		1. Something {#exampleid}
		2. Something

	
**Comments Supported**
```
/*
You Can Use Comments Easily
*/
```

**Heading**
```
# h1 Heading 
## h2 Heading {#this_is_id .classOne .classTwo font="Arial Size 12px"}
### h3 Heading {#anotherId}
#### h4 Heading {.JustClass }
##### h5 *_Heading-* { }
###### h6 Heading 
```

**Line Break**

You can simply break line using `--`. use only `--` in new line. nothing else. number of `-` needs to be more than one.
```
--
--{#this_is_id .classOne .classTwo font="Arial Size 12px"}
--
--- {#this_is_id}
```

**Horizontal Line**
use `==` to add Horizontal Line. use only `==` in new line. nothing else. number of `=` needs to be more than one
```
==
== {#this_is_id .classOne .classTwo font="Arial Size 12px"}
==
=== {#this_is_id}
```


**Syntax highlighting**

``` #this_is_id .This_is_Class .Another_Class backgroud="black"
var foo = function (bar) {
return bar++;
};

console.log(foo(5));
```


**Nested Order - Unordered List **
```
+ Create a list by starting a line with `+`, `-`, or `*` {#this_is_for_table .thisIsfortable }
+ Sub-lists are made by indenting 2 spaces:{#this_is_for_row_One}
    1 Marker character change forces new list start: {#this_is_Again_For_Table}
    2 Ac tristique libero volutpat at {#this_is_for_row_One}
        + Facilisis in pretium nisl aliquet {#this_is_for_table}
        * Ac tristique libero volutpat at {#this_is_for_row_two}
        * Ac tristique libero volutpat at {#this_is_for_row_three}
    1 Just checking again\n- Nulla volutpat aliquam velit
    1 Ac tristique libero volutpat at {#work }
    1 Ac tristique libero volutpat at {#work2 }
+ Very easy!

```


**Nested BlockQuote**
```
> This is also level One
>> its second levele {#thisisproperty}
>>> this is third
> again first

> This is also level One{#thisworks .orclass }
>> its second levele {why=yes}
>>> this is third{what=works}
> again first
```

**External HTML Codes**

```
.....
<div>
Here, What you want to add
</div>

.....
```





Want To Check Supported Syntax Example [input_markdown.md](https://raw.githubusercontent.com/surajsinghbisht054/advance-markdown/master/input_markdown.md)

### Still Under Progress.

Features Going To Add
- Link, Image, Video Syntax
- Italic, Bold, Stike, Indent Code.
- Other Markdown Syntax Support
- Diagram Support
- Syntax Highlighting Support
- BootStrap Support
- Much More
- Update Readme Documentation

