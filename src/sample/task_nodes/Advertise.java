package sample.task_nodes;

import org.tbot.internal.event.events.MessageEvent;
import org.tbot.internal.event.listeners.MessageListener;
import org.tbot.methods.input.keyboard.Keyboard;
import sample.TradeUtils;

/**
 * Created by David on 3/2/2017.
 */

public class Advertise extends TaskNode implements MessageListener {

    public Advertise(final TaskFlowController controller) {
        super(controller, "Advertising");
    }

    @Override
    protected TaskNode verify() {
        return getController().globalInfoExists("trader") ? getChild() : this;
    }

    @Override
    protected void execute() {
        Keyboard.sendText("Test message", true);
    }

    @Override
    public void messageReceived(MessageEvent messageEvent) {
        if (!messageEvent.getSource().equals(MessageEvent.SERVER)) return;
        final String trader = TradeUtils.getTraderFromMessage(messageEvent.getMessage().getText());
        if (trader != null) getController().updateGlobalInfo("trader", trader);
    }
}
