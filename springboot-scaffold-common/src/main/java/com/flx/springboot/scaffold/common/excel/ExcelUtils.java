package com.flx.springboot.scaffold.common.excel;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author yulinfu
 */
@Slf4j
@Component
public class ExcelUtils {

    private static final Integer MAX_SHEET_SIZE = 65535;

    /**
     * @param list      数据源
     * @param fieldMap  类的英文属性和Excel中的中文列名的对应关系
     *                  如果需要的是引用对象的属性，则英文属性使用类似于EL表达式的格式
     *                  如：list中存放的都是student，student中又有college属性，而我们需要学院名称，则可以这样写
     *                  fieldMap.put("college.collegeName","学院名称")
     * @param sheetName 工作表的名称
     * @param sheetSize 每个工作表中记录的最大个数
     * @param out       导出流
     * @throws
     * @MethodName : listToExcel
     * @Description : 导出Excel（可以导出到本地文件系统，也可以导出到浏览器，可自定义工作表大小）
     */
    public static <T> void listToExcel(List<T> list, Map<String, String> fieldMap, String sheetName, int sheetSize, OutputStream out) throws Exception {

        if (list == null||list.size() == 0) {
            throw new Exception("There is no data in Excel file!");
        }

        if (sheetSize > MAX_SHEET_SIZE || sheetSize < 1) {
            sheetSize = MAX_SHEET_SIZE;
        }

        //创建工作簿并发送到OutputStream指定的地方
        WritableWorkbook wwb;
        try {
            wwb = Workbook.createWorkbook(out);
            //因为2003的Excel一个工作表最多可以有65536条记录，除去列头剩下65535条
            //所以如果记录太多，需要放到多个工作表中，其实就是个分页的过程
            //1.计算一共有多少个工作表
            double sheetNum = Math.ceil(list.size() / (double) sheetSize);

            //2.创建相应的工作表，并向其中填充数据
            for (int i = 0; i < sheetNum; i++) {
                if (1 == sheetNum) {
                    //如果只有一个工作表的情况
                    WritableSheet sheet = wwb.createSheet(sheetName, i);
                    fillSheet(sheet, list, fieldMap, 0, list.size() - 1);
                } else {
                    //有多个工作表的情况
                    WritableSheet sheet = wwb.createSheet(sheetName + (i + 1), i);

                    //获取开始索引和结束索引
                    int firstIndex = i * sheetSize;
                    int lastIndex = (i + 1) * sheetSize - 1 > list.size() - 1 ? list.size() - 1 : (i + 1) * sheetSize - 1;
                    //填充工作表
                    fillSheet(sheet, list, fieldMap, firstIndex, lastIndex);
                }
            }
            wwb.write();
            wwb.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param list     数据源
     * @param fieldMap 类的英文属性和Excel中的中文列名的对应关系
     * @param out      导出流
     * @throws Exception
     * @MethodName : listToExcel
     * @Description : 导出Excel（可以导出到本地文件系统，也可以导出到浏览器，工作表大小为2003支持的最大值）
     */
    public static <T> void listToExcel(List<T> list, Map<String, String> fieldMap, String sheetName, OutputStream out) throws Exception {

        listToExcel(list, fieldMap, sheetName, MAX_SHEET_SIZE, out);

    }

    /**
     * @param fileNamePerfix 文件名前缀
     * @param list           数据源
     * @param fieldMap       类的英文属性和Excel中的中文列名的对应关系
     * @param sheetSize      每个工作表中记录的最大个数
     * @param response       使用response可以导出到浏览器
     * @throws Exception
     * @MethodName : listToExcel
     * @Description : 导出Excel（导出到浏览器，可以自定义工作表的大小）
     */
    public static <T> void listToExcel(String fileNamePerfix, List<T> list, Map<String, String> fieldMap, String sheetName, Integer sheetSize, HttpServletResponse response) throws Exception {

        //设置默认文件名为前缀+当前时间：年月日时分秒
        String fileName = fileNamePerfix + "-" + new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
        //设置response头信息
        //response.reset();
        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
        //创建工作簿并发送到浏览器
        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
            OutputStream out = response.getOutputStream();
            listToExcel(list, fieldMap, sheetName, sheetSize, out);
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            //fileName = new String(fileName.getBytes("gb2312"),"ISO8859-1");
            //response.setHeader("Content-disposition", "attachment;filename="+fileName+".xls");
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param list     数据源
     * @param fieldMap 类的英文属性和Excel中的中文列名的对应关系
     * @param response 使用response可以导出到浏览器
     * @throws Exception
     * @MethodName : listToExcel
     * @Description : 导出Excel（导出到浏览器，工作表的大小是2003支持的最大值）
     */
    public static <T> void listToExcel(String fileName, List<T> list, Map<String, String> fieldMap, String sheetName, HttpServletResponse response) throws Exception {
        listToExcel(fileName, list, fieldMap, sheetName, MAX_SHEET_SIZE, response);
    }

    /**
     * @param entityClass  ：List中对象的类型（Excel中的每一行都要转化为该类型的对象）
     * @param fieldMap     ：Excel中的中文列头和类的英文属性的对应关系Map
     * @param uniqueFields ：指定业务主键组合（即复合主键），这些列的组合不能重复
     * @return ：List
     * @throws Exception
     * @MethodName : excelToList
     * @Description : 将Excel转化为List
     */
    public static <T> List<T> excelToList(Workbook wb, String sheetName, Class<T> entityClass, Map<String, String> fieldMap, String[] uniqueFields) throws Exception {

        //定义要返回的list
        List<T> resultList = new ArrayList<T>();

        try {

            //根据Excel数据源创建WorkBook
            //Workbook wb=Workbook.getWorkbook(in);
            //获取工作表
            Sheet sheet = wb.getSheet(sheetName);

            //获取工作表的有效行数
            int realRows = 0;
            for (int i = 0; i < sheet.getRows(); i++) {
                int nullCols = 0;
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell currentCell = sheet.getCell(j, i);
                    if (currentCell == null || "".equals(currentCell.getContents().toString())) {
                        nullCols++;
                    }
                }

                if (nullCols == sheet.getColumns()) {
                    break;
                } else {
                    realRows++;
                }
            }

            //如果Excel中没有数据则提示错误
            if (realRows <= 1) {
                throw new Exception("There is no data in Excel file!");
            }


            Cell[] firstRow = sheet.getRow(0);

            String[] excelFieldNames = new String[firstRow.length];

            //获取Excel中的列名
            for (int i = 0; i < firstRow.length; i++) {
                excelFieldNames[i] = firstRow[i].getContents().toString().trim();
            }

            //判断需要的字段在Excel中是否都存在
            boolean isExist = true;
            List<String> excelFieldList = Arrays.asList(excelFieldNames);
            for (Entry e : fieldMap.entrySet()) {
                if (!excelFieldList.contains(e.getValue())) {
                    throw new Exception("Lack of necessary fields in Excel or incorrect field names {0}!||"+e.getValue());
                }
            }


            Map<String, Integer> colMap = new HashMap<String, Integer>(10);
            for (int i = 0; i < excelFieldNames.length; i++) {
                colMap.put(excelFieldNames[i], firstRow[i].getColumn());
            }

            if (null != uniqueFields) {
                //判断是否有重复行
                //1.获取uniqueFields指定的列
                Cell[][] uniqueCells = new Cell[uniqueFields.length][];
                for (int i = 0; i < uniqueFields.length; i++) {
                    int col = colMap.get(uniqueFields[i]);
                    uniqueCells[i] = sheet.getColumn(col);
                }

                //2.从指定列中寻找重复行
                for (int i = 1; i < realRows; i++) {
                    int nullCols = 0;
                    for (int j = 0; j < uniqueFields.length; j++) {
                        String currentContent = uniqueCells[j][i].getContents();
                        Cell sameCell = sheet.findCell(currentContent,
                                uniqueCells[j][i].getColumn(),
                                uniqueCells[j][i].getRow() + 1,
                                uniqueCells[j][i].getColumn(),
                                uniqueCells[j][realRows - 1].getRow(),
                                true);
                        if (sameCell != null) {
                            nullCols++;
                        }
                    }

                    if (nullCols == uniqueFields.length) {
                        // get reduplicate content
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < uniqueFields.length; j++) {
                            String currentContent = uniqueCells[j][i].getContents();
                            stringBuilder.append("|" + currentContent);
                        }
                        throw new Exception("There are duplicate rows in Excel,please check it,content={0}!||" + stringBuilder.toString());
                    }
                }
            }

            //将sheet转换为list
            for (int i = 1; i < realRows; i++) {
                //新建要转换的对象
                T entity = entityClass.newInstance();

                //给对象中的字段赋值
                for (Entry<String, String> entry : fieldMap.entrySet()) {
                    //获取中文字段名
                    String cnNormalName = entry.getValue();
                    //获取英文字段名
                    String enNormalName = entry.getKey();
                    //根据中文字段名获取列号
                    int col = colMap.get(cnNormalName);

                    //获取当前单元格中的内容
                    String content = sheet.getCell(col, i).getContents().toString().trim();

                    //给对象赋值
                    setFieldValueByName(enNormalName, content, entity);
                }

                resultList.add(entity);
            }
        } catch (Exception e) {
            log.error("Failed to import Excel",e);
        }
        return resultList;
    }

    /**
     * @param in
     * @param sheetName
     * @param entityClass
     * @param fieldMap
     * @param uniqueFields 指定业务主键组合（即复合主键），这些列的组合不能重复
     * @return
     * @throws
     */
    public static <T> List<T> excelToList(InputStream in, String sheetName, Class<T> entityClass, Map<String, String> fieldMap, String[] uniqueFields) {
        try {
            Workbook wb = Workbook.getWorkbook(in);
            return excelToList(wb, sheetName, entityClass, fieldMap, uniqueFields);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param in
     * @param entityClass
     * @param fieldMap
     * @param uniqueFields
     * @return
     * @throws
     */
    public static <T> List<T> excelToList(InputStream in, Class<T> entityClass, Map<String, String> fieldMap, String[] uniqueFields) throws Exception {
        Workbook workbook = Workbook.getWorkbook(in);
        List<T> list = new ArrayList<>();
        for (String sheetName : workbook.getSheetNames()) {
            list.addAll(
                    excelToList(workbook, sheetName, entityClass, fieldMap, uniqueFields));
        }
        return list;
    }

    /**
     * @param fieldName 字段名
     * @param o         对象
     * @return 字段值
     * @MethodName : getFieldValueByName
     * @Description : 根据字段名获取字段值
     */
    private static Object getFieldValueByName(String fieldName, Object o) throws Exception {

        Object value = null;
        Field field = getFieldByName(fieldName, o.getClass());

        if (field != null) {
            field.setAccessible(true);
            value = field.get(o);
        } else {
            throw new Exception("The class {0} does not exist field name {1}!||" + o.getClass().getSimpleName() + "||" + fieldName);
        }

        return value;
    }




    /*<-------------------------辅助的私有方法----------------------------------------------->*/

    /**
     * @param fieldName 字段名
     * @param clazz     包含该字段的类
     * @return 字段
     * @MethodName : getFieldByName
     * @Description : 根据字段名获取字段
     */
    private static Field getFieldByName(String fieldName, Class<?> clazz) {
        //拿到本类的所有字段
        Field[] selfFields = clazz.getDeclaredFields();

        //如果本类中存在该字段，则返回
        for (Field field : selfFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        //否则，查看父类中是否存在此字段，如果有则返回
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null && superClazz != Object.class) {
            return getFieldByName(fieldName, superClazz);
        }

        //如果本类和父类都没有，则返回空
        return null;
    }

    /**
     * @param fieldNameSequence 带路径的属性名或简单属性名
     * @param o                 对象
     * @return 属性值
     * @throws Exception
     * @MethodName : getFieldValueByNameSequence
     * @Description :
     * 根据带路径或不带路径的属性名获取属性值
     * 即接受简单属性名，如userName等，又接受带路径的属性名，如student.department.name等
     */
    private static Object getFieldValueByNameSequence(String fieldNameSequence, Object o) throws Exception {

        Object value = null;

        //将fieldNameSequence进行拆分
        String[] attributes = fieldNameSequence.split("\\.");
        if (attributes.length == 1) {
            value = getFieldValueByName(fieldNameSequence, o);
        } else {
            //根据属性名获取属性对象
            Object fieldObj = getFieldValueByName(attributes[0], o);
            String subFieldNameSequence = fieldNameSequence.substring(fieldNameSequence.indexOf(".") + 1);
            value = getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
        }
        return value;

    }

    /**
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @param o          对象
     * @MethodName : setFieldValueByName
     * @Description : 根据字段名给对象的字段赋值
     */
    private static void setFieldValueByName(String fieldName, Object fieldValue, Object o) throws Exception {

        Field field = getFieldByName(fieldName, o.getClass());
        if (field != null) {
            field.setAccessible(true);
            //获取字段类型
            Class<?> fieldType = field.getType();

            //根据字段类型给字段赋值
            String value = fieldValue + "";
            value = value.trim();
            if (String.class == fieldType) {
                field.set(o, value);
            } else if ((Integer.TYPE == fieldType)
                    || (Integer.class == fieldType)) {
                if (StringUtils.isEmpty(value)) {
                    field.set(o, null);
                } else {
                    field.set(o, Integer.parseInt(value));
                }
            } else if ((Long.TYPE == fieldType)
                    || (Long.class == fieldType)) {
                if (StringUtils.isEmpty(value)) {
                    field.set(o, null);
                } else {
                    field.set(o, Long.valueOf(value));
                }
            } else if ((Float.TYPE == fieldType)
                    || (Float.class == fieldType)) {
                if (StringUtils.isEmpty(value)) {
                    field.set(o, null);
                } else {
                    field.set(o, Float.valueOf(value));
                }
            } else if ((Short.TYPE == fieldType)
                    || (Short.class == fieldType)) {
                if (StringUtils.isEmpty(value)) {
                    field.set(o, null);
                } else {
                    field.set(o, Short.valueOf(value));
                }
            } else if ((Double.TYPE == fieldType)
                    || (Double.class == fieldType)) {
                if (StringUtils.isEmpty(value)) {
                    field.set(o, null);
                } else {
                    field.set(o, Double.valueOf(value));
                }
            } else if (Character.TYPE == fieldType) {
                if ((fieldValue != null) && (fieldValue.toString().length() > 0)) {
                    field.set(o, fieldValue.toString().charAt(0));
                }
            } else if (Date.class == fieldType) {
                if ((fieldValue != null) && (fieldValue.toString().length() > 0)) {
                    field.set(o, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fieldValue.toString()));
                }
            } else if (fieldType.isEnum()) {
                if ((fieldValue != null) && (fieldValue.toString().length() > 0)) {
                    Method m = fieldType.getMethod("valueOf", String.class);
                    Object writeValue = m.invoke(o, value);
                    field.set(o, writeValue);
                }
            } else {
                field.set(o, fieldValue);
            }
        } else {
            throw new Exception("The class {0} does not exist field name {1}!||" + o.getClass().getSimpleName() + "||" + fieldName);
        }
    }

    /**
     * @param ws
     * @MethodName : setColumnAutoSize
     * @Description : 设置工作表自动列宽和首行加粗
     */
    private static void setColumnAutoSize(WritableSheet ws, int extraWith) {
        //获取本列的最宽单元格的宽度
        for (int i = 0; i < ws.getColumns(); i++) {
            int colWith = 0;
            for (int j = 0; j < ws.getRows(); j++) {
                String content = ws.getCell(i, j).getContents().toString();
                int cellWith = content.length();
                if (colWith < cellWith) {
                    colWith = cellWith;
                }
            }
            //设置单元格的宽度为最宽宽度+额外宽度
            ws.setColumnView(i, colWith + extraWith);
        }

    }

    /**
     * @param sheet      工作表
     * @param list       数据源
     * @param fieldMap   中英文字段对应关系的Map
     * @param firstIndex 开始索引
     * @param lastIndex  结束索引
     * @MethodName : fillSheet
     * @Description : 向工作表中填充数据
     */
    private static <T> void fillSheet(
            WritableSheet sheet,
            List<T> list,
            Map<String, String> fieldMap,
            int firstIndex,
            int lastIndex
    ) throws Exception {

        //定义存放英文字段名和中文字段名的数组
        String[] enFields = new String[fieldMap.size()];
        String[] cnFields = new String[fieldMap.size()];

        //填充数组
        int count = 0;
        for (Entry<String, String> entry : fieldMap.entrySet()) {
            cnFields[count] = entry.getValue();
            enFields[count] = entry.getKey();
            count++;
        }
        //填充表头
        for (int i = 0; i < cnFields.length; i++) {
            Label label = new Label(i, 0, cnFields[i]);
            sheet.addCell(label);
        }

        //填充内容
        int rowNo = 1;
        for (int index = firstIndex; index <= lastIndex; index++) {
            //获取单个对象
            T item = list.get(index);
            if (item.getClass() == AddListResult.class) {
                AddListResult addListResult = (AddListResult) item;
                if (addListResult.getAddResult().equals(0)) {
                    WritableFont font1 = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
                    WritableCellFormat cellFormat = new WritableCellFormat(font1);
                    //设置背景颜色;
                    cellFormat.setBackground(Colour.YELLOW);
                    for (int i = 0; i < enFields.length; i++) {
                        Object objValue = getFieldValueByNameSequence(enFields[i], addListResult.getData());
                        String fieldValue = objValue == null ? "" : objValue.toString();
                        Label label = new Label(i, rowNo, fieldValue, cellFormat);
                        sheet.addCell(label);
                    }
                    String addResult = addListResult.getAddType() + " fail";
                    Label label = new Label(enFields.length, rowNo, addResult, cellFormat);
                    sheet.addCell(label);
                } else {
                    for (int i = 0; i < enFields.length; i++) {
                        Object objValue = getFieldValueByNameSequence(enFields[i], addListResult.getData());
                        String fieldValue = objValue == null ? "" : objValue.toString();
                        if (objValue != null) {
                            if (objValue.getClass().isAssignableFrom(Date.class)) {
                                if (StringUtils.isNotBlank(fieldValue)) {
                                    fieldValue = DateFormatUtils.format((Date) objValue, "yyyy/MM/dd HH:mm:ss");
                                }
                            } else if (objValue.getClass().isAssignableFrom(java.sql.Date.class)) {
                                if (StringUtils.isNotBlank(fieldValue)) {
                                    fieldValue = DateFormatUtils.format((java.sql.Date) objValue, "yyyy/MM/dd HH:mm:ss");
                                }
                            }
                        }
                        Label label = new Label(i, rowNo, fieldValue);
                        sheet.addCell(label);
                    }
                }
            } else {
                for (int i = 0; i < enFields.length; i++) {
                    Object objValue = getFieldValueByNameSequence(enFields[i], item);
                    String fieldValue = objValue == null ? "" : objValue.toString();
                    if (objValue != null) {
                        if (objValue.getClass().isAssignableFrom(Date.class)) {
                            if (StringUtils.isNotBlank(fieldValue)) {
                                fieldValue = DateFormatUtils.format((Date) objValue, "yyyy/MM/dd HH:mm:ss");
                            }
                        } else if (objValue.getClass().isAssignableFrom(java.sql.Date.class)) {
                            if (StringUtils.isNotBlank(fieldValue)) {
                                fieldValue = DateFormatUtils.format((java.sql.Date) objValue, "yyyy/MM/dd HH:mm:ss");
                            }
                        }
                    }
                    Label label = new Label(i, rowNo, fieldValue);
                    sheet.addCell(label);
                }
            }
            rowNo++;
        }

        //设置自动列宽
        setColumnAutoSize(sheet, 5);
    }

    private static String tryTransEncodingByRequest(String s) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes == null) {
            return s;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        if (request == null) {
            return s;
        }
        final String userAgent = request.getHeader("User-Agent");
        if (userAgent == null || userAgent.isEmpty()) {
            return s;
        }
        if (userAgent.contains("MSIE") || userAgent.contains("rv:11") || userAgent.contains("Edge")) {
            try {
                return URLEncoder.encode(s, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return s;
            }
        } else if (userAgent.contains("Mozilla")) {
            try {
                return new String(s.getBytes("UTF-8"), "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                return s;
            }
        } else {
            try {
                return URLEncoder.encode(s, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return s;
            }
        }
    }
}