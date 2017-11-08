package publisher;

import java.util.HashSet;
import java.util.Set;
import subscriber.Subscriber;

public class PublisherImpl implements PublisherAdmin, Publisher {

    private Set<Subscriber> subscriberSet;
    private int numPublishers;
    private String topic;
    
    public PublisherImpl(String topic){
        subscriberSet = new HashSet<Subscriber>();
        numPublishers = 1;
        this.topic = topic;
    }
    public int incPublishers(){
        return ++numPublishers;
    }
    public int decPublishers(){
        return --numPublishers;
    }
    public void attachSubscriber(Subscriber subscriber) {
        this.subscriberSet.add(subscriber);
    }
    public void detachSubscriber(Subscriber subscriber) {
        this.subscriberSet.remove(subscriber);
    }
    public void detachAllSubscribers() {
        this.subscriberSet.removeAll(subscriberSet);
    }
    public void publish(String topic, String event) {
        for(Subscriber subs: this.subscriberSet){
            subs.onEvent(topic, event);
        }
    }
}
