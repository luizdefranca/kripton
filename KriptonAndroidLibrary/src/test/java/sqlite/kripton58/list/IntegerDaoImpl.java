package sqlite.kripton58.list;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>IntegerBean</code>, based on interface <code>IntegerDao</code>
 * </p>
 *
 *  @see IntegerBean
 *  @see IntegerDao
 *  @see IntegerBeanTable
 */
public class IntegerDaoImpl extends AbstractDao implements IntegerDao {
  public IntegerDaoImpl(BindIntegerDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value2 FROM integer_bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public IntegerBean selectOne() {
    // build where condition
    String[] _args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value2 FROM integer_bean",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM integer_bean", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      IntegerBean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value2");

        resultBean=new IntegerBean();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.value=IntegerBeanTable.parseValue(cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.value2=IntegerBeanTable.parseValue2(cursor.getBlob(index2)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value2 FROM integer_bean WHERE value=${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${value}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public IntegerBean selectOne(List<Integer> value) {
    // build where condition
    String[] _args={(value==null?"":new String(serializer1(value),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value2 FROM integer_bean WHERE value='%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM integer_bean WHERE value=?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      IntegerBean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value2");

        resultBean=new IntegerBean();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.value=IntegerBeanTable.parseValue(cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.value2=IntegerBeanTable.parseValue2(cursor.getBlob(index2)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value2 FROM integer_bean WHERE value=${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${value}</code>
   * @param listener
   * 	is the IntegerBean listener
   */
  @Override
  public void selectOne(List<Integer> value, OnReadBeanListener<IntegerBean> listener) {
    // build where condition
    String[] _args={(value==null?"":new String(serializer1(value),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value2 FROM integer_bean WHERE value='%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM integer_bean WHERE value=?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      IntegerBean resultBean=new IntegerBean();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value2");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.id=0L;
          resultBean.value=null;
          resultBean.value2=null;

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.value=IntegerBeanTable.parseValue(cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.value2=IntegerBeanTable.parseValue2(cursor.getBlob(index2)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value2 FROM integer_bean WHERE value=${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>no bean's property is associated</dd>
   * 	<dt>value</dt><dd>no bean's property is associated</dd>
   * 	<dt>value2</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${value}</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(List<Integer> value, OnReadCursorListener listener) {
    // build where condition
    String[] _args={(value==null?"":new String(serializer1(value),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value2 FROM integer_bean WHERE value='%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM integer_bean WHERE value=?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value2 FROM integer_bean WHERE value=${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${value}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<IntegerBean> selectList(List<Integer> value) {
    // build where condition
    String[] _args={(value==null?"":new String(serializer1(value),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value2 FROM integer_bean WHERE value='%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM integer_bean WHERE value=?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<IntegerBean> resultList=new LinkedList<IntegerBean>();
      IntegerBean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value2");

        do
         {
          resultBean=new IntegerBean();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.value=IntegerBeanTable.parseValue(cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.value2=IntegerBeanTable.parseValue2(cursor.getBlob(index2)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE integer_bean SET value=${value} WHERE id=${id} and value=${paramValue}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * 	<dt>${paramValue}</dt><dd>is mapped to method's parameter <strong>paramValue</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as updated field <strong>value</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param paramValue
   * 	is used as where parameter <strong>${paramValue}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(List<Integer> value, long id, List<Integer> paramValue) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (value!=null) {
      contentValues.put("value", serializer1(value));
    } else {
      contentValues.putNull("value");
    }

    String[] whereConditionsArray={String.valueOf(id), (paramValue==null?"":new String(serializer1(paramValue),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE integer_bean SET value='"+StringUtils.checkSize(contentValues.get("value"))+"' WHERE id=%s and value=%s", (Object[])whereConditionsArray));
    int result = database().update("integer_bean", contentValues, "id=? and value=?", whereConditionsArray);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO integer_bean (id, value) VALUES (${id}, ${value})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to column <strong>id</strong>
   * @param value
   * 	is binded to column <strong>value</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(long id, List<Integer> value) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);

    if (value!=null) {
      contentValues.put("value", serializer1(value));
    } else {
      contentValues.putNull("value");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO integer_bean (id, value) VALUES ('"+StringUtils.checkSize(contentValues.get("id"))+"', '"+StringUtils.checkSize(contentValues.get("value"))+"')"));
    long result = database().insert("integer_bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO integer_bean (value, value2) VALUES (${bean.value}, ${bean.value2})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * 	<dt>value2</dt><dd>is mapped to <strong>${bean.value2}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(IntegerBean bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.value!=null) {
      contentValues.put("value", IntegerBeanTable.serializeValue(bean.value));
    } else {
      contentValues.putNull("value");
    }

    if (bean.value2!=null) {
      contentValues.put("value2", IntegerBeanTable.serializeValue2(bean.value2));
    } else {
      contentValues.putNull("value2");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO integer_bean (value, value2) VALUES ('"+StringUtils.checkSize(contentValues.get("value"))+"', '"+StringUtils.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("integer_bean", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE integer_bean WHERE value=${paramValue}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${paramValue}</dt><dd>is mapped to method's parameter <strong>paramValue</strong></dd>
   * </dl>
   *
   * @param paramValue
   * 	is used as where parameter <strong>${paramValue}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(List<Integer> paramValue) {
    String[] whereConditionsArray={(paramValue==null?"":new String(serializer1(paramValue),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE integer_bean WHERE value=%s", (Object[])whereConditionsArray));
    int result = database().delete("integer_bean", "value=?", whereConditionsArray);
    return result;
  }

  /**
   * write
   */
  private byte[] serializer1(List<Integer> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.size();
        Integer item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private List<Integer> parser1(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      List<Integer> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Integer> collection=new ArrayList<>();
        Integer item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getIntValue();
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
