package com.nishjournal.Nishjournal.service;

import com.nishjournal.Nishjournal.entry.JournalEntry;
import com.nishjournal.Nishjournal.entry.User;
import com.nishjournal.Nishjournal.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class JournalEntryService {
    @Autowired
    public JournalEntryRepository journalEntryRepository;

    @Autowired
    public UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
            journalEntry.setDate(LocalDateTime.now());
            User user = userService.findByUserName(userName);
           JournalEntry saved= journalEntryRepository.save(journalEntry);
           user.getNishjournaldb().add(saved);
           userService.saveUser(user);

    }

    public void saveEntry(JournalEntry journalEntry){
       journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void deleteById(ObjectId id, String username){
      try{
          User user =userService.findByUserName(username);
          boolean removed= user.getNishjournaldb().removeIf(z->z.getId().equals(id));
          if(removed){
              userService.saveUser(user);
              journalEntryRepository.deleteById(id);
          }
      }catch(Exception e){
          throw new RuntimeException("An error occurred while deleting the journal entry: " + e);
      }
    }


}
