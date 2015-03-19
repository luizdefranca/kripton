package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.binder.schema.MapEntryStrategyType;

/**
 * 
 * This annotation specify information to bind with xml format
 * 
 * @author xcesco
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindXml {

	/**
	 * Type of binding. Default is by TAG. See {@link BindXmlType}
	 * 
	 * @return Type of binding. Default is by TAG.
	 */
	public BindXmlType type() default BindXmlType.TAG;

	/**
	 * Type of mapping of element of a map. <strong>Valid only for
	 * maps</strong>. If used with a field who does not implement map interface,
	 * an runtime exception will be thrown. See {@link MapEntryStrategyType}.
	 * 
	 * @return Type of mapping of element of a map
	 */
	public MapEntryStrategyType mapEntryStrategy() default MapEntryStrategyType.ELEMENTS;

}