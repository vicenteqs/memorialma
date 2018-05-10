package es.vqs.memorial;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


public class Testeos extends PDFTextStripper {

    int contador = 0;
    int serie = 0;
    int contadorPrueba = 0;
    int contadorSerieTotal = 0;
    int contadorSerie = 0;
    int calle = 1;
    boolean relevo = false;
    boolean empezar = false;


    public Testeos() throws IOException {
    }

    public static void main(String[] args) {
        PDFTextStripper pdfStripper = null;
        File file = new File("E:/Descargas/seriesmemorial.pdf");
        try {
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
                PDFParser parser = new PDFParser(randomAccessFile);
                parser.parse();
                try (COSDocument cosDoc = parser.getDocument(); PDDocument pdDoc = new PDDocument(cosDoc);) {
                    pdfStripper = new Testeos();
                    pdfStripper.setStartPage(1);
                    pdfStripper.setEndPage(500);
                    pdfStripper.getText(pdDoc);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void writeString(String str, List<TextPosition> textPositions) throws IOException {
        Prueba pruebaActual = null;
        Serie serieActual = null;

        if (str.contains("RELEVO")) {
            contadorPrueba++;

            relevo = true;
            Prueba p = new Prueba();
            p.setNombre(str.split("  ")[0]);
            //   p = this.pruebaRepository.save(p);
            System.out.println("insert into prueba values (" + contadorPrueba + ",'" + str.substring(str.indexOf("RELEVO")) + "');");
            pruebaActual = p;
            contadorSerie = 0;
        } else if (str.contains("0 m. ")) {
            contadorPrueba++;

            String suber = str.substring(str.indexOf(" "));
            System.out.println("insert into prueba values (" + contadorPrueba + ",'" + suber.substring(suber.indexOf(" ")) + "');");


            relevo = false;
            Prueba p = new Prueba();
            p.setNombre(str);
            //   p = this.pruebaRepository.save(p);
            pruebaActual = p;
            contadorSerie = 0;


        } else if (str.contains("FINAL")) {
            contadorSerieTotal++;
            contadorSerie++;
            System.out.println("insert into serie values (" + contadorSerieTotal + "," + contadorPrueba + "," + contadorSerie + ");");
            this.empezar = false;
        } else if (str.contains("Calle")) {
            this.empezar = true;
            calle = 1;
        } else if (this.empezar && str.trim().length() > 5 && !str.contains("MEMORIAL") && !str.contains("Piscina de 50") && !str.contains("- DOMINGO MA")) {
            String[] nadador = str.split(" ");
            if (nadador.length >= 3) {
                String nombre = "";
                String club = "";
                if (!relevo) {

                    int i = 3;
                    for (i = 3; nadador.length > i && !nadador[i].contains("2"); i++) {
                        nombre += " " + nadador[i];
                    }
                    for (i = i + 1; nadador.length - 2 > i; i++) {
                        club += " " + nadador[i];
                    }
                } else {
                    String key = nadador[3];
                    if (key.equals("ABSO") || key.equals("ALEV")) {
                        key = nadador[4];
                    }
                    nombre = str.substring(str.indexOf(key), str.lastIndexOf(key));
                }
                System.out.println("insert into nadador (calle, club, nombre, id_serie) values ("+calle+",'"+club+"','"+nombre + "'," + contadorSerieTotal+");");

            }
            calle++;

        }
    }
}
