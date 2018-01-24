package com.xinhao.xhpay;

/**
 * Created by 14178 on 2018/1/24.
 */

public class WxOrAliPayData {


    /**
     * XINHAO_HAN  微信支付宝集成第二版本
     *
     *
     *
     * 信息填写  点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!点我!!!!
     *
     *
     * 注意事项: 在成功集成本Demo之后,在导入你的项目之后,请务必卸载本Demo,重启测试机,否则会导致支付失败,或者其它意料之外的错误
     *
     *
     * 请确认你的所有信息填写正确并没有任何问题
     *
     *
     * &**************************************
     *              关于拆分问题,关于这个你随意~~~~
     *              **********
     *
     *              如果你已经集成了微信或者支付宝其中一个Demo,那么你可以吧次Demo拆分成为2段,
     *
     *              支付宝:请把alipay文件夹下与本类文件复制到你的项目
     *
     *              微信:请把wxapi文件夹下与本类文件复制到你的项目
     *
     *              如果你正好想找一个微信登陆那么请访问:https://www.jianshu.com/p/04ed0b65f3df
     *
     *              下载微信登陆Demo,居所有Demo都简单格式化,非常便于理解与使用
     *
     */

    /**
     * 支付宝信息填写区域
     *
     *
     *  支付宝注意事项:填写正确的AppId与privateKey(秘钥)正常情况下都能调起支付宝支付
     */

    //你的AppId
    public static final String ALI_PAY_APPID = "";
    //你的支付宝秘钥
    public static final String ALI_PAY_PRIVATE_KEY = "";


    /**
     * 微信信息填写区域
     *
     *
     *  以下信息必须填写正确 (WE_CHAT_APP_SECRET)除外
     *
     *  1.调不起来支付页面?
     *
     *          请登录https://open.weixin.qq.com/
     *
     *          填写你正确的 包名 包名 包名!!!!!!!!!!
     *
     *          填写你正确的 MD5(签名) MD5(签名) MD5(签名)!!!!
     *
     *          如果觉得老铁我全都对着呀,那你就等2-3分钟,或者干脆直接重启手机(重启一般也2-3分钟),等微信服务器反应过来就OK了,
     *
     *          如果还不行老铁在仔细看一下
     *
     *          你要操作的部分
     *
     *          1.填写 WE_CHAT_APP_ID,WE_PRIVATE_KEY,MCH_ID UNIFIED_ORDER,URL(这个随便,问题是要看你们写后台的懒不懒了 0.0)
     *
     *          2.在你的微信开放平台上填写正确的包名以及MD5(签名)
     *
     *          3.回调WXPayEntryActivity   请按 ctrl + shift + f  或者 ctrl + shift + r 搜索 :  回调的详细信息页面
     *
     *          你就操作这3个步骤,所以来回细心看一下,你马上就能知道那错了,细心一点老铁~~~~
     *
     *  2.我能调起来了,问题是我在哪里获取回调?????
     *
     *             在你的包名下新建一个[wxapi]的文件夹
     *
     *             新建一个Activity名称为:WXPayEntryActivity
     *
     *             本lib也有一个相对应的WXPayEntryActivity,相关信息请到WXPayEntryActivity界面查看
     *
     *             打开WXPayEntryActivity!!!!!打开WXPayEntryActivity!!!!!打开WXPayEntryActivity!!!!!
     *
     *             打开WXPayEntryActivity!!!!!打开WXPayEntryActivity!!!!!打开WXPayEntryActivity!!!!!
     *
     *             获取微信的回调信息
     *
     *
     *
     *
     */

    //微信AppId
    public static final String WE_CHAT_APP_ID  = "";
 
    //微信秘钥
    public static final String WE_PRIVATE_KEY ="";
    //商户号
    public static final String MCH_ID = "";
    //微信统一下单接口
    public static final String UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //接受的回调地址
    public static final String URL = "www.suibian.com";


}
