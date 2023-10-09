<[[_TOC_]]

# Design of the domain services and infra

Make a design for domain services for the of slide show domain.

## Domain services

### SlideShowFactory

| Type | Responsibility     | Comments | Remarks |
|------|--------------------|----------|---------|
| Know | -                  |          |         |
| Can  | create a SlideShow |          |         |

### SlideFactory

| Type | Responsibility | Comments | Remarks |
|------|----------------|----------|---------|
| Know | -              |          |         |
| Can  | create a Slide |          |         |

### ContentFactory

| Type | Responsibility                                          | Comments | Remarks |
|------|---------------------------------------------------------|----------|---------|
| Know | -                                                       |          |         |
| Can  | create a Content (one method per Content implementation |          |         |

## Domain infra

### SlideReader

| Type | Responsibility                                                            | Comments | Remarks |
|------|---------------------------------------------------------------------------|----------|---------|
| Know | -                                                                         |          |         |
| Can  | Read an InputStream, parse it and create a SlideShow based on its content |          |         |

### JsonSlideReader

~~~mermaid
classDiagram
    class SlideReader {
        <<abstract>>
        +read(InputStream): SlideShow
    }

    class JsonSlideReader {
        +read(InputStream): SlideShow
    }

    class SlideShowFactory {
        <<abstract>>
        +createSlideShow(String title, String presenter, Date presentationDate): SlideShow
    }

    class SimpleSlideShowFactory {
        +createSlideShow(String title, String presenter, Date presentationDate): SlideShow
    }

    class SlideFactory {
        <<abstract>>
        +createSlide(): Slide
    }

    class SimpleSlideFactory {
        +createSlide(): Slide
    }

    class SimpleContentFactory {
        +createText(String): Text
        +createFigure(URI): Figure
        +createTable(int, int, Content[]): Table
        +createList(Content[], boolean)
    }

    SlideReader *.. SlideShowFactory
    SlideReader *.. SlideFactory
    SlideReader *.. ContentFactory
    SlideReader <|-- JsonSlideReader
    SlideShowFactory <|-- SimpleSlideShowFactory
    SlideFactory <|-- SimpleSlideFactory
    ContentFactory <|-- SimpleContentFactory
~~~
