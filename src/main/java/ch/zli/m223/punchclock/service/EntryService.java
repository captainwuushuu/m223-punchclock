package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.EntryRepository;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Service
public class EntryService {
    private EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry createEntry(Entry entry) {
        return entryRepository.saveAndFlush(entry);
    }

    public List<Entry> findAll() {
        return entryRepository.findAll();
    }

    public void deleteEntry(Long id) {
        entryRepository.deleteById(id);
    }

    public Entry updateEntry(Entry entry) {
        if (entryRepository.existsById(entry.getId())) {
            entryRepository.saveAndFlush(entry);
            return entry;
        } else {
            return createEntry(entry);
        }
    }
}
