package actor_tree;

import library.Message;

public class RemoveAnswerMessage extends Message {
    public RemoveAnswerMessage(int val, boolean bool){
        if(bool){
            this.setMessageContent("Removed", val);
        }
        else {
            this.setMessageContent("NotRemoved", val);
        }
    }
}
