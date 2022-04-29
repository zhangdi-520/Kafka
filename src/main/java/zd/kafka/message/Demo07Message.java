package zd.kafka.message;

/**
 * @version V1.0
 * @program: Kafka
 * @description: 实体类
 * @author: Mr.Zhang
 * @create: 2022-04-27 11:42
 **/
public class Demo07Message {

    public static final String TOPIC = "DEMO_07";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Demo07Message{" +
                "id=" + id +
                '}';
    }
}
