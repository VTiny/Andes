package com.mrliuxia.andes.working.list_refector;

import androidx.annotation.NonNull;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 * Author: liuxia
 * Data: 1/11/21
 *
 * @blame: liuxia
 */
public abstract class BaseWorker implements IWorker{

    private final IJarvis mIJarvis;
    private final Set<JarvisCommand> mCommandSet;

    public BaseWorker(IJarvis IJarvis) {
        mIJarvis = IJarvis;
        mCommandSet = new HashSet<>();
        try {
            Method[] methods = getClass().getMethods();
            for (Method m : methods) {
                Jarvis jarvis = m.getAnnotation(Jarvis.class);
                if (jarvis != null) {
                    mCommandSet.add(jarvis.command());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean dispatch(JarvisCommand command, Object args) {
        return mIJarvis.dispatchCommand(command, args);
    }

    @NonNull
    @Override
    public Set<JarvisCommand> getCommandSet() {
        return mCommandSet;
    }
}
