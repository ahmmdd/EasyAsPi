package team2.com.easyaspi.databasePackage;

/*
*   Name: XmlParser.java
*   Description: XML Parser Class
*   Last Modified: 2017, March 12
*   Last Modified By: Taera Kwon
 */

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;

public class XmlParser {
    public static void main (String args[]) throws XmlPullParserException, IOException{
        // Create new instance of the XML Pull Parser in XMPULL V1 API
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        // Specifies that the parser produced by factory will be validating
        factory.setNamespaceAware(true);
        // Creating new instance of a XML Pull Parser using the currently configured factory features.
        XmlPullParser xpp = factory.newPullParser();
    }
}
