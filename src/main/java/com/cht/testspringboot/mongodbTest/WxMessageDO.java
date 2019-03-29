package com.cht.testspringboot.mongodbTest;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @auther chen.haitao
 * @date 2019-03-29
 */
@Document(collection = "wx_message")
public class WxMessageDO implements Serializable {

    private static final long serialVersionUID = 7233596801700381622L;

    @Id
    private String id;

    private String sessionId;

    private String action;

    private String clientMsgId;

    private Long msgTime;

    private String wxId;

    private String to;

    public WxMessageDO(String id, String sessionId, String action, String clientMsgId, Long msgTime, String wxId, String to) {
        this.id = id;
        this.sessionId = sessionId;
        this.action = action;
        this.clientMsgId = clientMsgId;
        this.msgTime = msgTime;
        this.wxId = wxId;
        this.to = to;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getClientMsgId() {
        return clientMsgId;
    }

    public void setClientMsgId(String clientMsgId) {
        this.clientMsgId = clientMsgId;
    }

    public Long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Long msgTime) {
        this.msgTime = msgTime;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "WxMessageDO{" +
                "id='" + id + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", action='" + action + '\'' +
                ", clientMsgId='" + clientMsgId + '\'' +
                ", msgTime=" + msgTime +
                ", wxId='" + wxId + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
