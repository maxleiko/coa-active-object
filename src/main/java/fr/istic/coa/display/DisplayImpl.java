package fr.istic.coa.display;

import fr.istic.coa.generator.AsyncGenerator;
import fr.istic.coa.generator.Value;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.concurrent.ExecutionException;

/**
 *
 * Created by leiko on 10/17/17.
 */
public class DisplayImpl implements Display {

    private String name;
    private Label id;
    private Label value;

    public DisplayImpl(String name, Label id, Label value) {
        this.name = name;
        this.id = id;
        this.value = value;
    }

    @Override
    public void update(AsyncGenerator subject) {

        try {
            Value value = subject.getValue().get();
            Platform.runLater(() -> {
                this.id.setText("id=" + value.id());
                this.value.setText("value=" + value.value());
                System.out.println("   " + name + "> id=" + value.id() + ", value=" + value.value());
            });
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Something went wrong in getValue()", e);
        }
    }
}
