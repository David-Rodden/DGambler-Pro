package sample.task_nodes;

import sample.TradeUtils;

/**
 * Created by David on 3/4/2017.
 */
public class WaitForOffer extends TaskNode {
    public WaitForOffer(TaskFlowController controller, String name) {
        super(controller, "Waiting for " + controller.getGlobalValue("trader") + "'s offer");
    }

    @Override
    protected TaskNode verify() {
        //in the future, might want to decline if waiting too long
        return TradeUtils.getCoinsOffered(false) > 0 ? getChild() : this;
    }

    @Override
    protected void execute() {
        TradeUtils.acceptTrade();
    }
}
