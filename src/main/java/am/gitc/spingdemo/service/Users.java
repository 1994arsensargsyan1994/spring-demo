package am.gitc.spingdemo.service;

import am.gitc.spingdemo.dto.UserDto;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface Users {

  InputStream createExcel(List<UserDto> userDtos) throws IOException;

  InputStream createCsvFile(List<UserDto> userDtos) throws IOException;

}
