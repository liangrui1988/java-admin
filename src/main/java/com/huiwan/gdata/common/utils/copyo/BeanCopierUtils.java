package com.huiwan.gdata.common.utils.copyo;

import java.util.Map;
import java.util.WeakHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

public class BeanCopierUtils {
    // public static Map<String,BeanCopier> beanCopierMap = new HashMap<String,BeanCopier>();
	protected static Logger logger = LoggerFactory.getLogger(BeanCopierUtils.class);

     
     public static Map<String,BeanCopier> beanCopierMap = new WeakHashMap<String,BeanCopier>();

     public static void copyProperties(Object source, Object target){
    	 if(source==null||target==null){
        	 logger.error("copyProperties失败！ source或target为null");
    		 return;
    	 }
         String beanKey =  generateKey(source.getClass(), target.getClass());
         BeanCopier copier =  null;
         if(!beanCopierMap.containsKey(beanKey)){
              copier = BeanCopier.create(source.getClass(), target.getClass(), false);
              beanCopierMap.put(beanKey, copier);
         }else{
              copier = beanCopierMap.get(beanKey);
         }
         copier.copy(source, target, null);
     }   
     private static String generateKey(Class<?> class1,Class<?>class2){
         return class1.toString() + class2.toString();
     }
}