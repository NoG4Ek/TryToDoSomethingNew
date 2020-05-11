package scenes;

import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class IdentificationController {
    protected void animIncorrectInput(boolean forcibly, @NotNull TextField... textFields) {
        for (TextField textField: textFields) {
            if (textField.getText().equals("") || forcibly) {
                textField.setBorder(new Border(new BorderStroke(Color.rgb(110, 7, 38),
                        BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));
                textField.setEffect(new DropShadow(20, Color.rgb(89, 1, 28)));
            }
        }
    }

    protected void animIncorrectInputOff(@NotNull TextField textField) {
        textField.setBorder(Border.EMPTY);
        textField.setEffect(new DropShadow(4.5, Color.rgb(0, 0, 0)));
    }
}
