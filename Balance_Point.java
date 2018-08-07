package Problems;

import java.util.ArrayList;
import java.util.Date;

// 平衡点问题(balance point problem)
public class Balance_Point {
    public static void main(String[] args){

        // 创建测试数组a
        ArrayList<Integer> a = new ArrayList<>();
        for(int i=0; i<50000; i++) {
            a.add(1); a.add(-1);
        }
        int[] b = {1,3,5,7,8,25,4,20};
        for(int i: b) {a.add(i);}

        // 测试两种不同算法的消耗时间
        Date t1 =  new Date(); // 开始时间
        int index = balance_point(a);
        Date t2 =  new Date(); // 一般算法结束时间
        Long cost_time1 = t2.getTime()-t1.getTime();
        if(index != -1)
            System.out.println(String.format("一般算法：%d -> %d, 耗时：%.2fs.", index, a.get(index), cost_time1*1.0/1000));
        Date t3 =  new Date(); // 开始时间
        int index_opt = balance_point_opt(a);
        Date t4 =  new Date(); // 优化算法结束时间
        Long cost_time2 = t4.getTime()-t3.getTime();
        if(index != -1)
            System.out.println(String.format("优化算法：%d -> %d, 耗时：%.2fs.", index_opt, a.get(index_opt), cost_time2*1.0/1000));

    }

    // 一般算法
    public static int balance_point(ArrayList<Integer> a){

        /* 遍历第2个至倒数第2个元素，分别计算左半部分和右半部分的和
         * 如果左半部分和右半部分的和，则返回该下标
         */
        for(int i=1; i<a.size()-1;i++){
            int left_sum = 0; // 左半部分的和
            for(int j=0; j<i; j++)
                left_sum += a.get(j);
            int right_sum = 0; // 右半部分的和
            for(int k=i+1; k<a.size(); k++)
                right_sum += a.get(k);
            if(left_sum == right_sum)
                return i;
        }

        return -1;
    }

    // 优化算法
    public static int balance_point_opt(ArrayList<Integer> a){

        int left_sum = 0;
        int right_sum = 0;
        for(int i=1; i<a.size();i++)
            right_sum += a.get(i);

        // 下标为1,2,3,...,a.length-2时的情形
        for(int i=1; i<a.size()-1;i++){
            left_sum += a.get(i-1);
            right_sum -= a.get(i);
            if(left_sum == right_sum)
                return i;
        }

        return -1;
    }

}

