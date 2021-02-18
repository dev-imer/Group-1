package com.oopclass.breadapp.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.oopclass.breadapp.config.StageManager;
import com.oopclass.breadapp.models.Product;
import com.oopclass.breadapp.models.Reservation;
import com.oopclass.breadapp.models.User;
import com.oopclass.breadapp.services.impl.ReservationService;
import com.oopclass.breadapp.views.FxmlView;
import java.time.LocalDateTime;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * OOP Class 20-21
 *
 * @author Gerald Villaran
 */
@Controller
public class ReservationController implements Initializable {

    @FXML
    private Label reservationId;
    
    @FXML
    private Button openCustomer;

    @FXML
    private Button openEmployee;
    
    @FXML
    private TextField status;
    
    @FXML
    private TextField reservationAmount;
    
    @FXML
    private DatePicker date;
    
    @FXML
    private TextField fullName;
    
    @FXML
    private Button reset;

    @FXML
    private Button saveReservation;
    
    @FXML
    private Button deleteReservation;

    @FXML
    private TableView<Reservation> reservationTable;

    @FXML
    private TableColumn<Reservation, Long> colReservationId;

    @FXML
    private TableColumn<Reservation, LocalDate> colDate;
    
    @FXML
    private TableColumn<Reservation, LocalDate> colCreatedAt;
    
    @FXML
    private TableColumn<Reservation, LocalDateTime> colUpdatedAt;
    
    @FXML
    private TableColumn<Reservation, String> colReservationAmount;
    
     @FXML
    private TableColumn<Reservation, String> colStatus;

    @FXML
    private TableColumn<Reservation, String> colFullName;

    @FXML
    private TableColumn<Reservation, Boolean> colEdit;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private ReservationService reservationService;

    private ObservableList<Reservation> reservationList = FXCollections.observableArrayList();

//    @FXML
//    private void exit(ActionEvent event) {
//        Platform.exit();
//   }

    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }
    
    @FXML
    private void openCustomer(ActionEvent event){
    stageManager.switchScene(FxmlView.CUSTOMER);
    }
    
    @FXML
    private void openEmployee(ActionEvent event){
    stageManager.switchScene(FxmlView.EMPLOYEE);
    }
    
    @FXML
    private void saveReservation(ActionEvent event) {

        if (validate("Reservation Amount", getReservationAmount(), "^[0-9]*$")
                && validate("Full Name", getFullName(), "([a-zA-Z]{3,30}\\s*)+")
                && validate("Status", getStatus(), "([a-zA-Z]{3,30}\\s*)+")
                && emptyValidation("Date", date.getEditor().getText().isEmpty())) 
        {

            if (reservationId.getText() == null || "".equals(reservationId.getText())) {
                if (true) {

                    Reservation reservation = new Reservation();
                    reservation.setReservationAmount(getReservationAmount());
                    reservation.setFullName(getFullName());
                    reservation.setDate(getDate());
                    reservation.setStatus(getStatus());
                    reservation.setCreatedAt();
                    reservation.setUpdatedAt();
                    
                    Reservation newReservation = reservationService.save(reservation);

                    saveAlert(newReservation);
                }

            } else {
                Reservation reservation = reservationService.find(Long.parseLong(reservationId.getText()));
                reservation.setReservationAmount(getReservationAmount());
                reservation.setFullName(getFullName());
                reservation.setDate(getDate());
                reservation.setCreatedAt();
                reservation.setUpdatedAt();
                reservation.setStatus(getStatus());
                
                Reservation updatedReservation = reservationService.update(reservation);
                updateAlert(updatedReservation);
            }

            clearFields();
            loadReservationDetails();
        }

    }

    @FXML
    private void deleteReservation(ActionEvent event) {
        List<Reservation> reservations = reservationTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            reservationService.deleteInBatch(reservations);
        }

        loadReservationDetails();
    }

    private void clearFields() {
        reservationId.setText(null);
        reservationAmount.clear();
        fullName.clear();
        status.clear();
        date.getEditor().clear();
    }

    private void saveAlert(Reservation reservation) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Reservation saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The reservation for" + reservation.getFullName() + "with the amount of" + reservation.getReservationAmount()  
                + " has been created and with ID: "  + reservation.getId() + "." + " The reservation date is on" + reservation.getDate());
        alert.showAndWait();
    }

    private void updateAlert(Reservation reservation) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Reservation updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The reservation for" + reservation.getFullName() + "with the amount of" + reservation.getReservationAmount()  + " has been updated.");
        alert.showAndWait();
    }

    public String getReservationAmount() {
        return reservationAmount.getText();
    };
    
    public String getFullName() {
        return fullName.getText();
    };
    
     public String getStatus() {
        return status.getText();
    };
    
    public LocalDate getDate() {
        return date.getValue();
    };
      
    /*
	 *  Set All reservationTable column properties
     */
    private void setColumnProperties() {
        /* Override date format in table
		 * colDOB.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
			 String pattern = "dd/MM/yyyy";
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 }));*/

        colReservationId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colReservationAmount.setCellValueFactory(new PropertyValueFactory<>("reservationAmount"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        colEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<Reservation, Boolean>, TableCell<Reservation, Boolean>> cellFactory
            = new Callback<TableColumn<Reservation, Boolean>, TableCell<Reservation, Boolean>>() {
        @Override
        public TableCell<Reservation, Boolean> call(final TableColumn<Reservation, Boolean> param) {
            final TableCell<Reservation, Boolean> cell = new TableCell<Reservation, Boolean>() {
                Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                final Button btnEdit = new Button();

                @Override
                public void updateItem(Boolean check, boolean empty) {
                    super.updateItem(check, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btnEdit.setOnAction(e -> {
                            Reservation reservation = getTableView().getItems().get(getIndex());
                            updateReservation(reservation);
                        });

                        btnEdit.setStyle("-fx-background-color: transparent;");
                        ImageView iv = new ImageView();
                        iv.setImage(imgEdit);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnEdit.setGraphic(iv);

                        setGraphic(btnEdit);
                        setAlignment(Pos.CENTER);
                        setText(null);
                    }
                }

                private void updateReservation(Reservation reservation) {
                    reservationId.setText(Long.toString(reservation.getId()));
                    reservationAmount.setText(reservation.getReservationAmount());
                    fullName.setText(reservation.getFullName());
 
                }
            };
            return cell;
        }
    };

    /*
	 *  Add All reservations to observable list and update table
     */
    private void loadReservationDetails() {
        reservationList.clear();
        reservationList.addAll(reservationService.findAll());

        reservationTable.setItems(reservationList);
    }

    /*
	 * Validations
     */
    private boolean validate(String field, String value, String pattern) {
        if (!value.isEmpty()) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if (m.find() && m.group().equals(value)) {
                return true;
            } else {
                validationAlert(field, false);
                return false;
            }
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private boolean emptyValidation(String field, boolean empty) {
        if (!empty) {
            return true;
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private void validationAlert(String field, boolean empty) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if (field.equals("Role")) {
            alert.setContentText("Please Select " + field);
        } else {
            if (empty) {
                alert.setContentText("Please Enter " + field);
            } else {
                alert.setContentText("Please Enter Valid " + field);
            }
        }
        alert.showAndWait();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        reservationTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();

        // Add all reservations into table
        loadReservationDetails();
    }

}
