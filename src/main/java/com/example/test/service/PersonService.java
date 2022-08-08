package com.example.test.service;

import com.example.test.dao.PersonDao;
import com.example.test.entity.Person;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersonService {
    File file = new File("E:\\server\\test\\src\\main\\resources\\personList.csv");

    PersonDao personDao;

    public List<Person> getAll() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String str;
            List<Person> personList = new ArrayList<>();
            while ((str = bufferedReader.readLine()) != null) {
                String[] values = str.split(",");
                personList.add(new Person(values[0],  values[2], Integer.parseInt(values[1])));
            }
            return personList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveToList(Person person) {
        personDao.save(person);
        try (OutputStream outputStream = new FileOutputStream(file, true);
             PrintWriter printWriter = new PrintWriter(outputStream, true)) {
            printWriter.print(person.getName() + ",");
            printWriter.print(person.getSalary() + ",");
            printWriter.println(person.getJobTitle());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
