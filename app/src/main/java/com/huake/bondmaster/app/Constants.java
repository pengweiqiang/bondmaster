package com.huake.bondmaster.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by pengweiqiang on 2016/8/3.
 */
public class Constants extends com.huake.bondmaster.Constants{


    //================= RESULT ====================
    public static final int CODE_SUCCESS = 1;
    public static final int CODE_FAIL = 0;//失败
    public static final int CODE_QUIT = 2;//废弃
    public static final int CODE_INVALID_TOKEN = 4;//未登录或者token失效

    public static final String ARTICLE_URL= "/hk-soft-app/cmsApp/getArticle";//专栏内容

    //================= TYPE ====================

    public static final int TYPE_ZHIHU = 101;

    public static final int TYPE_ANDROID = 102;

    public static final int TYPE_IOS = 103;

    public static final int TYPE_WEB = 104;

    public static final int TYPE_GIRL = 105;

    public static final int TYPE_WECHAT = 106;

    public static final int TYPE_GANK = 107;

    public static final int TYPE_GOLD = 108;

    public static final int TYPE_VTEX = 109;

    public static final int TYPE_SETTING = 110;

    public static final int TYPE_LIKE = 111;

    public static final int TYPE_ABOUT = 112;

    public static final int PAGE_SIZE = 10;

    public static final String PARAM_SCALE = "2";//2 2倍图 3 3倍图

    //================= KEY ====================



    public static final String BUGLY_ID = "65cffac64b";

    //================= PATH ====================

    public static final String SHARE_APP_URL = "http://www.baidu.com";

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "bondmaster" + File.separator + "Bondmaster";

    //================= PREFERENCE ====================

    public static final String SP_NIGHT_MODE = "night_mode";

    public static final String SP_NO_IMAGE = "no_image";

    public static final String SP_AUTO_CACHE = "auto_cache";

    public static final String SP_CURRENT_ITEM = "current_item";

    public static final String SP_LIKE_POINT = "like_point";

    public static final String SP_VERSION_POINT = "version_point";

    public static final String SP_MANAGER_POINT = "manager_point";

    public static final String SP_USER_INSTANCE_STR = "userLoginInfo";

    public static final String SP_HOME_DATA_CACHE = "homePageData";//首页缓存数据

    //================= INTENT ====================
    public static final String SCENE_BEAN = "sceneBean";

    public static final String MOBILE = "mobile";

    public static final String WEB_URL = "webUrl";

    public static final String TITLE = "title";

    public static final String SEARCH_KEY = "searchKey";



}
