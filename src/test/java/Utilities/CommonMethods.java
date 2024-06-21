package Utilities;

import Driver.BaseDriver;
import com.github.javafaker.Faker;
import java.io.IOException;

public class CommonMethods extends BaseDriver {

        public void timeout() throws InterruptedException {
            Thread.sleep(2000);
        }
        public void timeout(int time) throws InterruptedException {
            Thread.sleep(time);
        }

        public void testDataGenerator() throws IOException {
            Faker faker = new Faker();

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            System.out.println(firstName);
            System.out.println(lastName);

            ExcelUtils excelUtils = new ExcelUtils();
            excelUtils.writeExcelData(firstName, lastName, "data", "7846547", "4578");

        }

    }
