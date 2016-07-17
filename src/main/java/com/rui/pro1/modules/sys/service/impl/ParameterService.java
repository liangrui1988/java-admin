package com.rui.pro1.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rui.pro1.modules.sys.mapper.ParameterMapper;
import com.rui.pro1.modules.sys.service.IParameterService;
@Service
public class ParameterService implements IParameterService {

	@Autowired
	private ParameterMapper ParameterMapper;
}
