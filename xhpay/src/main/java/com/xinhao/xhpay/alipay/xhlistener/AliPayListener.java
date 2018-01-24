package com.xinhao.xhpay.alipay.xhlistener;

/**
 * Created by 14178 on 2018/1/24.
 */

public interface AliPayListener {


    /**
     * 成功状态码
     */
    int SUCCESS = 1;
    /**
     * 失败状态码
     */
    int FAILURE = 2;

    /**
     * 用于支付的状态
     *
     * @param stat
     */
    void payListener(int stat);

}
