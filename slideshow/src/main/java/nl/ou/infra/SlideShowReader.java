package nl.ou.infra;

import nl.ou.domain.SlideShow;

import java.io.InputStream;

public interface SlideShowReader {

    SlideShow readSlideShow(InputStream inputStream);

}
