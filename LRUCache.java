//Time : O(1)
//Space : O(N)

public class LRUCache {
    class Node{
        int key;int value;
        Node prev; Node next;
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
    Node head;Node tail;
    int capacity; 
    HashMap<Integer, Node> map;
    public LRUCache(int capacity) {
        head = new Node(-1,-1);
        tail= new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
        this.capacity = capacity;       
    }
    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addToHead(Node node){
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev = node;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        }else{
            if(capacity == map.size()){
                Node tailNode = tail.prev;
                removeNode(tailNode);
                map.remove(tailNode.key);
            }
            Node node = new Node(key,value);
            map.put(key,node);
            addToHead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
{

}
