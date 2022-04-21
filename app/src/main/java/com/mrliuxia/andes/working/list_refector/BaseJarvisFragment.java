package com.mrliuxia.andes.working.list_refector;

import android.content.Context;
import androidx.annotation.NonNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: liuxia
 * Data: 1/11/21
 *
 * @blame: liuxia
 */
public class BaseJarvisFragment extends BaseFragment implements IJarvis {

    private final Map<WorkerType, IWorker> mWorkerMap = new LinkedHashMap<>();
    private final Map<JarvisCommand, List<Method>> mCommandMethodMap = new HashMap<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onCreateWorkers(mWorkerMap);
    }

    protected void onCreateWorkers(@NonNull Map<WorkerType, IWorker> workerMap) {

    }

    private void processWorkerMethods(@NonNull Map<WorkerType, IWorker> workerMap) {
        for (Map.Entry<WorkerType, IWorker> entry : mWorkerMap.entrySet()) {
            if (entry != null && entry.getKey() != null && entry.getValue() != null) {
                Method[] methods = entry.getClass().getMethods();
                for (Method m : methods) {
                    Jarvis jarvis = m.getAnnotation(Jarvis.class);
                    JarvisCommand command = jarvis.command();
                    if (mCommandMethodMap.get(command) == null) {
                        mCommandMethodMap.put(command, new ArrayList<>());
                    }
                    mCommandMethodMap.get(command).add(m);
                }
            }
        }
    }

    @Override
    public boolean dispatchCommand(JarvisCommand command, Object args) {
        if (mCommandMethodMap.containsKey(command)) {
            List<Method> methodList = mCommandMethodMap.get(command);
        }
        return false;
    }

}
