package topicmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import publisher.Publisher;
import publisher.PublisherAdmin;
import publisher.PublisherImpl;
import subscriber.Subscriber;

public class TopicManagerImpl implements TopicManager {

    private Map<String,PublisherAdmin> topicMap;

    public TopicManagerImpl() {
        topicMap = new HashMap<String,PublisherAdmin>();
    }
    
    public boolean isTopic(String topic){
        return this.topicMap.containsKey(topic);
    }
    
    public Set<String> topics(){
        return this.topicMap.keySet();
    }
    
    public Publisher addPublisherToTopic(String topic){        
        Publisher new_publisher = new PublisherImpl(topic);
        this.topicMap.put(topic, (PublisherAdmin)new_publisher);
        return new_publisher;
    }
    
    public int removePublisherFromTopic(String topic){
        PublisherAdmin temp_publisher = this.topicMap.get(topic);
        
        if (!this.topicMap.containsKey(topic)){
            return -1;
        }
        
        int n_publish = temp_publisher.decPublishers();
        if (n_publish < 1){
            temp_publisher.detachAllSubscribers();
        }
        this.topicMap.remove(topic);
        return n_publish;
    }
    
    public boolean subscribe(String topic, Subscriber subscriber){
        if(this.topicMap.containsKey(topic)){
            PublisherAdmin temp = this.topicMap.get(topic);
            temp.attachSubscriber(subscriber);
            return true;
        }else{
            return false;
        }
        
    }
    
    public boolean unsubscribe(String topic, Subscriber subscriber){
        if(this.topicMap.containsKey(topic)){
            PublisherAdmin temp = this.topicMap.get(topic);
            temp.detachSubscriber(subscriber);
            return true;
        }else{
            return false;
        }
    }
}