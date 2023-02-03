package t3.mongorest.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import jakarta.annotation.PostConstruct;

@Repository
public class MongoDBUserRepository implements UserRepository {
    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();

    @Autowired
    private MongoClient client;
    private MongoCollection<Userr> userCollection;
    private MongoCollection<Partida> partidaCollection;

    @PostConstruct
    void init(){
        userCollection = client.getDatabase("userdb").getCollection("users", Userr.class);
    }
    @Override
    public List<Userr> findAll(){
        return userCollection.find().into(new ArrayList<>());
    }
    @Override
    public Userr findById(String id){
        return userCollection.find(Filters.eq("_id", new ObjectId(id))).first();        
    }
    @Override
    public Userr save(Userr user){
        userCollection.insertOne(user);
        return user;
    }
    @Override
    public long delete(String name) {
        return 0;
    }
}
