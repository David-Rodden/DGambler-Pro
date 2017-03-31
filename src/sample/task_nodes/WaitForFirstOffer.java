package sample.task_nodes;

import sample.TradeUtils;

/**
 * Created by David on 3/4/2017.
 */
public class WaitForFirstOffer extends TaskNode {
    public WaitForFirstOffer(TaskFlowController controller) {
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
