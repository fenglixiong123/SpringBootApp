package com.flx.springboot.scaffold.application.test;

import com.flx.springboot.scaffold.common.pdf.PdfTemplateUtils;
import com.flx.springboot.scaffold.common.pdf.PdfUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.*;

@Slf4j
@RestController
@Api(value = "pdf生成接口")
@RequestMapping("/pdf")
public class TestPdf {

    @ApiOperation("下载pdf")
    @RequestMapping(value = "downloadPdfFromTemplate", method = RequestMethod.POST)
    public void downloadPdfFromTemplate(HttpServletResponse response){
        Map<String,Object> textMap = new HashMap<>();
        textMap.put("user.name","小明");
        textMap.put("user.age",25);

        Map<String, List<List<String>>> tableMap = new HashMap<>();
        List<String> row1 = new ArrayList<>();
        row1.add("name");
        row1.add("消化");
        List<String> row2 = new ArrayList<>();
        row2.add("jack");
        row2.add("22");
        List<String> row3 = new ArrayList<>();
        row3.add("marry");
        row3.add("33");
        List<List<String>> tableValue = new ArrayList<>();
        tableValue.add(row1);
        tableValue.add(row2);
        tableValue.add(row3);
        tableMap.put("userTable",tableValue);

        URL resource = this.getClass().getClassLoader().getResource("wawa.pdf");
        if(resource!=null)
            log.info("resource = "+resource.getPath());
        else
            log.info("resource is null");
        Optional.ofNullable(resource).ifPresent(e->{
            String templatePath = e.getPath();
            PdfTemplateUtils.downloadPdf(PdfTemplateUtils.PdfContent.of(templatePath,textMap,null,tableMap),"A001",response);
        });
    }

    @ApiOperation("下载pdf")
    @RequestMapping(value = "downloadPdfFromCoding", method = RequestMethod.POST)
    public void downloadPdf(HttpServletResponse response){
        List<PdfUtils.TableInfo> tableInfos = new ArrayList<>();
        PdfUtils.TableInfo tableInfo1 = new PdfUtils.TableInfo();
        tableInfo1.setTitle("用户信息表")
                .setWidthPercent(80).setWidths(new int[]{25,75})
                .setTableValue(Arrays.asList(Arrays.asList("姓名","爱好"),Arrays.asList("jack","篮球"),Arrays.asList("mary","看电视")));

        PdfUtils.TableInfo tableInfo2 = new PdfUtils.TableInfo();
        tableInfo2.setTitle("用户薪水表")
                .setWidthPercent(60)
                .setWidths(new int[]{25,25,50})
                .setTableValue(Arrays.asList(
                        Arrays.asList("姓名","年龄","薪水"), Arrays.asList("jack1","21","1000"),
                        Arrays.asList("jack2","22","2000"), Arrays.asList("jack3","23","3000")));
        tableInfos.add(tableInfo1);
        tableInfos.add(tableInfo2);
        PdfUtils.downloadPdf(response,"A", PdfUtils.DocumentInfo.builder()
                .setBaseInfo(PdfUtils.BaseInfo.of("TestPdf file","fenglixiong","test subject","test keywords","tester"))
                .setTitleInfo("我是个好人").setSignInfo(PdfUtils.SignInfo.of("姓名","fenglixiong","时间","2021-12-15 12:15:13"))
                .setEnableMark(true).setMarkText("FENGLIXIONG").setEnableHf(true).setTableInfo(tableInfos));
    }

    /**
     * 测试生成多页的Pdf
     * @param args
     */
    public static void main(String[] args) {
        PdfUtils.BaseInfo baseInfo = PdfUtils.BaseInfo.of("TestPdf file","fenglixiong","test subject","test keywords","tester");
        List<PdfUtils.TableInfo> tableInfos = new ArrayList<>();
        PdfUtils.TableInfo tableInfo1 = new PdfUtils.TableInfo();
        tableInfo1.setTitle("用户信息表");
        tableInfo1.setWidthPercent(80);
        tableInfo1.setWidths(new int[]{25,75});
        List<String> row1 = Arrays.asList("姓名","爱好");
        List<String> row2 = Arrays.asList("jack","篮球");
        List<String> row3 = Arrays.asList("mary","看电视");
        tableInfo1.setTableValue(Arrays.asList(row1,row2,row3));

        PdfUtils.TableInfo tableInfo2 = new PdfUtils.TableInfo();
        tableInfo2.setTitle("用户薪水表");
        tableInfo2.setWidthPercent(60);
        tableInfo2.setWidths(new int[]{25,25,50});
        tableInfo2.setTableValue(Arrays.asList(
                Arrays.asList("姓名","年龄","薪水"),
                Arrays.asList("jack1","21","1000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack2","22","2000"),
                Arrays.asList("jack3","23","3000")));
        tableInfos.add(tableInfo1);
        tableInfos.add(tableInfo2);
        PdfUtils.DocumentInfo documentInfo = new PdfUtils.DocumentInfo();
        documentInfo.setSignInfo(PdfUtils.SignInfo.of("报告生成用户","冯晓明","报告生成时间","2021-12-15 14:25:33"));
        documentInfo.setBaseInfo(baseInfo);
        documentInfo.setTitleInfo("报表生成内容");
        documentInfo.setTableInfo(tableInfos);
        try {
            PdfUtils.savePdfToDesk("D:\\pdf\\","G.pdf",documentInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
