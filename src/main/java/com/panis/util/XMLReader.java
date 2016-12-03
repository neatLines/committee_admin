package com.panis.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public class XMLReader {
    private static String dbConfig = "databaseConfig.xml";
    private static String className = "tableAndClassName.xml";

    public static Config getConfig() {
        Config config = new Config();
        String path = XMLReader.class.getResource("/").getPath();
        System.out.println(path);
        dbConfig = path+dbConfig;
        className = path+className;
        System.out.println(dbConfig+className);
        try {
            File f = new File(dbConfig);
            if (!f.exists()) {
                System.out.println("  Error : Config file doesn't exist!");
                System.exit(1);
            }
            SAXReader reader = new SAXReader();
            Document doc;
            doc = reader.read(f);
            Element root = doc.getRootElement();
            Element data;
            Iterator<?> itr = root.elementIterator("VALUE");
            data = (Element) itr.next();

            config.setServer(data.elementText("server").trim());
            config.setUser(data.elementText("user").trim());
            config.setPass(data.elementText("password").trim());
            config.setPort(data.elementText("port").trim());
            config.setDbname(data.elementText("dbName").trim());

            File f2 = new File(className);
            if (!f2.exists()) {
                System.out.println(" Error : ClassConfig file doesn't exist!");
                System.exit(1);
            }
            reader = new SAXReader();
            doc = reader.read(f2);
            root = doc.getRootElement();
            itr = root.elementIterator("VALUE");
            data = (Element) itr.next();

            HashMap<String,String> map = new HashMap<>();

            for (Iterator i = data.elementIterator();i.hasNext();) {
                Element node = (Element) i.next();
                map.put(node.getName(),node.getText());
//                System.out.println(node.getName()+":"+node.getText());
            }
            config.setClassName(map);

        } catch (Exception ex) {
            System.out.println("Error : " + ex.toString());
        }
        dbConfig = "databaseConfig.xml";
        className = "tableAndClassName.xml";
        return config;

    }
}
