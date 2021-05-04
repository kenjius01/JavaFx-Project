package Vivo;

import com.jfoenix.controls.JFXSlider;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import sample.HomePageController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class VivoV20 implements Initializable {
    @FXML
    private AnchorPane note20Anchor;
    @FXML
    private MediaView mv;
    @FXML
    private JFXSlider volSlider;
    @FXML
    private ImageView volImgView;
    private MediaPlayer mp;
    private Media me;
    @FXML
    private ImageView brandImgView;
    @FXML
    private ImageView backImgView;
    AnchorPane holderpane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File volFile = new File("res/symbol/volume.png");
        Image volImg = new Image(volFile.toURI().toString());
        volImgView.setImage(volImg);

        File backFile = new File("res/content/back.png");
        Image backImg = new Image(backFile.toURI().toString());
        backImgView.setImage(backImg);

        File brandFile = new File("res/vivo/vivoblue.gif");
        Image brandImg = new Image(brandFile.toURI().toString());
        brandImgView.setImage(brandImg);

        String path = new File
                ("res/vivo/Vivo V20 official Trailer video-VIVO v20 Trailer.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        mv.setFitHeight(540);
        mv.setFitWidth(554);
        volSlider.setValue(mp.getVolume() * 100);
        volSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mp.setVolume(volSlider.getValue()/100);
            }
        });

    }

    public void play() {
        mp.play();
    }
    public void pause() {
        mp.pause();
    }
    public void slow() {
        mp.setRate(mp.getRate() - 0.25);
    }
    public void fast() {
        mp.setRate(mp.getRate() + 0.25);
    }

    public void back() {
        mp.pause();
        HomePageController.getInstance().createPage(holderpane, "Vivo.fxml");
    }
}

