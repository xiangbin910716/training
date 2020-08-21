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
public class FirstGear extends GearState {


    FirstGear(Bike bike) {
        super(bike);
    }

    @Override
    public void gearUp() {
        System.out.println("Moving Up from FirstGear to SecondGear");
        bike.gearState =  new SecondGear(bike);
    }

    @Override
    public void gearDown() {
        System.out.println("Already in the FirstGear - cannot go lower");
    }
}
