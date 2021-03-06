package commons.kripton86.test3;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.util.HashSet;

/**
 * This class is binder map for Bean3_3
 *
 * @see Bean3_3
 */
@BindMap(Bean3_3.class)
public class Bean3_3BindMap extends AbstractMapper<Bean3_3> {
  @Override
  public int serializeOnJackson(Bean3_3 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueLongSet (mapped with "valueLongSet")
    if (object.valueLongSet!=null)  {
      fieldCount++;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongSet");
      jacksonSerializer.writeStartArray();
      for (Long item: object.valueLongSet) {
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean3_3 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueLongSet (mapped with "valueLongSet")
    if (object.valueLongSet!=null)  {
      fieldCount++;
      int n=object.valueLongSet.size();
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueLongSet");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (Long item: object.valueLongSet) {
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(PrimitiveUtils.writeLong(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean3_3 object, XMLSerializer xmlSerializer, int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean3_3");
    }

    // Persisted fields:

    // field valueLongSet (mapped with "valueLongSet")
    if (object.valueLongSet!=null)  {
      int n=object.valueLongSet.size();
      for (Long item: object.valueLongSet) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueLongSet");
        } else {
          xmlSerializer.writeAttribute("valueLongSet", PrimitiveUtils.writeLong(item));
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueLongSet");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean3_3 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean3_3 instance = new Bean3_3();
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "valueLongSet":
            // field valueLongSet (mapped with "valueLongSet")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashSet<Long> collection=new HashSet<>();
              Long item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getLongValue();
                }
                collection.add(item);
              }
              instance.valueLongSet=collection;
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean3_3 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean3_3 instance = new Bean3_3();
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "valueLongSet":
            // field valueLongSet (mapped with "valueLongSet")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashSet<Long> collection=new HashSet<>();
              Long item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readLong(jacksonParser.getText(), null);
                }
                collection.add(item);
              }
              instance.valueLongSet=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              HashSet<Long> collection=new HashSet<>();
              instance.valueLongSet=collection;
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Bean3_3 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean3_3 instance = new Bean3_3();
    int eventType = currentEventType;
    boolean read=true;

    if (currentEventType == 0) {
      eventType = xmlParser.next();
    } else {
      eventType = xmlParser.getEventType();
    }
    String currentTag = xmlParser.getName().toString();
    String elementName = currentTag;

    // attributes 
    String attributeName = null;
    int attributesCount = xmlParser.getAttributeCount();;
    for (int attributeIndex = 0; attributeIndex < attributesCount; attributeIndex++) {
      attributeName = xmlParser.getAttributeName(attributeIndex);
      switch(attributeName) {
          case "valueLongSet":
            // field valueLongSet (mapped by "valueLongSet")
             {
              HashSet<Long> collection=new HashSet<>();
              Long item;
              // add first element
              item=null;
              if (xmlParser.isEmptyElement()) {
                // if there's a an empty collection it marked with attribute emptyCollection
                if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                  collection.add(item);
                }
                xmlParser.nextTag();
              } else {
                item=PrimitiveUtils.readLong(xmlParser.getAttributeValue(attributeIndex), null);
                collection.add(item);
              }
              while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueLongSet")) {
                if (xmlParser.isEmptyElement()) {
                  item=null;
                  xmlParser.nextTag();
                } else {
                  item=PrimitiveUtils.readLong(xmlParser.getAttributeValue(attributeIndex), null);
                }
                collection.add(item);
              }
              instance.valueLongSet=collection;
              read=false;
            }
          break;
          default:
          break;
      }
    }

    //sub-elements
    while (xmlParser.hasNext() && elementName!=null) {
      if (read) {
        eventType = xmlParser.next();
      } else {
        eventType = xmlParser.getEventType();
      }
      read=true;
      switch(eventType) {
          case XmlPullParser.START_TAG:
            currentTag = xmlParser.getName().toString();
            // No property to manage here
          break;
          case XmlPullParser.END_TAG:
            if (elementName.equals(xmlParser.getName())) {
              currentTag = elementName;
              elementName = null;
            }
          break;
          case XmlPullParser.CDSECT:
          case XmlPullParser.TEXT:
            // no property is binded to VALUE o CDATA break;
          default:
          break;
      }
    }
    return instance;
  }
}
