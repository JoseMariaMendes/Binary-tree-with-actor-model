package actor_tree;

import library.Message;

public class RemoveMessage extends Message {
    public RemoveMessage(int val){
        this.setMessageContent("remove", val);
    }
}
