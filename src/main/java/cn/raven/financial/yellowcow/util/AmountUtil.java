package cn.raven.financial.yellowcow.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 浮点计算工具类
 */
public class AmountUtil {

    /**
     * 默认小数精度
     */
    private final static int DEFAULT_AMOUNT_SCALE = 2;
    private final static int DEFAULT_ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;

    /**
     * 数字格式化对象
     */
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance();

    /**
     * 转换成四舍五入之后之后的金额
     *
     * @param value
     * @return
     */
    public static BigDecimal toAmount(BigDecimal value) {
        return value.setScale(DEFAULT_AMOUNT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 进行四舍五入操作
     *
     * @param value
     * @param length
     * @return
     */
    public static double round(double value, int length) {
        return new BigDecimal(Double.toString(value)).divide(BigDecimal.ONE, length, DEFAULT_ROUNDING_MODE).doubleValue();
    }

    /**
     * 进行向上取整操作
     *
     * @param value
     * @param length
     * @return
     */
    public static double celling(double value, int length) {
        return new BigDecimal(Double.toString(value)).divide(BigDecimal.ONE, length, BigDecimal.ROUND_CEILING).doubleValue();
    }

    /**
     * 进行向下取整操作
     *
     * @param value
     * @param length
     * @return
     */
    public static double floor(double value, int length) {
        return new BigDecimal(Double.toString(value)).divide(BigDecimal.ONE, length, BigDecimal.ROUND_FLOOR).doubleValue();
    }

    /**
     * 格式化数字，不使用千分位分隔数字
     *
     * @param value
     * @return
     */
    public static String formatWithoutGroupingUsed(BigDecimal value) {
        NUMBER_FORMAT.setGroupingUsed(false);
        return NUMBER_FORMAT.format(value);
    }

    /**
     * ROUND_HALF_UP AND Compare
     *
     * @param a
     * @param b
     * @return
     */
    public static int roundUpAndCompare(BigDecimal a, BigDecimal b) {
        return toAmount(a).compareTo(toAmount(b));
    }

    /**
     * 锁定精度计算是否相当
     *
     * @param a
     * @param b
     * @return a > b = 1 a < b = -1 a == b = 0
     */
    public static int equals(BigDecimal a, BigDecimal b) {
        double x = toAmount(a).doubleValue();
        double y = toAmount(b).doubleValue();
        if (x > y) {
            return 1;
        } else if (x == y) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 进行除法运算
     *
     * @param p1
     * @param p2
     * @param length 保留小数精度
     * @return
     */
    public static BigDecimal divide(BigDecimal p1, BigDecimal p2, int length) {
        return p1.divide(p2, length, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 除法
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal divide(BigDecimal a, BigDecimal b){
        return a.divide(b,DEFAULT_AMOUNT_SCALE,DEFAULT_ROUNDING_MODE);
    }

    /**
     * 乘法
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b).setScale(DEFAULT_AMOUNT_SCALE,DEFAULT_ROUNDING_MODE);
    }

    /**
     * 进行加法运算
     *
     * @param p1
     * @param p2
     * @return
     */
    public static BigDecimal add(BigDecimal p1, BigDecimal p2) {
        return p1.add(p2);
    }

    /**
     * 减法
     *
     * @param p1
     * @param p2
     * @return
     */
    public static BigDecimal sub(BigDecimal p1, BigDecimal p2) {
        return p1.subtract(p2);
    }
}
