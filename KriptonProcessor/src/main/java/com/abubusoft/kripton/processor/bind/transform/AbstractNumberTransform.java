/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.common.BigDecimalUtil;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

abstract class AbstractNumberTransform extends AbstractBindTransform {

	protected Class<?> NUMBER_UTIL_CLAZZ;

	public AbstractNumberTransform() {
		NUMBER_UTIL_CLAZZ = BigDecimalUtil.class;
	}

	@Override
	public void generateParseOnJackson(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}
		
		methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($L.getText())"),  NUMBER_UTIL_CLAZZ, parserName);

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}

	}

	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}
		
		methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($L.getText())"), NUMBER_UTIL_CLAZZ, parserName);

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;

		switch (xmlType) {
		case ATTRIBUTE:
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($T.unescapeXml($L.read(attributeValue)))"), NUMBER_UTIL_CLAZZ, StringEscapeUtils.class, parserName);
			break;
		case TAG:
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($T.unescapeXml($L.getElementText()))"), NUMBER_UTIL_CLAZZ, StringEscapeUtils.class, parserName);
			break;
		case VALUE:
		case VALUE_CDATA:
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($T.unescapeXml($L.getText()))"), NUMBER_UTIL_CLAZZ, StringEscapeUtils.class, parserName);
			break;
		default:
			break;
		}

	}

	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		if (property.isElementInCollection()) {
			methodBuilder.addStatement("$L.writeString($T.write($L))", serializerName, NUMBER_UTIL_CLAZZ, getter(beanName, beanClass, property));
		} else {
			methodBuilder.addStatement("$L.writeStringField($S, $T.write($L))", serializerName, property.jacksonName, NUMBER_UTIL_CLAZZ, getter(beanName, beanClass, property));
		}

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		if (property.isElementInCollection()) {
			// we need to write only value
			methodBuilder.addStatement("$L.writeString($T.write($L))", serializerName, NUMBER_UTIL_CLAZZ, getter(beanName, beanClass, property));
		} else {
			methodBuilder.addStatement("$L.writeStringField($S, $T.write($L))", serializerName, property.jacksonName, NUMBER_UTIL_CLAZZ, getter(beanName, beanClass, property));
		}

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;

		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		switch (xmlType) {
		case ATTRIBUTE:
			methodBuilder.addStatement("$L.writeAttribute($S, $T.escapeXml10($T.write($L)))", serializerName, property.xmlInfo.tag, StringEscapeUtils.class, NUMBER_UTIL_CLAZZ, getter(beanName, beanClass, property));
			break;
		case TAG:
			methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
			methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($T.write($L)))", serializerName, StringEscapeUtils.class, NUMBER_UTIL_CLAZZ, getter(beanName, beanClass, property));
			methodBuilder.addStatement("$L.writeEndElement()", serializerName);
			break;
		case VALUE:
			methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($T.write($L)))", serializerName, StringEscapeUtils.class, NUMBER_UTIL_CLAZZ, getter(beanName, beanClass, property));
			break;
		case VALUE_CDATA:
			methodBuilder.addStatement("$L.writeCData($T.escapeXml10($T.write($L)))", serializerName, StringEscapeUtils.class, NUMBER_UTIL_CLAZZ, getter(beanName, beanClass, property));
			break;
		}

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}

	}
}