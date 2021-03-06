package com.flx.springboot.scaffold.common.pdf;

import com.flx.springboot.scaffold.common.utils.CollectionUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 利用模板生成pdf
 * 1.word编辑好模板，另存为aaa.pdf
 * 2.打开Adobe Acrobat pro软件选择准备表单制作模板
 * 3.使用util工具类生成pdf
 */
public class PdfTemplateUtils {

    public static void main(String[] args) {
        /*
        @RequestMapping(value = "downloadPdf", method = RequestMethod.POST)
        public void downloadPdf(HttpServletResponse response){
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("user.name","jack");
            dataMap.put("user.age",25);
            URL resource = this.getClass().getClassLoader().getResource("heihei.pdf");
            if(resource!=null)
                log.info("resource = "+resource.getPath());
            else
                log.info("resource is null");
            Optional.ofNullable(resource).ifPresent(e->{
                String templatePath = e.getPath();
                PdfTemplateUtils.downloadPdf(dataMap,"A001",templatePath,response);
            });
        }
        */
    }

    /**
     * 下载pdf文件
     * @param pdfContent
     * @param fileName
     * @param templatePath
     * @param response
     */
    public static void downloadPdf(PdfContent pdfContent,String fileName, String templatePath,HttpServletResponse response){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeSuffix = sdf.format(new Date());
        if(StringUtils.isBlank(fileName)){
            fileName = timeSuffix;
        }else {
            fileName = fileName + timeSuffix;
        }
        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/PDF;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8")+".pdf");
            createPdf(pdfContent,response.getOutputStream(),templatePath);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将填充好的pdf生成pdf文件
     * @param out 输出源
     * @param pdfContent 填充模板的数据
     * @param templatePath pdf模板路径
     * @throws DocumentException
     * @throws IOException
     */
    public static void createPdf(PdfContent pdfContent, ServletOutputStream out, String templatePath) throws DocumentException, IOException {

        PdfReader pdfReader = new PdfReader(templatePath);//输入源
        ByteArrayOutputStream bos = new ByteArrayOutputStream();//输出，array
        PdfStamper pdfStamper = new PdfStamper(pdfReader,bos);

        Document doc = new Document();
        PdfCopy pdfCopy = new PdfCopy(doc,out);
        doc.open();
        //填充好的pdf字节
        fillPdf(pdfStamper,pdfContent);
        int pageNo = pdfReader.getNumberOfPages();
        for (int i = 1; i <= pageNo; i++) {
            PdfImportedPage importedPage = pdfCopy.getImportedPage(new PdfReader(bos.toByteArray()),i);
            pdfCopy.addPage(importedPage);
        }
        doc.close();
        out.flush();
        out.close();
    }

    /**
     * 填充pdf，静态文字，表格图片
     * @param pdfStamper
     * @param pdfContent 各种数据文件
     * @throws DocumentException
     * @throws IOException
     */
    private static void fillPdf(PdfStamper pdfStamper,PdfContent pdfContent) throws DocumentException, IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("simsun.ttc");
        String fontPath = resource==null?null:resource.getPath();
        System.out.println("fontPath = "+fontPath);
        BaseFont font = BaseFont.createFont(fontPath+",1",BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
        pdfStamper.getAcroFields().addSubstitutionFont(font);
        fillPdfText(pdfStamper,pdfContent.getTextMap());
        fillPdfImage(pdfStamper,pdfContent.getImageMap());
        fillPdfTable(pdfStamper,pdfContent.getTableMap(),font);
        //设置pdf文件不可编辑
        pdfStamper.setFormFlattening(true);
        pdfStamper.close();
    }

    /**
     * 根据模板填充pdf内容
     * @param pdfStamper pdf模板
     * @param textMap 填充模板的文字数据
     */
    private static void fillPdfText(PdfStamper pdfStamper,Map<String,Object> textMap) throws IOException, DocumentException {
        if(CollectionUtils.isEmpty(textMap)){
            return;
        }
        AcroFields acroFields = pdfStamper.getAcroFields();
        for (String name : acroFields.getFields().keySet()) {
            String value = textMap.get(name) == null ? null : textMap.get(name).toString();
            acroFields.setField(name, value);
        }
    }

    /**
     * 处理表格类数据
     * 多个表格名称对应相应的表格的数据
     * K>>>>>data
     * userTable>>>List<List<String>>
     */
    private static void fillPdfTable(PdfStamper pdfStamper,Map<String, List<List<String>>> tableMap,BaseFont font) throws DocumentException {
        if(CollectionUtils.isEmpty(tableMap)){
            return;
        }
        for (String tableName : tableMap.keySet()){
            //获取表格数据
            List<List<String>> tableValue = tableMap.get(tableName);
            int pageNo = pdfStamper.getAcroFields().getFieldPositions(tableName).get(0).page;
            PdfContentByte pcb = pdfStamper.getOverContent(pageNo);
            //表格位置
            Rectangle rectangle = pdfStamper.getAcroFields().getFieldPositions(tableName).get(0).position;

            int column = tableValue.get(0).size();
            int row = tableValue.size();
            PdfPTable table = new PdfPTable(column);
            float totalWidth = rectangle.getRight() - rectangle.getLeft() - 1;
            float[] width = new float[column];
            for (int i = 0; i < column; i++) {
                if(i==0){
                    width[i] = 60f;
                }else {
                    width[i] = (totalWidth-60)/(column-1);
                }
            }
            table.setTotalWidth(width);
            table.setLockedWidth(true);
            table.setKeepTogether(true);
            table.setSplitLate(false);
            table.setSplitRows(true);
            Font f = new Font(font, 8, 0);
            //表格数据填写
            for (int i = 0; i < row; i++) {
                List<String> rowValue = tableValue.get(i);
                for (int j = 0; j < column; j++) {
                    Paragraph paragraph = new Paragraph(String.valueOf(rowValue.get(j)),f);
                    PdfPCell cell = new PdfPCell(paragraph);
                    cell.setBorderWidth(1);
                    cell.setPaddingBottom(8f);
                    cell.setVerticalAlignment(Element.ALIGN_CENTER);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setLeading(0, (float) 1.4);
                    table.addCell(cell);
                }
            }
            table.writeSelectedRows(0,-1,rectangle.getLeft(),rectangle.getTop(),pcb);
        }
    }

    /**
     * 处理pdf图片信息，可以添加多张图片
     * @param pdfStamper
     * @param imageMap
     */
    private static void fillPdfImage(PdfStamper pdfStamper,Map<String,String> imageMap) throws IOException, DocumentException {
        if(CollectionUtils.isEmpty(imageMap)){
            return;
        }
        for (String imageKey:imageMap.keySet()){
            //图片内容
            String imagePath = imageMap.get(imageKey);
            //获取图片页码
            int pageNo = pdfStamper.getAcroFields().getFieldPositions(imageKey).get(0).page;
            //获取图片位置
            Rectangle rectangle = pdfStamper.getAcroFields().getFieldPositions(imageKey).get(0).position;
            //获取x，y坐标
            float x = rectangle.getLeft();
            float y = rectangle.getBottom();
            //根据图片路径构建image对象
            Image image = Image.getInstance(imagePath);
            //图片大小自适应
            image.scaleToFit(rectangle.getWidth(),rectangle.getHeight());
            image.setAbsolutePosition(x,y);
            //获取所在图片页面
            PdfContentByte pcb = pdfStamper.getOverContent(pageNo);
            //添加图片
            pcb.addImage(image);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PdfContent {

        /**
         * 文字数据
         */
        private Map<String,Object> textMap;

        /**
         * 图片数据
         */
        private Map<String,String> imageMap;

        /**
         * 表格数据
         */
        private Map<String, List<List<String>>> tableMap;

        public static PdfContent of(Map<String,Object> textMap,Map<String,String> imageMap,Map<String, List<List<String>>> tableMap){
            return new PdfContent(textMap,imageMap,tableMap);
        }

    }

}
