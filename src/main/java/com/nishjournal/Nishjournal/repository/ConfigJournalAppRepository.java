package com.nishjournal.Nishjournal.repository;

import com.nishjournal.Nishjournal.entry.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}



//controller->service->repository