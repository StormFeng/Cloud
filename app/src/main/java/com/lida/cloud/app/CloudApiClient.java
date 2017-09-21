package com.lida.cloud.app;

import com.lida.cloud.bean.AboutUsBean;
import com.lida.cloud.bean.ActivitySearchReslutBean;
import com.lida.cloud.bean.AreaBean;
import com.lida.cloud.bean.BalanceBean;
import com.lida.cloud.bean.BalanceListBean;
import com.lida.cloud.bean.BankBean;
import com.lida.cloud.bean.BankCardBean;
import com.lida.cloud.bean.BannerBean;
import com.lida.cloud.bean.BookBean;
import com.lida.cloud.bean.CityBean;
import com.lida.cloud.bean.CollectBean;
import com.lida.cloud.bean.CommentBean;
import com.lida.cloud.bean.DetailBean;
import com.lida.cloud.bean.GoodBean;
import com.lida.cloud.bean.KCJFBean;
import com.lida.cloud.bean.LoginBean;
import com.lida.cloud.bean.MarkListBean;
import com.lida.cloud.bean.MemDetailBean;
import com.lida.cloud.bean.NewsBean;
import com.lida.cloud.bean.NewsDetailBean;
import com.lida.cloud.bean.PayMoneyBean;
import com.lida.cloud.bean.PersonalDataBean;
import com.lida.cloud.bean.PriceBean;
import com.lida.cloud.bean.ProvinceBean;
import com.lida.cloud.bean.PubBean;
import com.lida.cloud.bean.RecommendBean;
import com.lida.cloud.bean.RecommendShopBean;
import com.lida.cloud.bean.RegisterBean;
import com.lida.cloud.bean.RewardBean;
import com.lida.cloud.bean.RewardMarkBean;
import com.lida.cloud.bean.SellerBean;
import com.lida.cloud.bean.ShareUrlBean;
import com.lida.cloud.bean.ShopDetailBean;
import com.lida.cloud.bean.TypeBean;
import com.lida.cloud.bean.UpdateBean;
import com.midian.base.afinal.http.AjaxParams;
import com.midian.base.api.ApiCallback;
import com.midian.base.api.BaseApiClient;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.midian.base.util.Md5Utils;
import com.sina.weibo.sdk.utils.MD5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by WeiQingFeng on 2017/5/16.
 */

public class CloudApiClient extends BaseApiClient {
    public CloudApiClient(AppContext ac) {
        super(ac);
    }
    static public void init(AppContext appcontext) {
        if (appcontext == null)
            return;
        appcontext.api.addApiClient(new CloudApiClient(appcontext));
    }

    /**
     * 登录
     * @param name
     * @param pass
     * @param callback
     */
    public void login(String name, String pass, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("name",name);
        params.put("passwd", Md5Utils.md5(pass));
        post(callback, Constant.LOGIN, params, LoginBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 注册
     * @param name
     * @param passwd
     * @param tel
     * @param code
     * @param hname
     * @param number
     * @param tname
     * @param lv
     * @param callback
     */
    public void register(String name, String passwd, String tel, String code, String hname,
                         String number, String tname, String lv,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("name",name);
        params.put("passwd",Md5Utils.md5(passwd));
        params.put("tel",tel);
        params.put("code",code);
        params.put("hname",hname);
        params.put("number",number);
        params.put("tname",tname);
        params.put("lv",lv);
        post(callback, Constant.REGISTER, params, RegisterBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 首页banner
     * @param memid
     * @param callback
     */
    public void banner(String memid, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        post(callback, Constant.BANNER, params, BannerBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 首页banner
     * @param callback
     */
    public void news(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        post(callback, Constant.NEWS, params, NewsBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 首页公益活动
     * @param callback
     */
    public void pub(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        post(callback, Constant.PUB, params, PubBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     *商家分类
     * @param callback
     */
    public void type(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        post(callback, Constant.TYPE, params, TypeBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 商家列表
     * @param type
     */
    public SellerBean seller(String type,String lon,String dime) throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("type",type);
        params.put("lon",lon);
        params.put("dime",dime);
        return (SellerBean) postSync(Constant.SELLER, params, SellerBean.class);
    }

    /**
     * 商家详情
     * @param selid
     * @param callback
     */
    public void detail(String selid, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        params.put("selid",selid);
        post(callback, Constant.DETAIL, params, DetailBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 收藏商家
     * @param selid
     * @param callback
     */
    public void collection(String selid, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        params.put("selid",selid);
        post(callback, Constant.COLLECTION, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 取消收藏
     * @param selid
     * @param callback
     */
    public void coldel(String selid, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        params.put("selid",selid);
        post(callback, Constant.COLDEL, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 收藏列表
     * @param callback
     */
    public void collect(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        post(callback, Constant.COLLECT, params, CollectBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除收藏
     * @param colid
     * @param callback
     */
    public void del(String colid, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("colid",colid);
        post(callback, Constant.DEL, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 发卡银行
     * @return
     * @throws Exception
     */
    public BankBean bank() throws Exception {
        AjaxParams params=new AjaxParams();
        return (BankBean) postSync(Constant.BANK, params, BankBean.class);
    }

    /**
     * 个人银行卡
     * @return
     * @throws Exception
     */
    public BankCardBean card() throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        return (BankCardBean) postSync(Constant.CARD, params, BankCardBean.class);
    }

    /**
     * 个人添加银行卡
     * @param bankid
     * @param number
     * @param name
     * @param callback
     */
    public void bankcard(String bankid, String number, String name, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        params.put("bankid",bankid);
        params.put("number",number);
        params.put("name",name);
        post(callback, Constant.BANKCARD, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 判断是否商户
     * @param callback
     */
    public void clicksel(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        post(callback, Constant.CLICKSEL, params, ShopDetailBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 升级会员价格
     * @param callback
     */
    public void price(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        post(callback, Constant.PRICE, params, PriceBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 申请成为商户
     * @param fname
     * @param seltel
     * @param number
     * @param shopname
     * @param imgs
     * @param callback
     * @throws FileNotFoundException
     */
    public void seladd(String fname, String seltel, String number, String shoptype,String shopadd,
                       String lon,String dime,String saoe,String city,String area, String shopname,
                       List<String> imgs, ApiCallback callback) throws FileNotFoundException {
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        params.put("fname",fname);
        params.put("seltel",seltel);
        params.put("number",number);
        params.put("shopname",shopname);
        params.put("shoptype",shoptype);
        params.put("shopadd",shopadd);
        params.put("lon",lon);
        params.put("dime",dime);
        params.put("saoe",saoe);
        params.put("city",city);
        params.put("area",area);
        params.setHasFile(true);
        for (int i = 0; i < imgs.size(); i++) {
            File file = new File(imgs.get(i));
            if (file.exists())
                params.put("selimg"+i+1, file);
        }
        post(callback, Constant.SELADD, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改个人资料
     * @param nc
     * @param sex
     * @param tel
     * @param add
     * @param email
     * @param mem_tx
     * @param callback
     * @throws FileNotFoundException
     */
    public void alter(String nc, String sex, String tel, String add,String email,String mem_tx, ApiCallback callback) throws FileNotFoundException {
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        params.put("nc",nc);
        params.put("sex",sex);
        params.put("tel",tel);
        params.put("add",add);
        params.put("email",email);
        params.setHasFile(true);
        if(!"".equals(mem_tx)){
            File file = new File(mem_tx);
            if (file.exists())
                params.put("mem_tx", file);
        }
        post(callback, Constant.ALTER, params, PersonalDataBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 公告详情
     * @param newid
     * @param callback
     */
    public void newsDetail(String newid, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("newid",newid);
        post(callback, Constant.NEWSDETAIL, params, NewsDetailBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 保存商户资料
     * @param selid
     * @param selshopname
     * @param selimage
     * @param selshoptype
     * @param selshopadd
     * @param seltel
     * @param selemail
     * @param seldetail
     * @param callback
     */
    public void hold(String selid,String selshopname,String selimage,String selshoptype,
                     String selshopadd, String seltel,String selemail,String seldetail,
                     String dqid1,String dqid2, String dqid3,String lon,String dime, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("selid",selid);
        params.put("selshopname",selshopname);
        params.put("selshoptype",selshoptype);
        params.put("selshopadd",selshopadd);
        params.put("seltel",seltel);
        params.put("selemail",selemail);
        params.put("seldetail",seldetail);
        params.put("dqid1",dqid1);
        params.put("dqid2",dqid2);
        params.put("dqid3",dqid3);
        params.put("lon",lon);
        params.put("dime",dime);
        params.setHasFile(true);
        File file = new File(selimage);
        if(file.exists()){
            try {
                params.put("selimage",file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        post(callback, Constant.HOLD, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 转出积分
     * @param selid
     * @param mem_name
     * @param credit
     */
    public void out(String selid,String mem_name,String credit,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("selid",selid);
        params.put("mem_name",mem_name);
        params.put("credit",credit);
        post(callback, Constant.OUT, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 添加商品图片
     * @param selid
     * @param sp_image
     * @param callback
     */
    public void addShop(String selid, String sp_image, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("selid",selid);
        params.setHasFile(true);
        File file = new File(sp_image);
        if(file.exists()){
            try {
                params.put("sp_image",file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        post(callback, Constant.ADDSHOP, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 昨日结算
     * @param
     */
    public BalanceBean balance() throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        return (BalanceBean) postSync(Constant.BALANCE, params, BalanceBean.class);
    }

    /**
     * 修改密码
     * @param passwd
     * @param callback
     */
    public void passwd(String passwd, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        params.put("passwd", Md5Utils.md5(passwd));
        post(callback, Constant.PASSWD, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 忘记密码
     * @param passwd
     * @param callback
     */
    public void forgetPass(String tell_one, String passwd, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("tell_one",tell_one);
        params.put("passwd", Md5Utils.md5(passwd));
        post(callback, Constant.FORGETPASS, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 推荐的会员、商户
     * @param callback
     */
    public void commend(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        post(callback, Constant.COMMEND, params, RecommendBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 推荐的会员、商户
     * @param
     */
    public RecommendBean commend() throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        return (RecommendBean) postSync(Constant.COMMEND, params, RecommendBean.class);
    }

    /**
     * 奖励的金额
     * @return
     * @throws Exception
     */
    public RewardBean clickmoney() throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("name",ac.name);
        return (RewardBean) postSync(Constant.CLICKMONEY, params, RewardBean.class);
    }

    /**
     * 奖励的积分
     * @return
     * @throws Exception
     */
    public RewardMarkBean clickcredit() throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("name",ac.name);
        return (RewardMarkBean) postSync(Constant.CLICKCREDIT, params, RewardMarkBean.class);
    }

    /**
     * 举报商家
     * @param selid
     * @param con
     * @param callback
     */
    public void tip(String selid, String con, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.id);
        params.put("selid", selid);
        params.put("con", con);
        post(callback, Constant.TIP, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 库存积分
     * @return
     * @throws Exception
     */
    public KCJFBean jifen(String selid) throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("selid",selid);
        return (KCJFBean) postSync(Constant.JIFEN, params, KCJFBean.class);
    }

    /**
     *  库存积分明细
     * @param selid
     * @param callback
     */
    public void integral(String selid, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("selid",selid);
        post(callback, Constant.JIFEN, params, KCJFBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 购买积分
     * @param selid
     * @param jifen
     * @param callback
     */
    public void addjifen(String selid, String jifen, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("selid", selid);
        params.put("jifen", jifen);
        post(callback, Constant.ADDJIFEN, params, BookBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取商家商品图片
     * @param selid
     * @param callback
     */
    public void shopli(String selid, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("selid",selid);
        post(callback, Constant.SHOPLI, params, GoodBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取商家商品图片
     * @param callback
     */
    public void delshop(String sp_id, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("sp_id",sp_id);
        post(callback, Constant.DELSHOP, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 关于我们
     * @param callback
     */
    public void aboutUs(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        post(callback, Constant.ABOUTUS, params, AboutUsBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 关于我们
     * @param callback
     */
    public void prdetail(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        post(callback, Constant.PRDETAIL, params, AboutUsBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 提交评论
     * @param selid
     * @param con
     * @param callback
     */
    public void comment(String selid,String con,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.id);
        params.put("selid",selid);
        params.put("con",con);
        post(callback, Constant.COMMENT, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 评论列表
     * @return
     * @throws Exception
     */
    public CommentBean commentli(String selid) throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("selid",selid);
        return (CommentBean) postSync(Constant.COMMENTLI, params, CommentBean.class);
    }

    /**
     * 评论列表
     * @return
     * @throws Exception
     */
    public ActivitySearchReslutBean search(String con) throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("con",con);
        return (ActivitySearchReslutBean) postSync(Constant.SEARCH, params, ActivitySearchReslutBean.class);
    }

    /**
     * 获取个人中心余额和积分
     * @param hid
     * @param callback
     */
    public void getPersonalDataInfo(String hid, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("hid",hid);
        post(callback, Constant.INTEG, params, MemDetailBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取省份
     * @param callback
     */
    public void getProvince(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        post(callback, Constant.PROVINCE, params, ProvinceBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取城市
     * @param callback
     */
    public void getCity(String d2id, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("d2id", d2id);
        post(callback, Constant.CITY, params, CityBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取地区
     * @param callback
     */
    public void getArea(String d3id, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("d3id", d3id);
        post(callback, Constant.AREA, params, AreaBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取首页推荐的商家
     * @param callback
     */
    public void getRecommend(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        post(callback, Constant.RECOM, params, RecommendShopBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取区域
     * @param dqid1
     * @param dqid2
     * @param callback
     */
    public void getArea(String dqid1, String dqid2, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("dqid1",dqid1);
        params.put("dqid2",dqid2);
        post(callback, Constant.FDQQ, params, AreaBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 商家列表
     */
    public SellerBean nearby(String lon, String dime, String methers, String dqid) throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("lon",lon);
        params.put("dime",dime);
        params.put("methers",methers);
        params.put("dqid",dqid);
        return (SellerBean) postSync(Constant.NEARBY, params, SellerBean.class);
    }

    /**
     * 发送验证码
     * @param callback
     */
    public void code(String tel,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("tel",tel);
        post(callback, Constant.CODE, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 分享链接
     * @param callback
     */
    public void shareUrl(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        post(callback, Constant.SHAREURL, params, ShareUrlBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 验证验证码
     * @param callback
     */
    public void pdcode(String code,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("code",code);
        post(callback, Constant.PDCODE, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 验证验证码
     * @param callback
     */
    public void addlv(String memid,String lv,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        params.put("lv",lv);
        post(callback, Constant.ADDLV, params, UpdateBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 转账
     * @param callback
     */
    public void zz(String memid,String name,String money,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        params.put("name",name);
        params.put("money",money);
        post(callback, Constant.ZZ, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 提现
     * @param callback
     */
    public void paymoney(String zname,String name,String money,String number, String bank,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("zname",zname);
        params.put("name",name);
        params.put("money",money);
        params.put("number",number);
        params.put("bank",bank);
        post(callback, Constant.PAYMONEY,params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 充值
     * @param callback
     */
    public void comemoney(String memid,String money,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        params.put("money",money);
        post(callback, Constant.COMEMONEY,params, PayMoneyBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 余额明细
     */
    public BalanceListBean getBalanceList(String name) throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("name",name);
        return (BalanceListBean) postSync(Constant.BALANCELIST, params, BalanceListBean.class);
    }

    /**
     * 积分明细
     */
    public MarkListBean creditdetail(String name) throws Exception{
        AjaxParams params=new AjaxParams();
        params.put("name",name);
        return (MarkListBean) postSync(Constant.CREDITDETAIL, params, MarkListBean.class);
    }

    /**
     * 提现判断认证
     * @param callback
     */
    public void auth(String memid,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        post(callback, Constant.AUTH,params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 提现判断认证
     * @param callback
     */
    public void commitAuth(String memid, String name, String number, List<String> pics, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        params.put("name",name);
        params.put("number",number);
        params.setHasFile(true);
        for (int i = 0; i < pics.size(); i++) {
            File file = new File(pics.get(i));
            if(file.exists()){
                try {
                    params.put("image"+i+1, file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        post(callback, Constant.COMMITAUTH, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }
}








