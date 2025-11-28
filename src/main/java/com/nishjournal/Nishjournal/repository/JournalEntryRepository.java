package com.nishjournal.Nishjournal.repository;

import com.nishjournal.Nishjournal.entry.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}



//controller->service->repository