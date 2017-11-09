package fr.istic.coa.display;

import fr.istic.coa.generator.AsyncGenerator;
import fr.istic.coa.generator.CompletableGenerator;
import fr.istic.coa.generator.CompletableGeneratorImpl;
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
        CompletableGeneratorImpl adapter = new CompletableGeneratorImpl(subject);
        adapter.getValue().thenAcceptAsync((val) -> Platform.runLater(() -> {
            this.id.setText("id=" + val.id());
            this.value.setText("value=" + val.value());
            System.out.println("   " + name + "> id=" + val.id() + ", value=" + val.value());
        }));
    }
}
