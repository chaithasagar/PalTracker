package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{


    private Map<Long,TimeEntry> timeEntryMap= new HashMap<Long,TimeEntry>();
    private long counter = 1L;

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(counter++);
        timeEntryMap.put(timeEntry.getId(),timeEntry);
       return timeEntry;

    }

    public TimeEntry find(long timeEntryId) {
        return timeEntryMap.get(timeEntryId);

    }

    public List<TimeEntry> list() {
        List<TimeEntry> timeEntryList = new ArrayList<TimeEntry>();
        for (Long id:timeEntryMap.keySet()
             ) {
            timeEntryList.add(timeEntryMap.get(id));

        }
        return timeEntryList;
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
       TimeEntry timeEntryTemp = timeEntryMap.get(eq);
       if(timeEntryTemp == null){
           return null;
       }
       timeEntryTemp = any;
       timeEntryTemp.setId(eq);
       timeEntryMap.put(eq, timeEntryTemp);
       return timeEntryTemp;
    }

    @Override
    public TimeEntry delete(long timeEntryId) {
        TimeEntry te = timeEntryMap.get(timeEntryId);
        timeEntryMap.remove(timeEntryId);
        return  te;
    }

//    public TimeEntry update(int id, TimeEntry timeEntry) {
//
//       TimeEntry timeEntryTemp = timeEntryMap.get(Long.valueOf(id));
//       timeEntryTemp = timeEntry;
//       timeEntryMap.put(Long.valueOf(id), timeEntryTemp);
//    }
//
//    public void delete(int id) {
//        timeEntryMap.remove(Long.valueOf(id));
//    }
}
