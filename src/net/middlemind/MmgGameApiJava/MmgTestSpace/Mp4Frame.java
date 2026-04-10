package net.middlemind.MmgGameApiJava.MmgTestSpace;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.*;
import java.io.File;

/**
 *
 * @author brusc
 */
public class Mp4Frame extends JFrame {

    public int width = 1024;
    public int height = 768;
    
    public Mp4Frame() {
        setTitle("MP4 Player in JFrame");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        final JFXPanel fxPanel = new JFXPanel();
        add(fxPanel);

        Platform.runLater(() -> {
            String source = new File("C:\\FILES\\OIT_LAPTOP_BACKUP\\DOCUMENTS\\GitHub\\MmgGameApiJava\\storage\\jvgc_splash_video_2_1024x768.mp4").toURI().toString();
            Media media = new Media(source);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            MediaView mediaView = new MediaView(mediaPlayer);

            StackPane root = new StackPane();
            root.getChildren().add(mediaView);
            fxPanel.setScene(new Scene(root, width, height));
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Mp4Frame().setVisible(true);
        });
    }
}
