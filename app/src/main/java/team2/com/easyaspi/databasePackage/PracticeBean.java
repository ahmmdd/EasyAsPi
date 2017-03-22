package team2.com.easyaspi.databasePackage;

import java.io.Serializable;
/**
 * Created by Ali on 2017-03-22.
 */

public class PracticeBean extends ChapterBean {

    private Double topicid;
    private String topicname, imageaddress, instruction, question;

    // Public Getter
    public Double getId(){
        return topicid;
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

    public String getQuestion(){
        return question;
    }



    // Public Setter
    public void setTopicid(String id){
        this.topicid = Double.parseDouble(id);
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

    public void setQuestion(String question){
        this.question = question;
    }
}
