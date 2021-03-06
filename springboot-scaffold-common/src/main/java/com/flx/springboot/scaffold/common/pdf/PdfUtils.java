package com.flx.springboot.scaffold.common.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
public class PdfUtils {

    // main测试
    public static void main(String[] args) throws Exception {
        BaseInfo baseInfo = BaseInfo.of("TestPdf file","fenglixiong","test subject","test keywords","tester");
        List<TableInfo> tableInfos = new ArrayList<>();
        TableInfo tableInfo1 = new TableInfo();
        tableInfo1.setTitle("用户信息表");
        tableInfo1.setWidthPercent(80);
        tableInfo1.setWidths(new int[]{25,75});
        List<String> row1 = Arrays.asList("姓名","爱好");
        List<String> row2 = Arrays.asList("jack","篮球");
        List<String> row3 = Arrays.asList("mary","看电视");
        tableInfo1.setTableValue(Arrays.asList(row1,row2,row3));

        TableInfo tableInfo2 = new TableInfo();
        tableInfo2.setTitle("用户薪水表");
        tableInfo2.setWidthPercent(60);
        tableInfo2.setWidths(new int[]{25,25,50});
        tableInfo2.setTableValue(Arrays.asList(
                Arrays.asList("姓名","年龄","薪水"),
                Arrays.asList("jack1","21","1000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack3","23","3000")));
        tableInfos.add(tableInfo1);
        tableInfos.add(tableInfo2);
        DocumentInfo documentInfo = new DocumentInfo();
        documentInfo.setBaseInfo(baseInfo);
        documentInfo.setTableInfo(tableInfos);
        generatePdfDesk("D:\\pdf\\","G.pdf",documentInfo);
    }

    // 生成PDF文件
    public static void testPDF(Document document) throws Exception {

        // 段落
        Paragraph paragraph = new Paragraph("美好的一天从早起开始！", titleFont);
        paragraph.setAlignment(1); //设置文字居中 0靠左   1，居中     2，靠右
        paragraph.setIndentationLeft(12); //设置左缩进
        paragraph.setIndentationRight(12); //设置右缩进
        paragraph.setFirstLineIndent(24); //设置首行缩进
        paragraph.setLeading(20f); //行间距
        paragraph.setSpacingBefore(5f); //设置段落上空白
        paragraph.setSpacingAfter(10f); //设置段落下空白

        // 直线
        Paragraph p1 = new Paragraph();
        p1.add(new Chunk(new LineSeparator()));

        // 点线
        Paragraph p2 = new Paragraph();
        p2.add(new Chunk(new DottedLineSeparator()));

        // 超链接
        Anchor anchor = new Anchor("baidu");
        anchor.setReference("www.baidu.com");

        // 定位
        Anchor gotoP = new Anchor("goto");
        gotoP.setReference("#top");

        // 添加图片
        Image image = Image.getInstance("https://img-blog.csdn.net/20180801174617455?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zNzg0ODcxMA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70");
        image.setAlignment(Image.ALIGN_CENTER);
        image.scalePercent(40); //依照比例缩放

        // 表格
        PdfPTable table = createTable(new float[] { 40, 120, 120, 120, 80, 80 });
        table.addCell(createCell("美好的一天", headFont));
        table.addCell(createCell("早上9:00", keyFont));
        table.addCell(createCell("中午11:00", keyFont));
        table.addCell(createCell("中午13:00", keyFont));
        table.addCell(createCell("下午15:00", keyFont));
        table.addCell(createCell("下午17:00", keyFont));
        table.addCell(createCell("晚上19:00", keyFont));
        Integer totalQuantity = 0;
        for (int i = 0; i < 5; i++) {
            table.addCell(createCell("起床", textFont));
            table.addCell(createCell("吃午饭", textFont));
            table.addCell(createCell("午休", textFont));
            table.addCell(createCell("下午茶", textFont));
            table.addCell(createCell("回家", textFont));
            table.addCell(createCell("吃晚饭", textFont));
            totalQuantity ++;
        }
        table.addCell(createCell("总计", keyFont));
        table.addCell(createCell("", textFont));
        table.addCell(createCell("", textFont));
        table.addCell(createCell("", textFont));
        table.addCell(createCell(totalQuantity + "件事", textFont));
        table.addCell(createCell("", textFont));

        document.add(paragraph);
        document.add(anchor);
        document.add(p2);
        document.add(gotoP);
        document.add(p1);
        document.add(table);
        document.add(image);
    }

    // 定义全局的字体静态变量
    private static BaseFont baseFont;
    private static Font titleFont;
    private static Font headFont;
    private static Font keyFont;
    private static Font textFont;
    // 静态代码块
    static {
        try {
            // 不同字体（这里定义为同一种字体：包含不同字号、不同style）
            URL resource = Thread.currentThread().getContextClassLoader().getResource("simsun.ttc");
            String fontPath = resource==null?null:resource.getPath();
            System.out.println("fontPath = "+fontPath);
            baseFont = BaseFont.createFont(fontPath+",1",BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
            titleFont = new Font(baseFont, 16, Font.BOLD);
            headFont = new Font(baseFont, 14, Font.BOLD);
            keyFont = new Font(baseFont, 10, Font.BOLD);
            textFont = new Font(baseFont, 10, Font.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 浏览器下载pdf文档
     * @param response
     * @param documentInfo
     */
    public static void downloadPdf(HttpServletResponse response,String fileName,DocumentInfo documentInfo){
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

            //1.新建document对象
            Document document = new Document(PageSize.A4);// 建立一个Document对象

            //2.找到输出源，和document建立联系
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            if(documentInfo.enableMark) {
                writer.setPageEvent(new WaterMark(documentInfo.getMarkText()));//添加水印
            }
            if(documentInfo.isEnableHf()){
                writer.setPageEvent(new HeaderFooter());//页眉页脚
            }

            //3.打开文档
            document.open();

            // 4.向文档中添加内容
            generatePDF(document,documentInfo);

            // 5.关闭文档
            document.close();
            writer.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存pdf到磁盘上面
     */
    public static void generatePdfDesk(String filePath,String fileName,DocumentInfo documentInfo)throws Exception{

        // 1.新建document对象
        Document document = new Document(PageSize.A4);// 建立一个Document对象

        // 2.建立一个书写器(Writer)与document对象关联
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeSuffix = sdf.format(new Date());
        if(StringUtils.isBlank(fileName)){
            fileName = timeSuffix;
        }
        if(fileName.endsWith(".pdf")){
            fileName = fileName.substring(0,fileName.lastIndexOf("."))+timeSuffix+".pdf";
        }
        log.info("pathName = "+(filePath+fileName));
        File file = new File(filePath+fileName);
        boolean success = file.createNewFile();
        if(!success){ return;}
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));

        if(documentInfo.enableMark) {
            writer.setPageEvent(new WaterMark(documentInfo.getMarkText()));//添加水印
        }
        if(documentInfo.isEnableHf()){
            writer.setPageEvent(new HeaderFooter());//页眉页脚
        }

        //3.打开文档
        document.open();

        // 4.向文档中添加内容
        generatePDF(document,documentInfo);

        // 5.关闭文档
        document.close();

    }

    /**
     * 填充Pdf文件
     * @param document
     * @param documentInfo
     * @throws DocumentException
     */
    private static void generatePDF(Document document,DocumentInfo documentInfo) throws DocumentException {
        if(StringUtils.isNoneBlank(documentInfo.getTitleInfo())){
            Paragraph titleParagraph = new Paragraph(documentInfo.getTitleInfo(), new Font(baseFont, 24, Font.BOLD));
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            titleParagraph.setSpacingAfter(50f);
            document.add(titleParagraph);
        }
        if(documentInfo.getBaseInfo()!=null){
            addDocumentBase(document,documentInfo.getBaseInfo());
        }
        if(documentInfo.getTableInfo()!=null) {
            for (TableInfo tableInfo : documentInfo.getTableInfo()) {
                addTable(document, tableInfo);
            }
        }
        if(documentInfo.getSignInfo()!=null){
            addDocumentSign(document,documentInfo.getSignInfo());
        }
    }



    /**
     * 创建基本信息
     * @param document
     * @param baseInfo
     */
    public static void addDocumentBase(Document document,BaseInfo baseInfo){
        document.addTitle(baseInfo.getTitle());// 标题
        document.addAuthor(baseInfo.getAuthor());// 作者
        document.addSubject(baseInfo.getSubject());// 主题
        document.addKeywords(baseInfo.getKeywords());// 关键字
        document.addCreator(baseInfo.getCreator());// 创建者
    }

    /**
     * 添加落款信息
     * @param document
     * @param signInfo
     */
    private static void addDocumentSign(Document document, SignInfo signInfo) throws DocumentException {
        Paragraph userParagraph = new Paragraph(signInfo.getUserLabel()+": "+signInfo.getCreateUser(), textFont);
        userParagraph.setSpacingBefore(50f);
        userParagraph.setIndentationLeft(300f);
        document.add(userParagraph);

        Paragraph timeParagraph = new Paragraph(signInfo.getTimeLabel()+": "+signInfo.getCreateTime(), textFont);
        timeParagraph.setIndentationLeft(300f);

        document.add(timeParagraph);
    }

    /**
     * 向pdf中添加表格
     * @param document
     * @param tableInfo
     * @throws DocumentException
     */
    public static void addTable(Document document,TableInfo tableInfo) throws DocumentException {
        List<List<String>> tableValue = tableInfo.getTableValue();
        int column = tableValue.get(0).size();
        int row = tableValue.size();
        if(column!=tableInfo.getWidths().length){
            throw new RuntimeException("please check column size!");
        }
        PdfPTable table = createTable(tableInfo.getWidths(),tableInfo.getWidthPercent());
        //添加表头
        if(StringUtils.isNoneBlank(tableInfo.getTitle())){
            table.setSpacingBefore(20f);
            table.setSpacingAfter(30f);
            Paragraph titleParagraph = new Paragraph(tableInfo.getTitle(), titleFont);
            document.add(titleParagraph);
        }
        //添加表内容
        for (int i = 0; i < row; i++) {
            List<String> rowValue = tableValue.get(i);
            for (int j = 0; j < column; j++) {
                if(i==0) {
                    table.addCell(createCell(rowValue.get(j), keyFont));
                }else {
                    table.addCell(createCell(rowValue.get(j), textFont));
                }
            }
        }
        document.add(table);
    }



    /**
     * 创建单元格
     */
    public static PdfPCell createCell(String value, Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value, font));
        cell.setBorderWidth(1);
        cell.setPaddingBottom(8f);
        return cell;
    }

    /**
     * 创建单元格
     */
    public static PdfPCell createPerfectCell(String value,float fixedHeight,
                                             Font font, float[] borderWidth, float[] paddingSize,
                                             int vAlign,int hAlign,int rowspan,int colspan,
                                             BaseColor borderColor,BaseColor backColor) {
        PdfPCell cell = new PdfPCell();
        cell.setFixedHeight(fixedHeight);
        cell.setVerticalAlignment(vAlign);//垂直对齐 Element.ALIGN_MIDDLE
        cell.setHorizontalAlignment(hAlign);//水平对齐 Element.ALIGN_CENTER
        cell.setPhrase(new Phrase(value, font));//文字
        cell.setBorderWidthLeft(borderWidth[0]);//左边框宽度
        cell.setBorderWidthRight(borderWidth[1]);//右边框宽度
        cell.setBorderWidthTop(borderWidth[2]);//上边框宽度
        cell.setBorderWidthBottom(borderWidth[3]);//下边框宽度
        cell.setPaddingTop(paddingSize[0]);//上边间距
        cell.setPaddingBottom(paddingSize[1]);//下边间距 8实现垂直居中
        cell.setRowspan(rowspan);//行合并
        cell.setColspan(colspan);//合并个数
        cell.setBorderColor(borderColor);//边框颜色
        cell.setBackgroundColor(backColor);//背景颜色
        return cell;
    }

    /**
     * 创建表格
     */
    public static PdfPTable createTable(float[] columnWith) throws DocumentException {
        PdfPTable table = new PdfPTable(columnWith.length);
        float totalWidth = 0.0f;
        for (int i = 0; i < columnWith.length; i++) {
            totalWidth += columnWith[i];
        }
        table.setTotalWidth(totalWidth);
        table.setTotalWidth(columnWith);
        table.setLockedWidth(true);
        table.setKeepTogether(true);
        table.setSplitLate(false);
        table.setSplitRows(true);
        return table;
    }

    /**
     * 创建表格，按照比例设置宽度
     */
    public static PdfPTable createTable(int[] widths,float withPercent) throws DocumentException {
        PdfPTable table = new PdfPTable(widths.length);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setWidthPercentage(withPercent);//表格宽度比例
        table.setWidths(widths);//设置宽度比例
        table.setLockedWidth(false);//使用百分比不能锁行
        table.setKeepTogether(false);//解决表格数据过多空白行问题
        table.setSplitLate(false);
        table.setSplitRows(true);
        return table;
    }

    /**
     * 创建表格，按照实际长度
     */
    public static PdfPTable createTable(int column,float totalWidth) throws DocumentException {
        PdfPTable table = new PdfPTable(column);
        table.setTotalWidth(totalWidth);
        float[] columnWith = new float[column];
        for (int i=0;i<column;i++){
            columnWith[i] = totalWidth/column;
        }
        table.setTotalWidth(columnWith);
        table.setLockedWidth(true);
        table.setKeepTogether(true);
        table.setSplitLate(false);
        table.setSplitRows(true);
        return table;
    }

    /**
     * 创建表格
     */
    public static PdfPTable createPerfectTable(int column,
                                               float withPercent,int[] widths,
                                               float totalWidth,float[] columnWith,
                                               float spaceBefore,float spaceAfter,
                                               boolean lockWidth,boolean keepTogether,
                                               int hAlign,int border) throws DocumentException {
        PdfPTable table = new PdfPTable(column);
        table.setWidthPercentage(withPercent);//表格宽度比例
        table.setWidths(widths);//设置宽度比例
        table.setTotalWidth(totalWidth);//设置表格宽度
        table.setTotalWidth(columnWith);//设置表格各列宽度
        table.setLockedWidth(lockWidth);//锁定行
        table.setKeepTogether(keepTogether);//标题跟随表格
        table.setHorizontalAlignment(hAlign);//设置水平对齐 Element.ALIGN_CENTER
        table.getDefaultCell().setBorder(border);//边框
        table.setSpacingBefore(spaceBefore);//表格前面距离
        table.setSpacingAfter(spaceAfter);//表格后面距离
        table.setSplitLate(false);//当前页能放多少放多少,解决行优先问题
        table.setSplitRows(true);//和上面一起解决cell跨页问题
        return table;
    }

    /**
     * 生成文档所需数据
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DocumentInfo{
        private boolean enableMark;//是否允许水印
        private String markText;//水印内容
        private boolean enableHf;//是否允许页眉页脚
        private String titleInfo;//文章头信息
        private BaseInfo baseInfo;//基础信息
        private SignInfo signInfo;//落款信息
        private List<TableInfo> tableInfo;//表格信息
    }

    /**
     * 文档基本信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BaseInfo{
        private String title;
        private String author;
        private String subject;
        private String keywords;
        private String creator;
        public static BaseInfo of(String title,String author,String subject,String keywords,String creator){
            return new BaseInfo(title,author,subject,keywords,creator);
        }
    }

    /**
     * 文档表格信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TableInfo{
        private String title;
        private float widthPercent;
        private int[] widths;
        private List<List<String>> tableValue;
    }

    /**
     * 落款信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignInfo{
        private String userLabel;
        private String createUser;
        private String timeLabel;
        private String createTime;
        public static SignInfo of(String userLabel,String createUser,String timeLabel,String createTime){
            return new SignInfo(userLabel,createUser,timeLabel,createTime);
        }
    }

}
