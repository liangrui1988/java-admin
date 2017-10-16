package com.huiwan.gdata.modules.sys.web.converter.vail;

//import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.internal.constraintvalidators.bv.MaxValidatorForNumber;
import org.hibernate.validator.internal.constraintvalidators.bv.MinValidatorForNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huiwan.gdata.modules.sys.constants.SysComm;

@Deprecated
public class MaxVailResolverBack implements HandlerMethodArgumentResolver {
	private ObjectMapper objectMapper = new ObjectMapper().configure(
			DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	@Autowired
	protected Validator validator;
	
	
	ConstraintValidator<Max, Number> maxValidatorForNumber=new MaxValidatorForNumber();

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(Max.class))
			return true;
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		final HttpServletRequest httpServletRequest = webRequest
				.getNativeRequest(HttpServletRequest.class);
		Map<String, String[]> map = httpServletRequest.getParameterMap();

		String s = objectMapper.writeValueAsString(map);
		System.out.println(s);
		
		String pName = parameter.getParameterName();
		
		// 做成对象才能 到vaildation 这是个难题?
		String[] obj = (String[]) map.get(pName);


			//模拟异常
//			Set<ConstraintViolation<FalseBean>> constraintViolations = validator
//					.validateValue(FalseBean.class, "maxF", Long.MAX_VALUE);
//			if (!constraintViolations.isEmpty()) {
//				throw new ConstraintViolationException(constraintViolations);
//			}
		//}
		
		
//		 ClassPool pool = ClassPool.getDefault();  
//		 //获取需要修改的类  
//        CtClass ct = pool.get("com.huiwan.gdata.modules.sys.web.converter.vail.FalseBean.FalseBean");   
//		try {
//			
//		        //获取类里的maxF属性  
//		        CtField cf = ct.getField("maxF");  
//		        FieldInfo fieldInfo = cf.getFieldInfo();
//		        ConstPool cp = fieldInfo.getConstPool();  
//		        AnnotationsAttribute attribute2 = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag); 
//		        //修改验证注解 
//		        javassist.bytecode.annotation.Annotation annotation = 
//		        		new javassist.bytecode.annotation.Annotation("javax.validation.constraints.Max", cp);
//				Max max=parameter.getParameterAnnotation(Max.class);
//				max.value();
//			     //修改名称为 value的注解  
//		        annotation.addMemberValue("value", new  LongMemberValue(max.value(), cp));
//		        attribute2.addAnnotation(annotation);
//		        fieldInfo.addAttribute(attribute2);
//		        
//		        
//		        Class clz=ct.toClass();
//		//        Object o=clz.newInstance();
//		        
//		        //验证修改后的对象
//		        @SuppressWarnings("unchecked")
//				Set<ConstraintViolation<FalseBean>> constraintViolations = validator
//						.validateValue(clz, "maxF", Long.valueOf((String) obj[0]));
//				if (!constraintViolations.isEmpty()) {
//					throw new ConstraintViolationException(constraintViolations);
//				}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			ct.defrost(); 
//			//ct.detach();
//		}
		    //获取参数值
			Class clz=parameter.getParameterType();
			String pType=clz.toString();
			System.out.println(pType);
			Object returnObj =	VailResolverUtils.getType(pType,obj[0]);
			
			//获得注解 
			Max max=parameter.getParameterAnnotation(Max.class);
			long annotV=max.value();
			
			boolean hasError=false;
			Map<String,String> mapMess=new HashMap<String,String>();
			//直接不通过
			if(obj==null||obj.length<=0||obj[0]==null){
				hasError=true;
			}
			if(!hasError){
				//比较注解上面的值
				long paramValue=Long.valueOf(returnObj+"");
				if(paramValue>annotV){
					hasError=true;
				}
			}
			//验证不通过
			if(hasError){
//				httpServletRequest.setAttribute(SysComm.VAIL_ERROR_MESSAGE, "error");
				Object message= httpServletRequest.getAttribute(SysComm.VAIL_ERROR_MESSAGE);
				if(message!=null){
					mapMess=(Map<String, String>) message;
				}
				mapMess.put(pName, pName+":不能大于"+max.value());
				httpServletRequest.setAttribute(SysComm.VAIL_ERROR_MESSAGE, mapMess);//pName+":不能大于"+max.value()
			}
		return returnObj;

	}

}
