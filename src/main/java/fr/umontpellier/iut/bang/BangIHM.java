package fr.umontpellier.iut.bang;

import fr.umontpellier.iut.bang.logic.Game;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.ResultsView;
import fr.umontpellier.iut.bang.views.StartView;
import fr.umontpellier.iut.bang.views.ourviews.MyGameView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BangIHM extends Application {

    private GameView gameView;
    private StartView startView;
    private ResultsView resultsView;
    @FXML
    private Stage primaryStage;
    private IGame game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Bang");
        /*initStartView();
        startView.setPlayersListSetListener(whenPlayersNamesListIsSet);
        initPlayersNames();*/
        startGame();
    }

    public void startGame() {
        List<String> playerNames = new ArrayList<>() /*startView.getPlayersNamesList();*/;
        playerNames.add("John");
        playerNames.add("Jon");
        playerNames.add("Jhn");
        playerNames.add("Joh");
        game = new IGame(new Game(Game.makePlayers(playerNames.toArray(new String[playerNames.size()]))));
        initGameView();
        initResultView();
        Scene scene = new Scene(gameView);
        primaryStage.setMaxHeight(1200);
        primaryStage.setMaxWidth(2000);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> {
            this.onStopGame();
            event.consume();
        });
        primaryStage.show();
    }

    /**
     * Pour instancier la vue de renseignement des noms des joueurs
     */
    private void initStartView() {
        startView = null;
    }

    /**
     * Pour instancier la vue principale du jeu
     */
    private void initGameView() {
        gameView = new MyGameView(game);
    }

    /**
     * Pour instancier la vue de fin de partie
     */
    private void initResultView() {
        resultsView = null;
    }

    private final ListChangeListener<String> whenPlayersNamesListIsSet = change -> {
        if (!startView.getPlayersNamesList().isEmpty())
            startGame();
    };

    public IGame getIGame() {
        return game;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void initPlayersNames() {
        startView.show();
    }

    public void onStopGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Do you really want to stop playing ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}