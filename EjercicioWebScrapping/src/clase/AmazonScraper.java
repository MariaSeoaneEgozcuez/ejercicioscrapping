package clase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AmazonScraper {

    public static void main(String[] args) {
        String url = "https://www.amazon.com/s?k=juegos"; // URL de búsqueda en Amazon

        try {
            Document doc = Jsoup.connect(url).get(); // Obtiene el HTML de la página de resultados

            Elements productElements = doc.select("div[data-component-type='s-search-result']"); // Obtiene los elementos de los productos

            BufferedWriter writer = new BufferedWriter(new FileWriter("productos.csv")); // Crea el archivo CSV

            writer.write("Nombre del producto,Precio del producto"); // Encabezados del CSV
            writer.newLine();

            for (Element productElement : productElements) {
                Element titleElement = productElement.selectFirst("a-size-base-plus a-color-base a-text-normal"); // Obtiene el elemento de título del producto
                String title = titleElement.text(); // Obtiene el título del producto

                String price = productElement.select("a-price-whole").text(); // Obtiene el precio del producto

                // Escribe el título y el precio en el archivo CSV
                writer.write(title + ", (EUR)" + price);
                writer.newLine();
            }

            writer.close(); // Cierra el archivo CSV

            System.out.println("Se ha creado el archivo 'productos.csv' con los resultados de la búsqueda.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








