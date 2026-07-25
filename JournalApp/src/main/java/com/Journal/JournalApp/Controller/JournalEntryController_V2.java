package com.Journal.JournalApp.Controller;

import com.Journal.JournalApp.Entity.JournalEntry;
import com.Journal.JournalApp.Repository.JournalEntryRepository;
import com.Journal.JournalApp.Service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController_V2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() { //localhost:8080/journal - GET
        return null;
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){  //localhost:8080/journal - POST
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getEntry(@PathVariable long myId){
        return null;
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteEntry(@PathVariable long myId){
        return null;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry myEntry){
        return null;
    }
}
