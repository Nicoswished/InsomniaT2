package plugin.nicohaxz.Utils;

import plugin.nicohaxz.main;

import java.lang.reflect.InvocationTargetException;

public class ManagerUtils {
    private main instance;
    public ManagerUtils(main instance) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.instance = instance;
    }
}

