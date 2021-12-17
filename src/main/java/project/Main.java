package project;

import com.opencsv.exceptions.CsvException;
import csv_reader.CsvReader;
import models.PersonModel;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {
        CsvReader reader = new CsvReader("foreign_names.csv",";");
        reader.tryToRead();
        ArrayList<PersonModel> result = reader.getResult();
        System.out.println("Result: ");
        for(var person : result){
            System.out.println(person.toString());
        }
    }
}
