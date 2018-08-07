package Problems;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Date;

public class Find_Factor_Using_Continued_Fraction {
    public static void main(String[] args) {
        Date start_time =  new Date(); //开始时间
        final int scale = 50;

        // 计算N
        BigInteger N = new BigInteger("2").pow(101).subtract(BigInteger.ONE);
        BigDecimal sqrt_N = Sqrt(N);

        // 初始化P_{k},Q_{k}序列
        BigInteger seq_P = BigInteger.ZERO;
        BigInteger seq_Q = BigInteger.ONE;

        // 初始化seq_a, seq_p序列
        BigInteger a0 = sqrt_N.toBigInteger();
        BigInteger seq_a = a0;
        BigInteger[] seq_p = {BigInteger.ZERO, a0, BigInteger.ZERO};

        int i = 1; // 项数

        while(true){

            // 处理P_{k},Q_{k}序列
            seq_P = seq_a.multiply(seq_Q).subtract(seq_P);
            seq_Q = (N.subtract(seq_P.pow(2))).divide(seq_Q);
            seq_a = (new BigDecimal(seq_P.toString()).add(sqrt_N))
                    .divide(new BigDecimal(seq_Q.toString()), scale, BigDecimal.ROUND_HALF_UP)
                    .toBigInteger();

            // 处理p_{k}序列
            if(i == 1)
                seq_p[2] = a0.multiply(seq_a).add(BigInteger.ONE);
            else {
                seq_p[0] = seq_p[1];
                seq_p[1] = seq_p[2];
                seq_p[2] = seq_a.multiply(seq_p[1]).add(seq_p[0]);
            }

            // 分解因式
            if(i%2==0 && is_square(seq_Q)){

                BigInteger s = Sqrt(seq_Q).toBigInteger();
                BigInteger factor0 = N.gcd(seq_p[1].subtract(s));
                BigInteger factor1 = N.gcd(seq_p[1].add(s));

                if(factor0.compareTo(BigInteger.ONE) != 0 && factor1.compareTo(BigInteger.ONE) != 0){
                    System.out.println(String.format("第%d项：%s,%s", i,factor0,factor1));
                    Date end_time1 =  new Date(); // 结束时间
                    Long cost_time1 = end_time1.getTime()-start_time.getTime();  // 计算时间，返回毫秒数
                    System.out.println(String.format("程序运行完毕！耗时：%.3fs.", cost_time1*1.0/1000));
                    break;
                }
            }

            if(i%10000 == 0)
                System.out.println(String.format("已运行到第%d项", i));

            i += 1;

        }

    }

    // 判断是否为完全平方数
    public static boolean is_square(BigInteger s){
        // 牛顿法求解平方根, 求解a的平方根
        // x为a的平方根，x的初始值为1， 按x = (x+a/x)/2迭代， 误差为error
        // 若取初始值x0=1, 则当a>1时, 由数学归纳法知, xn > sqrt(a), n>1, 且xn递减
        BigDecimal x = BigDecimal.ONE;
        BigDecimal a = new BigDecimal(s.toString());
        BigDecimal eps = new BigDecimal("1");
        final BigDecimal error = new BigDecimal("1E-10");
        int scale = 50;

        // 进入循环
        while(eps.compareTo(error) == 1){ // eps > error
            x = x.add(a.divide(x, scale, BigDecimal.ROUND_HALF_UP))
                    .divide(new BigDecimal("2.0"), scale, BigDecimal.ROUND_HALF_UP);
            eps = x.multiply(x).subtract(a).abs();
        }

        BigInteger sqrt = x.toBigInteger();
        return sqrt.pow(2).equals(s)?true:false;

    }

    // 求解a的平方根
    public static BigDecimal Sqrt(BigInteger s){
        // 牛顿法求解平方根, 求解a的平方根
        // x为a的平方根，x的初始值为1， 按x = (x+a/x)/2迭代， 误差为error
        // 若取初始值x0=1, 则当a>1时, 由数学归纳法知, xn > sqrt(a), n>1, 且xn递减
        BigDecimal x = BigDecimal.ONE;
        BigDecimal a = new BigDecimal(s.toString());
        BigDecimal eps = new BigDecimal("1");
        final BigDecimal error = new BigDecimal("1E-10");
        int scale = 50;

        // 进入循环
        while(eps.compareTo(error) == 1){ // eps > error
            x = x.add(a.divide(x, scale, BigDecimal.ROUND_HALF_UP))
                       .divide(new BigDecimal("2.0"), scale, BigDecimal.ROUND_HALF_UP);
            eps = x.multiply(x).subtract(a).abs();
        }
        return x;
    }
}

/*
 * 运行结果:
 * 2**67-1: 0.6s
 * 2**71-1: 3.5s
 * 2**79-1: 2.0s
 * 2**83-1: 55.3s
 */
