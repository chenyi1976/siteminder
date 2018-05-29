/**
 * 
 */
package me.chenyi.sitemind.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MetaMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String msgId;
    private Map<String, String> headerMap;
    
    public MetaMessage(){
        this.msgId = UUID.randomUUID().toString();
        this.headerMap = new HashMap();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }
}
