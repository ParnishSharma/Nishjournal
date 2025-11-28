package com.nishjournal.Nishjournal.repository;

import com.nishjournal.Nishjournal.entry.JournalEntry;
import com.nishjournal.Nishjournal.entry.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);
    void deleteByUserName(String userName);
}



//controller->service->repository