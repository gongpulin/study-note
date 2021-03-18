package com.gpl.design_pattern.proxy;

/**
 * @author gongpulin
 * date 2021-02-05
 */
public class ProxyImage implements Image {
    private String fileName;
    private RealImage realImage;
    public ProxyImage(String fileNmae) {
        this.fileName = fileNmae;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
