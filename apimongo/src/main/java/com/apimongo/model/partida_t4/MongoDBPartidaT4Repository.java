package com.apimongo.model.partida_t4;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;

@Repository
public class MongoDBPartidaT4Repository implements PartidakT4Repository {
    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();
    @Autowired
    private MongoClient client;
    private MongoCollection<PartidaT4> partidaCollection;

    @PostConstruct
    void init() {
        partidaCollection = client.getDatabase("proba").getCollection("partidak_t4", PartidaT4.class);

    }

    @Override
    public List<PartidaT4> findAll() {
        return partidaCollection.find().into(new ArrayList<>());
    }

    @Override
    public List<PartidaT4> bestGames() {
        List<PartidaT4> orderedList = partidaCollection.find().sort(Sorts.descending("puntuazioa"))
                .into(new ArrayList<>());
        return orderedList;

    }

    @Override
    public List<PartidaT4> worseGames() {
        List<PartidaT4> orderedList = partidaCollection.find().sort(Sorts.ascending("puntuazioa"))
                .into(new ArrayList<>());
        return orderedList;

    }

    @Override
    public String newPartida(PartidaT4 partidaT4) {
        int new_id;
        PartidaT4 lastmatch = partidaCollection.find().sort(new BasicDBObject("_id", -1)).limit(1).first();
        if (lastmatch == null) {
            new_id = 1;
            partidaT4.setId(new_id);
        } else {
            new_id = lastmatch.getId() + 1;
            partidaT4.setId(new_id);
        }
        partidaCollection.insertOne(partidaT4);
        return "saved";
    }
}
