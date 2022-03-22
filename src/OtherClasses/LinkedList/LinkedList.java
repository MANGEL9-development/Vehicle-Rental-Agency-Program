package OtherClasses.LinkedList;

/**This is a generic LinkedList that can be used for any Object type. The
 * methods and fields in this class are based on VehicleList and VehicleNode
 * from Program 4.
 * @author Mark Angelot
 * @param <Type> the type of Objects that this LinkedList can hold
 * @date April 24, 2020
 */
public class LinkedList<Type>{
    /**
     * This holds the first {@link Node} of this LinkedList. Every subsequent
     * element is found by searching from this {@link Node} with the use of its
     * {@code getNext()} method
     */
    private Node head;
    
    /**
     * Constructs an empty LinkedList
     */
    public LinkedList(){
        head=null;
    }
    
    /**
     * @return how many {@link Node Nodes} are in this LinkedList
     */
    public int length(){
        if(head==null){
            return 0;
        }
        int l=0;
        for(Node i=head;i!=null;i=i.getNext()){
            l++;
        }
        return l;
    }
    
    /**
     * @return whether or not this LinkedList is empty (meaning that the head is
         null)
     */
    public boolean isEmpty(){
        return (head==null);
    }
    
    /**
     * @return the head {@link Node} in this LinkedList
     */
    public Node getHead(){
        return head;
    }
    /**
     * @return the last {@link Node} in this LinkedList
     */
    public Node findLastNode(){
        if(head==null){
            return null;
        }
        Node currentNode=head;
        while(true){
            if(currentNode.isLastNode()){
                return currentNode;
            }
            currentNode=currentNode.getNext();
        }
    }
    
    /**
     * Adds a {@link Node} to the end of this LinkedList
     * @param newElement the {@link Node} to be added to the end of this LinkedList
     */
    public void append(Type newElement){
        if(head==null){
            head=new Node(newElement);
        }
        else{
            Node lastNode=findLastNode();
            lastNode.setNext(new Node(newElement,lastNode));
        }
    }
    /**
     * Adds a {@link Node} to the beginning of this LinkedList
     * @param newElement the {@link Node} to be added to the beginning of
     *          this LinedList
     */
    public void prepend(Type newElement){
        if(head==null){
            head=new Node(newElement);
        }
        head.setPrevious(new Node(newElement));
        head=head.getPrevious();
    }
    
    /**
     * Removes the {@link Node} at the specified index
     * @param index the index at which a {@link Node} will be removed
     * @throws IndexOutOfBoundsException if index exceeds the length of this
         LinkedList
     */
    public void removeAtIndex(int index){
        int length=length();
        if(index>=length){
            throw new IndexOutOfBoundsException("Index "+index+" out of bounds for length "+length);
        }
        if(index==0){
            if(head.getNext()!=null){
                head.getNext().setPrevious(null);
                head=head.getNext();
            }
            else{
                head=null;
            }
        }
        else{
            Node removedElement=nodeAtIndex(index);
            removedElement.getPrevious().setNext(removedElement.getNext());
        }
    }
    
    /**
     * Empties this LinkedList by setting the head node to null
     */
    public void clearList(){
        head=null;
    }
    
    /**
     * Creates and returns an array with all the elements from this LinkedList
     * and in the same order
     * @implNote Because generic array creation is not allowed in Java, this
     * method works by creating an Object[] array and casting that into an
     * array of whatever Type of elements this collection holds. When using the
     * array returned by this method, cast each element into the Type that this
     * collection holds to avoid being thrown a {@link ClassCastException}.
     * @return this LinkedList in the form of an array
     */
    public Type[] asArray(){
        if(head==null){
            return (Type[])(new Object[0]);
        }
        Object[] array=new Object[length()];
        int i=0;
        for(Node current=head;i<length();current=current.getNext(),i++){
            array[i]=current.getValue();
        }
        return (Type[])array;
        
        /*ArrayList<Type> arrayList=new ArrayList();
        for(Node current=head;current!=null;current=current.getNext()){
            arrayList.add(current.getValue());
        }
        
        return arrayList.*/
    }
    
    /**
     * Finds the element at the specified index
     * @param index the index at which to return an element
     * @return the element at the specified index
     */
    public Type elementAtIndex(int index){
        return nodeAtIndex(index).getValue();
    }
    /**
     * Finds the {@link Node} at the specified index
     * @param index the index at which to return a {@link Node}
     * @return the {@link Node} at the specified index
     */
    public Node nodeAtIndex(int index){
        if(head==null){
            throw new IndexOutOfBoundsException("Index "+index+" out of bounds for length 0");
        }
        Node v=head;
        for(int i=0;i<index;i++){
            if(v.isLastNode()){
                throw new IndexOutOfBoundsException("Index "+index+" out of bounds for length "+i);
            }
            v=v.getNext();
        }
        return v;
    }
    
    
    /**
     * This represents an element in a LinkedList
     */
    public class Node{
        private final Type value;
        private Node previous;
        private Node next;

        /**
         * Constructs a head Node
         * @param value this Node's value
         */
        public Node(Type value){
            this.value=value;
            previous=null;
            next=null;
        }
        /**
         * Constructs a Node
         * @param value this Node's value
         * @param previous the Node that comes before this Node
         */
        public Node(Type value,Node previous){
            this.value=value;
            this.previous=previous;
            next=null;
        }

        /**
         * @return this Node's value
         */
        public Type getValue(){
            return value;
        }
        /**
         * @return the Node that comes before this Node
         */
        public Node getPrevious(){
            return previous;
        }
        /**
         * @return the Node that comes after this Node
         */
        public Node getNext(){
            return next;
        }

        /**
         * Sets which Node comes before this one
         * @param previous the Node that will be set as this Node's previous
         */
        public void setPrevious(Node previous){
            this.previous=previous;
        }
        /**
         * Sets which Node comes after this one
         * @param next the Node that will be set as this Node's next
         */
        public void setNext(Node next){
            this.next=next;
        }

        /**
         * @return whether or not this is the head node of a {@link LinkedList}
         */
        public boolean isHeadNode(){
            return (previous==null);
        }
        /**
         * @return whether or not this is neither the head node nor the last node of
         *         a {@link LinkedList}
         */
        public boolean isMiddleNode(){
            return (previous!=null && next!=null);
        }
        /**
         * @return whether or not this is the last node of a {@link LinkedList}
         */
        public boolean isLastNode(){
            return (next==null);
        }
    }
}
