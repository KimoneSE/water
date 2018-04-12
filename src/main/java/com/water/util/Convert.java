package com.water.util;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

/**
 * Created by Kimone.
 */
public class Convert {

    public static void docToHtml(String sourceFileName, String targetFileName) throws Exception{
//        String imagePathStr = "F:\\ac\\image\\";
        String imagePathStr = "/home/web_upload/image/";
        File dir = new File(imagePathStr);
        if(!dir.exists()){
            dir.mkdirs();
        }
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourceFileName));
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
        // 保存图片，并返回图片的相对路径
        wordToHtmlConverter.setPicturesManager((content, pictureType, name, width, height) -> {
            try(FileOutputStream out = new FileOutputStream(imagePathStr + name)){
                out.write(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "/home/web_upload/image/" + name;
        });
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(new File(targetFileName));

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
//        System.out.println(readfile(targetFileName));
    }

    public static void docxToHtml(String sourceFileName, String targetFileName) {
//        String imagePathStr = "F:\\ac\\image\\";
        String imagePathStr = "/home/web_upload/image/";
        File imgFolder = new File(imagePathStr);
        if(!imgFolder.exists()){
            imgFolder.mkdirs();
        }
        OutputStream out = null;
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
            XHTMLOptions options = XHTMLOptions.create();
            //存放图片的文件夹
            options.setExtractor(new FileImageExtractor(imgFolder));
            //html中图片的路径
            options.URIResolver(new FileURIResolver(imgFolder));
            out = new FileOutputStream(new File(targetFileName));
            XHTMLConverter xhtmlConverter = (XHTMLConverter)XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, out, options);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(out!=null){
                    out.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static String readfile(String filePath){
        File file = new File(filePath);
        InputStream input = null;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = new byte[1024];
        try {
            for(int n ; (n = input.read(bytes))!=-1 ; ){
                buffer.append(new String(bytes,0,n,"utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
         }

        String html_tmp = buffer.toString();
        String html = html_tmp.replaceAll("=\"/home","=\"");
        return html;
    }

    public static String getBody(String string) {
        String start = "<body";
        String end = "</body>";
        int index_tmp = string.indexOf(start);
        int startIndex = string.indexOf(62,index_tmp)+1;
        int endIndex = string.indexOf(end);
        String body = string.substring(startIndex, endIndex);
        return body;
    }

    public static String getStyle(String styleString) {
        String start = "<style";
        String end = "</style>";
        int index_tmp = styleString.indexOf(start);
        int startIndex = styleString.indexOf(62,index_tmp)+1;
        int endIndex = styleString.indexOf(end);
        String style = styleString.substring(startIndex, endIndex);
        return style;
    }
}
