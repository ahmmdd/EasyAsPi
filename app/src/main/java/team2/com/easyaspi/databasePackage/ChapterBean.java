package team2.com.easyaspi.databasePackage;

import java.io.Serializable;

/*
*   Name: ChapterBean.java
*   Description: Lesson Bean Class
*   Last Modified: 2017, March 12
*   Last Modified By: Taera Kwon
 */

public class ChapterBean implements Serializable {
    // PRIVATE VARIABLES
    private int chapter;
    private String chaptername;

    // PUBLIC GETTERS
    public int getChapter(){
        return chapter;
    }
    public String getChaptername(){
        return chaptername;
    }

    public void setChapter(int chapter){
        this.chapter = chapter;
    }

    public void setChaptername(String chaptername){
        this.chaptername = chaptername;
    }
}
