package _01_LinearList;

import java.lang.reflect.Array;

/**
 * @Classname LinearListDemo 线性表
 * @Description
 * @Date 2020/6/12 20:25
 * @Created by Harman
 */
class LinearList<E> {
    //定义表长
    private int iSize;
    //定义元素集合
    private E[] eArray;

    //定义初始化分配内存dax
    private static final int DEFAULT_CAPACITY = 10;
    //定义查无此元素
    private static final int ELEMENT_NOT_FOUND = -1;

    //元素类型
    private Class<E> eClass;

    //构造函数初始化
    public LinearList(Class<E> eClass){
        this(eClass,DEFAULT_CAPACITY);
    }

    public LinearList(Class<E> eClass,int iCapacity){
        //如果自动以内存大小大于默认分配，则取前者
        iCapacity = iCapacity >= DEFAULT_CAPACITY ? iCapacity : DEFAULT_CAPACITY;
        eArray = (E[]) Array.newInstance(eClass,iCapacity);
        this.eClass = eClass;
    }

    //清空表
    public void clear(){
        iSize = 0;
        //查看是否需要缩容
        shrinkCapacity();
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
    public void addElem2Last(E eElem){
        addElem2Index(iSize,eElem);
    }

    //添加元素至任意位置
    public void addElem2Index(int index,E eElem){
        //检查位置是否越界
        rangeCheck4Add(index);
        //查看是否需要扩容
        enSureCapacity(iSize + 1);
        for (int i = iSize; i > index; i--) {
            eArray[i] = eArray[i - 1];
        }
        eArray[index] = eElem;
        iSize++;
    }

    //删除指定位置的元素
    public void delElemOfIndex(int index){
        //检查位置是否越界
        rangeCheck(index);
        for (int i = index; i < iSize-1 ; i++) {
            eArray[i] = eArray[i + 1];
        }
        iSize--;
        //查看是否需要缩容
        shrinkCapacity();
    }

    //获取指定位置的元素
    public E getElemOfIndex(int index){
        //检查位置是否越界
        rangeCheck(index);
        return eArray[index];
    }

    //查看指定元素在表中的位置
    public int getIndexOfElem(E elem){
        for (int i = 0; i < iSize; i++) {
            if(eArray[i].equals(elem)){
                return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    //查看表中是否包含指定元素
    public boolean isContant(E elem){
        return getIndexOfElem(elem) != ELEMENT_NOT_FOUND;
    }

    //输出表
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("LinearList:").append("size=").append(iSize).append(" [");
        for (int i = 0; i < iSize; i++) {
            if(i != 0){
                sb.append(", ");
            }
            sb.append(eArray[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    //缩容
    private void shrinkCapacity(){
        int iOldCapacity = eArray.length;
        //如果现有内存大小小于默认内存大小，或者表长超过现有内存大小的一半，则不缩容
        if(iOldCapacity <= DEFAULT_CAPACITY || iSize > (iOldCapacity >> 1)) return;
        int iNewCapacity = iOldCapacity >> 1;
        E[] eNewArray = (E[]) Array.newInstance(eClass,iNewCapacity);
        for (int i = 0; i < iSize; i++) {
            eNewArray[i] = eArray[i];
        }
        eArray = eNewArray;
        System.out.println("缩容:将就内存大小为:" + iOldCapacity + ",缩小为:" + iNewCapacity);
    }

    //扩容
    public void enSureCapacity(int iNeedCapacity){
        int iOldCapacity = eArray.length;
        //如果需要内存小于现有内存，则不需要扩容
        if(iNeedCapacity <= iOldCapacity) return;
        int iNewCapacity = iOldCapacity + (iOldCapacity >> 1);
        E[] eNewArray = (E[]) Array.newInstance(eClass,iNeedCapacity);
        for (int i = 0; i < iSize; i++) {
            eNewArray[i] = eArray[i];
        }
        eArray = eNewArray;
        System.out.println("扩容:将旧内存大小为:" + iOldCapacity + "，扩容为:" + iNewCapacity);
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
public class LinearListDemo {
    public static void main(String[] args) {
        LinearList<Integer> arrayList = new LinearList<>(Integer.class);
        System.out.println("表是否为空:" + arrayList.isEmpty());
        System.out.println("表长:" + arrayList.getSizeOfList());
        System.out.println(arrayList);
        System.out.println("=============添加元素===============");
        for (int i = 0; i < 50; i++) {
            arrayList.addElem2Last(i * 2);
        }
        System.out.println("表是否为空:" + arrayList.isEmpty());
        System.out.println("表长:" + arrayList.getSizeOfList());
        System.out.println(arrayList);

        arrayList.addElem2Index(0,-1);
        arrayList.addElem2Index(2,1);
        arrayList.addElem2Index(arrayList.getSizeOfList(),29);
        System.out.println("表是否为空:" + arrayList.isEmpty());
        System.out.println("表长:" + arrayList.getSizeOfList());
        System.out.println(arrayList);
        System.out.println("=============添加元素===============");
        for (int i = 0; i < 40; i++) {
            arrayList.delElemOfIndex(0);
        }
        System.out.println("表是否为空:" + arrayList.isEmpty());
        System.out.println("表长:" + arrayList.getSizeOfList());
        System.out.println(arrayList);
        System.out.println("=============获取指定位置元素===============");
        System.out.println(arrayList.getElemOfIndex(0));
        System.out.println(arrayList.getElemOfIndex(arrayList.getSizeOfList() >> 1));
        System.out.println(arrayList.getElemOfIndex(arrayList.getSizeOfList() - 1));
        System.out.println("=============获取元素在表中的位置===============");
        System.out.println(arrayList.getIndexOfElem(76));
        System.out.println(arrayList.getIndexOfElem(88));
        System.out.println(arrayList.getIndexOfElem(29));
        System.out.println(arrayList.getIndexOfElem(129));
        System.out.println("=============表中是否包含指定元素===============");
        System.out.println(arrayList.isContant(29));
        System.out.println(arrayList.isContant(129));
        System.out.println("=============清空表===============");
        arrayList.clear();
        System.out.println("表是否为空:" + arrayList.isEmpty());
        System.out.println("表长:" + arrayList.getSizeOfList());
        System.out.println(arrayList);
    }
}
