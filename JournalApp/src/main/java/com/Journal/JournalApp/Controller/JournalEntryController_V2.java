package com.Journal.JournalApp.Controller;

import com.Journal.JournalApp.Entity.JournalEntry;
import com.Journal.JournalApp.Repository.JournalEntryRepository;
import com.Journal.JournalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController_V2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() { //localhost:8080/journal - GET
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){  //localhost:8080/journal - POST
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getEntry(@PathVariable ObjectId myId){
        return journalEntryService.getEntryById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteEntry(@PathVariable ObjectId myId){
        return null;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry){
        return null;
    }
}
