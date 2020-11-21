package _01_排序;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Classname _01_冒泡排序
 * @Description TODO
 * @Date 2020/11/21 21:35
 * @Created by XJC·AW
 */
public class _01_冒泡排序 {
    public static void main(String[] args) {
        List<Integer> randomList = createRandomList();
        bubbleSort(randomList);
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

    //冒泡排序
    private static void bubbleSort(List<Integer> list){
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if(list.get(j) > list.get(j + 1)){
                    int iTemp = list.get(j);
                    list.set(j,list.get(j + 1));
                    list.set(j + 1,iTemp);
                }
            }
        }
    }
}
