package com.example.client.ui;

import com.example.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserComponent extends Composite{

	@UiField
	HTMLPanel rootPanel;
	@UiField
	InputElement id;
	@UiField
	InputElement name;
	@UiField
	InputElement age;
	@UiField
	Button hideBtn;
	
	interface UserComponentUiBinder extends UiBinder<Widget, UserComponent> {}
	private static UserComponentUiBinder binder = GWT.create(UserComponentUiBinder.class);
	
	
	public UserComponent(User user){
		initWidget(binder.createAndBindUi(this));
		id.setValue(String.valueOf(user.getId()));
		name.setValue(user.getName());
		age.setValue(String.valueOf(user.getAge()));
		initBtnListeners();
	}

	private void initBtnListeners() {
		hideBtn.addClickHandler(e -> removeFromParent());
	}
	
}