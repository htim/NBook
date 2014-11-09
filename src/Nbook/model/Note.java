package Nbook.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Created by htim on 09.11.14.
 */
public class Note {

    private final IntegerProperty id;
    private final StringProperty title;
    private final StringProperty data;
    private final ObjectProperty<LocalDate> creationDate;
    private final ObjectProperty<LocalDate> lastModificationTime;

    public Note() {
        this(0,null, null);
    }

    @Override
    public String toString() {
        return title.get();

    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public Note(Integer id,String title, String data) {
        this.id = new SimpleIntegerProperty(id);

        this.title = new SimpleStringProperty(title);
        this.data = new SimpleStringProperty(data);
        this.creationDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(2014, 1, 1));
        this.lastModificationTime = new SimpleObjectProperty<LocalDate>(LocalDate.of(2014,1,1));
    }

    public String getData() {
        return data.get();
    }

    public StringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public LocalDate getCreationDate() {
        return creationDate.get();
    }

    public ObjectProperty<LocalDate> creationDateProperty() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate.set(creationDate);
    }

    public LocalDate getLastModificationTime() {
        return lastModificationTime.get();
    }

    public ObjectProperty<LocalDate> lastModificationTimeProperty() {
        return lastModificationTime;
    }

    public void setLastModificationTime(LocalDate lastModificationTime) {
        this.lastModificationTime.set(lastModificationTime);
    }
}
