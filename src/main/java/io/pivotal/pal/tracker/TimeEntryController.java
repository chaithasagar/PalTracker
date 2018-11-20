package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository repository;
    private DistributionSummary distributionSummary;
    private Counter actionCounter;

    public TimeEntryController(TimeEntryRepository timeEntryRepository, MeterRegistry meterRegistry) {
        this.repository=timeEntryRepository;
        distributionSummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");

    }

    @PostMapping
    public ResponseEntity create(@RequestBody  TimeEntry timeEntryToCreate) {
        timeEntryToCreate =  repository.create(timeEntryToCreate);
        actionCounter.increment();
        distributionSummary.record(repository.list().size());
        ResponseEntity response = new ResponseEntity(timeEntryToCreate,HttpStatus.CREATED);
        return response;


    }

    @GetMapping(value="/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
        ResponseEntity response;
        TimeEntry timeEntry = repository.find(timeEntryId);
        if(timeEntry != null) {
            actionCounter.increment();
             response = new ResponseEntity(timeEntry, HttpStatus.OK);
        }else{
             response = new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        actionCounter.increment();
        List<TimeEntry> timeEntryList = repository.list();
        ResponseEntity response = new ResponseEntity(timeEntryList,HttpStatus.OK);
        return response;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long timeEntryId, @RequestBody  TimeEntry expected) {
        TimeEntry timeEntry =  repository.update(timeEntryId,expected);
        ResponseEntity response;
        if(timeEntry != null) {
            actionCounter.increment();
            response = new ResponseEntity(timeEntry, HttpStatus.OK);
        }else{
            response = new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id")  long timeEntryId) {
        actionCounter.increment();
        TimeEntry timeEntry = repository.delete(timeEntryId);
        distributionSummary.record(repository.list().size());
        ResponseEntity response = new ResponseEntity(timeEntry,HttpStatus.NO_CONTENT);
return response;
    }
}
