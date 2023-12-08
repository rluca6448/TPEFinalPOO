package backend;

import backend.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CanvasState<E extends Figure> extends ArrayList<E> {
    // public void ungroup(List<E> figures){
    // for (E figure : figures){
    //   this.addAll(figure.getFigures);
    //   this.delete(figure);
    // }
    // }

    public void addFigure(E figure) {
        this.add(figure);
    }

    public void deleteFigure(E figure) {
        if (contains(figure)) {
            this.remove(figure);
            return;
        }
        System.out.println("not found");
    }

    public Iterable<E> figures() {
        return new ArrayList<>(this);
    }

}
