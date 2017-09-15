package com.zhang.util;

import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/9/5.
 */
public class JoinUtil {

    /**
     * jdk8以下没有String.join()方法
     * @param list
     * @return
     */
    public static String join(List<String> list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

}
