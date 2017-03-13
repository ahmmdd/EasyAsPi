package team2.com.easyaspi.databasePackage;

import java.io.Serializable;

/*
*   Name: LessonBean.java
*   Description: Lesson Bean Class
*   Last Modified: 2017, March 12
*   Last Modified By: Taera Kwon
 */

public class LessonBean implements Serializable {
    // PRIVATE VARIABLES
    private int id, chapter;
    private String chaptername, topicname, imageaddress, instruction;

    // PUBLIC GETTERS
    public int getId(){
        return id;
    }

    public int getChapter(){
        return chapter;
    }

    public String getChaptername(){
        return chaptername;
    }

    public String getTopicname(){
        return topicname;
    }

    public String getImageaddress(){
        return imageaddress;
    }

    public String getInstruction(){
        return instruction;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setChapter(String chapter){
        this.chapter = Integer.parseInt(chapter);
    }

    public void setChaptername(String chaptername){
        this.chaptername = chaptername;
    }

    public void setTopicname(String topicname){
        this.topicname = topicname;
    }

    public void setImageaddress(String imageaddress){
        this.imageaddress = imageaddress;
    }

    public void setInstruction(String instruction){
        this.instruction = instruction;
    }
}
