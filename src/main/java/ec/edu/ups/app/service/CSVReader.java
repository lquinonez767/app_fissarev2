package ec.edu.ups.app.service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
	public static void main(String[] args) {

        String csvFile = "C:/Users/Wilson/Desktop/examen.txt";
        NotificacionFAC nf = new NotificacionFAC();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

              
                 nf.enviarEmail("lenin.quinonez@taurustech.ec", "@quinonez@25", "jayora@est.ups.edu.ec", "Mensaje de Prueba", "Este es un mensaje para ");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
