package com.zjdt.dtsjwb.Util;

import android.content.res.XmlResourceParser;
import android.util.Log;

import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.R;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

public class ParseXml {
    /**
     * pull
     *
     *
     */

    public String parseXMLWithPull(String xmlData,String type){
       long time1= System.currentTimeMillis();
        try {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventtype=xmlPullParser.getEventType();
            String name="";
            while (eventtype!=xmlPullParser.END_DOCUMENT){
                String nodename=xmlPullParser.getName();
                //Log.e("西溪湿地",nodename+"1");
                if ("name".equals(nodename)){
                    //Log.e("西溪湿地",xmlPullParser.getAttributeValue(null,"class")+"--------");
                    if (type.equals(xmlPullParser.getAttributeValue(null,"class"))){
                        long time2= System.currentTimeMillis()-time1;
                        Log.e("解析时间",time2+"");//存在中间null 需要保证两种模式 start_tag end_tag
                        XmlResourceParser xmlResourceParser= AppApplication.getApp().getResources().getXml(R.xml.air_condition_inspection);
                       Log.e("解析时间",xmlResourceParser.toString());
                        return xmlPullParser.nextText();
                    }

                }
                xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * sax
     */

    private String parseXMLWithSax(String xmlData){
       /* SAXReader reader = new SAXReader();
        Document document = reader.read(new FileInputStream(f)); //dom4j读取
        String xpath = "/Record/Field[@name='Creator']";//查询属性type='Creator'
        Element element = (Element) document.selectSingleNode(xpath);//得到name=Creator的元素
        System.out.println(element.getText());
        xpath 方法明显方便*/

        try {
            SAXParserFactory factory=SAXParserFactory.newInstance();
            XMLReader xmlReader= factory.newSAXParser().getXMLReader();

            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
           // return handler.getName(); 这样它仍然认为是contentH 没有子类 直接去父类调方法 不是  它是基于类型判断的 最后基于对象
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return null;

    }
    public ContentHandler handler=new ContentHandler() {//不一定要继承

        private String nodename;
        private String name;
        private String attValue;
        private boolean isFind=false;

        public String getAttValue() {
            return attValue;
        }

        public void setAttValue(String attValue) {
            this.attValue = attValue;
        }

        public String getName() {
            return name;
        }

        @Override
        public void setDocumentLocator(Locator locator) {

        }

        @Override
        public void startDocument() throws SAXException {

        }

        @Override
        public void endDocument() throws SAXException {

        }

        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {

        }

        @Override
        public void endPrefixMapping(String prefix) throws SAXException {

        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
            nodename = localName;
           /* for (int i = 0; i < atts.getLength(); i++) {
                if (atts.getQName(i).equals("class")) {
                    //people.setId(attributes.getValue(i));
                    atts.getV
                }
            }*/
           if(atts.getValue("class").equals(attValue)){
               isFind=true;
           }
        }
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if("name".equals(nodename)){
                if (isFind)
                name=new String(ch,start,length);
            }
        }

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

        }

        @Override
        public void processingInstruction(String target, String data) throws SAXException {

        }

        @Override
        public void skippedEntity(String name) throws SAXException {

        }
    };

}
