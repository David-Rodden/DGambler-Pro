package sample.task_nodes;

import org.tbot.wrappers.Timer;

/**
 * Created by David on 3/2/2017.
 */
public abstract class TaskNode {
    private final TaskFlowController controller;
    private final String name;
    private TaskNode parent, child;
    private Timer instanceLife;

    public TaskNode(final TaskFlowController controller, final String name) {
        this.controller = controller;
        this.name = name;
    }

    /**
     * Verifies and returns which node should be accessed
     *
     * @return TaskNode either parent, self, or child
     */
    protected abstract TaskNode verify();

    /**
     * Emits an action based on what is encapsulated
     */
    protected abstract void execute();


    /**
     * @return String as the name of the task
     */
    protected String getName() {
        return name;
    }

    /**
     * @return TaskNode as the node from which this node derives
     */
    public TaskNode getParent() {
        return parent;
    }

    /**
     * @return TaskNode as the node which is the child of this node
     */
    public TaskNode getChild() {
        return child;
    }

    /**
     * @return the controller of which this node is a cog
     */
    public TaskFlowController getController() {
        return controller;
    }

    /**
     * Initializes the TaskNode's time attribute
     * in order to record elapsed instance time
     */
    public void initializeInstance() {
        instanceLife = new Timer();
    }

    /**
     * Error incurs if {@link #initializeInstance()} hasn't been called
     * @return int as the elapsed time of the instance
     */
    public int getInstanceElapsed() {
        return (int) (instanceLife.getElapsed() / 1000);
    }
}
