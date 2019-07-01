# Advance Markdown Implementation
Advance Markdown flavor with many extra features. I am going to add features like
	- Git MarkDown Syntax flavor
	- Pandoc Syntax Flavor 
	- Bootstrap Framework support
	- Many useful extra features



## Syntax Usages Example
---

Comments Supported
---
```
/*
You Can Use Comments Easily
*/
```

Heading
---
```
# H1
## H2
### H3
#### H4
##### H5
###### H6
```

Line Break
---
You can simply break line using `--`. use only `--` in new line. nothing else. number of `-` needs to be more than one.
```
--
-- {#this_for_id}
--- {#this_for_id .this_for_class}
```

Horizontal Line
---
use `==` to add Horizontal Line. use only `==` in new line. nothing else. number of `=` needs to be more than one
```
==
== {#this_for_id}
=== {#this_for_id .this_for_class}

```

Ordered List
---
```
Ordered

1. Lorem ipsum dolor sit amet
    2. Consectetur adipiscing elit
    3. Integer molestie lorem at massa


1. You can use sequential numbers...
1. ...or keep all the numbers as `1.`

```

Un Ordered List
---
```

+ Create a list by starting a line with `+`, `-`, or `*`
+ Sub-lists are made by indenting 2 spaces:
- Marker character change forces new list start:
 * Ac tristique libero volutpat at
 + Facilisis in pretium nisl aliquet
 - Nulla volutpat aliquam velit
+ Very easy!

```

Table
---
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

Codes
---

	```  <- Use before Starting
	
	Your Code Here
	
	``` <- Use After End

	And For Indent
	` Type here`


External HTML Codes
---
```
.....
<div>
Here, What you want to add
</div>

.....
```


BlockQuote
---
```
> Something here
> Or Here
```


User, can also add extra properties to HTML element in MarkDown. For Example
---
`## This Is Heading {Property elements for this heading} ` well, with the help of `{ .. }` user can easily add extra Html property elements into HTML object.

#### Property Usages Example
- `#this_for_id` Use this to add ID Element.
- `.this_for_class` Use this to add Class Elements. 
- `size="20px" style="font-family: Arial, bold;" ` Easily, Can Add HTML Element Directly.

#### Full Working Example
- `### this is a heading {#exampleid .heading .working}` Heading Property.
- `- {#exampleid .heading .working}`
- `= {#exampleid .heading .working}`
- OrderList Example
	1. this is Order List {#exampleid .heading .working}
	2. something 
	3. something
		1. Something {#exampleid}
		2. Something
		

---
## Still Under Progress.

Features Going To Add
- Link, Image, Video Syntax
- Italic, Bold, Stike, Indent Code.
- Other Markdown Syntax Support
- Diagram Support
- Syntax Highlighting Support
- BootStrap Support
- Much More

---
### Author
	- Suraj Singh Bisht 
