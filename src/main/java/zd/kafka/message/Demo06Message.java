package zd.kafka.message;

/**
 * @version V1.0
 * @program: Kafka
 * @description: kafka并发消费
 * @author: Mr.Zhang
 * @create: 2022-04-27 10:31
 **/
public class Demo06Message {

    public static final String TOPIC = "DEMO_06";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Demo06Message{" +
                "id=" + id +
                '}';
    }
}
