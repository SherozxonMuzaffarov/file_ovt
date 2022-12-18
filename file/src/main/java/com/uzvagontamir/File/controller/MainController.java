package com.uzvagontamir.File.controller;

import com.uzvagontamir.File.model.Attachment;
import com.uzvagontamir.File.model.AttachmentContent;
import com.uzvagontamir.File.model.User;
import com.uzvagontamir.File.service.AttachmentContentService;
import com.uzvagontamir.File.service.AttachmentService;
import com.uzvagontamir.File.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    AttachmentContentService contentService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String loginPage() {
        return "user_login";
    }

    @GetMapping("/user/home")
    public String homepage(Model model) {
        User user = getUser();
        String korxona = user.getKorxona();
        if (korxona.equalsIgnoreCase("ot")) {
            model.addAttribute("files", attachmentService.findAll());
            model.addAttribute("user", "ot");
        }else if (korxona.equalsIgnoreCase("oe")) {
            model.addAttribute("files", attachmentService.findAllByOTChecked(true));
            model.addAttribute("user", "oe");
        }else if (korxona.equalsIgnoreCase("ut")) {
            model.addAttribute("files", attachmentService.findAllByOEChecked(true));
            model.addAttribute("user", "ut");
        }else if (korxona.equalsIgnoreCase("ur")) {
            model.addAttribute("files", attachmentService.findAllByUTChecked(true));
            model.addAttribute("user", "ur");
        }else if (korxona.equalsIgnoreCase("ue")) {
            model.addAttribute("files", attachmentService.findAllByURChecked(true));
            model.addAttribute("user", "ue");
        }else if (korxona.equalsIgnoreCase("uf")) {
            model.addAttribute("files", attachmentService.findAllByUEChecked(true));
            model.addAttribute("user", "uf");
        }else if (korxona.equalsIgnoreCase("un")) {
            model.addAttribute("files", attachmentService.findAllByUEChecked(true));
            model.addAttribute("user", "un");
        } else
            model.addAttribute("files", attachmentService.findAll());
        return "user_home";
    }

    public User getUser(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        return userService.findByUsername(username);
    }

    @PostMapping("/user/upload")
    public String uploadFileToDB(MultipartHttpServletRequest request,@RequestParam(value = "name", required = false) String name, @RequestParam(value = "izoh", required = false) String izoh, RedirectAttributes attributes) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (!file.isEmpty()) {
            User user = getUser();
            String korxona = user.getKorxona();

            //File haqida malumot olish
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            long size = file.getSize();

            Attachment attachment = new Attachment();
            attachment.setName(name);
            attachment.setFileOriginalName(originalFilename);
            attachment.setContentType(contentType);
            attachment.setSize(size);

            if (korxona.equalsIgnoreCase("VCHD-3"))
                attachment.setDepoNomi("VCHD-3");
            else if (korxona.equalsIgnoreCase("VCHD-5"))
                attachment.setDepoNomi("VCHD-5");
            else
                attachment.setDepoNomi("VCHD-6");

            attachment.setOTDepoIzoh(izoh);

            attachment.setOTChecked(false);
            attachment.setOEChecked(false);
            attachment.setUTChecked(false);
            attachment.setURChecked(false);
            attachment.setUEChecked(false);
            attachment.setUFChecked(false);
            attachment.setUNChecked(false);
            Attachment savedAttachment = attachmentService.save(attachment);

            //Fileni ichidagi Contentni saqlaydi
            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setMainContent(file.getBytes());
            attachmentContent.setAttachment(savedAttachment);
            contentService.save(attachmentContent);

            attributes.addFlashAttribute("message", "File " + savedAttachment.getName() + " nomi bilan saqlandi ");

            return "redirect:/user/home";
        }
        attributes.addFlashAttribute("message", "File tanlang");
        return "redirect:/user/home";
    }

    @GetMapping("/user/goToFile/{id}")
    public String goToFile(@PathVariable Integer id, Model model) {
        Optional<Attachment> optionalAttachment = attachmentService.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> contentOptional = contentService.findByAttachmentId(id);
            if (contentOptional.isPresent()){
                AttachmentContent attachmentContent =  contentOptional.get();
                model.addAttribute("content", attachmentContent);

            }
            model.addAttribute("file", attachment);
        }

        return "open_file";
    }

    @GetMapping("/user/getFile/{id}")
    public void getFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentService.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> contentOptional = contentService.findByAttachmentId(id);
            if (contentOptional.isPresent()){
                AttachmentContent attachmentContent = contentOptional.get();

                //fileni nomini berish uchun
                response.setHeader("Content-Disposition",
                        "attachment;fileName=\"" + attachment.getFileOriginalName()+"\"");

                System.out.println(attachment.getContentType());

                //file typeni berish uchun
                response.setContentType(attachment.getContentType());

                //file contentini berish uchun
                FileCopyUtils.copy(attachmentContent.getMainContent(), response.getOutputStream());
            }
        }
    }

    @GetMapping("/user/openFile/{id}")
    public void openFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {

        Optional<Attachment> optionalAttachment = attachmentService.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> contentOptional = contentService.findByAttachmentId(id);
            if (contentOptional.isPresent()){
                AttachmentContent attachmentContent = contentOptional.get();

                //fileni nomini berish uchun
                response.setHeader("Content-Disposition",
                        "inline;fileName=\"" + attachment.getFileOriginalName()+"\"");

                //file typeni berish uchun
                response.setContentType(attachment.getContentType());
//                System.out.println("** :" + attachment.getContentType());

                //file contentini berish uchun
                FileCopyUtils.copy(attachmentContent.getMainContent(), response.getOutputStream());

            }
        }
    }

    @PostMapping("/user/check/{id}")
    public String checkFile(@PathVariable("id") Integer id, @RequestParam(value = "izoh", required = false) String izoh, RedirectAttributes redirectAttributes){
        User user = getUser();
        String korxona = user.getKorxona();
        Optional<Attachment> optionalAttachment = attachmentService.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            if (korxona.equalsIgnoreCase("ot")) {
                attachment.setOTChecked(true);
                attachment.setOTIzoh(izoh);
            }else if(korxona.equalsIgnoreCase("oe")) {
                attachment.setOEChecked(true);
                attachment.setOEIzoh(izoh);
            }else if(korxona.equalsIgnoreCase("ut")) {
                attachment.setUTChecked(true);
                attachment.setUTIzoh(izoh);
            }else if(korxona.equalsIgnoreCase("ur")) {
                attachment.setURChecked(true);
                attachment.setURIzoh(izoh);
            }else if(korxona.equalsIgnoreCase("ue")) {
                attachment.setUEChecked(true);
                attachment.setUEIzoh(izoh);
            }else if (korxona.equalsIgnoreCase("uf") ) {
                attachment.setUFChecked(true);
                attachment.setUFIzoh(izoh);
            } else if (korxona.equalsIgnoreCase("un")) {
                attachment.setUNChecked(true);
                attachment.setUNIzoh(izoh);
            }
            attachmentService.save(attachment);
        }
//        redirectAttributes.addAttribute("id", id);
        return "redirect:/user/goToFile/{id}";
    }




//
//    @PostMapping("/user/check/{id}")
//    public String check(@PathVariable("id") Integer id){
//        return "redirect:/user/check/id";
//    }
}
