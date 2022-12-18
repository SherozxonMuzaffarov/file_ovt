package com.uzvagontamir.File.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileOriginalName;  // tdtu.jpg, tdtu.pdf
    private String contentType;  // application/pdf, image/png  google->file content type
    private Long size;  //fileni necha baytligi
    private String name; // systemga yuklaganda qanday nom bilan papkaga joylash 2ta bir xil nom bob qolmasligi uchun
    private String DepoNomi; //O'vt tex.adel


    private boolean isOTChecked; //O'vt tex.adel
    private boolean isOEChecked; //O'vt Ekonomist
    private boolean isUTChecked; //Upr. tex.adel
    private boolean isURChecked; //Upr. Remont
    private boolean isUEChecked; //Upr .ekonomist
    private boolean isUFChecked; //Upr. Finansist
    private boolean isUNChecked; //Upr. Hoks


    private String OTDepoIzoh; //O'vt depo
    private String OTIzoh; //O'vt tex.adel
    private String OEIzoh; //O'vt Ekonomist
    private String UTIzoh; //Upr. tex.adel
    private String URIzoh; //Upr. Remont
    private String UEIzoh; //Upr .ekonomist
    private String UFIzoh; //Upr. Finansist
    private String UNIzoh; //Upr. Hoks

}