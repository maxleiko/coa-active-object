package fr.istic.coa.display;

import fr.istic.coa.generator.AsyncGenerator;
import fr.istic.coa.generator.Generator;
import fr.istic.coa.generator.Value;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.concurrent.ExecutionException;

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
    public void update(AsyncGenerator subject) {
        try {
            Value value = subject.asyncGetValue().get();
            Platform.runLater(() -> {
                this.id.setText("id=" + value.id());
                this.value.setText("value=" + value.value());
            });
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Something went wrong in getValue()", e);
        }
    }
}
