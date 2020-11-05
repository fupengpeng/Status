package com.fpp.status.utils;

import android.content.Context;
import android.widget.Toast;

import com.fpp.status.entity.CardBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.Pattern;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtil {

    private static WritableCellFormat arial14format = null;
    private static WritableCellFormat arial10format = null;
    private static WritableCellFormat arial12format = null;
    private final static String UTF8_ENCODING = "UTF-8";

    /**
     * 单元格的格式设置 字体大小 颜色 对齐方式、背景颜色等...
     */
    private static void format() {
        try {
            //
            WritableFont arial14font = new WritableFont(WritableFont.ARIAL, 30, WritableFont.BOLD,
                    false, UnderlineStyle.SINGLE, Colour.BLUE, ScriptStyle.SUBSCRIPT);
            arial14font.setColour(Colour.RED);

            arial14format = new WritableCellFormat(arial14font);
            arial14format.setAlignment(Alignment.CENTRE);
            arial14format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            arial14format.setBackground(Colour.LIGHT_BLUE);

            // 标题栏格式设置
            WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
            arial10format = new WritableCellFormat(arial10font);

            arial10format.setAlignment(Alignment.CENTRE);
            arial10format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            arial10format.setBackground(Colour.GRAY_25);

            // 内容格式设置               // 字体               // 大小 // 样式
            //
            WritableFont arial12font = new WritableFont(WritableFont.TAHOMA, 11, WritableFont.NO_BOLD,
                    //         下划线                      字体颜色
                    false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE, ScriptStyle.NORMAL_SCRIPT);

            arial12format = new WritableCellFormat(arial12font);
            arial12format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, Colour.YELLOW2);    // 设置边框
            arial12format.setBackground(Colour.BRIGHT_GREEN);                    // 设置背景
            arial12format.setAlignment(Alignment.CENTRE);                                        // 水平对齐格式
            arial12format.setVerticalAlignment(VerticalAlignment.CENTRE);


        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化Excel create black
     *
     * @param fileName 导出excel存放的地址（目录）
     * @param colName  excel中包含的列名（可以有多个）
     */
        public static void initExcel(String fileName, String[] colName) {
            format();
            WritableWorkbook wWorkbook = null;
            try {
                File file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                wWorkbook = Workbook.createWorkbook(file);
                //设置表格的名字
                WritableSheet wSheet = wWorkbook.createSheet("账单", 0);
                //创建标题栏
                wSheet.addCell((WritableCell) new Label(0, 0, fileName, arial14format));
                for (int col = 0; col < colName.length; col++) {
                    wSheet.addCell(new Label(col, 0, colName[col], arial10format));
                }
                //设置行高
                wSheet.setRowView(0, 340);
                wWorkbook.write();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (wWorkbook != null) {
                    try {
                        wWorkbook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    public static <T> void writeObjListToExcel(List<T> objList, String fileName, Context c) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);

                for (int j = 0; j < objList.size(); j++) {
                    CardBean projectBean = (CardBean) objList.get(j);
                    List<String> list = new ArrayList<>();
                    // TODO: fpp 2020/9/2 0002 保存数据
                    list.add(projectBean.getAtr());
                    list.add(projectBean.getArtt());
                    list.add(projectBean.getAtrr());
                    list.add(projectBean.getaBoolean());
                    list.add(projectBean.getAnInt());
                    for (int i = 0; i < list.size(); i++) {
                        sheet.addCell(new Label(i, j + 1, list.get(i), arial12format));
                        if (list.get(i).length() <= 4) {
                            //设置列宽
                            sheet.setColumnView(i, list.get(i).length() + 8);
                        } else {
                            //设置列宽
                            sheet.setColumnView(i, list.get(i).length() + 5);
                        }
                    }
                    //设置行高
                    sheet.setRowView(j + 1, 340);
                }
                writebook.write();
                Toast.makeText(c, "导出Excel成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
