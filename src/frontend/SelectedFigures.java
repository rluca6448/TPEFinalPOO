package frontend;

import frontend.model.FigureFront;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.function.Function;

public class SelectedFigures extends ArrayList<FigureFront> {

    public void updateCheckBox(CheckBox checkBox,Function<FigureFront, Boolean> stateFunction) {
        boolean oneTrue = false;
        boolean oneFalse = false;
        for (FigureFront figure : this) {
            boolean apply = stateFunction.apply(figure);
            if (apply) {
                oneTrue = true;
            } else {
                oneFalse = true;
            }
            if ((oneTrue && oneFalse)) {
                checkBox.setIndeterminate(true);
                return;
            }
        }
        checkBox.setIndeterminate(false);
        checkBox.setSelected(oneTrue);
    }
}
