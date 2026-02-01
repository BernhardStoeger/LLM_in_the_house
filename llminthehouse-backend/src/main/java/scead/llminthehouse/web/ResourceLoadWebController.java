package scead.llminthehouse.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/file")
public class ResourceLoadWebController {

    @Value("${pictures.basepath}")
    protected String basepath;

    @GetMapping("/load")
    public ResponseEntity<Resource> loadFile(@RequestParam("path") String path) {
        File file = Path.of(basepath + "/" + path).toAbsolutePath().normalize().toFile();
        if (!file.exists() || !file.isFile()) {
            return ResponseEntity.notFound().build();
        }
        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(resource);
    }
}
