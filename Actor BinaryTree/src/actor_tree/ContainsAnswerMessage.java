package actor_tree;

import library.Message;

public class ContainsAnswerMessage extends Message {
    public ContainsAnswerMessage(int val, boolean bool){
        if(bool){
            this.setMessageContent("InTree", val);
        }
        else {
            this.setMessageContent("NotInTree", val);
        }
    }
}
