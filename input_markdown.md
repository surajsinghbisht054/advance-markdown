
/* 
[ Heading Section ] (START) 
*/


# h1 Heading 
## h2 Heading {#this_is_id .classOne .classTwo font="Arial Size 12px"}
### h3 Heading {#anotherId}
#### h4 Heading {.JustClass }
##### h5 *_Heading-* { }
###### h6 Heading 

## Simple Heading Followed By Horizontal Line
===


### Heading Followed By Line Break
---



/* 

[ Heading Section ] (END) 


[ Line Break Section ](START)

*/

--
--{#this_is_id .classOne .classTwo font="Arial Size 12px"}
--
--- {#this_is_id}


/* 

[ Line Break Section ](END)


[ Horizontal Line Section ](START)

*/
==
={#this_is_id .classOne .classTwo font="Arial Size 12px"}
==
=== {#this_is_id}



/*

[ Horizontal Line Section ](END)


[ Code Section ](START)
*/
Simple 

```
Sample text here...
```

Syntax highlighting

``` #this_is_id .This_is_Class .Another_Class backgroud="black"
var foo = function (bar) {
return bar++;
};

console.log(foo(5));
```



/*

[ Code Section ](END)



[ List Section ] (START) 

*/

+ Create a list by starting a line with `+`, `-`, or `*` {#this_is_for_table .thisIsfortable }
+ Sub-lists are made by indenting 2 spaces:{#this_is_for_row_One}
    * Marker character change forces new list start: {#this_is_Again_For_Table}
    * Ac tristique libero volutpat at {#this_is_for_row_One}
        + Facilisis in pretium nisl aliquet {#this_is_for_table}
        * Ac tristique libero volutpat at {#this_is_for_row_two}
        * Ac tristique libero volutpat at {#this_is_for_row_three}
    * Just checking again\n- Nulla volutpat aliquam velit
    * Ac tristique libero volutpat at {#work }
    * Ac tristique libero volutpat at {#work2 }
+ Very easy!



Simple ordered table

5. foo{#works}
1. bar
1. works

Nested Ordered Table

1. starting line numbers does not matter
    2. because browser assign its own numbers
    3. so you just have to use any digit only to show that its a ordered list

1. You can also set property {#like_this_one}
	1. ...or keep all the numbers as `1.`{#or_this_one}




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




1. Lorem ipsum dolor sit amet
    2. Consectetur adipiscing elit
    3. Integer molestie lorem at massa


1. You can use sequential numbers...
1. ...or keep all the numbers as `1.`

Start numbering with offset:

57. foo
1. bar



## Lists


+ Create a list by starting a line with `+`, `-`, or `*`
+ Sub-lists are made by indenting 2 spaces:
- Marker character change forces new list start:
 * Ac tristique libero volutpat at
 + Facilisis in pretium nisl aliquet
 - Nulla volutpat aliquam velit
+ Very easy!





/*

[ List Section ] (END) 

[ BlockQuote Section ](START)

*/



> This is the first level of quoting.
> is it here
> > This is nested blockquote.
> yes
> Back to the first level.



> This is also level One
>> its second levele {#thisisproperty}
>>> this is third
> again first

> This is also level One{#thisworks .orclass }
>> its second levele {why=yes}
>>> this is third{what=works}
> again first





/*

[ BlockQuote Section ](END)



[ Indent Code Section ](START)

*/

    // Some comments
    line 1 of code
    line 2 of code
    line 3 of code



/*
[ Indent Code Section ](END)


## Tables

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





---
__Advertisement :)__

- __[pica](https://nodeca.github.io/pica/demo/)__ - high quality and fast image
resize in browser.
- __[babelfish](https://github.com/nodeca/babelfish/)__ - developer friendly
i18n with plurals support and easy syntax.

You will like those projects!

---



## Typographic replacements

Enable typographer option to see result.

(c) (C) (r) (R) (tm) (TM) (p) (P) +-

test.. test... test..... test?..... test!....

!!!!!! ???? ,,  -- ---

"Smartypants, double quotes" and 'single quotes'


## Emphasis

**This is bold text**

__This is bold text__

*This is italic text*

_This is italic text_

~~Strikethrough~~



## Code

Inline `code`


## Links

[link text](http://dev.nodeca.com)

[link with title](http://nodeca.github.io/pica/demo/ "title text!")

Autoconverted link https://github.com/nodeca/pica (enable linkify to see)


## Images

![Minion](https://octodex.github.com/images/minion.png)
![Stormtroopocat](https://octodex.github.com/images/stormtroopocat.jpg "The Stormtroopocat")

Like links, Images also have a footnote style syntax

![Alt text][id]

With a reference later in the document defining the URL location:

[id]: https://octodex.github.com/images/dojocat.jpg  "The Dojocat"


## Plugins

The killer feature of `markdown-it` is very effective support of
[syntax plugins](https://www.npmjs.org/browse/keyword/markdown-it-plugin).


### [Emojies](https://github.com/markdown-it/markdown-it-emoji)

> Classic markup: :wink: :crush: :cry: :tear: :laughing: :yum:
>
> Shortcuts (emoticons): :-) :-( 8-) ;)

see [how to change output](https://github.com/markdown-it/markdown-it-emoji#change-output) with twemoji.


### [Subscript](https://github.com/markdown-it/markdown-it-sub) / [Superscript](https://github.com/markdown-it/markdown-it-sup)

- 19^th^
- H~2~O

afsdfsfdaasdf

### [\<ins>](https://github.com/markdown-it/markdown-it-ins)

++Inserted text++


### [\<mark>](https://github.com/markdown-it/markdown-it-mark)

==Marked text==


### [Footnotes](https://github.com/markdown-it/markdown-it-footnote)

Footnote 1 link[^first].

Footnote 2 link[^second].

Inline footnote^[Text of inline footnote] definition.

Duplicated footnote reference[^second].

[^first]: Footnote **can have markup**

 and multiple paragraphs.

[^second]: Footnote text.


### [Definition lists](https://github.com/markdown-it/markdown-it-deflist)

Term 1

:   Definition 1
with lazy continuation.

Term 2 with *inline markup*

:   Definition 2

     { some code, part of Definition 2 }

 Third paragraph of definition 2.

_Compact style:_

Term 1
~ Definition 1

Term 2
~ Definition 2a
~ Definition 2b


### [Abbreviations](https://github.com/markdown-it/markdown-it-abbr)

This is HTML abbreviation example.

It converts "HTML", but keep intact partial entries like "xxxHTMLyyy" and so on.


### [Custom containers](https://github.com/markdown-it/markdown-it-container)
::: warning
*here be dragons*
:::    

*/