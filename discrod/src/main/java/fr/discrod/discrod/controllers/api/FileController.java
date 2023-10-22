package fr.discrod.discrod.controllers.api;

import fr.discrod.discrod.enums.FileType;
import fr.discrod.discrod.exceptions.ItemNotFoundException;
import fr.discrod.discrod.exceptions.ItemNotValidException;
import fr.discrod.discrod.modeles.FileModel;
import fr.discrod.discrod.repositories.FileRepository;
import fr.discrod.discrod.repositories.UserRepository;
import fr.discrod.discrod.requestModeles.FileRequest;
import fr.discrod.discrod.responseModeles.FileResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private FileRepository repository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{idFile}")
    public ResponseEntity<byte[]> get(@PathVariable String idFile) {
        var item = repository.findById(idFile).orElseThrow(ItemNotFoundException::new);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + item.getName() + "\"")
                .body(item.getData());
    }

    @GetMapping("/info/{idFile}")
    public FileResponse getInfo(@PathVariable String idFile, HttpServletRequest request) {
        var item = repository.findById(idFile).orElseThrow(ItemNotFoundException::new);
        return new FileResponse(item, request);
    }
    @GetMapping
    public List<FileResponse> getAll(HttpServletRequest request) {
        List<FileResponse> item = new ArrayList<>();
        repository.findAll().forEach(i -> item.add(new FileResponse(i, request)));
        return item;
    }

    @PutMapping()
    public FileResponse update(@Validated @RequestBody FileRequest itemRequest, BindingResult errors, HttpServletRequest request) {
        ResolveErrors(errors);

        var item = repository.findById(itemRequest.getId()).orElseThrow(ItemNotFoundException::new);
        if (repository.findById(itemRequest.getId()).isPresent()) {
            var newItem = new FileModel();
            BeanUtils.copyProperties(itemRequest, newItem);
            item = repository.save(newItem);
        }
        return new FileResponse(item, request);
    }

    @PostMapping("/{idOwner}")
    public FileResponse create(@RequestParam MultipartFile file, @PathVariable String idOwner, HttpServletRequest request) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            FileModel item = new FileModel();
            item.setName(fileName);
            item.setData(file.getBytes());

            if (file.getContentType().toUpperCase().contains("IMAGE"))
                item.setType(FileType.IMAGE);
            else if (file.getContentType().toUpperCase().contains("VIDEO"))
                item.setType(FileType.VIDEO);
            else
                throw new ItemNotValidException();

            var itemUser = this.userRepository.findById(idOwner).orElseThrow(ItemNotFoundException::new);
            item.setOwner(itemUser);

            return new FileResponse(repository.save(item), request);
        } catch (Exception e) {
            throw new ItemNotValidException();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        var item = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        repository.delete(item);
    }

    private void ResolveErrors(BindingResult errors) {
        if(errors.hasErrors())
            throw new ItemNotValidException();
    }
}