package com.uzvagontamir.File.service;

import com.uzvagontamir.File.model.AttachmentContent;
import com.uzvagontamir.File.repository.AttachmentContentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttachmentContentService {
    final
    AttachmentContentRepository contentRepository;

    public AttachmentContentService(AttachmentContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public void save(AttachmentContent attachmentContent) {
        contentRepository.save(attachmentContent);
    }

    public Optional<AttachmentContent> findByAttachmentId(Integer id) {
        return contentRepository.findByAttachmentId(id);
    }

    public void deleteById(Integer id) {
        contentRepository.deleteById(id);
    }
}
