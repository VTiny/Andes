package com.mrliuxia.andes.working.list_refector;

import java.util.List;

/**
 * Description:
 * Author: liuxia
 * Data: 1/11/21
 *
 * @blame: liuxia
 */
public class ListWorker extends BaseWorker implements IWorker.IListWorker {

    public ListWorker(IJarvis IJarvis) {
        super(IJarvis);
    }

    @Jarvis(command = JarvisCommand.LIST_UPDATE, listItemType = String.class)
    public void updateList(List<String> list) {
        System.out.println("List Worker update List");
    }


}
