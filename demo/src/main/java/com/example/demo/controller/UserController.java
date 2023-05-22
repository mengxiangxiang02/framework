package com.example.demo.controller;

import com.example.demo.javainaction.Trader;
import com.example.demo.javainaction.Transaction;
import com.example.demo.service.PdfExportService;
import com.example.demo.service.PdfView;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/detail")
    @ResponseBody
    // 指定状态码为201（资源创建成功）
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Trader> userinfo(HttpServletRequest request)
    {
        Trader raoul = new Trader("Raoul", "Cambridge");
        request.getSession().setAttribute("raoul",raoul);
        HttpHeaders headers = new HttpHeaders();
        String success =
                (raoul == null ) ? "false" : "true";
        // 设置响应头，比较常用的方式
        headers.add("success", success);
        // 下面是使用集合（List）方式，不是太常用
        // headers.put("success", Arrays.asList(success));
        // 返回创建成功的状态码
        return new ResponseEntity<Trader>(raoul, headers, HttpStatus.CREATED);
    }

    @GetMapping("/info/{id}")
    public   Trader getTrader(@PathVariable("id") String id)
    {
        Trader raoul = new Trader(id, "Cambridge");
        return raoul;
    }

    @GetMapping("/info/format")
    public   Trader getTraderinfo(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @NumberFormat(pattern = "#, ###.##") double d)
    {
        Trader raoul = new Trader(date.toString(), "Cambridge");
        return raoul;
    }


    public ModelAndView procssTrader(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("/detail");
        Trader raoul = new Trader("Raoul", "Cambridge");
        ModelMap modelMap = new ModelMap();
        modelMap.put("trader",raoul);
        modelAndView.addObject("trader",raoul);
        return modelAndView;
    }

    // 导出接口
    @GetMapping("/export/pdf")
    public ModelAndView exportPdf() {
        // 查询用户信息列表
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        // 定义PDF视图
        View view = new PdfView(exportService());
        ModelAndView mv = new ModelAndView();
        // 设置视图
        mv.setView(view);
        // 加入数据模型
        mv.addObject("transactionList", transactions);
        return mv;
    }

    // 导出PDF自定义
    @SuppressWarnings("unchecked")
    private PdfExportService exportService() {
        // 使用Lambda表达式定义自定义导出
        return (model, document, writer, request, response) -> {
            try {
                // A4纸张
                document.setPageSize(PageSize.A4);
                // 标题
                document.addTitle("用户信息");
                // 换行
                document.add(new Chunk("\n"));
                // 表格，3列
                PdfPTable table = new PdfPTable(3);
                // 单元格
                PdfPCell cell = null;
                // 字体，定义为蓝色加粗
                Font f8 = new Font();
                f8.setColor(Color.BLUE);
                f8.setStyle(Font.BOLD);
                // 标题
                cell = new PdfPCell(new Paragraph("name", f8));
                // 居中对齐
                cell.setHorizontalAlignment(1);
                // 将单元格加入表格
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("value", f8));
                // 居中对齐
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("year", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                // 获取数据模型中的用户列表
                List<Transaction> transactionList = (List<Transaction>) model.get("transactionList");
                for (Transaction transaction : transactionList) {
                    document.add(new Chunk("\n"));
                    cell = new PdfPCell(new Paragraph(transaction.getTrader().getName() + ""));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(transaction.getValue()+ ""));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(transaction.getYear()+ ""));
                    table.addCell(cell);
                }
                // 在文档中加入表格
                document.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        };
    }

    public static List<Transaction> findUser(String userName,
                                        String note, int start, int limit) {
        RestTemplate restTmpl = new RestTemplate();
        // 使用Map封装多个参数，以提高可读性
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userName", "user");
        params.put("note", "note");
        params.put("start", start);
        params.put("limit", limit);
        // Map中的key和URI中的参数一一对应
        String url = "http://localhost:8080/users/{userName}/{note}/{start}/{limit}";
        // 请求后端
        ResponseEntity<List> responseEntity = restTmpl.getForEntity(url, List.class, params);
        List<Transaction> userVoes = responseEntity.getBody();
        return userVoes;
    }

}
