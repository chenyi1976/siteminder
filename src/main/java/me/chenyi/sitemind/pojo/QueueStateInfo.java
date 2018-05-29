/**
 * 
 */
package me.chenyi.sitemind.pojo;

public class QueueStateInfo {
    
    private String queueName;
    private long queueSize;
    private long consumerCount;
    private long enqueueCount;
    private long dequeueCount;
    
    public String getQueueName() {
        return queueName;
    }
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
    public long getQueueSize() {
        return queueSize;
    }
    public void setQueueSize(long queueSize) {
        this.queueSize = queueSize;
    }
    public long getConsumerCount() {
        return consumerCount;
    }
    public void setConsumerCount(long consumerCount) {
        this.consumerCount = consumerCount;
    }
    public long getEnqueueCount() {
        return enqueueCount;
    }
    public void setEnqueueCount(long enqueueCount) {
        this.enqueueCount = enqueueCount;
    }
    public long getDequeueCount() {
        return dequeueCount;
    }
    public void setDequeueCount(long dequeueCount) {
        this.dequeueCount = dequeueCount;
    }
}
