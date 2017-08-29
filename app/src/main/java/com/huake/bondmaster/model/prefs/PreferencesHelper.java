package com.huake.bondmaster.model.prefs;

import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.model.bean.UserBean;

/**
 * @author: pengweiqiang
 * @date: 2017/4/21
 * @description:
 */

public interface PreferencesHelper {

    boolean getNightModeState();

    void setNightModeState(boolean state);

    boolean getNoImageState();

    void setNoImageState(boolean state);

    boolean getAutoCacheState();

    void setAutoCacheState(boolean state);

    int getCurrentItem();

    void setCurrentItem(int item);

    boolean getLikePoint();

    void setLikePoint(boolean isFirst);

    boolean getVersionPoint();

    void setVersionPoint(boolean isFirst);

    boolean getManagerPoint();

    void setManagerPoint(boolean isFirst);

    void setHomePageCache(String homePageCache);

    HomePageBean getHomePageCache();

    void setUserInstance(UserBean userBean);

    UserBean getUserInstance();

}
