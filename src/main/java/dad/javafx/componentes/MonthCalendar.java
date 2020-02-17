package dad.javafx.componentes;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MonthCalendar extends VBox implements Initializable {

	// Model
	private IntegerProperty year = new SimpleIntegerProperty();
	private IntegerProperty month = new SimpleIntegerProperty();
	private ListProperty<Label> listLbl = new SimpleListProperty<Label>();

	@FXML
	private VBox view;

	@FXML
	private Label lblMonth;

	@FXML
	private Label lbl1;

	@FXML
	private Label lbl2;

	@FXML
	private Label lbl3;

	@FXML
	private Label lbl4;

	@FXML
	private Label lbl5;

	@FXML
	private Label lbl6;

	@FXML
	private Label lbl7;

	@FXML
	private Label lbl8;

	@FXML
	private Label lbl9;

	@FXML
	private Label lbl10;

	@FXML
	private Label lbl11;

	@FXML
	private Label lbl12;

	@FXML
	private Label lbl13;

	@FXML
	private Label lbl14;

	@FXML
	private Label lbl15;

	@FXML
	private Label lbl16;

	@FXML
	private Label lbl17;

	@FXML
	private Label lbl18;

	@FXML
	private Label lbl19;

	@FXML
	private Label lbl20;

	@FXML
	private Label lbl21;

	@FXML
	private Label lbl22;

	@FXML
	private Label lbl23;

	@FXML
	private Label lbl24;

	@FXML
	private Label lbl25;

	@FXML
	private Label lbl26;

	@FXML
	private Label lbl27;

	@FXML
	private Label lbl28;

	@FXML
	private Label lbl29;

	@FXML
	private Label lbl30;

	@FXML
	private Label lbl31;

	@FXML
	private Label lbl32;

	@FXML
	private Label lbl33;

	@FXML
	private Label lbl34;

	@FXML
	private Label lbl35;

	@FXML
	private Label lbl36;

	@FXML
	private Label lbl37;

	@FXML
	private Label lbl38;

	@FXML
	private Label lbl39;

	@FXML
	private Label lbl40;

	@FXML
	private Label lbl41;

	@FXML
	private Label lbl42;

	// Others
	private int desface = 1;

	public MonthCalendar() {
		super();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MonthCalendarView.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		year.addListener((o, ov, nv) -> selectMonthAndYear());
		month.addListener((o, ov, nv) -> selectMonthAndYear());

		List<Label> list = new ArrayList<Label>();

		list.add(lbl1);
		list.add(lbl2);
		list.add(lbl3);
		list.add(lbl4);
		list.add(lbl5);
		list.add(lbl6);
		list.add(lbl7);
		list.add(lbl8);
		list.add(lbl9);
		list.add(lbl10);
		list.add(lbl11);
		list.add(lbl12);
		list.add(lbl13);
		list.add(lbl14);
		list.add(lbl15);
		list.add(lbl16);
		list.add(lbl17);
		list.add(lbl18);
		list.add(lbl19);
		list.add(lbl20);
		list.add(lbl21);
		list.add(lbl22);
		list.add(lbl23);
		list.add(lbl24);
		list.add(lbl25);
		list.add(lbl26);
		list.add(lbl27);
		list.add(lbl28);
		list.add(lbl29);
		list.add(lbl30);
		list.add(lbl31);
		list.add(lbl32);
		list.add(lbl33);
		list.add(lbl34);
		list.add(lbl35);
		list.add(lbl36);
		list.add(lbl37);
		list.add(lbl38);
		list.add(lbl39);
		list.add(lbl40);
		list.add(lbl41);
		list.add(lbl42);

		listLbl.set(FXCollections.observableArrayList(list));

	}

	private void selectMonthAndYear() {

		LocalDate localDate = LocalDate.of(year.get(), month.get(), 1);
		int monthNDays = YearMonth.of(year.getValue(), month.getValue()).lengthOfMonth();
		int startDay = localDate.getDayOfWeek().getValue() - desface;

		clean();

		for (int i = startDay, j = 1; j < monthNDays + 1; i++, j++) {
			listLbl.get(i).setText(String.valueOf(j));
		}

		int monthN = month.get();
		if (monthN == 1) {
			lblMonth.setText("Enero");
		} else if (monthN == 2) {
			lblMonth.setText("Febrero");
		} else if (monthN == 3) {
			lblMonth.setText("Marzo");
		} else if (monthN == 4) {
			lblMonth.setText("Abril");
		} else if (monthN == 5) {
			lblMonth.setText("Mayo");
		} else if (monthN == 6) {
			lblMonth.setText("Junio");
		} else if (monthN == 7) {
			lblMonth.setText("Julio");
		} else if (monthN == 8) {
			lblMonth.setText("Agosto");
		} else if (monthN == 9) {
			lblMonth.setText("Septiembre");
		} else if (monthN == 10) {
			lblMonth.setText("Octubre");
		} else if (monthN == 11) {
			lblMonth.setText("Noviembre");
		} else if (monthN == 12) {
			lblMonth.setText("Diciembre");
		}

	}

	private void clean() {
		for (int i = 0; i < listLbl.size(); i++) {
			listLbl.get(i).setText("");
		}

	}

	public IntegerProperty yearProperty() {
		return this.year;
	}

	public int getYearProperty() {
		return this.yearProperty().get();
	}

	public void setYearProperty(final int year) {
		this.yearProperty().set(year);
	}

	public IntegerProperty monthProperty() {
		return this.month;
	}

	public int getMonthProperty() {
		return this.monthProperty().get();
	}

	public void setMonthProperty(final int month) {
		this.monthProperty().set(month);
	}

}
