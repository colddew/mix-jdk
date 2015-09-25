package edu.ustc.mix.miscellaneous.annotation;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

// 使用javac -parameters XXX.java编译源码，可以通过反射获取方法参数的名称
@SupportedAnnotationTypes({ "TestCase", "TestCases" })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TestCaseProcessor extends AbstractProcessor {
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		
		for (TypeElement elements : annotations) {
			for (Element element : roundEnv.getElementsAnnotatedWith(elements)) {
				System.out.println(element + " " + elements);
			}
		}
		
		return true;
	}
}
