package actor_tree;

import library.Message;

public class InsertAnswerMessage extends Message {
    public InsertAnswerMessage(int val, boolean bool) {
        if (bool) {
            this.setMessageContent("Added", val);
        } else {
            this.setMessageContent("NotAdded", val);
        }
    }
}
