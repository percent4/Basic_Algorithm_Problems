package Problems;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Square_Root {
    public static void main(String[] args) {

        // 计算2**128+1
        BigInteger F7 = new BigInteger("2").pow(128).add(BigInteger.ONE);
        // BigInteger F7 = new BigInteger("100");

        // 牛顿法求解平方根, 求解a的平方根
        // x为a的平方根，x的初始值为1， 按x = (x+a/x)/2迭代， 误差为error
        // 若取初始值x0=1, 则当a>1时, 由数学归纳法知, xn > sqrt(a), n>1, 且xn递减
        BigDecimal x = BigDecimal.ONE;
        BigDecimal a = new BigDecimal(F7.toString());
        BigDecimal eps = new BigDecimal("1");
        final BigDecimal error = new BigDecimal("1E-50");
        int scale = 100;
        while(eps.compareTo(error) == 1){
            x = x.add(a.divide(x, scale, BigDecimal.ROUND_HALF_UP))
                    .divide(new BigDecimal("2.0"), scale, BigDecimal.ROUND_HALF_UP);
            // System.out.println(x);
            eps = x.multiply(x).subtract(a).abs();
        }
        System.out.println(x); // x为sqrt(a)的近似值
        // 输出结果: 18446744073709551616.0000000000000000000271050543121376108501863200217485427856246176011108443465371389950279036268398354
    }
}
