package sample.task_nodes;

import sample.TradeUtils;

/**
 * Created by David on 3/30/2017.
 */
public class WaitForSecondOffer extends TaskNode {
    public WaitForSecondOffer(TaskFlowController controller, String name) {
        super(controller, "Waiting for " + controller.getGlobalValue("trader") + "'s finalization");
    }

    @Override
    protected TaskNode verify() {
        if(!TradeUtils.isTrading()){

        }
        return null;
    }

    @Override
    protected void execute() {

    }
}
