package scead.llminthehouse.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/admin/test")
public class AdminWebController
{
    @GetMapping(value = "/helloworld", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testAvailability()
    {
        try
        {
            return ResponseEntity.ok("Hello, World! llminthehouse is up and running.");
        }
        catch (IllegalArgumentException e)
        {
            return ResponseEntity.badRequest()
                .body("Resolvable error occurred: " + e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unresolvable error occurred: " + e.getMessage());
        }
    }

}
