package backend;

import backend.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CanvasState<E extends Figure> extends ArrayList<E> {
    private List<List<E>> groups = new ArrayList<>();

    public void addFigure(E figure) {
        this.add(figure);
    }

    public void deleteFigure(E figure) {
        if (this.remove(figure)) {
            return;
        }
        System.out.println("not found");
    }

    public void group(List<E> group){
        if (groups.contains(group)) throw new RuntimeException("Group already exists");
        groups.add(group);
    }
    public void unGroup(List<E> group){
        if (!groups.contains(group)) throw new RuntimeException("Group does not exists");
        groups.remove(group);
    }
    public Iterable<E> figures() {
        return new ArrayList<>(this);
    }

    public Iterable<List<E>> groups() {
        return new ArrayList<>(groups);
    }

    public List<E> getGroup(E figure){
        for (List<E> group: groups){
            if (group.contains(figure)) return new ArrayList<>(group);
        }
        //todo ver si se puede resumir
        List<E> toReturn = new ArrayList<>();
        toReturn.add(figure);
        return toReturn;
    }

}
