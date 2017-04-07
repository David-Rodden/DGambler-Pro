package sample.task_nodes;

import org.tbot.methods.input.keyboard.Keyboard;

/**
 * Created by David on 4/6/2017.
 */
public class MentionWin extends TaskNode {
    public MentionWin(TaskFlowController controller) {
        super(controller, "Mentioning " + controller.getGlobalValue("trader") + " has won");
    }

    @Override
    protected TaskNode verify() {
        return getController().globalInfoExists("rolled") ? this : getChild();
    }

    /**
     * Remove rolled from hashtable so that verifier knows that a win has been mentioned
     */
    @Override
    protected void execute() {
        final TaskFlowController controller = getController();
        controller.removeGlobalValue("rolled");
        Keyboard.sendText(controller.getGlobalValue("trader") + " has won! Paying out " + 2 * Integer.parseInt(controller.getGlobalValue("receiving")) + " GP!");
    }
}
