package com.mmgiec.app.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmgiec.app.entities.Communication;
import com.mmgiec.app.entities.ImageUrl;
import com.mmgiec.app.entities.Phone;
import com.mmgiec.app.model.PhoneJSON;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class ConverterJSON {

    public List<Phone> convertToList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<PhoneJSON> objects = Arrays.asList(mapper.readValue(new File("d:\\phones-final.json"), PhoneJSON[].class));
        List<Phone> phones = new ArrayList<>();
        Phone phone = null;
        for (PhoneJSON p : objects) {
            phone = new Phone();
            phone.setBrand(p.getBrand());
            phone.setFullName(p.getFullName());
            phone.setPrice(p.getPrice());
            phone.setProcessor(p.getProcessor());
            phone.setGraphics(p.getGraphics());
            phone.setRam(p.getRam());
            phone.setBuiltInMemory(p.getBuiltInMemory());
            phone.setTypeOfDisplay(p.getTypeOfDisplay());
            phone.setSizeOfDisplay(p.getSizeOfDisplay());
            phone.setResolutionOfDisplay(p.getResolutionOfDisplay());

            List<Communication> communications = new ArrayList<>();
            for (String c : p.getCommunication()) {
                communications.add(new Communication(c));
            }
            phone.setCommunication(communications);
            phone.setNavigation(p.getNavigation());
            phone.setConnectors(p.getConnectors());
            phone.setCapacityOfBattery(p.getCapacityOfBattery());
            phone.setOperatingSystem(p.getOperatingSystem());
            phone.setFrontCameraMPX(p.getFrontCameraMPX());
            phone.setCameraMPX(p.getCameraMPX());
            phone.setFlashLamp(p.getFlashLamp());
            phone.setThickness(p.getThickness());
            phone.setWidth(p.getWidth());
            phone.setHeight(p.getHeight());
            phone.setWeight(p.getWeight());
            phone.setColour(p.getColour());

//            List<ImageUrl> urls = new ArrayList<>();
//            for (String u : p.getImagesUrl()) {
//                urls.add(new ImageUrl(u));
//            }
//            phone.setImageUrl(urls);
            ImageUrl imageUrl = new ImageUrl();
            if (!p.getImageTabs().isEmpty()) {
                byte[] tab = p.getImageTabs().get(0);
                String base64Encoded = Base64.getEncoder().encodeToString(tab);
//                StringBuffer sBuffer = new StringBuffer();
//                for(byte b : tab){
//                    sBuffer.append(b);
//                }
                //imageUrl.setImage(p.getImageTabs().get(0));
                imageUrl.setImage(base64Encoded);
                phone.getImageUrl().add(imageUrl);
            }
            phone.setImageUrlXKom(p.getImageUrlXKom());
            phone.setExtraInfo(p.getExtraInfo());
            phone.setGuarantee(p.getGuarantee());
            phone.setResolutionRecordingVideo(p.getResolutionRecordingVideo());
            phone.setIncludedAccessories(p.getIncludedAccessories());
            //phone.setImageTab(p.getImageTab());

            phones.add(phone);
        }
        return phones;
    }
}
