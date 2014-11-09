package Nbook.view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.Button;
import Nbook.MainApp;
import Nbook.model.Note;
import org.controlsfx.dialog.*;

/**
 * Created by htim on 09.11.14.
 */
public class NoteOverviewController {

    @FXML
    private ListView<Note> noteListView;

    @FXML
    private WebView webView;

    @FXML
    private Button newButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteBStringutton;

    private MainApp mainApp;

    private WebEngine engine;

    public NoteOverviewController() {
    }

    @FXML
    private void initialize() {
        showDataOfNote(null);

        noteListView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showDataOfNote(newValue));

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        noteListView.setItems(mainApp.getNoteList());
        engine = webView.getEngine();
        engine.loadContent(mainApp.getNoteList().get(0).getData());
    }

    private void showDataOfNote(Note note) {
        if (note!=null) {
            engine = webView.getEngine();
            engine.loadContent(note.getData());
        } else {
            engine = webView.getEngine();
            engine.loadContent("<!DOCTYPE html PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n" +
                                "\"<p>Не выбрано записей\"</p> \"</HTML>\"");
        }
    }

    @FXML
    private void handleDeleteButton() {
        int selectedIndex = noteListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex>=0) {
            noteListView.getItems().remove(selectedIndex);
        } else {
             Dialogs.create()
                    .title("Ошибка")
                    .masthead("Нет ни одной записи")
                    .message("Пожалуйста, создайте запись")
                    .showWarning();
        }

    }

    @FXML
    private void handleNewButton() {
        Note tempNote = new Note();
        boolean isOkClicked = mainApp.showNoteEditDialog(tempNote);
        if (isOkClicked) {
            mainApp.getNoteList().add(tempNote);
        }
    }

    @FXML
    private void handleEditButton() {
        Note selectedNote = noteListView.getSelectionModel().getSelectedItem();
        if (selectedNote!=null) {
            boolean okClicked = mainApp.showNoteEditDialog(selectedNote);
            if (okClicked) {
                showDataOfNote(selectedNote);
            }
        } else {
            Dialogs.create()
                    .title("Ошибка")
                    .masthead("Не выбрана запись")
                    .message("Выберите запись")
                    .showWarning();
        }
    }
}
