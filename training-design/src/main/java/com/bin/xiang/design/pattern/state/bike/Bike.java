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
public class Bike {

    GearState gearState;
    public Bike(){
        gearState = new FirstGear(this);
    }

    public void gearUp(){
        gearState.gearUp();
    }
    public void gearDown(){
        gearState.gearDown();
    }
}
