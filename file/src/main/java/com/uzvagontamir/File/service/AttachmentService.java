package com.uzvagontamir.File.service;

import com.uzvagontamir.File.model.Attachment;
import com.uzvagontamir.File.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    public Attachment save(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    public Optional<Attachment> findById(Integer id) {
        return attachmentRepository.findById(id);
    }

    public List<Attachment> findAll() {
        return attachmentRepository.findAll();
    }

    public List<Attachment> findAllByOTChecked(boolean ischecked){
        return attachmentRepository.findAllByOTChecked(ischecked);
    }

    public List<Attachment> findAllByOEChecked(boolean ischecked) {
        return attachmentRepository.findAllByOEChecked(ischecked);
    }

    public List<Attachment> findAllByUTChecked(boolean ischecked) {
        return attachmentRepository.findAllByUTChecked(ischecked);
    }

    public List<Attachment> findAllByURChecked(boolean ischecked) {
        return attachmentRepository.findAllByURChecked(ischecked);
    }

    public List<Attachment> findAllByUEChecked(boolean ischecked) {
        return attachmentRepository.findAllByUEChecked(ischecked);
    }
}
