package fr.discrod.discrod.controllers;

import fr.discrod.discrod.enums.ChannelType;
import fr.discrod.discrod.exceptions.ItemNotFoundException;
import fr.discrod.discrod.exceptions.ItemNotValidException;
import fr.discrod.discrod.modeles.ChannelModel;
import fr.discrod.discrod.modeles.MessageModel;
import fr.discrod.discrod.repositories.ChannelRepository;
import fr.discrod.discrod.repositories.MessageRepository;
import fr.discrod.discrod.requestModeles.ChannelRequest;
import fr.discrod.discrod.responseModeles.ChannelResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/channel")
public class ChannelController {
    @Autowired
    private ChannelRepository repository;

    @Autowired
    private MessageRepository messageRepository;
    @GetMapping("/{id}")
    public ChannelResponse get(@PathVariable String id) {
        var item = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        return new ChannelResponse(item);
    }
    @GetMapping
    public List<ChannelResponse> getAll() {
        List<ChannelResponse> item = new ArrayList<>();
        repository.findAll().forEach(i -> item.add(new ChannelResponse(i)));
        return item;
    }

    @GetMapping("/{id}/{text}")
    public ChannelResponse getAllFiltered(@PathVariable String id, @PathVariable String text) {
        ChannelModel item = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        List<MessageModel> itemFiltered = messageRepository.findAllByTextContaining(text).orElse(new ArrayList<>());
        item.setMessageModel(itemFiltered);
        return new ChannelResponse(item);
    }
    @PutMapping()
    public ChannelResponse update(@Validated @RequestBody ChannelRequest itemRequest, BindingResult errors) {
        if(errors.hasErrors()) {
            throw new ItemNotValidException();
        }
        var item = repository.findById(itemRequest.getId()).orElseThrow(ItemNotFoundException::new);
        if (repository.findById(itemRequest.getId()).isPresent()) {
            ChannelModel newItem = new ChannelModel();
            BeanUtils.copyProperties(itemRequest, newItem);
            item = repository.save(newItem);
        }
        return new ChannelResponse(item);
    }

    @PostMapping
    public ChannelResponse create(@Validated @RequestBody ChannelRequest itemRequest, BindingResult errors) {
        if(errors.hasErrors()) {
            throw new ItemNotValidException();
        }
        var item = new ChannelModel();
        try {
            item.setChannelType(ChannelType.valueOf(itemRequest.getChannelTypeName().toUpperCase()));
        } catch (IllegalArgumentException argumentException) {
            throw new ItemNotValidException();
        }
        BeanUtils.copyProperties(itemRequest, item);
        item.setMessageModel(new ArrayList<>());
        repository.save(item);
        return new ChannelResponse(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        var item = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        repository.delete(item);
    }
}
