package team2.com.easyaspi.databasePackage;

/*
*   Name: XmlParser.java
*   Description: XML Parser Class
*   Last Modified: 2017, March 13
*   Last Modified By: Taera Kwon
 */

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class XmlParser {
    public List<LessonBean> LessonParser(InputStream inputStream) throws XmlPullParserException, IOException {
        // Create a null Lesson object
        List<LessonBean> lessonList = null;
        try {
            // New Lesson Object
            LessonBean lesson = new LessonBean();
            // Create new instance of the XML Pull Parser in XMPULL V1 API
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            // Specifies that the parser produced by factory will be validating
            factory.setNamespaceAware(true);
            // Creating new instance of a XML Pull Parser using the currently configured factory features.
            XmlPullParser xpp = factory.newPullParser();
            // Set Input source
            xpp.setInput(inputStream, null);
            // Get type of event
            int event = xpp.getEventType();
            while (event != XmlPullParser.END_DOCUMENT){
                // Get tag name
                String tagName = xpp.getName();
                // Switch statement checking event type
                switch(event){
                    case XmlPullParser.START_TAG:
                        // If tag is "chapter"
                        if (tagName.equals("chapter")){
                            // Set chapter properties
                            lesson.setChapter(xpp.getAttributeValue(null, "number"));
                            lesson.setChaptername(xpp.getAttributeValue(null, "name"));
                            break;
                        } else if (tagName.equals("topic")){
                            // Set topic properties

                        }
                    case XmlPullParser.END_TAG:
                }
                // Go to next tag
                event = xpp.next();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lessonList;
    }
}
