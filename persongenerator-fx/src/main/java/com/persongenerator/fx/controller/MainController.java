package com.persongenerator.fx.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import com.egoday.persongenerator.model.Gender;
import com.egoday.persongenerator.model.Person;
import com.egoday.persongenerator.service.PersonGenerator;
import com.persongenerator.fx.controller.util.GenderImage;
import com.persongenerator.fx.controller.util.Pref;
import com.persongenerator.fx.controller.util.PrefUtil;

public class MainController implements Initializable {
	
	@FXML private Label messageLabel;
    @FXML private Button nextButton;
    @FXML private TabPane tabPane;
    @FXML private Tab generationTab;
    @FXML private Tab generatedTab;
    @FXML @Pref private ChoiceBox<Gender> genderChoiceBox;
    @FXML @Pref private TextField dni1TextField;
    @FXML @Pref private TextField dni2TextField;
    @FXML @Pref private TextField dni3TextField;
    @FXML @Pref private TextField dni4TextField;
    @FXML @Pref private TextField dni5TextField;
    @FXML @Pref private TextField dni6TextField;
    @FXML @Pref private TextField dni7TextField;
    @FXML @Pref private TextField dni8TextField;
    @FXML @Pref private Slider nameSlider;
    @FXML @Pref private Slider appsSlider;
    @FXML private ImageView imageView;
    @FXML private TextField nameTextField;
    @FXML private TextField dniTextField;
    
    private PersonGenerator personGenerator = new PersonGenerator();
    private Person person;
    
    @FXML protected void onNext(ActionEvent event) {
    	new ViewAction(messageLabel.textProperty()) {

			@Override
			public void action() {
		    	PrefUtil.save(MainController.this);

				Gender gender = genderChoiceBox.getSelectionModel().getSelectedItem();
				double namesPercentage = nameSlider.getValue();
				double appsPercentage = appsSlider.getValue();
				List<String> dniPattern = dniPattern();

				person = personGenerator.nextPerson(
						gender,
						namesPercentage,
						appsPercentage,
						dniPattern);
				
				nameTextField.setText(person.fullName());
				dniTextField.setText(person.getDni());
				imageView.setImage(GenderImage.fromGender(person.getGender()));

				selectTab(generatedTab);
			}
    	}.doIt();
    }

    @FXML protected void onCopy(ActionEvent event) {
    	new ViewAction(messageLabel.textProperty()) {
    		
    		@Override
    		public String successMsg() { return "Ok"; }

			@Override
			public void action() {
		    	if (person != null) {
		        	final Clipboard clipboard = Clipboard.getSystemClipboard();
		            final ClipboardContent content = new ClipboardContent();
		            content.putString(person.toString());
		            clipboard.setContent(content);
		            
		            selectTab(generatedTab);
		    	}
			}
    		
    	}.doIt();
    }

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		selectTab(generatedTab);

    	genderChoiceBox.setItems(genders());
    	genderChoiceBox.setConverter(new GenderConverter());
    	
    	PrefUtil.load(this);
    	
		for (TextField field : dniFields()) {
			field.textProperty().addListener(new DniTextFieldListener(field));
		}
	}
	
	private void selectTab(Tab tab) {
    	SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
    	selectionModel.select(tab);
	}
	
	private ObservableList<Gender> genders() {
		List<Gender> genders = new ArrayList<Gender>();
		genders.add(null);
		genders.addAll(Arrays.asList(Gender.values()));

		return FXCollections.observableList(genders);
	}
	
	private List<TextField> dniFields() {
		return Arrays.asList(dni1TextField,
				dni2TextField, dni3TextField, dni4TextField,
				dni5TextField, dni6TextField, dni7TextField,
				dni8TextField);
	}
	
	private List<String> dniPattern() {
		List<String> dniPattern = new ArrayList<String>();
		
		for (TextField field : dniFields())
			dniPattern.add(field.getText());

		return dniPattern;
	}
}