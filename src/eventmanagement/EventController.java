/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package eventmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;

public class EventController {
    @FXML private TableView<Event> eventTable;
    @FXML private TableColumn<Event, Integer> idColumn;
    @FXML private TableColumn<Event, String> nameColumn;
    @FXML private TableColumn<Event, LocalDate> dateColumn;
    @FXML private TableColumn<Event, String> locationColumn;

    @FXML private TextField nameField;
    @FXML private DatePicker datePicker;
    @FXML private TextField locationField;

    private ObservableList<Event> eventList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        try {
            eventList.addAll(DatabaseUtil.getAllEvents());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        eventTable.setItems(eventList);
    }

    @FXML
    private void saveEvent() {
        String eventName = nameField.getText();
        LocalDate eventDate = datePicker.getValue();
        String location = locationField.getText();

        if (eventName != null && eventDate != null && location != null) {
            Event event = new Event(0, eventName, eventDate, location);
            try {
                DatabaseUtil.saveEvent(event);
                eventList.add(event);
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void updateEvent() {
        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            selectedEvent.setEventName(nameField.getText());
            selectedEvent.setEventDate(datePicker.getValue());
            selectedEvent.setLocation(locationField.getText());

            try {
                DatabaseUtil.updateEvent(selectedEvent);
                eventTable.refresh();
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void deleteEvent() {
        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            try {
                DatabaseUtil.deleteEvent(selectedEvent.getId());
                eventList.remove(selectedEvent);
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearFields() {
        nameField.clear();
        datePicker.setValue(null);
        locationField.clear();
    }
}