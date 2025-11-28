package com.nishjournal.Nishjournal.controller;
//we are creating controller jo ki special type of component jo handle kreinge http requests thats why we use @RestController annotation for
//this class

import com.nishjournal.Nishjournal.entry.JournalEntry;
import com.nishjournal.Nishjournal.entry.User;
import com.nishjournal.Nishjournal.service.JournalEntryService;
import com.nishjournal.Nishjournal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    @GetMapping()
public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=userService.findByUserName(userName);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    List<JournalEntry>all= user.getNishjournaldb(); //using user object to get journal entries of that user
    if(all!=null && !all.isEmpty()){
         return new ResponseEntity<>(all,HttpStatus.OK);
    }
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 }

 @PostMapping()

 public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry ){
     try {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String userName = authentication.getName();
         journalEntryService.saveEntry(myEntry,userName);
         return new ResponseEntity<>(myEntry,HttpStatus.OK);
     }catch(Exception e){
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

     }

 }

 @GetMapping("/id/{id}")
 public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId id){
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     String userName = authentication.getName();
     User user=userService.findByUserName(userName);
     List<JournalEntry> collect=user.getNishjournaldb().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
     if(!collect.isEmpty()){
         Optional<JournalEntry> journalEntry=journalEntryService.findById(id);
         if(journalEntry.isPresent()){
             return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
         }
     }

     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 }

 @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myid ){
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     String userName = authentication.getName();
        journalEntryService.deleteById(myid,userName);
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 }

 @PutMapping("id/{id}")
    public ResponseEntity<JournalEntry> updateEntryById(@PathVariable ObjectId id,
                                                        @RequestBody JournalEntry updatedEntry){
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     String userName = authentication.getName();
     User user=userService.findByUserName(userName);
     List<JournalEntry> collect=user.getNishjournaldb().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());

     if(!collect.isEmpty()){
         Optional<JournalEntry> journalEntry=journalEntryService.findById(id);
         if(journalEntry.isPresent()){
             JournalEntry old=journalEntry.get();
             old.setTitle(!updatedEntry.getTitle().isEmpty() ? updatedEntry.getTitle():old.getTitle());
             old.setContent(updatedEntry.getContent()!=null && !  updatedEntry.getContent().isEmpty() ? updatedEntry.getContent():old.getContent());
             journalEntryService.saveEntry(old, userName);
             return new ResponseEntity<>(old,HttpStatus.OK);
         }
     }


     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 }


}
