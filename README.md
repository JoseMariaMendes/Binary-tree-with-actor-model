# Binary-tree-with-actor-model
Binary tree that utilizes an actor model with messages for deleting, adding and finding elements.

  This program uses the Actor Model which helps to facilitate the development of concurrent applications, as well as
distributed. Each Actor has an Address that allows messages to be sent and a handleMessage function that will
determine how to act according to the type of message you receive.
To develop the binary tree I started by creating a Client, which is an actor and will receive messages sent by
tree nodes with responses to certain messages, and a RootNode, which is also an actor and will serve as an intermediary,
in the sense that you will receive the messages and send them to the tree. When creating the RootNode as well as the Nodes, the
Address of Client and RootNode id sent, so that all nodes can, if necessary, send messages to both.
The RootNode class can only have one child, but in case the child does not yet exist, if it receives a ContainsMessage,
which asks if the tree has a certain value, will send a message to the Client saying that the tree is empty. At the
case of receiving an InsertMessage, which gives an order to the tree to add a node of a certain value, the RootNode
will create a Node with that value, which will be your only child and will still be the first element in the tree. After the existence of a
son, all messages will be forwarded to the tree.
The Node class, which is also an Actor, has two children, LeftChild and RightChild, both variables of type Node and
you can receive 5 types of messages: InsertMessage, ContainsMessage, RemoveMessage, ReaddMessage and PrintMessage.

  - InsertMessage: When a Node receives an InsertMessage, it starts by checking if the value in the message is
greater, less or equal to the value of the node itself. In the case of being a minor, it is checked whether LeftChild exists or not,
to exist, an InsertMessage is sent to that child so that he can repeat the process as his children, in the case
if it does not exist, a new node is created with the given value, which will be the LeftChild of the current node. If it is larger, the
process is the same, but with RightChild. If the value is equal to Node sends a message to the customer saying that the
element has not been added to the tree.

  - ContainsMessage: Node can also receive a ContainsMessage, which checks whether a certain value exists or
not inside the tree. To verify this, the node again checks whether the value it receives is greater, less than or equal to its
own. If it is larger, Node sends the message to RightChild, if it is smaller it sends the message to LeftChild,
if it is the same, send a ContainsAnswerMessage with a boolean true, to determine that the value exists. In case of
the message reaches a node that does not have the child to whom the message should be sent, a message is sent
ContainsAnswerMessage with a false boolean, to indicate that the value does not exist in the tree.

  - RemoveMessage: The node can receive a RemoveMessage, which orders the tree to delete a certain node with
a certain value, and, when receiving this type of message, it checks whether the given value is greater, less or equal to its
own. If it is bigger, send a RemoveMessage to RightChild, if it is bigger, send a RemoveMessage to
LeftChild, if equal send a ReaddMessaga to all daughters and kill yourself after sending one
RemoveAnswerMessage for the client with a boolean true, indicating that the node has been successfully removed. In case
the message reaches a leaf in the tree and the value is still not the same, the node sends a
RemoveAnswerMessage with a boolean with a false value to indicate that the value was not removed because it does not exist
on the tree.

  - ReaddMessage: This message starts by sending a ReaddMessage message to the children, then sends
for root, an InserMessage with the value of the current node and finally kill.

  - PrintMessage: This message runs through all nodes, that is, each node sends to all children and when a node
the receiver prints the value it has saved, this to verify which values ​​exist within the tree.

  The Client class, just checks what type of message it received depending on its content and prints a
message indicating the result of the message sent first.

  To test the functionality, I start by creating a tree with 10 nodes with random numbers between -2000 to 2000, from
then I add the number 400, to have a known number. Then, I send two ContainMessage, one with
value 400 and another value 2001, to have one that certainly exists and another that does not exist. Finally, I send root one
PrintMessage, so that all tree values ​​are printed, in order to confirm that the desired value has been removed, in this
case the 400.
