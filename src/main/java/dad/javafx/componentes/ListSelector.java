package dad.javafx.componentes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;

public class ListSelector<T> extends GridPane implements Initializable {
	// model
	private ListProperty<T> left = new SimpleListProperty<>(
			FXCollections.observableArrayList(/* new ArrayList<T>() */));
	private ListProperty<T> right = new SimpleListProperty<>(FXCollections.observableArrayList());
	private StringProperty leftTitle = new SimpleStringProperty();
	private StringProperty rightTitle = new SimpleStringProperty();
	// view

	@FXML
	private Label leftLabel;

	@FXML
	private Label rightLabel;

	@FXML
	private ListView<T> leftList;

	@FXML
	private Button moveToRightButton;

	@FXML
	private Button moveAllToRightButton;

	@FXML
	private Button moveToLeftButton;

	@FXML
	private Button moveAllToLeftButton;

	@FXML
	private ListView<T> rightList;

	public ListSelector() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListSelectorView.fxml"));
			loader.setController(this);
			loader.setRoot(this); // establecer la rootView
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		leftList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		rightList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		leftList.itemsProperty().bind(left);
		rightList.itemsProperty().bind(right);

		leftLabel.textProperty().bind(leftTitle);
		rightLabel.textProperty().bind(rightTitle);

	}

	@FXML
	void moveAllToLeftAction(ActionEvent event) {
		left.addAll(right);
		right.clear();
	}

	@FXML
	void moveAllToRightAction(ActionEvent event) {
		right.addAll(left);
		left.clear();
	}

	@FXML
	void moveToLeftAction(ActionEvent event) {
		left.addAll(rightList.getSelectionModel().getSelectedItems());
		right.removeAll(rightList.getSelectionModel().getSelectedItems());
	}

	@FXML
	void moveToRightAction(ActionEvent event) {
		right.addAll(leftList.getSelectionModel().getSelectedItems());
		left.removeAll(leftList.getSelectionModel().getSelectedItems());
	}

	public final ListProperty<T> leftProperty() {
		return this.left;
	}

	public final ObservableList<T> getLeft() {
		return this.leftProperty().get();
	}

	public final void setLeft(final ObservableList<T> left) {
		this.leftProperty().set(left);
	}

	public final ListProperty<T> rightProperty() {
		return this.right;
	}

	public final ObservableList<T> getRight() {
		return this.rightProperty().get();
	}

	public final void setRight(final ObservableList<T> right) {
		this.rightProperty().set(right);
	}

	public final StringProperty leftTitleProperty() {
		return this.leftTitle;
	}

	public final String getLeftTitle() {
		return this.leftTitleProperty().get();
	}

	public final void setLeftTitle(final String leftTitle) {
		this.leftTitleProperty().set(leftTitle);
	}

	public final StringProperty rightTitleProperty() {
		return this.rightTitle;
	}

	public final String getRightTitle() {
		return this.rightTitleProperty().get();
	}

	public final void setRightTitle(final String rightTitle) {
		this.rightTitleProperty().set(rightTitle);
	}
}
