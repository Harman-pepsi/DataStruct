package _01_排序;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Classname _02_选择排序
 * @Description TODO
 * @Date 2020/11/21 21:42
 * @Created by XJC·AW
 */
public class _02_选择排序 {
    public static void main(String[] args) {
        List<Integer> randomList = createRandomList();
        selectSort(randomList);
        System.out.println(randomList.toString());
    }

    //生成十个随机数的集合
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

    //选择排序
    private static void selectSort(List<Integer> list){
        int key = 0;
        for (int i = 0; i < list.size(); i++) {
            key = i;
            for (int j = key + 1; j < list.size(); j++) {
                if(list.get(j) < list.get(key)){
                    key = j;
                }
            }
            if(key != i){
                int iTemp = list.get(i);
                list.set(i,list.get(key));
                list.set(key,iTemp);
            }
        }
    }
}
