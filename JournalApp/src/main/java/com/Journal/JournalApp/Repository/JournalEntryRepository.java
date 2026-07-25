package com.Journal.JournalApp.Repository;

import com.Journal.JournalApp.Entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournalEntry,String> {

}
