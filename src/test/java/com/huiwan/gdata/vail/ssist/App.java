package com.huiwan.gdata.vail.ssist;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.IntegerMemberValue;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.huiwan.gdata.common.annotatiions.PermissionAnnot;

/**
 * add annotation with javassist
 * Created by outofmemory.cn on 2015/12/14.
 */
public class App {
    public static void main(String[] args) throws CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();

        // create the class
        CtClass cc = pool.makeClass("tempClzValidate");
        ClassFile ccFile = cc.getClassFile();
        ConstPool constpool = ccFile.getConstPool();

        // create the annotation
        AnnotationsAttribute attr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
        Annotation annot = new Annotation("MyAnnotation", constpool);
        annot.addMemberValue("value", new IntegerMemberValue(ccFile.getConstPool(), 0));
        
        attr.addAnnotation(annot);

        // create the method
        CtMethod mthd = CtNewMethod.make("public String getNamex(@Min(2)int driver,@NotNull @Size(min = 2, max = 14) String name ) { return null; }", cc);
        cc.addMethod(mthd);
        mthd.getMethodInfo().addAttribute(attr);
        
        
//        CtField f = new CtField(CtClass.voidType, "i", cc);
//        cc.addField(f);

        //cc.writeFile("./");
        // generate the class
        Class<?> clazz = cc.toClass();
        
        for(Method m:clazz.getMethods()){
        	System.out.println(m.getName());
        	for(java.lang.annotation.Annotation ann:m.getAnnotations()){
        		System.out.println(ann.getClass());
        	}
        }

        // length is zero
//        java.lang.annotation.Annotation[] annots = clazz.getAnnotations();
//        System.out.println(annots);
    }
}