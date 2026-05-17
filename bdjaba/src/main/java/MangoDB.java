import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Accumulators;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.Arrays;

public class MangoDB {
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("travel_agency");

            System.out.println("Селектор: пошук турів");
            MongoCollection<Document> toursCollection = database.getCollection("tours");

            Bson filter = Filters.eq("resort.country", "Turkey");
            FindIterable<Document> tours = toursCollection.find(filter);

            for (Document tour : tours) {
                System.out.println(tour.toJson());
            }

            System.out.println("\nАгрегація: загальна сума продажів по кожному агенту");
            MongoCollection<Document> contractsCollection = database.getCollection("contracts");

            Bson group = Aggregates.group("$agent_ref.$id", Accumulators.sum("total_sales", "$total_price"));
            AggregateIterable<Document> aggResults = contractsCollection.aggregate(Arrays.asList(group));

            for (Document doc : aggResults) {
                System.out.println(doc.toJson());
            }

        } catch (Exception e) {
            System.err.println("Помилка підключення: " + e.getMessage());
        }
    }
}