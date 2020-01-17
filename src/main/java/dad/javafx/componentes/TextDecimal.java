package dad.javafx.componentes;


import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;



public class TextDecimal extends HBox implements Initializable{
	
	//Model
	private DoubleProperty decimal = new SimpleDoubleProperty(); 
	
	//View
	@FXML
	private TextField txtNumber;

	
	
	public TextDecimal() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TextDecimalView.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Bindings.bindBidirectional(txtNumber.textProperty(), decimal, new NumberStringConverter());
		
		textFieldFormmater(txtNumber);
				
	}
	
	/**
	* Metodo tipo TextFormater  
	* se encarga de que solo se puedan introducir
	* valores de coma flotante en los textfield
	* @param TextField txt
	*/ 
	
	public void textFieldFormmater(TextField txt) {
		DecimalFormat format = new DecimalFormat("#.0");
		txtNumber.setTextFormatter(new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty()) {
				return c;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		}));
	}

	public final DoubleProperty decimalProperty() {
		return this.decimal;
	}
	

	public final double getDecimal() {
		return this.decimalProperty().get();
	}
	

	public final void setDecimal(final double decimal) {
		this.decimalProperty().set(decimal);
	}
	
	

}
