package Problems;

import java.math.BigInteger;
import java.math.BigDecimal;

public class IS_Square {
    public static void main(String[] args) {

        // 计算2**128+1
        BigInteger a0 = new BigInteger("2").pow(128).add(BigInteger.ONE);
        //BigInteger a0 = new BigInteger("101");

        // 牛顿法求解平方根, 求解a的平方根
        // x为a的平方根，x的初始值为1， 按x = (x+a/x)/2迭代， 误差为error
        // 若取初始值x0=1, 则当a>1时, 由数学归纳法知, xn > sqrt(a), n>1, 且xn递减
        BigDecimal x = BigDecimal.ONE;
        BigDecimal a = new BigDecimal(a0.toString());
        BigDecimal eps = new BigDecimal("1");
        final BigDecimal error = new BigDecimal("1E-50");
        int scale = 100;
        while(eps.compareTo(error) == 1){
            x = x.add(a.divide(x, scale, BigDecimal.ROUND_HALF_UP))
                       .divide(new BigDecimal("2.0"), scale, BigDecimal.ROUND_HALF_UP);
            eps = x.multiply(x).subtract(a).abs();
        }
        // System.out.println(x); // x为sqrt(a)的近似值
        BigInteger s = x.toBigInteger();
        if(s.multiply(s).equals(a0))
            System.out.println(String.format("%s是完全平方数.", a));
        else
            System.out.println(String.format("%s不是完全平方数.", a));
    }

}

