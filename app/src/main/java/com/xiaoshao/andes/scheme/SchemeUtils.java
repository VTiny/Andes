package com.xiaoshao.andes.scheme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.xiaoshao.andes.learning.EllipsisTextViewFragment;
import com.xiaoshao.andes.learning.ItemDecorationLearningFragment;
import com.xiaoshao.andes.learning.TouchDelegateLearningFragment;
import com.xiaoshao.andes.util.DataUtil;
import com.xiaoshao.andes.util.SingleFragmentHelper;
import com.xiaoshao.andes.working.ExpandableIndexFragment;
import com.xiaoshao.andes.working.MotionLayoutFragment;

import java.util.List;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/3/18
 */
public class SchemeUtils {

    public static Intent getAndesIntent(Context context, Uri uri) {
        if (context == null || uri == null) {
            return null;
        }
        String host = uri.getHost();
        Intent intent = null;
        if (SchemeProtocol.ANDES_PROTOCOL_HOST.equals(host)) {
            String firstSegment = getSegment(uri, 0);
            if (DataUtil.isValid(firstSegment)) {
                switch (firstSegment) {
                    case SchemeProtocol.ANDES_PROTOCOL_EXPANDABLE_INDEX:
                        intent = SingleFragmentHelper.getStartIntent(context, ExpandableIndexFragment.class, null);
                        break;
                    case SchemeProtocol.ANDES_PROTOCOL_MOTIONLAYOUT:
                        intent = SingleFragmentHelper.getStartIntent(context, MotionLayoutFragment.class, null);
                        break;
                    case SchemeProtocol.ANDES_PROTOCOL_TOUCH_DELEGATE:
                        intent = SingleFragmentHelper.getStartIntent(context, TouchDelegateLearningFragment.class, null);
                        break;
                    case SchemeProtocol.ANDES_PROTOCOL_ITEM_DECORATION:
                        intent = SingleFragmentHelper.getStartIntent(context, ItemDecorationLearningFragment.class, null);
                        break;
                    case SchemeProtocol.ANDES_PROTOCOL_ELLIPSIS_TEXT_VIEW:
                        intent = SingleFragmentHelper.getStartIntent(context, EllipsisTextViewFragment.class, null);
                        break;
                }
            }
        }
        return intent;
    }

    public static String getSegment(Uri uri, int index) {
        String result = "";
        if (uri == null || index < 0) {
            return result;
        }
        List<String> segments = uri.getPathSegments();
        if (DataUtil.isValid(segments, index)) {
            result = segments.get(index);
        }
        return result;
    }

}
