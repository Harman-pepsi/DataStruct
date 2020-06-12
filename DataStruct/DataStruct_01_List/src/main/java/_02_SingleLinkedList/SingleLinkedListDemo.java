package _02_SingleLinkedList;

/**
 * @Classname SingleLinkedListDemo
 * @Description
 * @Date 2020/6/13 1:01
 * @Created by Harman
 */
class SingleLinkedList<E>{
    //定义表长
    private int iSize;
    //定义首个节点
    private Node<E> nFirst;
    //定义查无此元素
    private static final int ELEMENT_NOT_FOUNT = -1;

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
    }

    //清空表
    public void clear(){
        iSize = 0;
        nFirst = null;
    }

    //判断表是否为空
    public boolean isEmptyOfList(){
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
            nFirst = new Node<>(eElem,nFirst);
        }else{
            Node<E> preNode = getNodeOfIndex(index - 1);
            preNode.next = new Node<>(eElem,preNode.next);
        }
        iSize++;
    }

    //删除指定位置的元素
    public void delNodeOfIndex(int index){
        //检查位置是否越界
        rangeCheck(index);
        if(index == 0){
            nFirst = nFirst.next;
        }else{
            Node<E> preNode = getNodeOfIndex(index - 1);
            preNode.next = preNode.next.next;
        }
        iSize--;
    }

    //查看表中指定位置的元素
    public E getElemOfIndex(int index){
        //检查位置是否越界
        rangeCheck(index);
        return getNodeOfIndex(index).eElem;
    }

    //查看指定元素在表中的位置
    public int getIndexOfElem(E eElem){
        Node node = nFirst;
        for (int i = 0; i < iSize; i++) {
            if(node.eElem.equals(eElem)){
                return i;
            }
            node = node.next;
        }
        return ELEMENT_NOT_FOUNT;
    }

    //判断表中是否包含指定元素
    public boolean isContant(E eElem){
        return getIndexOfElem(eElem) != ELEMENT_NOT_FOUNT;
    }

    //翻转(递归)
    public Node<E> recursionOfFlip(Node<E> hNode){
        if(hNode == null || hNode.next == null) return hNode;
        Node<E> newHead = recursionOfFlip(hNode.next);
        hNode.next.next = hNode;
        hNode.next = null;
        return nFirst = newHead;
    }

    //翻转(迭代)
    public Node<E> IteatorOfFlip(Node<E> hNode){
        if(hNode == null || hNode.next == null) return hNode;
        Node<E> newHead = null;
        for (int i = 0; i < iSize; i++) {
            Node<E> nTemp = hNode.next;
            hNode.next = newHead;
            newHead = hNode;
            hNode = nTemp;
        }
        return nFirst = newHead;
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
        sb.append("SingleLinkedList:").append("size=").append(iSize).append(" [");
        Node node = nFirst;
        for (int i = 0; i < iSize; i++) {
            if(i != 0){
                sb.append(", ");
            }
            sb.append(node.eElem);
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
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList<Integer> arrayList = new SingleLinkedList<>();
        System.out.println("表是否为空:" + arrayList.isEmptyOfList());
        System.out.println("表长:" + arrayList.getSizeOfList());
        System.out.println(arrayList);
        System.out.println("==========添加元素==============");
        for (int i = 0; i < 10; i++) {
            arrayList.addNode2Last(i*2);
        }
        System.out.println("表是否为空:" + arrayList.isEmptyOfList());
        System.out.println("表长:" + arrayList.getSizeOfList());
        System.out.println(arrayList);
        arrayList.addNode2Index(0,-1);
        arrayList.addNode2Index(2,1);
        arrayList.addNode2Index(arrayList.getSizeOfList(),arrayList.getSizeOfList());
        System.out.println("表是否为空:" + arrayList.isEmptyOfList());
        System.out.println("表长:" + arrayList.getSizeOfList());
        System.out.println(arrayList);
        System.out.println("==========删除元素==============");
        for (int i = 0; i < 5; i++) {
            arrayList.delNodeOfIndex(0);
        }
        System.out.println("表是否为空:" + arrayList.isEmptyOfList());
        System.out.println("表长:" + arrayList.getSizeOfList());
        System.out.println(arrayList);
        arrayList.delNodeOfIndex(0);
        arrayList.delNodeOfIndex(2);
        arrayList.delNodeOfIndex(arrayList.getSizeOfList() - 1);
        System.out.println("表是否为空:" + arrayList.isEmptyOfList());
        System.out.println("表长:" + arrayList.getSizeOfList());
        System.out.println(arrayList);
        System.out.println("==========获取指定位置的元素==============");
        System.out.println(arrayList.getElemOfIndex(0));
        System.out.println(arrayList.getElemOfIndex(arrayList.getSizeOfList() >> 1));
        System.out.println(arrayList.getElemOfIndex(arrayList.getSizeOfList() - 1));
        System.out.println("==========获取指定元素在表中的位置==============");
        System.out.println(arrayList.getIndexOfElem(8));
        System.out.println(arrayList.getIndexOfElem(14));
        System.out.println(arrayList.getIndexOfElem(18));
        System.out.println("==========判断表中是否包含指定元素==============");
        System.out.println(arrayList.isContant(14));
        System.out.println(arrayList.isContant(114));
        System.out.println("==========清空表==============");
        arrayList.clear();
        System.out.println("表是否为空:" + arrayList.isEmptyOfList());
        System.out.println("表长:" + arrayList.getSizeOfList());
        System.out.println(arrayList);
    }
}
