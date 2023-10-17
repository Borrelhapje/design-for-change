package nl.ou.domain;

import java.util.List;
import java.util.Optional;

public class TableContent implements CompositeContent {

    private List<List<Content>> table;

    public TableContent(List<List<Content>> table) {
        this.table = table;
    }

    @Override
    public Optional<CompositeContent> getComposite() {
        return Optional.of(this);
    }

    @Override
    public Iterator<Content> getIterator() {
        return new TableIterator();
    }

    @Override
    public void accept(ContentVisitor visitor) {
        visitor.doForTableRowStart(this);
        table.forEach(list -> {
            visitor.doForTableRowStart(null);
            list.forEach(c -> c.accept(visitor));
            visitor.doForTableRowEnd(null);
        });
        visitor.doForTableEnd(this);
    }

    private class TableIterator implements Iterator<Content> {

        private Iterator<Iterator<Content>> iterator;

        @Override
        public Content current() {
            return iterator.current().current();
        }

        @Override
        public boolean hasNext() {
            return iterator.current().hasNext();
        }

        @Override
        public boolean hasPrevious() {
            return iterator.current().hasPrevious();
        }

        /**
         * deze en previous() zijn echt een bitch om goed te implementeren.
         */
        @Override
        public boolean next() {
            if (hasNext()) {
                return iterator.current().next();
            }
            return false;
        }

        @Override
        public boolean previous() {
            if (hasPrevious()) {
                return iterator.current().previous();
            }
            return false;
        }

        public boolean startOfRow() {
            return !iterator.current().hasPrevious();
        }

        public boolean endOfRow() {
            return !iterator.current().hasNext();
        }

        public boolean nextRow() {
            if (iterator.hasNext()) {
                return iterator.next();
            }
            return false;
        }

        public boolean previousRow() {
            if (iterator.hasPrevious()) {
                return iterator.previous();
            }
            return false;
        }
    }
    
}
