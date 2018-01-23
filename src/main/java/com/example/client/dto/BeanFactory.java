package com.example.client.dto;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;


public interface BeanFactory extends AutoBeanFactory{
	AutoBean<UserDto> user();
}
