package Nbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Nbook.model.Note;
import Nbook.view.NoteEditDialogController;
import Nbook.view.NoteOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Created by htim on 09.11.14.
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Note> noteList = FXCollections.observableArrayList();

    public MainApp() {
        noteList.add(new Note(0,"Sample note","<!DOCTYPE html PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n" +
                "<HTML>\n" +
                "   <HEAD>\n" +
                "      <TITLE>\n" +
                "         A Small Hello \n" +
                "      </TITLE>\n" +
                "   </HEAD>\n" +
                "<BODY>\n" +
                "   <H1>Hi</H1>\n" +
                "   <P>This is very minimal \"hello world\" HTML document.</P> \n" +
                "</BODY>\n" +
                "</HTML>"));
        noteList.add(new Note(1,"Sample note 1", "<!DOCTYPE html PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n" +
                "<HTML>\n" +
                "   <HEAD>\n" +
                "      <TITLE>\n" +
                "         A Small Hello \n" +
                "      </TITLE>\n" +
                "   </HEAD>\n" +
                "<BODY>\n" +
                "   <H1>Hi</H1>\n" +
                "   <P>This is very minimal \"hello world\" HTML document. Azazaza</P> \n" +
                "</BODY>\n" +
                "</HTML>" ));
    }
    public ObservableList<Note> getNoteList() {
        return noteList;
    }

    public ObservableList<String> getTitlesAndLastModTime() {
        ObservableList<String> titleList = FXCollections.observableArrayList();
        noteList.stream().forEach(x->titleList.add(x.getTitle()));
        return titleList;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("NoteBook");


        initRootLayout();

        showNoteOverView();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(IOException e) {
                e.printStackTrace();
        }
    }


    public void showNoteOverView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/NoteOverview.fxml"));
            AnchorPane noteOverView = (AnchorPane) loader.load();

            rootLayout.setCenter(noteOverView);

            NoteOverviewController noteOverviewController = loader.getController();
            noteOverviewController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public boolean showNoteEditDialog(Note note) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/NoteEditDialog.fxml"));
            AnchorPane noteEdit = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование записи");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(noteEdit);
            dialogStage.setScene(scene);

            NoteEditDialogController noteEditDialogController = loader.getController();
            noteEditDialogController.setDialogStage(dialogStage);
            noteEditDialogController.setNote(note);

            dialogStage.showAndWait();

            return noteEditDialogController.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static void main(String[] args) {
        launch(args);
    }

}
