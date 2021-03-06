package com.huake.bondmaster.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by pengweiqiang on 2016/8/3.
 */
public class Constants extends com.huake.bondmaster.Constants{


    //HTTP
    public static final int CODE_SUCCESS = 1;
    public static final int CODE_FAIL = 0;//失败
    public static final int CODE_QUIT = 2;//废弃
    public static final int CODE_INVALID_TOKEN = 4;//未登录或者token失效

    //================= RESULT ====================
    public static final int SELECT_INDUSTRY_REQUEST_CODE = 0x0001;

    public static final int IMAGE_PICKER = 0x0002;

    //=================URL=========================
    public static final String ARTICLE_URL= "/hk-soft-app/cmsApp/getArticle";//专栏内容

    public static final String ASSOCIATION_MAP= "hk-soft-app/associationMap";//债券图谱

    //查看债种今日新发债
    public static final String HK_MARKET_OVERVIEW = "hk-soft-app/marketOverview/findHkMarketOverviewDataPage";


    public static final String MARKET_URL = "hk-soft-app/marketOverview";//市场

    public static final String ABOUT_US = "hk-soft-app/aboutus";//关于我们

    public static final String REGISTER_PROTOCOL = "/hk-soft-app/registerProtocol";//用户协议

    //评测结果

    public static final String EVALUATION_RESULT = "/hk-soft-app/scene/viewIssuanceEvaluationResult";

    public static final String EVALUATION_RESULT2 = "/hk-soft-app/evaluate/viewIssuanceEvaluationResult";

    //融资报告
    public static final String FINANCING_PLAN_REPORT = "hk-soft-app/scene/getFinancingPlanReportData";

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

    public static final String SHARE_APP_URL = "http://fir.im/rz3v";

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "bondmaster" + File.separator + "Bondmaster";


    public static final String PDF = "bondMaster/pdf";
    public static final String IMAGE = "bondMaster/screenhoot";
    public static final String END_PDF = ".pdf";

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

    public static final String URL_KEY = "urlKey";

    public static final String INDUSTRY_SELECTED = "selectedIndustry";

    public static final String PARENT_INDUSTRY_SELECTED = "parentSelectedIndustry";

    public static final String SELECT_ADDRESS = "selectedAddress";
    public static final String SELECT_COMPANY_NATURE = "selectedCompanyNature";
    public static final String SELECT_COMPANY_TYPE = "selectedCompanyType";
    public static final String SELECT_COMPANY_NAME = "companyName";

    public static final String EVALUATE_PARAMS = "evaluateParams";
    public static final String ENTERPRISE_INFO = "enterpriseInfo";

    public static final String TOTAL_ASSETS = "totalAssets";
    public static final String LIABILITIES = "liabilities";
    public static final String FLOW_RATE = "FlowRate";
    public static final String INCOME = "income";
    public static final String PROFIT_TOTAL = "profit_total";
    public static final String RETAINED_PROFITS = "retained_profits";
    public static final String THREE_AVG_PROFIT = "three_avg_profit";
    public static final String EBIT = "ebit";
    public static final String CASH = "cash";

    public static final String HK_MARKET_OVERVIEW_BEAN = "hkMarketOverview";


    public static String APP_DESC = "债懂，让企业更懂债";





}
