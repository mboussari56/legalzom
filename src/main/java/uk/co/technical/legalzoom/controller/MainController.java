package uk.co.technical.legalzoom.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import uk.co.technical.legalzoom.domain.BankDetail;
import uk.co.technical.legalzoom.service.BankService;

@Controller
public class MainController implements WebMvcConfigurer {

    @Autowired
    private BankService bankService;

    @GetMapping("/result")
    public String mainWithParam(Model model) {
        model.addAttribute("records", bankService.retrieveAllRecords());
        return "result"; // view
    }

    @GetMapping("/")
    public String main(BankDetail bankDetail) {

        return "index"; // view
    }
    
    @GetMapping("/reset")
    public String mainReset(BankDetail bankDetail) {
        bankService.deleteAll();
        return "index"; // view
    }

    @PostMapping("/")
    public String saveNewRecord(@Valid BankDetail bankDetail, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        bankService.saveOrUpdate(bankDetail);

        return "redirect:/result";
    }

    @PostMapping("/file")
    public String fileUploadHandler(@RequestParam("file") MultipartFile file) {

        if (file == null) {
            throw new IllegalArgumentException("File is null");
        }

        try {

            bankService.saveRecordFromCsv(file.getBytes());
        } catch (IllegalStateException e) {
            // TODO log error
            e.printStackTrace();
        } catch (IOException e) {
            // TODO log error
            e.printStackTrace();
        }

        return "redirect:/result";
    }

}
