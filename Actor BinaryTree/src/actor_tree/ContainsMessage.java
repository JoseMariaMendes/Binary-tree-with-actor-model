package actor_tree;

import library.Message;

public class ContainsMessage extends Message {
    public ContainsMessage(int val){
        this.setMessageContent("contains", val);
    }
}
