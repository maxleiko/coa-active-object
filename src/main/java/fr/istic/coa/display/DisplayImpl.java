package fr.istic.coa.display;

import fr.istic.coa.generator.Generator;
import fr.istic.coa.generator.Value;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * Created by leiko on 10/17/17.
 */
public class DisplayImpl implements Display {

    private Label id;
    private Label value;

    public DisplayImpl(Label id, Label value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public void update(Generator subject) {
        Value value = subject.getValue();

        Platform.runLater(() -> {
            this.id.setText("id=" + value.id());
            this.value.setText("value=" + value.value());
        });
    }
}
