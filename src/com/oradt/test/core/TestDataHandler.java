package com.oradt.test.core;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import jxl.*;

/**
 * Excel放在resource/data文件夹下</p>
 * Excel第一行为Map键值</p>
 * 代码参考郑鸿志的Blog
 * {@link www.zhenghongzhi.cn/post/42.html}
 * @ClassName: ExcelDataProvider
 * @Description: TODO(读取Excel数据)
 */
public class TestDataHandler implements Iterator<Object[]> {

    private Workbook book         = null;
    private Sheet    sheet        = null;
    private int      rowNum       = 0;
    private int      currentRowNo = 0;
    private int      columnNum    = 0;
    private String[] columnnName;
    boolean flag = true;

    public TestDataHandler(String classname, String sheetName, int  apiNum) {

        try {

            int dotNum = classname.indexOf(".");

            if (dotNum > 0) {
                classname = classname.substring(classname.lastIndexOf(".") + 1,
                        classname.length());
            }

            String path = "resource/data/" + classname + ".xls";
            InputStream inputStream = new FileInputStream(path);
                        
            book = Workbook.getWorkbook(inputStream);
            sheet = book.getSheet(sheetName);
            //sheet = book.getSheet(0);
            rowNum = sheet.getRows();
            Cell[] cell = sheet.getRow(apiNum - 1);
            columnNum = cell.length;
            columnnName = new String[cell.length];
            
            for (int i = 0; i < cell.length; i++) {
                columnnName[i] = cell[i].getContents().toString();
            }
//            this.currentRowNo++;
            this.currentRowNo = apiNum;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("unable to read Excel data");
        }
    }

	public boolean hasNext() {
		flag = !flag;
		if(flag == true){
//        if (this.rowNum == 0 || this.currentRowNo >= this.rowNum) {
            try {
                book.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } else {
            // sheet下一行内容为空判定结束
            if ((sheet.getRow(currentRowNo))[0].getContents().equals(""))
                return false;
            return true;
        }
    }

    public Object[] next() {

        Cell[] c = sheet.getRow(this.currentRowNo);
        Map<String, String> data = new HashMap<String, String>();
        // List<String> list = new ArrayList<String>();
        
        for (int i = 0; i < this.columnNum; i++) {

            String temp = "";

            try {
                temp = c[i].getContents().toString();
            } catch (ArrayIndexOutOfBoundsException ex) {
                temp = "";
            }

            // if(temp != null&& !temp.equals(""))
            // list.add(temp);
            data.put(this.columnnName[i], temp);
        }
        Object object[] = new Object[1];
        object[0] = data;
        this.currentRowNo++;
        return object;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove unsupported.");
    }
}