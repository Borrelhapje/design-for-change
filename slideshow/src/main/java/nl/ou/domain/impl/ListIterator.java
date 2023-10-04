package nl.ou.domain.impl;

import java.util.List;
import java.util.Objects;

import nl.ou.domain.Iterator;

public class ListIterator<T> implements Iterator<T> {

    public ListIterator(List<T> slides) {
        this.slides = Objects.requireNonNull(slides);
    }
    
    private List<T> slides;
    private int position;
        @Override
        public T current() {
            return slides.get(position);
        }

        @Override
        public boolean hasNext() {
            return position < slides.size() - 1;
        }
        @Override
        public boolean hasPrevious() {
            return position > 0 && !slides.isEmpty();
        }
        @Override
        public boolean next() {
            if (hasNext()) {
                position++;
                return true;
            }
            return false;
        }
        @Override
        public boolean previous() {
            if (hasPrevious()) {
                position--;
                return true;
            }
            return false;
        }
}
