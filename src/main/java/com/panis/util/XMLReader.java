package com.panis.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

/**
 * Created by fuyipeng on 01/12/2016.
 */
public class XMLReader {
    private static String fileName= "databaseConfig.xml";

    private Config config;


    public static Config getConfig() {
        Config config = new Config();
        String path = XMLReader.class.getResource("/").getPath();
        System.out.println(path);
        fileName = path+fileName;
        System.out.println(fileName);
        try {
            File f = new File(fileName);
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

            config.server = data.elementText("server").trim();
            config.user = data.elementText("user").trim();
            config.pass = data.elementText("password").trim();
            config.port = data.elementText("port").trim();
            config.dbname = data.elementText("dbName").trim();

        } catch (Exception ex) {
            System.out.println("Error : " + ex.toString());
        }
        fileName = "databaseConfig.xml";
        return config;

    }
}
