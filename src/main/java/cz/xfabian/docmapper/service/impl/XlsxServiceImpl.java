package cz.xfabian.docmapper.service.impl;

import cz.xfabian.docmapper.comparator.PartnerComparator;
import cz.xfabian.docmapper.entity.Partner;
import cz.xfabian.docmapper.service.XlsxService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Norbert Fabian on 26.12.2016.
 */

@Service
public class XlsxServiceImpl implements XlsxService {

    @Override
    public List<Partner>  readOrganizations(String file) throws IOException {
        File myFile = new File(file);
        FileInputStream fis = new FileInputStream(myFile);
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        Iterator<Row> rowIterator = mySheet.iterator();
        rowIterator.next();
        List<Partner> partners = readPartners(rowIterator);
        Collections.sort(partners, new PartnerComparator());
        return partners;

    }

    /**
     * Iterates throw the rows and creates partners from the read information in a row.
     * Return partners as List.
     *
     * @param rowIterator Xslx row iterator
     * @return List of partners created by the information in each row
     */
    private List<Partner> readPartners(Iterator<Row> rowIterator) {
        List<Partner> partners = new ArrayList<Partner>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            partners.add(
                    new Partner().setPic(getNextCellValue(cellIterator))
                            .setCountry(getNextCellValue(cellIterator))
                            .setRepresentativeFirstName(getNextCellValue(cellIterator))
                            .setRepresentativeSurname(getNextCellValue(cellIterator))
                            .setRepresentativeFunction(getNextCellValue(cellIterator))
                            .setName(getNextCellValue(cellIterator))
                            .setAcronomy(getNextCellValue(cellIterator))
                            .setLegalStatus(getNextCellValue(cellIterator))
                            .setRegistrationNumber(getNextCellValue(cellIterator))
                            .setAddress(getNextCellValue(cellIterator))
                            .setZip(getNextCellValue(cellIterator))
                            .setCity(getNextCellValue(cellIterator))
                            .setVat(getNextCellValue(cellIterator)));

        }
        return partners;
    }

    /**
     * Returns the nex cell value from the cell iterator as String.
     *
     * @param cellIterator Cell iterator of a row
     * @return Value of the cell as String
     */
    private String getNextCellValue(Iterator<Cell> cellIterator) {
        if (cellIterator.hasNext()) {
            Cell cell = cellIterator. next();
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String value = cell.getStringCellValue().trim();
            return value.equals("-") ? null : value;
        }
        return null;
    }
}
