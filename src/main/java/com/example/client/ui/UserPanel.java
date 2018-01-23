package com.example.client.ui;
 
 
import java.util.List;

import com.example.client.dto.DtoHelper;
import com.example.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;


public class UserPanel extends Composite{

	@UiField
	HTMLPanel root;
	@UiField
	Button findBtn;
	@UiField
	Button findAllBtn;
	@UiField
	InputElement searchIdElement;
	
	interface UserHomeUiBinder extends UiBinder<Widget, UserPanel> {}
	private static UserHomeUiBinder binder = GWT.create(UserHomeUiBinder.class);
	
	public UserPanel(){
		initWidget(binder.createAndBindUi(this));
		initListaners();
	}
	

	private void initListaners() {
		findBtn.addClickHandler(event ->{
			String id = searchIdElement.getValue();
			findUser(id);
		});
		findAllBtn.addClickHandler(e -> findAllUser());
	}

	private DtoHelper dtoHelper = new DtoHelper();
	
	private void findUser(String id){ 
		if(id == null || id.isEmpty()){
			return;
		}
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "rest/api/user/" + id);
		try{
			builder.sendRequest(null, new RequestCallback() {
				@Override
				public void onResponseReceived(Request request, Response response) {
					if(response.getStatusCode() == 404){
						searchIdElement.setValue("not found");
					}else{
						User user = dtoHelper.parseFormModel(response.getText());
						root.add(new UserComponent(user));
					}
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
				}
			});
		}catch(RequestException ex){
			ex.printStackTrace();
		}
	}

	private List<User> findAllUser(){
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "rest/api/users");
		try{
			builder.sendRequest(null, new RequestCallback() {
				@Override
				public void onResponseReceived(Request request, Response response) {
					JSONObject users = JSONParser.parseStrict(response.getText()).isObject();
					if(users == null){
						return;
					}
					JSONArray arr = users.get("user").isArray();
					if(arr == null){
						return;
					}
					for(int i = 0 ; i < arr.size(); i++){
						JSONObject obj = arr.get(i).isObject();
						User user = dtoHelper.parseFormModel(obj.toString());
						root.add(new UserComponent(user));
					}
				}
				@Override
				public void onError(Request request, Throwable exception) {
				
				}
			});
		}catch(RequestException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
}