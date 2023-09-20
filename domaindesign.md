<[[_TOC_]]

# Design of the domain

Make a design for the entities in the domain of slide shows.

# Responsibilities

## Slide show

| Type | Responsibility | Comments | Remarks |
|------|----------------|----------|---------|
| Know | its slides |  |  |
|  | its meta information |  |  |
|  | stop |  |  |
|  | show next slide |  |  |
|  | show previous slide |  |  |

## Slide

| Type | Responsibility | Comments | Remarks |
|------|----------------|----------|---------|
| Know | its content |  |  |
|  | its meta information | title, slide number |  |
| Can | render its content |  |  |

## Content

| Type | Responsibility | Comments | Remarks |
|------|----------------|----------|---------|
| Know | information to be shown | for text and image |  |
|  | child content items | for table and list |  |
| Can | render its content | |  |

## SlideShow Meta
| Type | Responsibility | Comments |
|------|----------------|----------|
| Know | title | |
|      | subtitle | |
|      | presenter names | |
|      | date | |
| Can | be shown | |

## Slide Meta
| Type | Responsibility | Comments |
|------|----------------|----------|
| Know | title | |
|      | slide number in sequence | |
| Can | be shown | |

# Design

## Slide show

```mermaid
classDiagram
class SlideShow{
	-slides : Vector of Slide
	-meta : SlideShowMeta
        +getSlideIterator(): Iterator of Slide
        +getMeta()
	+start()
	+stop()
}
```
- Design pattern **Iterator** for navigation over the slides?

## Slide
- I doubt we will need anything more complex than a Slide, as Content should be able to handle any table of contents, title slide or other special slides 

```mermaid
classDiagram
class Slide{
	-content : Content
        -meta: SlideMeta
        +getMeta(): SlideMeta
        +getContent(): Content
}
```

## Content

- Design pattern **Composite** for content and its subtypes.
- **Iterator** to navigate through these

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
  +next():boolean
  +previous():boolean
  +current(): T|null
}
class ListIterator {
  +next():boolean
  +previous():boolean
  +current(): Content|null
}
class TableIterator {
  +next():boolean
  +previous():boolean
  +current(): Content|null
}

Iterator <|-- ListIterator
Iterator <|-- TableIterator
```
