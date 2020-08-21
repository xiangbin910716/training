package com.bin.xiang.design.pattern.state.bike;

/**
 * <p>
 *
 * </p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @since 1.0
 */
public class StateDemo {
    public static void main(String[] args) {
        Bike bike = new Bike();
        bike.gearDown();
        bike.gearUp();
        bike.gearUp();
        bike.gearUp();
        bike.gearUp();
        bike.gearDown();
        bike.gearDown();
        bike.gearDown();
        bike.gearDown();
    }
}
