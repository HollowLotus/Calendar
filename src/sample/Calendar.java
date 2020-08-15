package sample; /**
 * Calendar that allow you to make, delete and open note entries.
 *
 * @author Allan Atton
 * @version 1.0
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Spinner;
import javafx.util.converter.LocalTimeStringConverter;

public class Calendar extends Application {

    private VBox pane = new VBox();
    private VBox mondayBox = new VBox();
    private VBox tuesdayBox = new VBox();
    private VBox wednesdayBox = new VBox();
    private VBox thursdayBox = new VBox();
    private VBox fridayBox = new VBox();
    private Scene scene;
    private Button clear = new Button("Clear Note");
    private Button openAppointment = new Button("Open Note");
    private Button delete = new Button("Delete Note");
    private Button createAppointment = new Button("Calender Date");
    private Button deleteNote = new Button("Delete Note");
    private DateTimeFormatter changeDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private DateTimeFormatter changeTime = DateTimeFormatter.ofPattern("hh:mm a");
    private ComboBox<WeekTime> daysOfWeek = new ComboBox<>();
    private Label mondayLabel = new Label("Monday");
    private Label tuesdayLabel = new Label("Tuesday");
    private Label wednesdayLabel = new Label("Wednesday");
    private Label thursdayLabel = new Label("Thursday");
    private Label fridayLabel = new Label("Friday");
    private TextArea mondayArea = new TextArea();
    private TextArea tuesdayArea = new TextArea();
    private TextArea wednesdayArea = new TextArea();
    private TextArea thursdayArea = new TextArea();
    private TextArea fridayArea = new TextArea();
    private List<String> appointmentNote = new ArrayList<String>();
    private List<LocalDate> calendar = new ArrayList<LocalDate>();
    private List<String> clock = new ArrayList<String>();



    @Override
    public void start(Stage primaryStage) {

        //1st VBox


        daysOfWeek.getItems().setAll(WeekTime.values());
        //daysOfWeek.setPromptText("Week Days");
        //daysOfWeek.getItems().add("Monday");
        //daysOfWeek.getItems().add("Tuesday");
        //daysOfWeek.getItems().add("Wednesday");
        //daysOfWeek.getItems().add("Thursday");
        // daysOfWeek.getItems().add("Friday");
        HBox dateBox = new HBox(daysOfWeek, clear, openAppointment, delete, createAppointment);
        dateBox.setPadding(new Insets(15));
        dateBox.setSpacing(20.0);
        dateBox.setAlignment(Pos.CENTER);

        //Monday VBox
        mondayArea.setPrefColumnCount(30);
        mondayArea.setPrefRowCount(17);
        mondayArea.setWrapText(true);
        mondayBox.getChildren().addAll(mondayLabel, mondayArea);

        //Tuesday VBox
        tuesdayArea.setPrefColumnCount(30);
        tuesdayArea.setPrefRowCount(17);
        tuesdayArea.setWrapText(true);
        tuesdayBox.getChildren().addAll(tuesdayLabel, tuesdayArea);

        //Wednesday VBox
        wednesdayArea.setPrefColumnCount(30);
        wednesdayArea.setPrefRowCount(17);
        wednesdayBox.getChildren().addAll(wednesdayLabel, wednesdayArea);

        //Thursday VBox
        thursdayArea.setPrefColumnCount(30);
        thursdayArea.setPrefRowCount(17);
        thursdayBox.getChildren().addAll(thursdayLabel, thursdayArea);

        //Friday VBox
        fridayArea.setPrefColumnCount(30);
        fridayArea.setPrefRowCount(17);
        fridayBox.getChildren().addAll(fridayLabel, fridayArea);


        Spinner<LocalTime> deleteSpinner = new Spinner<>(new SpinnerValueFactory<LocalTime>() {
            {
                setConverter(new LocalTimeStringConverter(FormatStyle.SHORT));
            }
            @Override
            public void decrement(int steps) {
                if(getValue() == null)
                    setValue(LocalTime.now());
                else{
                    LocalTime time = (LocalTime) getValue();
                    setValue(time.minusMinutes(steps));
                }
            }

            @Override
            public void increment(int steps) {
                if (this.getValue() == null)
                    setValue(LocalTime.now());
                else{
                    LocalTime time = (LocalTime) getValue();
                    setValue(time.plusMinutes(steps));
                }
            }
        });

        Spinner<LocalTime> openSpinner = new Spinner<>(new SpinnerValueFactory<LocalTime>() {
            {
                setConverter(new LocalTimeStringConverter(FormatStyle.SHORT));
            }
            @Override
            public void decrement(int steps) {
                if(getValue() == null)
                    setValue(LocalTime.now());
                else{
                    LocalTime time = (LocalTime) getValue();
                    setValue(time.minusMinutes(steps));
                }
            }

            @Override
            public void increment(int steps) {
                if (this.getValue() == null)
                    setValue(LocalTime.now());
                else{
                    LocalTime time = (LocalTime) getValue();
                    setValue(time.plusMinutes(steps));
                }
            }
        });

        createAppointment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage secondStage = new Stage();
                TextArea calendarNote = new TextArea();
                DatePicker calendarDate = new DatePicker();
                Button submitNote = new Button("Submit Note");
                Button submitOpen = new Button("Sumbit & Open");

                Spinner<LocalTime> spinner = new Spinner<>(new SpinnerValueFactory<LocalTime>() {
                    {
                        setConverter(new LocalTimeStringConverter(FormatStyle.SHORT));
                    }
                    @Override
                    public void decrement(int steps) {
                        if(getValue() == null)
                            setValue(LocalTime.now());
                        else{
                            LocalTime time = (LocalTime) getValue();
                            setValue(time.minusMinutes(steps));
                        }
                    }

                    @Override
                    public void increment(int steps) {
                        if (this.getValue() == null)
                            setValue(LocalTime.now());
                        else{
                            LocalTime time = (LocalTime) getValue();
                            setValue(time.plusMinutes(steps));
                        }
                    }
                });

                spinner.setEditable(true);


                //Submit Note Button
                submitNote.setOnAction(e -> {

                    if(calendarDate.getValue() == null)
                    {
                        JOptionPane.showMessageDialog(null, "Please enter a date to create your note!");
                    }
                    if(calendarDate.getValue().getDayOfWeek().toString().equalsIgnoreCase("saturday") || calendarDate.getValue().getDayOfWeek().toString().equalsIgnoreCase("sunday"))
                    {
                        JOptionPane.showMessageDialog(null, "I'm sorry but we're closed on Saturday & Sunday");
                    }
                    else
                    {
                        appointmentNote.add(calendarNote.getText());
                        calendar.add(calendarDate.getValue());
                        clock.add(changeTime.format(spinner.getValue()));
                        secondStage.close();
                    }
                });

                //Submit Note and Post
                submitOpen.setOnAction(e -> {

                    if(calendarDate.getValue().getDayOfWeek().toString().equalsIgnoreCase("monday"))
                    {
                        appointmentNote.add(calendarNote.getText());
                        calendar.add(calendarDate.getValue());
                        clock.add(changeTime.format(spinner.getValue()));

                        mondayArea.setText(changeDate.format(calendarDate.getValue()) + "\n" + changeTime.format(spinner.getValue()) + "\n" + calendarNote.getText());

                        secondStage.close();
                    }
                    if(calendarDate.getValue().getDayOfWeek().toString().equalsIgnoreCase("tuesday"))
                    {
                        appointmentNote.add(calendarNote.getText());
                        calendar.add(calendarDate.getValue());
                        clock.add(changeTime.format(spinner.getValue()));

                        tuesdayArea.setText(changeDate.format(calendarDate.getValue()) + "\n" + changeTime.format(spinner.getValue()) + "\n" + calendarNote.getText());

                        secondStage.close();
                    }
                    if(calendarDate.getValue().getDayOfWeek().toString().equalsIgnoreCase("wednesday"))
                    {
                        appointmentNote.add(calendarNote.getText());
                        calendar.add(calendarDate.getValue());
                        clock.add(changeTime.format(spinner.getValue()));

                        wednesdayArea.setText(changeDate.format(calendarDate.getValue()) + "\n" + changeTime.format(spinner.getValue()) + "\n" + calendarNote.getText());

                        secondStage.close();
                    }
                    if(calendarDate.getValue().getDayOfWeek().toString().equalsIgnoreCase("thursday"))
                    {
                        appointmentNote.add(calendarNote.getText());
                        calendar.add(calendarDate.getValue());
                        clock.add(changeTime.format(spinner.getValue()));

                        thursdayArea.setText(changeDate.format(calendarDate.getValue()) + "\n" + changeTime.format(spinner.getValue()) + "\n" + calendarNote.getText());

                        secondStage.close();
                    }
                    if(calendarDate.getValue().getDayOfWeek().toString().equalsIgnoreCase("friday"))
                    {
                        appointmentNote.add(calendarNote.getText());
                        calendar.add(calendarDate.getValue());
                        clock.add(changeTime.format(spinner.getValue()));

                        fridayArea.setText(changeDate.format(calendarDate.getValue()) + "\n" + changeTime.format(spinner.getValue()) + "\n" + calendarNote.getText());

                        secondStage.close();
                    }
                    if(calendarDate.getValue().getDayOfWeek().toString().equalsIgnoreCase("saturday") || calendarDate.getValue().getDayOfWeek().toString().equalsIgnoreCase("sunday"))
                    {
                        JOptionPane.showMessageDialog(null, "I'm sorry but we're closed on Saturday & Sunday");
                    }
                });

                HBox secondaryWeek = new HBox(20, calendarDate, spinner);
                //secondaryWeek.getChildren().addAll(calendarDate, spinner);

                HBox noteButtons = new HBox(20, submitNote, submitOpen);
                noteButtons.setAlignment(Pos.CENTER);

                VBox secondaryLayout = new VBox(20, secondaryWeek, calendarNote, noteButtons);

                secondaryLayout.setPadding(new Insets(15));

                Scene secondScene = new Scene(secondaryLayout, 400, 200);

                //Stage secondStage = new Stage();
                secondStage.setTitle("Calendering Note");
                secondStage.setScene(secondScene);
                secondStage.show();

            }
        });

        openAppointment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Button openNote = new Button("Open Note");
                DatePicker mainCalendar = new DatePicker();
                Stage fourthStage = new Stage();

                openNote.setOnAction(e ->{

                    for (int count = 0; count < calendar.size(); count++)
                    {

                        if (calendar.get(count).equals(mainCalendar.getValue()) && mainCalendar.getValue().getDayOfWeek().toString().equalsIgnoreCase("monday") && clock.get(count).equals(changeTime.format(openSpinner.getValue())))
                        {
                            mondayArea.setText(calendar.get(count) + "\n" + clock.get(count) + "\n" + appointmentNote.get(count));
                            break;
                        }
                        if (calendar.get(count).equals(mainCalendar.getValue()) && mainCalendar.getValue().getDayOfWeek().toString().equalsIgnoreCase("tuesday") && clock.get(count).equals(changeTime.format(openSpinner.getValue())))
                        {
                            tuesdayArea.setText(calendar.get(count) + "\n" + clock.get(count) + "\n" + appointmentNote.get(count));
                            break;
                        }
                        if (calendar.get(count).equals(mainCalendar.getValue()) && mainCalendar.getValue().getDayOfWeek().toString().equalsIgnoreCase("wednesday") && clock.get(count).equals(changeTime.format(openSpinner.getValue())))
                        {
                            wednesdayArea.setText(calendar.get(count) + "\n" + clock.get(count) + "\n" + appointmentNote.get(count));
                            break;
                        }
                        if (calendar.get(count).equals(mainCalendar.getValue()) && mainCalendar.getValue().getDayOfWeek().toString().equalsIgnoreCase("thursday") && clock.get(count).equals(changeTime.format(openSpinner.getValue())))
                        {
                            thursdayArea.setText(calendar.get(count) + "\n" + clock.get(count) + "\n" + appointmentNote.get(count));
                            break;
                        }
                        if (calendar.get(count).equals(mainCalendar.getValue()) && mainCalendar.getValue().getDayOfWeek().toString().equalsIgnoreCase("friday") && clock.get(count).equals(changeTime.format(openSpinner.getValue())))
                        {
                            fridayArea.setText(calendar.get(count) + "\n" + clock.get(count) + "\n" + appointmentNote.get(count));
                            break;
                        }
                    }
                });

                HBox openAppointmentBox = new HBox(20, mainCalendar, openSpinner);

                HBox openAppointmentBox2 = new HBox(20, openNote);


                VBox fourthLayout = new VBox(20, openAppointmentBox, openAppointmentBox2);
                fourthLayout.setPadding(new Insets(15));
                openAppointmentBox2.setAlignment(Pos.CENTER);

                Scene fourthScene = new Scene(fourthLayout,400, 100);
                fourthStage.setTitle("Opening Note");
                fourthStage.setScene(fourthScene);
                fourthStage.show();
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage thirdStage = new Stage();
                DatePicker deleteCalendar = new DatePicker();
                HBox deleteBox = new HBox(20, deleteCalendar, deleteSpinner);
                HBox deleteBox2 = new HBox(20, deleteNote);

                deleteNote.setOnAction( e -> {

                    for (int count = 0; count < calendar.size(); count++)
                    {

                        if(calendar.get(count).equals(deleteCalendar.getValue()) && clock.get(count).equals(changeTime.format(deleteSpinner.getValue())))
                        {
                            calendar.remove(count);
                            clock.remove(count);
                            appointmentNote.remove(count);
                            thirdStage.close();
                            JOptionPane.showMessageDialog(null, "Note has been deleted!");
                            break;

                        }
                    }
                });

                VBox thirdLayout = new VBox(20, deleteBox, deleteBox2);

                thirdLayout.setPadding(new Insets(15));
                deleteBox2.setAlignment(Pos.CENTER);

                Scene thirdScene = new Scene(thirdLayout,400, 120);
                thirdStage.setTitle("Calendering Note");
                thirdStage.setScene(thirdScene);
                thirdStage.show();
            }
        });


        //Clear Date Button
        clear.setOnAction( e -> {

            if(daysOfWeek.getValue().toString().equals("Week Days"))
            {
                JOptionPane.showMessageDialog(null, "Select a weekday to clear!");
            }
            if(daysOfWeek.getValue().toString().equals("Monday"))
            {
                mondayArea.setText(null);
            }
            if(daysOfWeek.getValue().toString().equals("Tuesday"))
            {
                tuesdayArea.setText(null);
            }
            if(daysOfWeek.getValue().toString().equals("Wednesday"))
            {
                wednesdayArea.setText(null);
            }
            if(daysOfWeek.getValue().toString().equals("Thursday"))
            {
                thursdayArea.setText(null);
            }
            if(daysOfWeek.getValue().toString().equals("Friday"))
            {
                fridayArea.setText(null);
            }
        });

        //week
        HBox weekBox = new HBox(20, mondayBox, tuesdayBox, wednesdayBox, thursdayBox, fridayBox);
        weekBox.setPadding(new Insets(15));


        pane = new VBox(20, weekBox, dateBox);
        scene = new Scene(pane, 1100, 500);
        primaryStage.setTitle("Calendar");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public enum WeekTime
    {
        Monday, Tuesday, Wednesday, Thursday, Friday
    }

    public static void main(String[] args) { launch(args); }
}
