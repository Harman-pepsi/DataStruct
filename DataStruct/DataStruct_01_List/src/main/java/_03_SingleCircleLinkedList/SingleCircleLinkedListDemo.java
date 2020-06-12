package _03_SingleCircleLinkedList;

/**
 * @Classname SingleCircleLinkedList
 * @Description
 * @Date 2020/6/13 1:17
 * @Created by Harman
 */
class SingleCircleLinkedList<E>{
    //定义表长
    private int iSize;
    //定义首节点
    private Node<E> nFirst;
    //查无此元素的定义
    private static final int ELEMENT_NOT_FOUND = -1;

    //定义节点
    private static class Node<E>{
        //定义节点元素
        E eElem;
        //定义下个节点地址
        Node<E> next;

        public Node(E eElem, Node<E> next) {
            this.eElem = eElem;
            this.next = next;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(eElem).append("_");
            if(next != null){
                sb.append(next.eElem);
            }else{
                sb.append("null");
            }
            return sb.toString();
        }
    }

    //清空表
    public void clear(){
        nFirst = null;
        iSize = 0;
    }

    //判断表是否为空
    public boolean isEmpty(){
        return iSize == 0;
    }

    //获取表长
    public int getSizeOfList(){
        return iSize;
    }

    //添加节点至表尾
    public void addNode2Last(E eElem){
        addNode2Index(iSize,eElem);
    }

    //添加元素至任意位置
    public void addNode2Index(int index,E eElem){
        //检查位置是否越界
        rangeCheck4Add(index);
        if(index == 0){
            Node<E> newNode = new Node<>(eElem,nFirst);
            Node<E> nLast = isEmpty() ? newNode : getNodeOfIndex(iSize - 1);
            nLast.next = newNode;
            nFirst = newNode;
        }else{
            Node<E> preNode = getNodeOfIndex(index - 1);
            preNode.next = new Node<>(eElem,preNode.next);
        }
        iSize++;
    }

    //删除指定位置的节点
    public void delNodeOfIndex(int index){
        //检查位置是否越界
        rangeCheck(index);
        if(index == 0){
            if(iSize == 1){
                nFirst = null;
            }else{
                Node<E> newFirst = nFirst.next;
                Node<E> nLast = getNodeOfIndex(iSize -1);
                nLast.next = newFirst;
                nFirst = newFirst;
            }
        }else{
            Node<E> preNode = getNodeOfIndex(index - 1);
            preNode.next = preNode.next.next;
        }
        iSize--;
    }

    //获取指定位置的节点元素
    public E getElemOfIndex(int index){
        //检查位置是否越界
        rangeCheck(index);
        return getNodeOfIndex(index).eElem;
    }

    //获取指定元素在表中的位置
    public int getIndexOfElem(E eElem){
        Node<E> node = nFirst;
        for (int i = 0; i < iSize; i++) {
            if(node.eElem.equals(eElem)){
                return i;
            }
            node = node.next;
        }
        return ELEMENT_NOT_FOUND;
    }

    //判断表中是否包含指定元素
    public boolean isContains(E eElem){
        return getIndexOfElem(eElem) != ELEMENT_NOT_FOUND;
    }

    //获取指定位置的节点
    private Node<E> getNodeOfIndex(int index){
        //检查位置是否越界
        rangeCheck(index);
        Node node = nFirst;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    //输出表
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("SingleCircleLinkedList:").append("size=").append(iSize).append(" [");
        Node node = nFirst;
        for (int i = 0; i < iSize; i++) {
            if(i != 0){
                sb.append(", ");
            }
            sb.append(node);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    //检查位置是否越界
    private void rangeCheck(int index){
        if(index < 0 || index >= iSize)
            throw new IndexOutOfBoundsException("index:" + index + ",size:" + iSize);
    }

    private void rangeCheck4Add(int index){
        if(index < 0 || index > iSize)
            throw new IndexOutOfBoundsException("index:" + index + ",size:" + iSize);
    }
}
public class SingleCircleLinkedListDemo {
    public static void main(String[] args) {
        SingleCircleLinkedList<Integer> list = new SingleCircleLinkedList<>();
        System.out.println("表是否为空:" + list.isEmpty());
        System.out.println("表长:" + list.getSizeOfList());
        System.out.println(list);
        System.out.println("=========添加元素==========");
        for (int i = 0; i < 15; i++) {
            list.addNode2Last(i);
        }
        System.out.println("表是否为空:" + list.isEmpty());
        System.out.println("表长:" + list.getSizeOfList());
        System.out.println(list);
        list.addNode2Index(0,-1);
        list.addNode2Index(2,-2);
        list.addNode2Index(list.getSizeOfList(),-11);
        System.out.println("表是否为空:" + list.isEmpty());
        System.out.println("表长:" + list.getSizeOfList());
        System.out.println(list);
        System.out.println("=========删除元素==========");
        for (int i = 0; i < 10; i++) {
            list.delNodeOfIndex(0);
        }
        System.out.println("表是否为空:" + list.isEmpty());
        System.out.println("表长:" + list.getSizeOfList());
        System.out.println(list);
        System.out.println("=========查看指定元素位置==========");
        System.out.println(list.getElemOfIndex(0));
        System.out.println(list.getElemOfIndex(list.getSizeOfList() >> 1));
        System.out.println(list.getElemOfIndex(list.getSizeOfList() - 1));
        System.out.println("=========查看指定元素在表中的位置==========");
        System.out.println(list.getIndexOfElem(8));
        System.out.println(list.getIndexOfElem(12));
        System.out.println(list.getIndexOfElem(-11));
        System.out.println("=========是否包含指定元素==========");
        System.out.println(list.isContains(12));
        System.out.println(list.isContains(112));
        System.out.println("=========清空表==========");
        list.clear();
        System.out.println("表是否为空:" + list.isEmpty());
        System.out.println("表长:" + list.getSizeOfList());
        System.out.println(list);
    }
}