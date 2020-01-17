package dad.javafx.componentes;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class DateChooser extends HBox implements Initializable {
	// model
	private BooleanProperty spanish = new SimpleBooleanProperty();

	private ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<LocalDate>();

	private ArrayList<String> monthList;

	private ChangeListener<String> changeListener;	
	
	
	@FXML
	private ComboBox<Integer> dayCombo;

	@FXML
	private ComboBox<String> monthCombo;

	@FXML
	private ComboBox<String> yearCombo;

	public DateChooser() {
		super();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DateChooserView.fxml"));
			loader.setController(this);
			loader.setRoot(this); // establecer la rootView
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		onChangeLenguaje(spanish.getValue()); //Por defecto se inicializará en ingles
		

		getMonthCombo().getItems().addAll(monthList);
		getMonthCombo().getSelectionModel().selectedItemProperty().addListener(e -> changeMonth());

		getYearCombo().getItems().addAll(getYears());
		getYearCombo().getSelectionModel().selectedItemProperty().addListener(e -> changeMonth());

		changeListener = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				setYearNumber();
			}
		};

		getYearCombo().getEditor().textProperty().addListener(changeListener);
		dateProperty.set(LocalDate.MIN);

		getMonthCombo().getSelectionModel().selectedItemProperty().addListener(e -> onChangeDate(1));

		getDayCombo().getSelectionModel().selectedItemProperty().addListener(e -> onChangeDate(2));

		getYearCombo().getSelectionModel().selectedItemProperty().addListener(e -> onChangeDate(3));

		dateProperty.addListener(e -> setCombos());

		spanish.addListener((o, ov, nv) -> onChangeLenguaje(nv));

	}
	
	/**
	 * Método que detecta si se selecciona el booleando de lenguaje
	 * @param nv
	 */
	public void onChangeLenguaje(Boolean nv) {
		
		try {
			
			if (nv == true) {

				monthList = new ArrayList<String>(Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
						"Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"));

				ObservableList<String> obs = FXCollections.observableArrayList(monthList);

				monthCombo.setItems(obs);

			} else {

				monthList = new ArrayList<String>(Arrays.asList("January", "February", "March", "April", "May", "June",
						"July", "August", "September", "October", "November", "December"));

				ObservableList<String> obs = FXCollections.observableArrayList(monthList);

				monthCombo.setItems(obs);

			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}


	}
	
	/**
	 * Establece la fecha en los comboBox
	 */
	public void setCombos() {

		

			int month = dateProperty.get().getMonthValue();
			int day = dateProperty.get().getDayOfMonth();
			int year = dateProperty.get().getYear();
			
			getYearCombo().getSelectionModel().select(year+"");
			getMonthCombo().getSelectionModel().select(month-1);
			getDayCombo().getSelectionModel().select(day-1);

		

	}
	
	
	/**
	 * Listener que detectaCambios en la fecha recibe como parametro un entero que indica el combo 
	 * donde se han producido las modificaciones
	 * @param cod
	 */
	private void onChangeDate(int cod) {
		
		try {
			
			switch (cod) {

			case 1:
				int month = getMonthCombo().getSelectionModel().getSelectedIndex() + 1;
				dateProperty.set(dateProperty.get().withMonth(month));

				break;

			case 2:
				try {
					int day = getDayCombo().getSelectionModel().getSelectedItem();
					dateProperty.set(dateProperty.get().withDayOfMonth(day));

				} catch (NullPointerException e) {

				}
				break;

			case 3:
				int year = Integer.parseInt(getYearCombo().getSelectionModel().getSelectedItem());
				dateProperty.set(dateProperty.get().withYear(year));

				break;
			}
			
			
		} catch (Exception e) {
			
		}

		
	}
	
	/**
	 * Setea un valor númerico del año en el combo, cambiando el número de días con respexto al mes
	 */
	private void setYearNumber() {

		StringBuilder text = new StringBuilder(getYearCombo().getEditor().textProperty().get());

		if (text.length() > 0) {
			for (int i = 0; i < text.length(); i++) {
				if (!Character.isDigit(text.charAt(i))) {
					text.deleteCharAt(i);
				}
			}
			getYearCombo().getEditor().textProperty().removeListener(changeListener);
			getYearCombo().getEditor().textProperty().set(text.toString());
			getYearCombo().getEditor().textProperty().addListener(changeListener);
		}

	}

	
	/**
	 * Devuelve una lista de años para ser usda por el combo
	 * @return
	 */
	private ArrayList<String> getYears() {
		ArrayList<String> yearList = new ArrayList<>();

		int year = Year.now().getValue();

		for (int i = 0; i < 10; i++) {
			yearList.add(year + "");
			year++;
		}

		return yearList;
	}

	
	/**
	 * Detecta cambios en los meses
	 */
	private void changeMonth() {

		int month = getMonthCombo().getSelectionModel().getSelectedIndex();
		getDayCombo().getItems().clear();

		String year = "";

		if (getYearCombo().getSelectionModel().getSelectedItem() != null) {
			year = getYearCombo().getSelectionModel().getSelectedItem();
		}

		if (month == 1) {

			if (year == "" || !Year.of(Integer.parseInt(year)).isLeap()) {
				for (int i = 1; i < 29; i++) {
					getDayCombo().getItems().add(i);
				}
			} else {

				for (int i = 1; i <= 29; i++) {
					getDayCombo().getItems().add(i);
				}
			}

		} else if (month == 0 | month == 2 | month == 4 | month == 6 | month == 7 | month == 9 | month == 11) {

			for (int i = 1; i <= 31; i++) {
				getDayCombo().getItems().add(i);
			}

		} else {

			for (int i = 1; i < 31; i++) {
				getDayCombo().getItems().add(i);
			}
		}

	}

	public final ObjectProperty<LocalDate> datePropertyProperty() {
		return this.dateProperty;
	}

	public final LocalDate getDateProperty() {
		return this.datePropertyProperty().get();
	}

	public final void setDateProperty(final LocalDate dateProperty) {
		this.datePropertyProperty().set(dateProperty);
	}

	public ComboBox<String> getMonthCombo() {
		return monthCombo;
	}

	public void setMonthCombo(ComboBox<String> monthCombo) {
		this.monthCombo = monthCombo;
	}

	public ComboBox<Integer> getDayCombo() {
		return dayCombo;
	}

	public void setDayCombo(ComboBox<Integer> dayCombo) {
		this.dayCombo = dayCombo;
	}

	public ComboBox<String> getYearCombo() {
		return yearCombo;
	}

	public void setYearCombo(ComboBox<String> yearCombo) {
		this.yearCombo = yearCombo;
	}

	public final BooleanProperty spanishProperty() {
		return this.spanish;
	}

	public final boolean isSpanish() {
		return this.spanishProperty().get();
	}

	public final void setSpanish(final boolean spanish) {
		this.spanishProperty().set(spanish);
	}




	
	
	
	
}
