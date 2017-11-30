package com.system.mrqin.commonutil.log.wrapper;

import android.util.Log;

import com.system.mrqin.commonutil.log.LogUtil;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Created by Administrator on 2017/11/28.
 */

public class XmlLog {

    public static void printXml(String tag, String xml) {

        if (xml != null) {
            xml = XmlLog.formatXML(xml);
        } else {
            xml = tag + ":" + LogUtil.NULL_TIPS;
        }

        Line.printLine(tag, true);
        String[] lines = xml.split(LogUtil.LINE_SEPARATOR);
        for (String line : lines) {
            if (!Line.isEmpty(line)) {
                Log.w(tag, "║ " + line);
            }
        }
        Line.printLine(tag, false);
    }

    private static String formatXML(String inputXML) {
        try {
            Source xmlInput = new StreamSource(new StringReader(inputXML));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
        } catch (Exception e) {
            e.printStackTrace();
            return inputXML;
        }
    }

}
