package com.abubusoft.kripton.processor.sqlite;

import java.util.Date;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelElementVisitor;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

/**
 * @author xcesco
 *
 */
public class TableGenerator extends AbstractBuilder implements ModelElementVisitor<SQLEntity, SQLProperty> {

	public static final String SUFFIX = "Table";

	private Converter<String, String> columnNameConverter;

	public TableGenerator(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}

	/**
	 * Generate table for entities
	 * 
	 * @param elementUtils
	 * @param filer
	 * @param model
	 * @throws Exception
	 */
	public static void generate(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) throws Exception {
		TableGenerator visitor = new TableGenerator(elementUtils, filer, model);

		for (SQLEntity item : model.getEntities()) {
			visitor.visit(item);
		}
	}

	@Override
	public void visit(SQLEntity kriptonClass) throws Exception {
		SQLEntity entity=kriptonClass;
				
		String classTableName =  entity.getSimpleName() + SUFFIX;		

		PackageElement pkg = elementUtils.getPackageOf(entity.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		
		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, packageName, classTableName);
		builder = TypeSpec.classBuilder(classTableName).addModifiers(Modifier.PUBLIC);
		// javadoc
		builder.addJavadoc("Generated by Kripton Library.\n\n @since $L\n\n", (new Date()).toString()); 

		columnNameConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);

		entity.buildTableName(elementUtils, model);
		{
			// table_name
			FieldSpec fieldSpec = FieldSpec.builder(String.class, "TABLE_NAME", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).initializer("\"$L\"", entity.getTableName()).build();
			builder.addField(fieldSpec);
		}
		
		{
			// create table SQL
			// "CREATE TABLE IF NOT EXISTS TutorialsPoint(Username VARCHAR,Password VARCHAR);"
			FieldSpec.Builder fieldSpec = FieldSpec.builder(String.class, "CREATE_TABLE_SQL").addModifiers(Modifier.STATIC, Modifier.FINAL, Modifier.PUBLIC);			
			
			StringBuilder buffer=new StringBuilder();
			buffer.append("CREATE TABLE "+entity.getTableName());
			// define column name set
			
			String separator=" ";
			// primary key can be column id or that marked as PRIMARY_KEY
			ModelProperty primaryKey = entity.getPrimaryKey();
			ModelAnnotation annotation;
			
			buffer.append("(");
			for (ModelProperty item : entity.getCollection()) {
				buffer.append(separator);
				buffer.append(model.classNameConverter.convert(item.getName()));
				buffer.append(" "+Transformer.columnType(item));
				
				annotation=item.getAnnotation(BindColumn.class);
				
				if (annotation!=null)
				{
					ColumnType columnType=ColumnType.valueOf(AnnotationUtility.extractAsEnumerationValue(elementUtils, item, annotation, AnnotationAttributeType.ATTRIBUTE_VALUE));
					switch(columnType)
					{
					case PRIMARY_KEY:
						buffer.append(" PRIMARY KEY AUTOINCREMENT");
						break;
					case UNIQUE:
						buffer.append(" UNIQUE");
						break;
					case STANDARD:
						break;
					case FOREIGN_KEY:
						break;
					}
					
					boolean nullable=Boolean.valueOf(AnnotationUtility.extractAsEnumerationValue(elementUtils, item, annotation, AnnotationAttributeType.ATTRIBUTE_NULLABLE));				
					if (!nullable)
					{
						buffer.append(" NOT NULL");
					}
				} else if (primaryKey.equals(item)) {
					buffer.append(" PRIMARY KEY AUTOINCREMENT");
				}
				
				separator=", ";
			}
			buffer.append(")");
			buffer.append(";");
			builder.addField(fieldSpec.initializer("$S",buffer.toString()).build());						
		}
		
		{			
			// drop table SQL
			// "CREATE TABLE IF NOT EXISTS TutorialsPoint(Username VARCHAR,Password VARCHAR);"
			String sql="DROP TABLE IF EXISTS "+entity.getTableName()+";";
			
			FieldSpec fieldSpec = FieldSpec.builder(String.class, "DROP_TABLE_SQL").addModifiers(Modifier.STATIC, Modifier.FINAL, Modifier.PUBLIC).initializer("$S",sql).build();
			builder.addField(fieldSpec);
		}

		// define column name set
		for (ModelProperty item : kriptonClass.getCollection()) {
			item.accept(this);
		}

		TypeSpec typeSpec = builder.build();
		//BindDatabaseProcessor.info("WRITE "+typeSpec.name);		
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
	}

	@Override
	public void visit(SQLProperty kriptonProperty) {
		FieldSpec fieldSpec = FieldSpec.builder(String.class, "COLUMN_" + columnNameConverter.convert(kriptonProperty.getName()), Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
				.initializer("\"$L\"", model.columnNameConverter.convert(kriptonProperty.getName())).build();

		builder.addField(fieldSpec);
	}


}
