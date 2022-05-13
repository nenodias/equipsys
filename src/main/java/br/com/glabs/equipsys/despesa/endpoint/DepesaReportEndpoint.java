package br.com.glabs.equipsys.despesa.endpoint;

import br.com.glabs.equipsys.despesa.service.DespesaReportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@CrossOrigin
@RequestMapping("/report/despesa")
@Controller
public class DepesaReportEndpoint {

    @Autowired
    private DespesaReportService despesaReportService;

    @GetMapping
    public void excel(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        Workbook workbook = despesaReportService.createWorkbook();
        response.addHeader("Content-disposition", "attachment;filename=despesa-report" + now.toString() + ".xls");
        response.setContentType("application/xls");
        workbook.write(response.getOutputStream());
        workbook.close();
        response.flushBuffer();
    }

}
