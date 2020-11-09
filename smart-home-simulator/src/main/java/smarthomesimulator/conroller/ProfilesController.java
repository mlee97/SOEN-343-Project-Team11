package smarthomesimulator.conroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class ProfilesController {

    @GetMapping(value = "/printProfiles")
    public String printProfiles() {
        return "printProfiles";
    }

    @Autowired
    ServletContext context;

    @RequestMapping("/download/{fileName:.+}")
    public void downloader(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable("fileName") String fileName) {

        System.out.println("Downloading file :- " + fileName);

        String downloadFolder = context.getRealPath("/WEB-INF/download/");
        Path file = Paths.get(downloadFolder, fileName);
        // Check if file exists
        if (Files.exists(file)) {
            // set content type
            response.setContentType("application/pdf");
            // add response header
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                // copies all bytes from a file to an output stream
                Files.copy(file, response.getOutputStream());
                // flushes output stream
                response.getOutputStream().flush();
            } catch (IOException e) {
                System.out.println("Error :- " + e.getMessage());
            }
        } else {
            System.out.println("Sorry File not found!!!!");
        }
    }
}
