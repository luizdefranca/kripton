package com.abubusoft.kripton.processor.sqlite;

import javax.lang.model.element.Element;

/**
 * Attribute name used in annotations. Introduced to avoid to type name attribute as string
 * 
 * @author xcesco
 *
 *
 * @since 05/mag/2016
 */
public enum AnnotationAttributeType {

	ATTRIBUTE_VALUE("value"),
	ATTRIBUTE_EXCLUDED_FIELDS("excludedFields"),	
	ATTRIBUTE_DISTINCT("distinct"),
	ATTRIBUTE_WHERE("where"),	
	ATTRIBUTE_HAVING("having"),
	ATTRIBUTE_GROUP_BY("groupBy"),
	ATTRIBUTE_ORDER_BY("orderBy");
	
	private String value;
	
	public String getValue()
	{
		return value;
	}

	private AnnotationAttributeType(String value)
	{
		this.value=value;
	}
	
	public boolean isEquals(Element element)
	{
		return value.equals(element.getSimpleName().toString());
	}
}
