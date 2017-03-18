package team2.com.easyaspi.databasePackage;

/*
*   Name: TopicBean.java
*   Description: Topic Bean Class
*   Last Modified: 2017, March 12
*   Last Modified By: Taera Kwon
 */

public class TopicBean extends ChapterBean {
    // Private ID
    private Double topicid;
    private String topicname, imageaddress, instruction;

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
    public String getInstruction(){ return instruction; }

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
}
