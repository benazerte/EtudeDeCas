package webcaisse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Chargement du fichier FXML depuis le dossier resources
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/webcaisse/vue/recherche.fxml"));

        // Création de la scène avec des dimensions par défaut
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        // Paramétrage de la fenêtre principale
        stage.setTitle("WebCaisse - Module GRC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
