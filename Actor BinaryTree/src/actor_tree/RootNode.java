package actor_tree;

import library.Actor;
import library.Address;
import library.Message;

public class RootNode extends Actor {
    Node child;
    Address client;

    public RootNode(Address client) {
        this.client = client;
    }
    //ActorTree tree = new ActorTree(this);

    public void addRootNode(Node child){
        this.child = child;
    }

    @Override
    protected void handleMessage(Message m) {

        if (child == null && m.content.equals("insert")){
            System.out.println("Primeiro elemento da arvore criado com o valor  " + m.val);
            child = new Node(m.val, client, this.getAddress());
        }
        else if (child != null){
            child.getAddress().sendMessage(m);
        }
        else{
            System.out.println("Tree is empty");
        }
    }
}
