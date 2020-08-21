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
public class ThirdGear extends GearState {

    ThirdGear(Bike bike) {
        super(bike);
    }

    @Override
    public void gearUp() {
        System.out.println("Already in the ThirdGear - cannot go higher");
    }

    @Override
    public void gearDown() {
        System.out.println("Moving Down from ThirdGear to SecondGear");
        bike.gearState =  new SecondGear(bike);
    }
}
