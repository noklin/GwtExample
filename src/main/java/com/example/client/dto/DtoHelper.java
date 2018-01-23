package com.example.client.dto;

import com.example.shared.User;
import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;


public class DtoHelper {
	private final BeanFactory beanFactory = GWT.create(BeanFactory.class);
	
	public String serializeUser(User user) {
		UserDto userDto = beanFactory.user().as();
		userDto.setAge("" + user.getAge());
		userDto.setName(user.getName());
		userDto.setId("" + user.getId());
		AutoBean<UserDto> userBean = AutoBeanUtils.getAutoBean(userDto) ;
		return AutoBeanCodex.encode(userBean).getPayload();

	}
	public User parseFormModel(String json){
		AutoBean<UserDto> bean = AutoBeanCodex.decode(beanFactory , UserDto.class, json);
		UserDto userDto = bean.as();
		User user = new User();
		user.setAge(Integer.valueOf(userDto.getAge()));
		user.setName(userDto.getName());
		user.setId(Integer.valueOf(userDto.getId()));
		return user;
	}
}