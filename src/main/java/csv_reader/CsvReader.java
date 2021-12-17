package csv_reader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import models.PersonModel;
import models.SubDivisionModel;
import org.apache.commons.lang3.tuple.Triple;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CsvReader {
    private String filePath;
    private String separator;
    private final ArrayList<PersonModel> result;
    /**
     *First of all, trying to read file. In case its empty or file is epsent then error is thrown, otherwise we start
     * work with file. Creating HashMap with unique subdivisions.
     * Variable id is necessary for id of subdivision
     * Boolean isFirst is also necessary in order to avoid working with first line because in consist of names of columns such as id, name,subdiv, etc
     * Then a string array is created in order to read the line
     * Until current line exist, we moving through it. We divide it using ; and write the result into lineArray
     * if string first then we skip it
     * else we check if data is full(in order to avoid not full string)
     *Further into variable value of subdivision and check if there is this subdivision into HashMap with unique subdivs.
     * If there is no such subdivision before, we add it into it(Into HashMap)
     * Next step is creating new object(SubdivisionModel). Then we divide birthdate using . and write the result in ArrayList<String> date
     *Next step would be creating object PersonModel, and writing all data into it. Then this person is put into ArrayList result.
     * */
    public void tryToRead() throws IOException, CsvValidationException {

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(filePath);
             CSVReader reader = in == null ? null : new CSVReader(new InputStreamReader(in))) {
            if (reader == null) {
                throw new FileNotFoundException(filePath);
            }
            HashMap<String, Integer> uniqueSubDivisions = new HashMap<>();
            int id = 1;
            boolean isFirst = true;
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                for (var line : nextLine) {
                    ArrayList<String> lineArray = new ArrayList<String>(Arrays.stream((line.split(separator))).toList());
                    if (!isFirst) {
                        if (lineArray.size() == 6) {
                            String key = lineArray.get(4);
                            if (!uniqueSubDivisions.containsKey(key)) {
                                uniqueSubDivisions.put(key, id);
                                id += 1;
                            }
                            SubDivisionModel newSubDivisionModel = new SubDivisionModel(key, uniqueSubDivisions.get(key));
                            String[] dateArray = lineArray.get(3).split("\\.");
                            ArrayList<String> date = new ArrayList<String>(Arrays.stream(dateArray).toList());
                            PersonModel newPersonModel = new PersonModel(Integer.parseInt(lineArray.get(0)),
                                    lineArray.get(1), lineArray.get(2), newSubDivisionModel, Integer.parseInt(lineArray.get(5)), Triple.of(Integer.parseInt(date.get(0)),
                                    Integer.parseInt(date.get(1)), Integer.parseInt(date.get(2))));
                            result.add(newPersonModel);
                        }
                    } else {
                        isFirst = false;
                    }

                }
            }
        }
    }

    public CsvReader(String filePath, String separator) {
        this.separator = separator;
        this.filePath = filePath;
        this.result = new ArrayList<>();
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public void setFileName(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<PersonModel> getResult() {
        return result;
    }
}
