
package javafx;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fx extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Argazkiak");

        // BILDUMAK
        //bildumen izenak zehaztu
        List<String> bildumak = List.of("Abereak", "Landareak", "Frutak");

        //Sortu bildumak bere izenekin
        ObservableList<Bilduma> bildumaList =
                FXCollections.observableArrayList();

        ComboBox comboBilduma = new ComboBox();
        bildumak.forEach((bildumaIzen) -> {
            bildumaList.add(new Bilduma(bildumaIzen));
        });

        comboBilduma.setItems(bildumaList);


        // ARGAZKIAK
        Map<String, List<Argazki>> bildumaMap = new HashMap<>();

        //

        bildumaMap.put("Abereak", List.of(
                new Argazki("Elefantea", "elefantea.png"),
                new Argazki("Txakurra", "txakurra.jpg"),
                new Argazki("Untxia", "untxia.jpeg")
        ));

        bildumaMap.put("Landareak", List.of(
                new Argazki("Kaktusa", "kaktusa.jpg"),
                new Argazki("Eguzkilorea", "eguzkilorea.jpg"),
                new Argazki("Zuhaitza", "zuhaitza.jpg")
        ));

        bildumaMap.put("Frutak", List.of(
                new Argazki("Marrubia", "marrubia.png"),
                new Argazki("Sandia", "sandia.png"),
                new Argazki("Sagarra", "sagarra.png")
        ));

        // Abereak | argazki 1 (izena,argazkia), argazki2, ...

        ListView listViewOfArgazki = new ListView<>();
        ObservableList<Argazki> argazkiList = FXCollections.observableArrayList();

        comboBilduma.setOnAction(a -> {
            argazkiList.setAll(bildumaMap.get(comboBilduma.getValue().toString()));
            listViewOfArgazki.setItems(argazkiList);

        });


        //ARGAZKIA IKUSI

        ImageView imageView=new ImageView();


        listViewOfArgazki.getSelectionModel().selectedItemProperty().addListener(  (observable, oldValue, newValue) -> {
            if (observable.getValue() == null) return;

            String fitx = ((Argazki)observable.getValue()).getFitxategia();

            try {
                imageView.setImage(lortuIrudia(fitx /* 48x48 */));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        //Lehioa sortu
        VBox vbox = new VBox(comboBilduma,listViewOfArgazki,imageView);
        Scene scene = new Scene(vbox, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private Image lortuIrudia(String location) throws IOException {

        InputStream is = getClass().getResourceAsStream("/" + location);
        BufferedImage reader = ImageIO.read(is);
        return SwingFXUtils.toFXImage(reader, null);

    }


    public static void main(String[] args) {
        Application.launch(args);
    }

}
