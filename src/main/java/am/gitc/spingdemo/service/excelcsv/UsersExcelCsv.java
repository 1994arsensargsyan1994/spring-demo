package am.gitc.spingdemo.service.excelcsv;

import am.gitc.spingdemo.dto.UserDto;
import am.gitc.spingdemo.service.Users;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class UsersExcelCsv implements Users {

  @Override
  public InputStream createExcel(List<UserDto> objects) throws IOException {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Users");
    this.createAndFillHeader(sheet);
    this.createAndFillBody(sheet, objects);
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    workbook.write(bos);
    workbook.close();
    return new ByteArrayInputStream(bos.toByteArray());
  }

  private void createAndFillBody(Sheet sheet, List<UserDto> userDtos) {
    int rowNum = 1;
    for (UserDto userDto : userDtos) {
      int colNum = 0;
      Row row = sheet.createRow(rowNum++);
      row.createCell(colNum++).setCellValue(userDto.getId());
      row.createCell(colNum++).setCellValue(userDto.getName());
      row.createCell(colNum++).setCellValue(userDto.getSurname());
      row.createCell(colNum).setCellValue(userDto.getEmail());
    }
  }

  private void createAndFillHeader(Sheet sheet) {
    Row header = sheet.createRow(0);
    header.createCell(0).setCellValue("Id");
    header.createCell(1).setCellValue("Name");
    header.createCell(2).setCellValue("Surname");
    header.createCell(3).setCellValue("Email");
  }

  @Override
  public InputStream createCsvFile(List<UserDto> userDtos) throws IOException {
    CharArrayWriter charArrayWriter = new CharArrayWriter();
    CSVPrinter csvPrinter = CSVFormat.DEFAULT
        .withDelimiter(',')
        .withQuote('"')
        .withHeader("Id", "Name", "Surname", "Email")
        .print(charArrayWriter);
    for (UserDto userDto : userDtos) {
      csvPrinter.printRecord(userDto.getId(), userDto.getName(), userDto.getSurname(), userDto.getEmail());
    }
    csvPrinter.close();
    return new ByteArrayInputStream(charArrayWriter.toString().getBytes());
  }
}
