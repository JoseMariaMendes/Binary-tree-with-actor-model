package actor_tree;

import library.*;

import java.util.Random;
import java.util.Scanner;

public class Main{
    public static int NNODES = 10; // depth 13 16383
    public static int MAXNUM = 2000;
    public static int MINNUM = -2000;
    public static Random r = new Random();

    public static void main(String[] args) {
        //r.nextInt(MAXNUM - MINNUM) + MINNUM

        Client client = new Client();
        RootNode root = new RootNode(client.getAddress());

        for (int i = 0; i< NNODES; i++){
            int num = r.nextInt(MAXNUM - MINNUM) + MINNUM;
            root.getAddress().sendMessage(new InsertMessage(num));
        }

        root.getAddress().sendMessage(new InsertMessage(400));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        root.getAddress().sendMessage(new ContainsMessage(400));//esta sempre na arvore
        root.getAddress().sendMessage(new ContainsMessage(2001));// nunca estara na arvore

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        root.getAddress().sendMessage(new RemoveMessage(400));//está sempre na arvore
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        root.getAddress().sendMessage(new RemoveMessage(2001));// nunca estará na arvore
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        root.getAddress().sendMessage(new PrintMessage());

    }
}
