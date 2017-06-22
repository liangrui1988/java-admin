package com.huiwan.gdata.vail.ssist;

import org.apache.xmlbeans.impl.piccolo.xml.EntityManager;
import org.hibernate.validator.constraints.Length;

public class CollectionBase{  
    /** 
     * 注入实体单元 
     */  
	@Length(min = 2, max = 6)
    protected EntityManager em;  
    /**EntityManger 
     * 实例化 
     */  
    protected EntityManager getEntityManager() {          
        return this.em;  
    }  
}  