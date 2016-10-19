/*
package com.zhiyi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinatricom.message.IDeliverMsg;
import com.chinatricom.message.IReportMsg;
import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.slidewindow.SMSCallback;
import com.chinatricom.smsclient.ctcpp.CtcppChannel;
import com.chinatricom.smsclient.message.DeliverMsg;
import com.chinatricom.smsclient.message.ReportMsg;
import com.chinatricom.smsclient.message.SubmitMsg;

*/
/**
 * 手机短信发送工具类
 * <p>注:原来调用utils方式的,改为调用服务来调用</p>
 * @author donghui
 *//*

public class SmsUtils {
    private static final Logger LOG = LoggerFactory.getLogger(SmsUtils.class);

    */
/**
     * 调用IP地址,例如127.0.0.1为本机项目发布IP
     *//*

    private static final String HOST = "180.153.246.63";
    private static final int PORT = 28020;
    private static final int CHANNEL = 1;
    private static final int WND_SIZE = 16;
    private static final int TIMEOUT = 180 * 1000; // 连接超时时间
    private static final int TRYS = 3;
    private static final int SPEED = 0;

    //小区无忧
    private static final String USER = "dh1850";
    private static final String PWD = "1850.com";

    //测试账户
    //private static final String USER = "dhlftest";
    //private static final String PWD = "rz7M?P28";

    private static final CtcppChannel CTCPP_CHANNEL = new CtcppChannel(HOST, PORT, USER, PWD, CHANNEL, WND_SIZE, TIMEOUT, TRYS, SPEED, new SMSCallback(){

        */
/**
         * 接收上行短信 短信的各个字段都放在DeliverMsg
         *//*

        @Override
        public boolean onDeliverSMS(IDeliverMsg arg0) {
            DeliverMsg msg = (DeliverMsg) arg0;
            if (msg != null) {
                LOG.debug("收到手机短信 phone:{}, 内容:{}", msg.getFrom(), msg.getMsg());
            }
            return true;
        }

        */
/**
         * 接收下行短信响应
         * 接收下行短信的response，SubmitMsg里面有了result和msgId,这里的msg就是_chan.submit(_msg)里面的_msg
         *//*

        @Override
        public boolean onSubmitedSMS(ISubmitMsg arg0) {
            return true;
        }

        @Override
        public boolean onMTReportSMS(IReportMsg arg0) {
            ReportMsg msg = (ReportMsg)arg0;
            String result = null;
            switch (msg.getResult()) {
                case 0:
                    result = "成功";
                    break;
                case 1:
                    result = "等待发送";
                    break;
                case 2:
                    result = "失败";
                    break;
            }
            LOG.error("短信:[{}] 手机号:[{}] 消息ID:[{}]", result, msg.getPhone(),msg.getMsgid());
            return false;
        }

    }, 0, 0, 1, 1);

    */
/**
     * 发送短信
     * <p>注：各子系统不可以直接调用,需通过服务来调用</p>
     * @param to  手机号码，支持多号码，以英文逗号隔开。建议一次最多100个；
     * @param msg 短信内容
     *//*

    public static boolean sendSms(String to, String msg) {
        try {
            CTCPP_CHANNEL.start();
            SubmitMsg _msg = new SubmitMsg();
            _msg.setTo(to);
            _msg.setMsg(msg);
            boolean sendSuccess = CTCPP_CHANNEL.submit(_msg);
            LOG.info("send sms[" + to + "] msg[" + msg + "] " + sendSuccess);
            return sendSuccess;
        } catch (Exception e) {
            LOG.error("sendSms error", e);
        } 
        */
/*finally {
            int result = CTCPP_CHANNEL.stop();
            LOG.debug("断开连接 连接成果:{}", result);
        }*//*

        return false;
    }

}*/
