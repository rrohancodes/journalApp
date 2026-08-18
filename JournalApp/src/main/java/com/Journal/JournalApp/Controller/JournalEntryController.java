package com.Journal.JournalApp.Controller;

import com.Journal.JournalApp.Entity.JournalEntry;
import com.Journal.JournalApp.Entity.User;
import com.Journal.JournalApp.Service.JournalEntryService;
import com.Journal.JournalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username) { //localhost:8080/journal - GET
        User user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username){  //localhost:8080/journal - POST
        try {
            journalEntryService.saveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntry(@PathVariable ObjectId myId){
        Optional<JournalEntry> entryById = journalEntryService.getEntryById(myId);
        return entryById.map(journalEntry -> new ResponseEntity<>(journalEntry, HttpStatus.OK)).orElseGet(() -> entryById.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/id/{username}/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId, @PathVariable String username) {
        journalEntryService.deleteEntryById(myId, username);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry){
//        JournalEntry old = journalEntryService.getEntryById(id).orElse(null);
//        if(old != null){
//            old.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
//            old.setContent(myEntry.getContent() != null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
//            journalEntryService.saveEntry(old);
//            return new ResponseEntity<>(old, HttpStatus.OK);
//        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
