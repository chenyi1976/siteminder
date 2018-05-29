package me.chenyi.sitemind.util;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import me.chenyi.sitemind.pojo.QueueStateInfo;

import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ActiveMQUtil {

    private static Log log = LogFactory.getLog(ActiveMQUtil.class);

    public static Map<String,QueueStateInfo> getAllQueueInfoMap() {
        Map<String,QueueStateInfo> queueMap=new HashMap<String, QueueStateInfo>();
        BrokerViewMBean mBean=null;
        MBeanServerConnection connection=null;
        try{
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
            JMXConnector connector = JMXConnectorFactory.connect(url);
            connector.connect();
            connection = connector.getMBeanServerConnection();
            ObjectName name = new ObjectName("myDomain:BrokerName=localhost,Type=Broker");
            mBean = MBeanServerInvocationHandler.newProxyInstance(connection, name, BrokerViewMBean.class, true);
        }catch (IOException e){
            log.error("ActiveMQUtil.getAllQueueInfo",e);
        }catch (MalformedObjectNameException e){
            log.error("ActiveMQUtil.getAllQueueInfo",e);
        }

        if(mBean != null){
            for (ObjectName queueName : mBean.getQueues()) {
                QueueViewMBean queueMBean = MBeanServerInvocationHandler.newProxyInstance(connection, queueName, QueueViewMBean.class, true);
                QueueStateInfo queue = new QueueStateInfo();
                queue.setQueueName(queueMBean.getName());
                queue.setConsumerCount(queueMBean.getConsumerCount());
                queue.setQueueSize(queueMBean.getQueueSize());
                queue.setEnqueueCount(queueMBean.getEnqueueCount());
                queue.setDequeueCount(queueMBean.getDequeueCount());
                queueMap.put(queueMBean.getName(), queue);
            }
        }
        return queueMap;
    }

}