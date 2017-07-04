package scene.event.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import resources.database.DB;
import scene.event.entity.Event;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Liu Woon Kit on 29/6/2017.
 */
public class EventListController implements Initializable {
    private EventListController eventListController = this;
    private DB db = new DB();

    @FXML
    Label clockDisplay;

    @FXML
    HBox newEventListBox;

    @FXML
    HBox eventListBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //getEventsFromDB();


        for(int i = 0;i < 100; i++) {
            addEventToDisplay(new Event("Help NYP", 1, "Reorganize Books", new GregorianCalendar(), null, null));
        }
        addEventToDisplay(new Event("Help NYP", 1, "Reorganize Books", new GregorianCalendar(), null, null));

        //startClock();

    }

    public void getEventsFromDB() {
        clearDisplays();
        /*List list = null;// db.read("SELECT eventTitle, eventID, eventDesc, dateOfEvent, startTime, endTime FROM event WHERE isApproved = true;");
        for(Object o : list) {
            HashMap hm = (HashMap) o;
            addEventToDisplay(new Event((String)hm.get("eventTitle"), (int)hm.get("eventID"), (String)hm.get("eventDesc"), (GregorianCalendar) hm.get("dateOfEvent"), (Time)hm.get("startTime"), (Time)hm.get("endTime")));
        }*/
    }

    public void addEventToDisplay(Event event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../EventObject.fxml"));
        fxmlLoader.setController(new EventObjectController(eventListController, event));
        try {eventListBox.getChildren().add(fxmlLoader.load());} catch (IOException e) {}
    }

    private static Date date;
    private static Timer timer = new Timer();
    private static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    public void startClock() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                date = new Date();
                Platform.runLater(() -> clockDisplay.setText(dateFormat.format(date)));
            }
        },0, 1000);
    }

    public void clearDisplays() {
        newEventListBox.getChildren().clear();
        eventListBox.getChildren().clear();
    }
}