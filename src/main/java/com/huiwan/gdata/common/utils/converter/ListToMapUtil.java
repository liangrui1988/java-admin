package com.huiwan.gdata.common.utils.converter;
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
/** 
 *  
 * 转换工具 
 *  
 * @date 2016/12/7 
 * @author ruiliang 
 * @version 1.0.0 
 * 
 */  
public class ListToMapUtil {  
    /** 
     * 把list集合分片到指定槽位的map上 
     *  
     * @param list 
     * @param slotPosition 
     *            map大小->槽位 
     * @return 
     */  
    public static <T> Map<Integer, List<T>> shardingToMap(List<T> list, int slotPosition) {  
  
        // 返回数据集合  
        Map<Integer, List<T>> hmap = new HashMap<Integer, List<T>>();  
        int list_size = list.size();  
        int thread_exec_count = slotPosition;// 每个线程分的数据数  
        int thread_num = 1;// 根据数据量 求出线程数  
        int list_remainder_num = 0;// 集合余数  
        // 先把list集合进行分片处理  
        if (list_size > thread_exec_count) {  
            thread_num = list_size / thread_exec_count;  
            if (list_size % thread_exec_count > 0) {// 有余数  
                // 求集合余数  
                list_remainder_num = thread_num * thread_exec_count;  
                thread_num++;  
            }  
        }  
  
        if (thread_num == 1) {  
            // 如果1个槽位可以装下，直接返回一个  
            hmap.put(1, list);  
        }  
  
        // ~~~~~~~~~~~~~~~~~~~如果数据需要多线程处理~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  
        // 根据j,来反推key  
        int key = 0;  
        int j = 1;  
        List<T> temp = new ArrayList<>();  
        for (T t : list) {  
            temp.add(t);  
            // 每过一个线程，K就变+1,根据线程来归纳数据  
            if (j >= thread_exec_count) {  
                // System.out.println("temp:" + temp);  
                hmap.put(key, temp);  
                temp = new ArrayList<>();  
                j = 0;  
                key++;// key++ 相当于在化分大集合  
            }  
            j++;  
        }  
  
        // 余数加入map  
        hmap.put(-1, list.subList(list_remainder_num, list_size));  
        return hmap;  
    }  
  
    public static void main(String[] args) {  
        List<Long> accounts = new ArrayList<>();  
        for (long i = 1; i < 103; i++) {  
            accounts.add(i);  
        }  
  
        System.out.println(accounts);  
        Map<Integer, List<Long>> maps = ListToMapUtil.shardingToMap(accounts, 5);  
        System.out.println(maps);  
    }  
  
    /** 
     * oputput: 
     * <p> 
     * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 
     * 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 
     * 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 
     * 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
     * 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 
     * 93, 94, 95, 96, 97, 98, 99, 100, 101, 102] 
     * <p> 
     * {0=[1, 2, 3, 4, 5], 1=[6, 7, 8, 9, 10], 2=[11, 12, 13, 14, 15], 3=[16, 
     * 17, 18, 19, 20], 4=[21, 22, 23, 24, 25], 5=[26, 27, 28, 29, 30], 6=[31, 
     * 32, 33, 34, 35], 7=[36, 37, 38, 39, 40], 8=[41, 42, 43, 44, 45], 9=[46, 
     * 47, 48, 49, 50], 10=[51, 52, 53, 54, 55], 11=[56, 57, 58, 59, 60], 
     * 12=[61, 62, 63, 64, 65], 13=[66, 67, 68, 69, 70], 14=[71, 72, 73, 74, 
     * 75], 15=[76, 77, 78, 79, 80], 17=[86, 87, 88, 89, 90], 16=[81, 82, 83, 
     * 84, 85], 19=[96, 97, 98, 99, 100], 18=[91, 92, 93, 94, 95], -1=[101, 
     * 102]} 
     * <p> 
     *  
     */  
  
}  