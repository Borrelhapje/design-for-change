package nl.ou.domain;

import java.util.List;
import java.util.Optional;

public class TableContent implements CompositeContent {

    private List<List<Content>> table;

    @Override
    public Optional<CompositeContent> getComposite() {
        return Optional.of(this);
    }

    @Override
    public Iterator<Content> getIterator() {
        return new TableIterator();
    }

    private class TableIterator implements Iterator<Content> {

        private Iterator<Iterator<Content>> iterator;
        private int row;
        private int column;

        @Override
        public Content current() {
            return iterator.current().current();
        }

        @Override
        public boolean hasNext() {
            return iterator.current().hasNext() || iterator.hasNext();
        }

        @Override
        public boolean hasPrevious() {
            return iterator.current().hasPrevious() || iterator.hasPrevious();
        }

        /**
         * deze en previous() zijn echt een bitch om goed te implementeren.
         */
        @Override
        public boolean next() {
            if (hasNext()) {
                while(!iterator.current().next()) {
                    iterator.next();
                }
                if (iterator.current().next()) {
                    return true;
                }
                
                if (column < table.get(row).size() - 1) {
                    column++;
                    return true;
                }
                column = 0;
                do {
                    row++;
                } while (column <= table.get(row).size() - 1);
                return true;
            }
            return false;
        }

        @Override
        public boolean previous() {
            if (hasPrevious()) {

            }
            return false;
        }
    }
    
}
