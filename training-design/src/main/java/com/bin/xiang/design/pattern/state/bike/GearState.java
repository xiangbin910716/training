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
public abstract  class GearState {
    Bike bike;
    GearState(Bike bike){
        this.bike = bike;
    }
    public abstract void gearUp();
    public abstract void gearDown();
}
