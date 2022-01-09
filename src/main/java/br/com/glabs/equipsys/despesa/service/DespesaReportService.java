package br.com.glabs.equipsys.despesa.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaReportService {

    private static final List<String> colunas = List.of(
            "Data Emissão",
            "Data Vencimento",
            "Data Pagamento",
            "Data realização obra",
            "Descrição",
            "Total",
            "Valor",
            "Parcela",
            "Equipamento",
            "Situação",
            "Informações adicionais",
            "Fornecedor",
            "Obra",
            "Conta",
            "Nota Fiscal"
    );

    public Workbook createWorkbook() {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("merge-cells-example");
        fillHeaders(sheet);
        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row index in zero-based
                0, //last row index in zero-based
                0, //first column index in zero-based
                13  //last column index in zero-based
        ));
        return workbook;
    }

    public void fillHeaders(final Sheet sheet) {
        final Row row = sheet.createRow(0);
        final Cell cell = row.createCell(0);
        cell.setCellValue("Equipsys");
        final Row columRow = sheet.createRow(1);

        Integer index = 0;
        for(String coluna: colunas){
            final Cell celula = columRow.createCell(index);
            celula.setCellValue(coluna);
            index++;
        }
    }
}
