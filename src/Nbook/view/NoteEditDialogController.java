package Nbook.view;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import Nbook.model.Note;
import Nbook.util.DateUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

/**
 * Created by htim on 09.11.14.
 */
public class NoteEditDialogController {

    @FXML
    private HTMLEditor htmlEditor;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    private Stage dialogStage;
    private Note note;
    private boolean okClicked;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setNote(Note note) {
        this.note = note;
        htmlEditor.setHtmlText(note.getData());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (note.getTitle()==null || note.getTitle().length()==0) {
            Optional<String> respone = Dialogs.create()
                    .owner(dialogStage)
                    .title("Сохранение")
                    .masthead("Название записи")
                    .message("Введите название записи")
                    .showTextInput();

            respone.ifPresent(x->note.setTitle(x));

        }
        if (htmlEditor.getHtmlText().length()!=0 || htmlEditor.getHtmlText()!=null) {
            note.setData(htmlEditor.getHtmlText());
            note.setId(0);
            note.setLastModificationTime(null);
            note.setCreationDate(null);
            okClicked = true;
            dialogStage.close();
        }
    }
    @FXML
    private void handleCancel() {
        htmlEditor.setHtmlText(null);
        dialogStage.close();
    }



}
