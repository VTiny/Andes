package com.mrliuxia.andes.working.list_refector;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Set;

/**
 * Description:
 * Author: liuxia
 * Data: 1/11/21
 *
 * @blame: liuxia
 */
@SuppressWarnings("unused")
public interface IWorker {

    @NonNull
    Set<JarvisCommand> getCommandSet();

    interface IListWorker {
        void updateList(List<String> list);
    }

}
