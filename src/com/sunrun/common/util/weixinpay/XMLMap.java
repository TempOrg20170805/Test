package com.sunrun.common.util.weixinpay;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * XMLMap互转
 * 
 * @author jmm
 * 
 */
public class XMLMap {
	/**
	 * 将map类型数据转换成XML文档
	 * 
	 * @param map
	 *            该节点
	 * @param element
	 *            上一级节点
	 * @return
	 */
	public static void parseToXML(Map map, Element element) {
		/** 开始对map进行解析 */
		if (map == null)
			throw new NullPointerException("map 数据为空,不能解析!");
		Set set = map.entrySet();
		Iterator records = set.iterator();
		while (records.hasNext()) {
			Map.Entry entry = (Map.Entry) records.next();
			if (entry.getValue().getClass().equals(TreeMap.class)) {// 子目录
				Element node = new Element(entry.getKey().toString());
				//node.setAttribute("name", entry.getKey().toString());
				element.addContent(node);
				parseToXML((TreeMap) entry.getValue(), node);
			} else {// 到达顶点
				Element node = new Element(entry.getKey().toString());
				//node.setAttribute("name", entry.getKey().toString());
				/*Element value = new Element("value");
				value.setText(entry.getValue().toString());*/
				node.addContent(entry.getValue().toString());
				element.addContent(node);
			}
		}
	}
	
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map m = new HashMap();
		
		InputStream in = new ByteArrayInputStream(strxml.getBytes("utf-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLMap.getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
	
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(XMLMap.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取xml编码字符集
	 * @param strxml
	 * @return
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static String getXMLEncoding(String strxml) throws JDOMException, IOException {
		InputStream in = HttpClientUtil.String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		in.close();
		return (String)doc.getProperty("encoding");
	}
}
