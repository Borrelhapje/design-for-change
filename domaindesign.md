<[[_TOC_]]

# Design of the domain

Make a design for the entities in the domain of slide shows.

# Responsibilities

## Slide show

| Type | Responsibility | Comments | Remarks |
|------|----------------|----------|---------|
| Know | its slides |  |  |
|  | its meta information |  |  |
| Can  | supply slide iterator                  |  |  |

## Slide Iterator

| Type | Responsibility          | Comments | Remarks |
|------|-------------------------|----------|---------|
| Know | slides                  |  |  |
|      | current slide           | | |
| can  | navigate through slides |  |  |


## Slide

| Type | Responsibility | Comments | Remarks |
|------|----------------|----------|---------|
| Know | its content |  |  |
|  | its meta information | title, slide number |  |
| Can |  |  |  |

## Content

| Type | Responsibility | Comments | Remarks |
|------|----------------|----------|---------|
| Know | information to be shown | for text and image |  |
|  | child content items | for table and list |  |
| Can | supply content iterator | |  |

## SlideShow Meta
| Type | Responsibility | Comments |
|------|----------------|----------|
| Know | title | |
|      | subtitle | |
|      | presenter names | |
|      | date | |
| Can |  | |

## Slide Meta
| Type | Responsibility | Comments |
|------|----------------|----------|
| Know | title | |
|      | slide number in sequence | |
| Can |  | |

# Design

## Slide show and slide
- I doubt we will need anything more complex than a Slide, as Content should be able to handle any table of contents, title slide or other special slides 

```mermaid
classDiagram
    class SlideShow{
        -slides : Vector of Slide
        -meta : SlideShowMeta
        +getSlideIterator(): Iterator of Slide
        +getMeta()
    }
class Slide{
	-content : Content
        -meta: SlideMeta
        +getMeta(): SlideMeta
        +getContent(): Content
}
class Content{
  <<abstract>>
}

SlideShow "1" <-- "*" Slide: slides
Slide "1" <-- "1" Content: content
```

## Content

- Design pattern **Composite** for content and its subtypes.
- **Iterator** to navigate through these
- It is a possibility to include Slide and even SlideShow into the Composite hierarchy. We decided against that, because we see too much difference between Slide/SlideShow and the other Components and too little shared functionality.

### Mapping for Composite

| Class | Role |
|-------|------|
| Content | Component |
| List | Composite |
| Table | Composite |
| Figure | Leaf |
| Text | Leaf |

```mermaid
classDiagram
class Content{
	<<abstract>>
	+getComposite(): CompositeContent|null
}
class CompositeContent{
	-elements : Vector of Content
    +getIterator():Iterator of Content
}
class List{
    -bulleted: boolean
}
class Table{
    -rowCount: int
    -columnCount: int
}
class Figure{
    -source: URL
}
class Text{
    -text: String
}
Content <|-- Figure
Content <|-- Text
Content <|-- CompositeContent
CompositeContent <|-- List
CompositeContent <|-- Table
```

## Iterator
```mermaid
classDiagram
class Iterator{
  <<abstract>>
  +hasNext():boolean
  +next():boolean
  +hasPrevious(): boolean
  +previous():boolean
  +current(): T|null
}
class ContentIterator{
  <<abstract>>
  +hasNext():boolean
  +next():boolean
  +hasPrevious(): boolean
  +previous():boolean
  +current(): Content|null
}
class ListIterator {
  +hasNext():boolean
  +next():boolean
  +hasPrevious(): boolean
  +previous():boolean
  +current(): Content|null
}
class TableIterator {
  +hasNext():boolean
  +next():boolean
  +hasPrevious(): boolean
  +previous():boolean
  +current(): Content|null
}
class SlideIterator {
  +hasNext():boolean
  +next():boolean
  +hasPrevious(): boolean
  +previous():boolean
  +current(): Slide|null
}

    Iterator <|-- SlideIterator
    Iterator <|-- ContentIterator
    ContentIterator <|-- ListIterator
    ContentIterator <|-- TableIterator
```
