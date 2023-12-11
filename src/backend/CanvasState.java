package backend;

import backend.model.*;

import java.util.ArrayList;

import java.util.List;

public class CanvasState<E extends Figure> extends ArrayList<E> {
    private List<List<E>> groups = new ArrayList<>();

    public void addFigure(E figure) {
        if (this.contains(figure)) return;
        this.add(figure);
    }

    public void deleteFigure(E figure) {
        // No se verifica que contenga figure pues remove() de la lib ya lo verif.
        this.remove(figure);
    }

    //devuelve el grupo que contiene figure. Este es un puntero al grupo y no una copia.
    private List<E> getLocalGroup(E figure){
        for (List<E> group: this.groups){
            if (group.contains(figure)){
                return group;
            }
        }
        return new ArrayList<>();
    }

    public void group(List<E> group){
        if (this.groups.contains(group)) return;
        for(E figure : group) groups.remove(getLocalGroup(figure));
        this.groups.add(group);
    }
    public void unGroup(List<E> group){
        for(E figure : group) groups.remove(getLocalGroup(figure));
    }
    public Iterable<E> figures() {
        return new ArrayList<>(this);
    }

    //devuelve una copia del grupo al que pertenece figure
    //si figure no pertenece a ningún grupo, devuelve una lista que solo contiene figure
    public List<E> getGroup(E figure){
        List<E> toReturn = new ArrayList<>(getLocalGroup(figure));
        if (toReturn.isEmpty()) toReturn.add(figure);
        return toReturn;
    }

}
