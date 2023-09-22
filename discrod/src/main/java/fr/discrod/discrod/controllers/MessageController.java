package fr.discrod.discrod.controllers;

import fr.discrod.discrod.exceptions.ItemNotFoundException;
import fr.discrod.discrod.modeles.MessageModel;
import fr.discrod.discrod.modeles.UserModel;
import fr.discrod.discrod.repositories.MessageRepository;
import fr.discrod.discrod.repositories.UserRepository;
import fr.discrod.discrod.requestModeles.MessageRequest;
import fr.discrod.discrod.responseModeles.MessageResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private MessageRepository repository;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/{id}")
    public MessageResponse get(@PathVariable String id) {
        MessageModel item = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        return new MessageResponse(item);
    }
    @GetMapping
    public List<MessageResponse> getAll() {
        List<MessageResponse> item = new ArrayList<>();
        repository.findAll().forEach(i -> item.add(new MessageResponse(i)));
        return item;
    }

    @PutMapping()
    public MessageResponse update(@RequestBody MessageRequest itemRequest) {
        var item = repository.findById(itemRequest.getId()).orElseThrow(ItemNotFoundException::new);
        if (repository.findById(itemRequest.getId()).isPresent()) {
            MessageModel newItem = new MessageModel();
            BeanUtils.copyProperties(itemRequest, newItem);
            item = repository.save(newItem);
        }

        return new MessageResponse(item);
    }

    @PostMapping
    public MessageResponse create(@RequestBody MessageRequest itemRequest) {
        MessageModel item = new MessageModel();
        BeanUtils.copyProperties(itemRequest, item);
        ItemNotFoundException itemNotFoundException = new ItemNotFoundException();
        UserModel userModel = userRepository.findById(itemRequest.getSender().getId())
                .orElseThrow(() -> itemNotFoundException);
        item.setSender(userModel);
        repository.save(item);
        return new MessageResponse(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        MessageModel item = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        repository.delete(item);
    }
}
