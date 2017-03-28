package sample.task_nodes;

import java.util.Hashtable;

/**
 * Created by David on 3/4/2017.
 */
public class TaskFlowController {
    private final Hashtable<String, String> globalInfo;
    private final TaskNode root, current;

    public TaskFlowController(final TaskNode root) {
        globalInfo = new Hashtable<>();
        this.root = current = root;
    }

    protected boolean globalInfoExists(final String key) {
        return globalInfo.containsKey(key);
    }

    protected void updateGlobalInfo(final String key, final String value) {
        globalInfo.put(key, value);
    }

    protected String getGlobalValue(final String key) {
        return globalInfo.get(key);
    }

}
