package com.png.comms.email;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Mail {
    private String fromAddress;
    private String fromName;
    private String to;
    private String toList[];
    private String cc;
    private String ccList[];
    private String bcc;
    private String bccList[];
    private String subject;
    private String content;
    private String pathToAttachment;
    private String attachmentFilename;
    private HashMap<String, Object> templateModel;
    private InternetAddress internetAddressFrom;

    public  Mail() {
        this.internetAddressFrom = new InternetAddress();
    }


    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
        this.internetAddressFrom.setAddress(fromAddress);
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) throws UnsupportedEncodingException {
        this.fromName = fromName;
        this.internetAddressFrom.setPersonal(fromName);
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPathToAttachment() {
        return pathToAttachment;
    }

    public void setPathToAttachment(String pathToAttachment) {
        this.pathToAttachment = pathToAttachment;
    }

    public String getAttachmentFilename() {
        return attachmentFilename;
    }

    public void setAttachmentFilename(String attachmentFilename) {
        this.attachmentFilename = attachmentFilename;
    }

    public String[] getToList() {
        return toList;
    }

    public void setToList(String[] toList) {
        this.toList = toList;
    }

    public HashMap<String, Object> getTemplateModel() {
        return templateModel;
    }

    public void setTemplateModel(HashMap<String, Object> templateModel) {
        this.templateModel = templateModel;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String[] getCcList() {
        return ccList;
    }

    public void setCcList(String[] ccList) {
        this.ccList = ccList;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String[] getBccList() {
        return bccList;
    }

    public void setBccList(String[] bccList) {
        this.bccList = bccList;
    }

    public InternetAddress getInternetAddressFrom() {
        return internetAddressFrom;
    }

    public void setInternetAddressFrom(InternetAddress internetAddressFrom) {
        this.internetAddressFrom = internetAddressFrom;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "from='" + fromAddress + '\'' +
                "from-name='" + fromName + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
