package actor_tree;

import library.Actor;
import library.Message;

public class Client extends Actor {

    @Override
    protected void handleMessage(Message m) {
        switch (m.content) {
            case "InTree" -> System.out.println(m.val + " está na árvore");
            case "NotInTree" -> System.out.println(m.val + " não está na árvore");
            case "Added" -> System.out.println("Node com o valor " + m.val + " foi adicionada à árvore");
            case "NotAdded" -> System.out.println("Node com o valor " + m.val + " não foi adicionada à árvore");
            case "Removed" -> System.out.println("Node com o valor " + m.val + " foi removida da árvore");
            case "NotRemoved" -> System.out.println("Node com o valor " + m.val + " não foi removida da árvore, porque não existe");
        }
    }
}
