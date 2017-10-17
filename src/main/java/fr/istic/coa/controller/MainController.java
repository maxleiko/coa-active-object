package fr.istic.coa.controller;

import fr.istic.coa.channel.Channel;
import fr.istic.coa.channel.ChannelImpl;
import fr.istic.coa.display.Display;
import fr.istic.coa.display.DisplayImpl;
import fr.istic.coa.generator.GeneratorImpl;
import fr.istic.coa.scheduler.Scheduler;
import fr.istic.coa.strategy.AtomicBroadcastImpl;
import fr.istic.coa.strategy.BroadcastAlgo;
import fr.istic.coa.strategy.SequentialBroadcastImpl;
import fr.istic.coa.strategy.VersionBroadcastImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * Created by leiko on 10/16/17.
 */
public class MainController {

    @FXML private Button startBtn;
    @FXML private Button stopBtn;
    @FXML private Label displayId0;
    @FXML private Label displayValue0;
    @FXML private Label displayId1;
    @FXML private Label displayValue1;
    @FXML private Label displayId2;
    @FXML private Label displayValue2;
    @FXML private Label displayId3;
    @FXML private Label displayValue3;

    private Scheduler scheduler = Scheduler.getInstance();

    // generator
    private GeneratorImpl generator = new GeneratorImpl();

    // Broadcast algorithms
    private BroadcastAlgo atomicBroadcast = new AtomicBroadcastImpl();
    private BroadcastAlgo sequentialBroadcast = new SequentialBroadcastImpl();
    private BroadcastAlgo versionBroadcast = new VersionBroadcastImpl();

    @FXML
    public void initialize() {
        this.generator.setBroadcast(this.atomicBroadcast);

        Channel channel0 = new ChannelImpl(this.generator, 0);
        Channel channel1 = new ChannelImpl(this.generator, 50);
        Channel channel2 = new ChannelImpl(this.generator, 500);
        Channel channel3 = new ChannelImpl(this.generator, 1000);

        Display display0 = new DisplayImpl(this.displayId0, this.displayValue0);
        Display display1 = new DisplayImpl(this.displayId1, this.displayValue1);
        Display display2 = new DisplayImpl(this.displayId2, this.displayValue2);
        Display display3 = new DisplayImpl(this.displayId3, this.displayValue3);

        this.generator.attach(channel0);
        this.generator.attach(channel1);
        this.generator.attach(channel2);
        this.generator.attach(channel3);

        channel0.attach(display0);
        channel1.attach(display1);
        channel2.attach(display2);
        channel3.attach(display3);
    }

    /**
     * Starts a scheduler that generates values periodically
     */
    @FXML
    private void onStart() {
        this.scheduler.start();
        this.scheduler.schedulePeriodically(this.generator::generate, 0, 50);
        System.out.println("START");
        this.startBtn.setDisable(true);
        this.stopBtn.setDisable(false);
    }

    /**
     * Stops the scheduler
     */
    @FXML
    private void onStop() {
        this.scheduler.stop();
        System.out.println("STOP");
        this.startBtn.setDisable(false);
        this.stopBtn.setDisable(true);
    }

    public void setAtomicAlgo() {
        this.generator.setBroadcast(this.atomicBroadcast);
    }

    public void setSequentialAlgo() {
        this.generator.setBroadcast(this.sequentialBroadcast);
    }

    public void setVersionAlgo() {
        this.generator.setBroadcast(this.versionBroadcast);
    }
}
