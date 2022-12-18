package com.uzvagontamir.File.repository;

import com.uzvagontamir.File.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    @Query("select u from Attachment u where u.isOTChecked = ?1")
    List<Attachment> findAllByOTChecked(boolean checked);

    @Query("select u from Attachment u where u.isOEChecked = ?1")
    List<Attachment> findAllByOEChecked(boolean checked);

    @Query("select u from Attachment u where u.isUTChecked = ?1")
    List<Attachment> findAllByUTChecked(boolean checked);

    @Query("select u from Attachment u where u.isURChecked = ?1")
    List<Attachment> findAllByURChecked(boolean checked);

    @Query("select u from Attachment u where u.isUEChecked = ?1")
    List<Attachment> findAllByUEChecked(boolean checked);
}
