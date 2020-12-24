package actor_tree;

import library.Message;

public class InsertMessage extends Message {
    public InsertMessage(int val){
        this.setMessageContent("insert", val);
    }
}
