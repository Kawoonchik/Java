import redis.clients.jedis.Jedis;
import java.util.Map;

public class JedisTravelAgency {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.hset("agent:1", Map.of(
                "name", "Anatolii",
                "phone", "0501234567",
                "email", "anatolii@example.com"
        ));

        jedis.hset("resort:101", Map.of(
                "name", "Bukovel",
                "location", "Carpathians",
                "star_rating", "5"
        ));

        jedis.hset("contract:5001", Map.of(
                "agent_id", "1",
                "resort_id", "101",
                "client_name", "Ivan Koval",
                "price", "15000",
                "commission", "1500"
        ));

        Map<String, String> contractData = jedis.hgetAll("contract:5001");
        System.out.println(contractData);

        jedis.hset("contract:5001", "price", "16000");
        jedis.hset("contract:5001", "commission", "1600");

        System.out.println(jedis.hgetAll("contract:5001"));

        jedis.del("agent:1", "resort:101", "contract:5001");

        jedis.close();
    }
}