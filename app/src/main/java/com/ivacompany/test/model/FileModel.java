package com.ivacompany.test.model;

import java.util.Date;

/**
 * Created by root on 27.12.16.
 */

public class FileModel {
    private String fileName;
    private boolean isFolder;
    private Date modDate;
    private enum FileType {png, docx, pdf}
    private String fileType;
    private boolean isOrange;
    private boolean isBlue;

    private int id;
    private int parentId;

    public FileModel(int id, int parentId, String fileName, boolean isFolder, Date modDate, String fileType, int isOrange, int isBlue) {
        this.id = id;
        this.parentId = parentId;
        this.fileName = fileName;
        this.isFolder = isFolder;
        this.modDate = modDate;
        this.fileType = fileType;
        if (isOrange != 0) {
            this.isOrange = true;
        } else {
            this.isOrange = false;
        }
        if (isBlue != 0) {
            this.isBlue = true;
        } else {
            this.isBlue = false;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public boolean isOrange() {
        return isOrange;
    }

    public void setOrange(boolean orange) {
        isOrange = orange;
    }

    public boolean isBlue() {
        return isBlue;
    }

    public void setBlue(boolean blue) {
        isBlue = blue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getFileType(){
        switch (fileType) {
            case "image":
                return "." + FileType.png.name();
            case "docx":
                return "." + FileType.docx.name();
            case "pdf":
                return "." + FileType.pdf.name();
        }
        return "";
    }
}
