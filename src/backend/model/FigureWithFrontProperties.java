package backend.model;

import java.util.Set;

public interface FigureWithFrontProperties<E extends Figure> extends Figure {
    Set<E> getFigures();
}
