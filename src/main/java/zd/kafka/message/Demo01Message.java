package zd.kafka.message;

import java.lang.ref.PhantomReference;

/**
 * @version V1.0
 * @program: Kafka
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2022-04-25 15:41
 **/
public class Demo01Message {

    public static final String TOPIC = "DEMO_01";

    //编号
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Demo01Message{" +
                "id=" + id +
                '}';
    }
}
