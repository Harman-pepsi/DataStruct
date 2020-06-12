package _05_DoubleCircleLinkedList;

/**
 * @Classname DoubleCircleLinkedListDemo
 * @Description
 * @Date 2020/6/13 1:32
 * @Created by Harman
 */
class DoubleCircleLinkedList<E>{
    //定义表长
    private int iSize;
    //定义首尾节点
    private Node<E> nFirst,nLast;
    //定义查无此元素
    private static final int ELEMENT_NOT_FOUND = -1;

    //定义节点
    private static class Node<E>{
        //定义前一个节点地址
        Node<E> prev;
        //定义节点元素
        E eElem;
        //定义下一个节点地址
        Node<E> next;

        public Node(Node<E> prev, E eElem, Node<E> next) {
            this.prev = prev;
            this.eElem = eElem;
            this.next = next;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            if(prev != null){
                sb.append(prev.eElem);
            }else{
                sb.append("null");
            }
            sb.append("_").append(eElem).append("_");
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
        nLast = null;
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

    //添加元素至表尾
    public void addNode2Last(E eElem){
        addNode2Index(iSize,eElem);
    }

    //添加节点至任意位置
    public void addNode2Index(int index,E eElem){
        //检查位置是否越界
        rangeCheck4Add(index);
        if(index == iSize){
            nLast = new Node<>(nLast,eElem,nFirst);
            if(nLast.prev == null){
                nFirst = nLast;
                nFirst.next = nFirst;
                nFirst.prev = nFirst;
            }else{
                nLast.prev.next = nLast;
                nFirst.prev = nLast;
            }
        }else{
            Node<E> next = getNodeOfIndex(index);
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<>(prev,eElem,next);
            next.prev = newNode;
            prev.next = newNode;
            if(next == nFirst){
                nFirst = newNode;
            }
        }
        iSize++;
    }

    //删除指定位置的节点
    public void delNodeOfIndex(int index){
        //检查位置是否越界
        rangeCheck(index);
        if(iSize == 1){
            nFirst = null;
            nLast = null;
        }else{
            Node<E> node = getNodeOfIndex(index);
            Node<E> next = node.next;
            Node<E> prev = node.prev;
            next.prev = prev;
            prev.next = next;
            if(node == nFirst){
                nFirst = next;
            }
            if(node == nLast){
                nLast = prev;
            }
        }
        iSize--;
    }

    //获取指定位置的元素
    public E getElemOfIndex(int index){
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
    public boolean isContais(E eElem){
        return getIndexOfElem(eElem) != ELEMENT_NOT_FOUND;
    }

    //获取任意位置的节点
    private Node<E> getNodeOfIndex(int index){
        //检查位置是否越界
        rangeCheck(index);
        Node<E> node = null;
        if(index < (iSize >> 1)){
            node = nFirst;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }else{
            node = nLast;
            for (int i = iSize - 1; i > index ; i--) {
                node = node.prev;
            }
        }
        return node;
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

    //输出表
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("DoubleCircleLinkedList:").append("size=").append(iSize).append(" [");
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
}
public class DoubleCircleLinkedListDemo {
    public static void main(String[] args) {
        DoubleCircleLinkedList<Integer> list = new DoubleCircleLinkedList<>();
        System.out.println("表是否为空:" + list.isEmpty());
        System.out.println("表长:" + list.getSizeOfList());
        System.out.println(list);
        System.out.println("==========添加节点============");
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
        System.out.println("==========删除节点============");
        for (int i = 0; i < 10; i++) {
            list.delNodeOfIndex(0);
        }
        System.out.println("表是否为空:" + list.isEmpty());
        System.out.println("表长:" + list.getSizeOfList());
        System.out.println(list);
        list.delNodeOfIndex(0);
        list.delNodeOfIndex(list.getSizeOfList() >> 1);
        list.delNodeOfIndex(list.getSizeOfList() - 1);
        System.out.println("表是否为空:" + list.isEmpty());
        System.out.println("表长:" + list.getSizeOfList());
        System.out.println(list);
        System.out.println("==========获取指定元素的位置============");
        System.out.println(list.getIndexOfElem(9));
        System.out.println(list.getIndexOfElem(11));
        System.out.println(list.getIndexOfElem(14));
        System.out.println("==========获取指定位置的元素============");
        System.out.println(list.getElemOfIndex(0));
        System.out.println(list.getElemOfIndex(list.getSizeOfList() >> 1));
        System.out.println(list.getElemOfIndex(list.getSizeOfList() - 1));
        System.out.println("==========判断表中是否包含指定元素============");
        System.out.println(list.isContais(11));
        System.out.println(list.isContais(111));
        System.out.println("==========清空表============");
        list.clear();
        System.out.println("表是否为空:" + list.isEmpty());
        System.out.println("表长:" + list.getSizeOfList());
        System.out.println(list);
    }
}