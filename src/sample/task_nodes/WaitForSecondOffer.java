package sample.task_nodes;

import org.tbot.methods.Time;
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
        return TradeUtils.isSecondTradeOpen() ? (getInstanceElapsed() > TradeUtils.TRADE_TIMOUT_SEC ? getChild() : this) : getParent();
    }

    @Override
    protected void execute() {
        getController().updateGlobalInfo("receiving", String.valueOf(TradeUtils.getCoinsOffered(false)));
        TradeUtils.acceptTrade();
        Time.sleepUntil(() -> !TradeUtils.isTrading(), TradeUtils.TRADE_TIMEOUT);
    }
}
