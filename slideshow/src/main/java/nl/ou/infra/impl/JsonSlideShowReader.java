package nl.ou.infra.impl;

import nl.ou.domain.*;
import nl.ou.domain.impl.SimpleSequence;
import nl.ou.infra.SlideShowReader;
import nl.ou.services.AbstractContentFactory;
import nl.ou.services.AbstractSlideFactory;
import nl.ou.services.AbstractSlideShowFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static nl.ou.infra.impl.SlideShowAttributeConstants.*;

public class JsonSlideShowReader implements SlideShowReader {

    private final AbstractSlideShowFactory slideShowFactory;
    private final AbstractSlideFactory slideFactory;
    private final AbstractContentFactory contentFactory;

    public JsonSlideShowReader(AbstractSlideShowFactory slideShowFactory, AbstractSlideFactory slideFactory, AbstractContentFactory contentFactory) {
        this.slideShowFactory = slideShowFactory;
        this.slideFactory = slideFactory;
        this.contentFactory = contentFactory;
    }

    @Override
    public SlideShow readSlideShow(InputStream inputStream) {
        JSONTokener tokener = new JSONTokener(inputStream);
        Object token = tokener.nextValue();
        if (token instanceof JSONObject) {
            JSONObject slideShowJson = (JSONObject) token;
            return slideShowFromJSON(slideShowJson);
        } else {
            throw new IllegalArgumentException("Expected JSON representing a slide show. Got: " + token.getClass());
        }
    }

    private SlideShow slideShowFromJSON(JSONObject slideShowJson) {
        SlideshowMeta slideshowMeta = getSlideshowMeta(slideShowJson);
        JSONArray jsonSlides = slideShowJson.getJSONArray(SLIDES);
        boolean addTitleSlide = slideShowJson.optBoolean(SHOW_TITLE_SLIDE);
        boolean addTOCSlide = slideShowJson.optBoolean(SHOW_TOC_SLIDE);
        List<Slide> slides = slidesFromJSON(jsonSlides);
        if (addTOCSlide) {
            slides.add(0,
                    slideShowFactory.createTOCSlide(slides.stream().map(Slide::getMeta).collect(Collectors.toList())));
        }
        if (addTitleSlide) {
            slides.add(0, slideShowFactory.createTitleSlide(slideshowMeta));
        }
        List<Sequence> sequences = sequencesFromJSON(slideShowJson.getJSONArray("sequences"), slides);
        return slideShowFactory.createSlideShow(slideshowMeta, slides, sequences);
    }

    private SlideshowMeta getSlideshowMeta(JSONObject slideShowJson) {
        String title = slideShowJson.getString(TITLE);
        String subtitle = slideShowJson.optString(SUBTITLE);
        String presenter = slideShowJson.optString(PRESENTER);
        String dateString = slideShowJson.optString(DATE, null);
        LocalDate date = dateString == null ? null : LocalDate.parse(dateString);
        return slideShowFactory.createSlideShowMeta(title, subtitle, presenter, date);
    }

    private List<Sequence> sequencesFromJSON(JSONArray sequences, List<Slide> slides) {
        List<Sequence> result = new ArrayList<>(sequences.length());
        for (var sequence : sequences) {
            result.add(sequenceFromJSON((JSONObject)sequence, slides));
        }
        return result;
    }

    private Sequence sequenceFromJSON(JSONObject sequence, List<Slide> slides) {
        String key = sequence.getString(KEY);
        String description = sequence.optString(DESCRIPTION);
        JSONArray slideNumbers = sequence.getJSONArray(SLIDES);
        List<Slide> includedSlides = new ArrayList<>(slideNumbers.length());
        for (var number : slideNumbers) {
            includedSlides.add(slides.get(((Integer) number) - 1));
        }
        return new SimpleSequence(description, key, includedSlides);
    }

    private List<Slide> slidesFromJSON(JSONArray jsonSlides) {
        List<Slide> slides = new ArrayList<>();
        int slideNumber = 1;
        for (Object jsonSlide : jsonSlides) {
            slides.add(slideFromJson(jsonSlide, slideNumber++));
        }
        return slides;
    }

    private Slide slideFromJson(Object object, int slideNumber) {
        if (object instanceof JSONObject) {
            JSONObject slideJson = (JSONObject) object;
            String title = slideJson.getString(TITLE);
            SlideMeta meta = slideFactory.createSlideMeta(title, slideNumber);
            Content content = createContent(slideJson.getJSONObject(CONTENT));
            return slideFactory.createSlide(meta, content);
        } else {
            throw new IllegalArgumentException("Expected JSON representing a slide. Got: " + object.getClass() + ". " + object);
        }
    }

    private Content createContent(JSONObject jsonContent) {
        String type = jsonContent.getString(TYPE);
        switch (type) {
            case "text":
                return createText(jsonContent);
            case "list":
                return createList(jsonContent);
            case "figure":
                return createFigure(jsonContent);
            case "table":
                return createTable(jsonContent);
            default:
                throw new UnsupportedOperationException(String.format("Content type %s not supported", type));
        }
    }

    private Content createText(JSONObject jsonText) {
        return contentFactory.createText(jsonText.getString(TEXT));
    }

    private Content createList(JSONObject jsonList) {
        boolean bulleted = jsonList.optBoolean(BULLETED, false);
        JSONArray listItemsJson = jsonList.getJSONArray(ITEMS);
        List<Content> listItems = new ArrayList<>();
        for (Object listItemJson : listItemsJson) {
            if (listItemJson instanceof JSONObject) {
                listItems.add(createContent((JSONObject) listItemJson));
            } else {
                throw new IllegalArgumentException("Expected JSON representing content. Got: " + listItemJson.getClass() + ". " + listItemJson);
            }
        }
        return contentFactory.createList(listItems, bulleted);
    }

    private Content createFigure(JSONObject jsonFigure) {
        String uriString = jsonFigure.getString(URL);
        try {
            URI uri = new URI(uriString);
            return contentFactory.createFigure(uri);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(String.format("Not a valid URI: %s", uriString));
        }
    }

    private Content createTable(JSONObject jsonTable) {
        List<List<Content>> tableItems = new ArrayList<>();
        for (Object tableRowJson : jsonTable.getJSONArray(ROWS)) {
            if (tableRowJson instanceof JSONArray) {
                List<Content> tableRow = new ArrayList<>();
                for (Object tableCellJson : (JSONArray) tableRowJson) {
                    if (tableCellJson instanceof JSONObject) {
                        tableRow.add(createContent((JSONObject) tableCellJson));
                    } else {
                        throw new IllegalArgumentException("Expected JSON representing content. Got: " + tableCellJson.getClass() + ". " + tableCellJson);
                    }
                }
                tableItems.add(tableRow);
            } else {
                throw new IllegalArgumentException("Expected JSON representing content. Got: " + tableRowJson.getClass() + ". " + tableRowJson);
            }
        }
        return contentFactory.createTable(tableItems);
    }
}
