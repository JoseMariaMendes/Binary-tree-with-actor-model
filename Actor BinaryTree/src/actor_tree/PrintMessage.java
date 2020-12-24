package actor_tree;

import library.Message;

public class PrintMessage extends Message {
    public  PrintMessage(){
        this.setMessageContent("print", 0);
    }
}
