package cc.baka9.catseedlogin.task;

import cc.baka9.catseedlogin.CatSeedLogin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Task implements Runnable {
    private static TaskAutoKick taskAutoKick;
    private static TaskSendLoginMessage taskSendLoginMessage;
    private static List<BukkitTask> bukkitTaskList = new ArrayList<>();
    private static CatSeedLogin plugin = CatSeedLogin.instance;

    protected Task() {
    }

    public static TaskAutoKick getTaskAutoKick() {
        if (taskAutoKick == null) {
            taskAutoKick = new TaskAutoKick();
        }
        return taskAutoKick;

    }

    public static TaskSendLoginMessage getTaskSendLoginMessage() {
        if (taskSendLoginMessage == null) {
            taskSendLoginMessage = new TaskSendLoginMessage();
        }
        return taskSendLoginMessage;

    }

    public static void runAll() {
        runTaskTimer(Task.getTaskSendLoginMessage(), 20 * 5);
        runTaskTimer(Task.getTaskAutoKick(), 20 * 10);

    }

    public static void cancelAll() {
        Iterator<BukkitTask> iterator = bukkitTaskList.iterator();
        while (iterator.hasNext()) {
            iterator.next().cancel();
            iterator.remove();
        }

    }

    public static void runTaskTimer(Runnable runnable, long l) {
        bukkitTaskList.add(plugin.getServer().getScheduler().runTaskTimer(plugin, runnable, 0, l));

    }
}
