package sample.task_nodes;

import org.tbot.methods.input.keyboard.Keyboard;

import java.util.Random;

/**
 * Created by David on 4/6/2017.
 */
public class RollDice extends TaskNode {
    public RollDice(TaskFlowController controller) {
        super(controller, "Rolling the dice");
    }

    /**
     * Currently only checks for whether a roll is 60+ for payout
     *
     * @return
     */
    @Override
    protected TaskNode verify() {
        final TaskFlowController controller = getController();
        return controller.globalInfoExists("rolled") ? Integer.parseInt(controller.getGlobalValue("rolled")) >= 60 ? getChild() : getParent() : this;
    }

    /**
     * Rolls a value between 1 and 100 and stores it within the global hashtable as "rolled"
     */
    @Override
    protected void execute() {
        final TaskFlowController controller = getController();
        final String rolled = String.valueOf(new Random().nextInt(100) + 1);
        Keyboard.sendText(controller.getGlobalValue("trader") + " rolls a " + rolled);
        getController().updateGlobalInfo("rolled", rolled);
    }
}
