package frontend;

import backend.EffectState;
import backend.model.Figure;
import frontend.model.FigureFront;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.function.Function;

public class SelectedFigures extends ArrayList<FigureFront> {

    public void updateCheckBox(CheckBox checkBox,Function<FigureFront, EffectState> stateFunction) {
        boolean oneTrue = false;
        boolean oneFalse = false;
        boolean oneUndetermined = false;
        for (FigureFront figure : this) {
            switch (stateFunction.apply(figure)) {
                case TRUE -> oneTrue = true;
                case FALSE -> oneFalse = true;
                case UNDETERMINED -> oneUndetermined = true;
            }
            // Aunque el if podria estar afuera, se ubica aca para que en caso de saber que es indeterminado retorne sin recorrer el resto del array
            if ((oneTrue && oneFalse) || oneUndetermined) {
                checkBox.setIndeterminate(true);
                return;
            }
        }
        if (oneTrue){
            checkBox.setIndeterminate(false);
            checkBox.setSelected(true);
        }else {
            checkBox.setIndeterminate(false);
            checkBox.setSelected(false);
        }
    }
}
