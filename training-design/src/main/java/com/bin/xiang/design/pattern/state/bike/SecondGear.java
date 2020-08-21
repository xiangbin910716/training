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
public class SecondGear extends GearState {

    SecondGear(Bike bike) {
        super(bike);
    }

    @Override
    public void gearUp() {
        System.out.println("Moving Up from SecondGear to ThirdGear");
        bike.gearState =  new ThirdGear(bike);
    }

    @Override
    public void gearDown() {
        System.out.println("Moving Down from SecondGear to FirstGear");
        bike.gearState =  new FirstGear(bike);
    }
}
