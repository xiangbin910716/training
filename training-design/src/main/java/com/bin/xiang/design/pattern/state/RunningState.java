package com.bin.xiang.design.pattern.state;

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
public class RunningState extends LiftState {

    @Override
    public void setContext(Context _context) {
        super.context = _context;
    }

    @Override
    public void open() {
        //do nothing
    }

    @Override
    public void close() {//虽然可以关门，但这个动作不归我执行
        //状态修改
        super.context.setLiftState(Context.closeingState);
        //动作委托为CloseState来执行，也就是委托给了ClosingState子类执行这个动作
        super.context.getLiftState().close();

    }

    @Override
    public void run() {
        System.out.println("电梯运行...");
    }

    @Override
    public void stop() {
        //状态修改
        super.context.setLiftState(Context.stoppingState);
        //动作委托为CloseState来执行，也就是委托给了ClosingState子类执行这个动作
        super.context.getLiftState().stop();
    }
}
