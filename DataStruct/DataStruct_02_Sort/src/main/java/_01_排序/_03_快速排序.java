package _01_排序;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Classname _03_快速排序
 * @Description TODO
 * @Date 2020/11/21 21:54
 * @Created by XJC·AW
 */
public class _03_快速排序 {
    public static void main(String[] args) {
        List<Integer> randomList = createRandomList();
        fastSort(randomList,0,randomList.size() - 1);
        System.out.println(randomList.toString());
    }

    //生成十个随机数集合
    private static List<Integer> createRandomList(){
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        int iRandom = 0, i = 0;
        do{
            iRandom = random.nextInt(100);
            if(!list.contains(iRandom)){
                list.add(iRandom);
            }
        }while (list.size() <= 10);
        return list;
    }

    //快速排序
    private static void fastSort(List<Integer> list,int initLeft,int initRight){
        if(null == list || list.isEmpty() || initLeft > initRight) return;
        int iLeft=initLeft,iRight = initRight,baseNum = list.get(initLeft);
        while (iLeft < iRight){
            while (iLeft < iRight && list.get(iRight) >= baseNum){
                iRight--;
            }
            while (iLeft < iRight && list.get(iLeft) <= baseNum){
                iLeft++;
            }
            if(iLeft < iRight){
                swap(list,iLeft,iRight);
            }
        }
        swap(list,initLeft,iLeft);
        fastSort(list,initLeft,iLeft - 1);
        fastSort(list,iRight + 1,initRight);
    }

    //交换
    private static void swap(List<Integer> list,int iFrom,int iTo){
        int iTemp = list.get(iFrom);
        list.set(iFrom,list.get(iTo));
        list.set(iTo,iTemp);
    }
}
