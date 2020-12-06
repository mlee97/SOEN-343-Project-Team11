package smarthomesimulator.conroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import smarthomesimulator.SmartHomeController;
import smarthomesimulator.model.Profile;
import smarthomesimulator.model.Simulator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


@Controller
public class ProfilesController{

    @GetMapping(value = "/printProfiles")
    public String printProfiles() {
        return "printProfiles";
    }

    @Autowired
    ServletContext context;

    @RequestMapping("/download")
    public void downloader(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder newFileInput = new StringBuilder();

        for (Profile profile: Simulator.profilesOfHouse) {
            newFileInput.append(profile.toString());
        }

        try{
            String downloadFolder = context.getRealPath("/WEB-INF/download/");
            File file = new File(downloadFolder + "user.txt");
            Writer output = null;
            output = new BufferedWriter(new FileWriter(file));
            output.write(newFileInput.toString());

            output.close();
            System.out.println(newFileInput.toString() + "File has been written");

        }catch(Exception e){
            System.out.println("Could not create file");
        }

        System.out.println("Downloading file :- " + "user.txt");

        String downloadFolder = context.getRealPath("/WEB-INF/download/");
        Path file = Paths.get(downloadFolder, "user.txt");
        // Check if file exists
        if (Files.exists(file)) {
            // set content type
            response.setContentType("application/pdf");
            // add response header
            response.addHeader("Content-Disposition", "attachment; filename=" + "user.txt");
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
