import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Main {

    public static void main(String[] args) {
        ConnectionString connectionString = new ConnectionString("mongodb://root:root@localhost");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("carCards");
        MongoIterable<String> list = database.listCollectionNames();
        for (String name : list) {
            System.out.println(name);
        }
    }
}


//docker pull mongo
//docker run --name mongodb-container -d -p 27017:27017 mongo
//After put the movie.json with Compass in the DB
