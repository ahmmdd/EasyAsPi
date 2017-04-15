package team2.com.easyaspi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import team2.com.easyaspi.databasePackage.ChapterBean;
import team2.com.easyaspi.databasePackage.TopicBean;
import team2.com.easyaspi.databasePackage.XmlParser;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/**
 * Created by Taera on 2017-04-15.
 * Description: Tests XML Parser
 */

@RunWith(MockitoJUnitRunner.class)
public class MockitoTestXmlParser {
    @Mock
    XmlParser xmlParser = mock(XmlParser.class);
    MainActivity mainActivity = mock(MainActivity.class);
    HashMap<String, List> map;
    List<TopicBean> topicList =  new ArrayList<TopicBean>();
    List<ChapterBean> chapterList = new ArrayList<ChapterBean>();
    ChapterBean chapter = new ChapterBean();

    @Test
    public void TestXmlParser() throws IOException, XmlPullParserException {
        // Given
        String testXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?><lessons><chapter number=\"1\" name=\"Number Sense and Numeration\"></chapter></lessons>";
        chapter.setChapter(1);
        chapter.setChaptername("Number Sense and Numeration");
        map = new HashMap<String, List>();
        chapterList.add(chapter);
        map.put("chaptersList", chapterList);
        map.put("topicsList", topicList);
        // Converts String to InputStream
        ByteArrayInputStream testInputStream = new ByteArrayInputStream(testXML.getBytes());
        // Define return value for method GradeParser
        when(xmlParser.GradeParser(testInputStream)).thenReturn(map);
        // Use mock in xmlParser
        assertEquals(xmlParser.GradeParser(testInputStream), map);
    }
}
