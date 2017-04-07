package sample.task_nodes;

import java.util.Hashtable;

/**
 * Created by David on 3/4/2017.
 */
public class TaskFlowController {
    private final Hashtable<String, String> globalInfo;
    private final TaskNode root;
    private TaskNode current;

    /**
     * Initializes the TaskFlowController with the initial TaskNode root
     * Should map out entire TradeUtil in separate class and simply input the root
     *
     * @param root
     */
    public TaskFlowController(final TaskNode root) {
        globalInfo = new Hashtable<>();
        this.root = current = root;
    }

    /**
     * Verifies if the TaskNode is the correct one to be executed
     * If so, executes, otherwise, traverses according to TaskNode's conditions
     * Relies on mapping fed to constructor.
     */
    public void execute() {
        final TaskNode verifiedTask = current.verify();
        if (verifiedTask != current) {
            current = verifiedTask;
            return;
        }
        current.execute();
    }

    /**
     * Informs whether a value for an input string exists within the hashtable
     *
     * @param key
     * @return boolean for whether a specific key is found in the global hashtable
     */
    protected boolean globalInfoExists(final String key) {
        return globalInfo.containsKey(key);
    }


    /**
     * Can be used to either add or update specific values in the hashtable
     *
     * @param key   as the input used to then look up the value in the hashtable
     * @param value as the value which will be added or override the previous one
     */
    protected void updateGlobalInfo(final String key, final String value) {
        globalInfo.put(key, value);
    }

    /**
     * Obtain a global value that exists by inputting the respective key String
     *
     * @param key
     * @return String being the value based on a certain input key
     */
    protected String getGlobalValue(final String key) {
        return globalInfo.get(key);
    }

    /**
     * Removes the value from the hashtable
     * @param key
     */
    protected void removeGlobalValue(final String key) {
        globalInfo.remove(key);
    }
}
