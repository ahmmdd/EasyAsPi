package team2.com.easyaspi.databasePackage;

/*
*   Name: XmlParser.java
*   Description: XML Parser Class
*   Last Modified: 2017, March 13
*   Last Modified By: Taera Kwon
 */

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XmlParser {
    public HashMap<String, List> GradeParser(InputStream inputStream) throws XmlPullParserException, IOException {
        // Create a null Hash, and two list objects
        HashMap<String, List> hash = new HashMap<String, List>();
        List<TopicBean> topicList =  new ArrayList<TopicBean>();
        List<ChapterBean> chapterList = new ArrayList<ChapterBean>();
        // New Lesson Object
        TopicBean topic = new TopicBean();
        ChapterBean chapter = new ChapterBean();
        // Counter
        try {
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
                            chapter.setChapter(Integer.parseInt(xpp.getAttributeValue(null, "number")));
                            chapter.setChaptername(xpp.getAttributeValue(null, "name"));
                            // Set topic properties
                            topic.setChapter(chapter.getChapter());
                            topic.setChaptername(chapter.getChaptername());
                            break;
                        } else if (tagName.equals("topic")){
                            // Set topic properties
                            topic.setTopicid(xpp.getAttributeValue(null, "id"));
                            topic.setTopicname(xpp.getAttributeValue(null, "name"));
                            break;
                        } else if (tagName.equals("image")){
                            //Set image properties
                            topic.setImageaddress(xpp.nextText());
                            break;
                        } else if (tagName.equals("instruction")){
                            // Set instruction properties
                            topic.setInstruction(xpp.nextText());
                            break;
                        }
                    case XmlPullParser.END_TAG:
                        // If tag is </topic>
                        if (tagName.equals("topic")){
                            // Adds topic object to topicList
                            topicList.add(topic);
                            topic = new TopicBean();
                        } else if (tagName.equals("chapter")){
                            // Adds chapter object to chapterList
                            chapterList.add(chapter);
                            // Set chapter object to null
                            chapter = new ChapterBean();
                        }
                }
                // Go to next tag
                event = xpp.next();
            }
            // Add chapterList and topicList to hash
            hash.put("Chapters", chapterList);
            hash.put("Topics", topicList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return hash;
    }
}
