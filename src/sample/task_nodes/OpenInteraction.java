package sample.task_nodes;

import org.tbot.methods.Players;
import org.tbot.wrappers.Player;
import sample.TradeUtils;

/**
 * Created by David on 3/3/2017.
 */
public class OpenInteraction extends TaskNode {
    private final String trader;

    public OpenInteraction(final TaskFlowController controller) {
        super(controller, "Opening trade with " + controller.getGlobalValue("trader"));
        trader = controller.getGlobalValue("trader");
    }

    @Override
    protected TaskNode verify() {
        if (TradeUtils.isTrading()) return getChild();
        final Player tradeMatch = Players.getLoaded(trader);
        return tradeMatch != null ? this : getParent();
    }

    @Override
    protected void execute() {
        TradeUtils.tradeWith(Players.getLoaded(trader));
    }
}
