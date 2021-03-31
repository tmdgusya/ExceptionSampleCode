package me.roach.demobootweb;

import me.roach.demobootweb.error.ErrorCode;
import me.roach.demobootweb.error.ErrorResponse;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class SampleController {

    private final static Logger log = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/events/form")
    public String eventsForm(Model model) {
        Event e = new Event();
        e.setName("roach");
        e.setLimit(10);
        log.info("Event : {}", e);
        model.addAttribute("event", e);
        return "events/form";
    }

    @PostMapping("/api/events")
    @ResponseBody
    public ResponseEntity<?> getEvent(@Validated @ModelAttribute Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorResponse.of(ErrorCode.BAD_REQUEST, bindingResult, UUID.randomUUID()));
        }
        return ResponseEntity.ok().body(event);
    }

    @GetMapping("/file/{filename}")
    @ResponseBody
    public ResponseEntity fildDownload(@PathVariable String filename) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + filename);
        log.info("resource : {}", resource);
        File file = resource.getFile();

        Tika tika = new Tika();
        String mediaType = tika.detect(file);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement : filename=\"" + resource.getFilename() +"\"")
                .header(HttpHeaders.CONTENT_TYPE, mediaType)
                .header(HttpHeaders.CONTENT_LENGTH, file.length()+"")
                .body(resource);
    }

    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity<?> test() {

        Event e = new Event();
        e.setName("TEST EVENT");
        e.setLimit(10);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, "TEST")
                .body(e);
    }

}
