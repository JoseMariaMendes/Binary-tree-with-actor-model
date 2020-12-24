package actor_tree;

import library.Message;

public class ReaddMessage extends Message {
    public ReaddMessage(){
        this.setMessageContent("readd", 0);
    }
}
