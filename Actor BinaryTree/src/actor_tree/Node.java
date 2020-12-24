package actor_tree;

import library.*;

public class Node extends Actor{
    Node LeftChild;
    Node RightChild;
    int val;
    Address client;
    Address root;

    public Node(int val, Address client, Address root){
        this.val = val;
        this.client = client;
        this.root = root;
        client.sendMessage(new InsertAnswerMessage(val, true));
    }


    @Override
    protected void handleMessage(Message m) {
        switch (m.content) {
            case "insert":

                if (m.val < val) {
                    if (LeftChild == null) {
                        LeftChild = new Node(m.val, client, root);
                        return;
                    }
                    LeftChild.getAddress().sendMessage(m);
                } else if (m.val > val) {
                    if (RightChild == null) {
                        RightChild = new Node(m.val, client, root);
                        return;
                    }
                    RightChild.getAddress().sendMessage(m);
                }
                else{// se o valor for igual
                    client.sendMessage(new InsertAnswerMessage(m.val, false));
                }
                break;
            case "contains":
                if (m.val < val) {
                    if (LeftChild == null) {
                        client.sendMessage(new ContainsAnswerMessage(m.val, false));
                        return;
                    }
                    LeftChild.getAddress().sendMessage(m);
                } else if (m.val > val) {
                    if (RightChild == null) {
                        client.sendMessage(new ContainsAnswerMessage(m.val, false));
                        return;
                    }
                    RightChild.getAddress().sendMessage(m);
                }
                else {
                    client.sendMessage(new ContainsAnswerMessage(m.val, true));
                }
                break;
            case "remove":
                if (m.val < val) {
                    if (LeftChild == null) {
                        client.sendMessage(new RemoveAnswerMessage(m.val, false));
                        return;
                    }
                    LeftChild.getAddress().sendMessage(new RemoveMessage(m.val));
                } else if (m.val > val) {
                    if (RightChild == null) {
                        client.sendMessage(new RemoveAnswerMessage(m.val, false));
                        return;
                    }
                    RightChild.getAddress().sendMessage(new RemoveMessage(m.val));
                }
                else {
                    if(LeftChild != null){
                        LeftChild.getAddress().sendMessage(new ReaddMessage());
                    }
                    if(RightChild != null){
                        RightChild.getAddress().sendMessage(new ReaddMessage());
                    }
                    client.sendMessage(new RemoveAnswerMessage(val, true));
                    this.getAddress().sendMessage(new SystemKillMessage());
                }
                break;
            case "readd":
                if(LeftChild != null){
                    LeftChild.getAddress().sendMessage(new ReaddMessage());
                }
                if(RightChild != null){
                    RightChild.getAddress().sendMessage(new ReaddMessage());
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                root.sendMessage(new InsertMessage(val)); // reinsert a node with this value
                this.getAddress().sendMessage(new SystemKillMessage());

                break;
            case "print":
                if(LeftChild != null){
                    LeftChild.getAddress().sendMessage(new PrintMessage());
                }
                if(RightChild != null){
                    RightChild.getAddress().sendMessage(new PrintMessage());
                }
                System.out.println(val);
                break;
            default:
                System.out.println("message not recognized");
                break;
        }
    }
}
