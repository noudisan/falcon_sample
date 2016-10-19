package com.zhiyi.common.dto;

import org.apache.commons.lang.StringUtils;

/**
 * 公用一些枚举集合
 */
public class CommonEnums {


    /**
     * 关闭，打开
     */
    public enum OpenStatus {

        OPEN("打开"), CLOSE("关闭"), ;

        private String message;

        OpenStatus(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public static OpenStatus value(String message) {
            if (StringUtils.isNotBlank(message)) {
                return OpenStatus.valueOf(message);
            }
            return null;
        }
    }


    /**
     * 可见不可见
     */
    public enum VisibleStatus {

        VISIBLE("可见"), INVISIBLE("不可见");

        private String message;

        VisibleStatus(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public static VisibleStatus value(String message) {
            if (StringUtils.isNotBlank(message)) {
                return VisibleStatus.valueOf(message);
            }
            return null;
        }
    }

    /**
     * 商家类型(小区送/上门易/享周边)
     */
    public enum StoreType {
        XQS("小区送"), SMY("上门易"), XZB("享周边");

        private String message;

        StoreType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 模版状态枚举
     * <p>delete/normal</p>
     */
    public enum TemplateStatus {
        Delete(0), Normal(1);

        private int status;

        TemplateStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }

    /**
     * 类型(消息类型等)
     */
    public enum Types {
        MESSAGE("消息类别");

        private String message;

        Types(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 菜单标签状态
     * <p>NORMAL(正常)/OFF(下架)</p>
     */
    public enum StorePriceTypeStatus {

        NORMAL(1), OFF(2);

        private int status;

        StorePriceTypeStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }

    /**
     * 消息是否已读状态
     * <p>UNREAD(未读)/READED(已读)</p>
     */
    public enum MessageReadStatus {

        UNREAD("未读"), READED("已读");

        private String message;

        MessageReadStatus(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 商家媒体信息类型(视频等)
     */
    public enum StoreMediaType {
        VIDEO("视频"), PICTURE("图片");

        private String message;

        StoreMediaType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }


    public enum TrueOrFalse {
        TRUE, FALSE;
    }

}
