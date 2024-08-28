package tdr.pet.weblibrary.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tdr.pet.weblibrary.exception.author.AuthorNotFoundException;
import tdr.pet.weblibrary.model.entity.Publisher;
import tdr.pet.weblibrary.repository.PublisherRepository;
import tdr.pet.weblibrary.service.PublisherService;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    @Override
    public Publisher getPublisherByName(String name) {
        return publisherRepository.getPublisherByName(name)
                .orElseThrow(() -> new AuthorNotFoundException("Publisher with this name wasn't found"));
    }

    @Override
    public List<Publisher> getPublishersByAddress(String address) {
        return publisherRepository.getPublishersByAddress(address);
    }

    @Override
    public Publisher getPublisherByNameAndAddress(String name, String address) {
        return publisherRepository.getPublisherByNameAndAddress(name, address)
                .orElseThrow(() -> new AuthorNotFoundException("Publisher with this name and address wasn't found"));
    }

    @Override
    public void createNewPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    public void updatePublisherById(Long id, Publisher publisher) {
        publisherRepository.updatePublisherById(id, publisher);
    }

    @Override
    public void updatePublisherByName(String name, Publisher publisher) {
        publisherRepository.updatePublisherById(getPublisherByName(name).getId(), publisher);
    }

    @Override
    public void deletePublisherById(Long id) {
        publisherRepository.deletePublisherById(id);
    }

    @Override
    public void deletePublisherByName(String name) {
        publisherRepository.deletePublisherById(getPublisherByName(name).getId());
    }
}