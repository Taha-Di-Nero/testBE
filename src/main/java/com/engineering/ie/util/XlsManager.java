package com.engineering.ie.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.LoggerFactory;

import com.engineering.ie.vo.GeologicalClassVO;
import com.engineering.ie.vo.GeologicalSectionVO;

public class XlsManager {

	private static org.slf4j.Logger LOG = LoggerFactory.getLogger(XlsManager.class);

	public static List<GeologicalSectionVO> parseFile(byte[] xls) throws IOException {

		List<GeologicalSectionVO> sections = new ArrayList<>();

		try (HSSFWorkbook workbook = new HSSFWorkbook(new ByteArrayInputStream(xls))) {
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {

				GeologicalSectionVO.GeologicalSectionVOBuilder sectionVOBuilder = GeologicalSectionVO.newBuilder();

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				Cell currentCell = cellIterator.next();
				sectionVOBuilder.setName(currentCell.getStringCellValue());

				while (cellIterator.hasNext()) {
					currentCell = cellIterator.next();
					String className = currentCell.getStringCellValue();
					currentCell = cellIterator.next();
					String code = currentCell.getStringCellValue();
					sectionVOBuilder.addClass(
							GeologicalClassVO.newBuilder().setCode(code).setName(className).createGeologicalClassVO());
				}
				sections.add(sectionVOBuilder.createGeologicalSectionVO());
			}
		}
		return sections;
	}

}
