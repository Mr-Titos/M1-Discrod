package fr.discrod.discrod.controllers.api;

import fr.discrod.discrod.exceptions.ItemNotFoundException;
import fr.discrod.discrod.exceptions.ItemNotValidException;
import fr.discrod.discrod.modeles.GuildModel;
import fr.discrod.discrod.repositories.ChannelRepository;
import fr.discrod.discrod.repositories.FileRepository;
import fr.discrod.discrod.repositories.GuildRepository;
import fr.discrod.discrod.repositories.UserRepository;
import fr.discrod.discrod.requestModeles.ChannelGuildRequest;
import fr.discrod.discrod.requestModeles.GuildRequest;
import fr.discrod.discrod.requestModeles.UserMinRequest;
import fr.discrod.discrod.responseModeles.ChannelResponse;
import fr.discrod.discrod.responseModeles.GuildResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private FileRepository repository;

    @GetMapping("/{id}")
    public GuildResponse get(@PathVariable String id) {
        var item = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        return new GuildResponse(item);
    }
    @GetMapping
    public List<GuildResponse> getAll() {
        List<GuildResponse> item = new ArrayList<>();
        repository.findAll().forEach(i -> item.add(new GuildResponse(i)));
        return item;
    }

    @GetMapping("/channels/{id}")
    public List<ChannelResponse> getAllChannelsByID(@PathVariable String id) {
        List<ChannelResponse> item = new ArrayList<>();
        var itemGuild = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        channelRepository.findAllByGuild(itemGuild).forEach(i -> item.add(new ChannelResponse(i)));
        return item;
    }

    @PutMapping()
    public GuildResponse update(@Validated @RequestBody GuildRequest itemRequest, BindingResult errors) {
        ResolveErrors(errors);

        var item = repository.findById(itemRequest.getId()).orElseThrow(ItemNotFoundException::new);
        if (repository.findById(itemRequest.getId()).isPresent()) {
            var newItem = new GuildModel();
            BeanUtils.copyProperties(itemRequest, newItem);
            item = repository.save(newItem);
        }
        return new GuildResponse(item);
    }

    @PostMapping
    public GuildResponse create(@Validated @RequestBody GuildRequest itemRequest, BindingResult errors) {
        ResolveErrors(errors);

        var item = new GuildModel();
        BeanUtils.copyProperties(itemRequest, item);
        item.setUsers(new ArrayList<>());
        item.setChannels(new ArrayList<>());
        repository.save(item);
        return new GuildResponse(item);
    }

    @PostMapping("/user/{id}")
    public void addUser(@Validated @RequestBody UserMinRequest itemRequest, @PathVariable String id, BindingResult errors) {
        ResolveErrors(errors);

        var item = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        var itemUser = userRepository.findById(itemRequest.getId()).orElseThrow(ItemNotFoundException::new);
        itemUser.getGuilds().add(item);
        userRepository.save(itemUser);
        repository.save(item);
    }

    @PostMapping("/channel/{id}")
    public void addChannel(@Validated @RequestBody ChannelGuildRequest itemRequest, @PathVariable String id, BindingResult errors) {
        ResolveErrors(errors);

        var item = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        var itemChannel = channelRepository.findById(itemRequest.getId()).orElseThrow(ItemNotFoundException::new);
        itemChannel.setGuild(item);
        repository.save(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        var item = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        repository.delete(item);
    }

    @DeleteMapping("/user/{id}")
    public void removeUser(@Validated @RequestBody UserMinRequest itemRequest, @PathVariable String id, BindingResult errors) {
        ResolveErrors(errors);

        var item = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        var itemChannel = userRepository.findById(itemRequest.getId()).orElseThrow(ItemNotFoundException::new);
        item.getUsers().remove(itemChannel);
        repository.save(item);
    }

    private void ResolveErrors(BindingResult errors) {
        if(errors.hasErrors())
            throw new ItemNotValidException();
    }
}