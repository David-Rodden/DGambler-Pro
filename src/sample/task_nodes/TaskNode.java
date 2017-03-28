package sample.task_nodes;

/**
 * Created by David on 3/2/2017.
 */
public abstract class TaskNode {
    private final TaskFlowController controller;
    private final String name;
    private TaskNode parent, child;

    public TaskNode(final TaskFlowController controller, final String name) {
        this.controller = controller;
        this.name = name;
    }

    protected abstract TaskNode verify();

    protected abstract void execute();

    protected String getName() {
        return name;
    }

    public TaskNode getParent() {
        return parent;
    }

    public TaskNode getChild() {
        return child;
    }

    public TaskFlowController getController() {
        return controller;
    }
}
