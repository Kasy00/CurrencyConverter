package dataproviders;

import collections.CurrencyCollection;
import collections.ICurrencyCollection;
import currencies.Currency;
import currencies.Exchange;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

public class XMLCurrencyCollectionProvider implements IXMLCurrencyCollectionProvider {
    @Override
    public ICurrencyCollection<Currency> parse(String data) {
        CurrencyCollection currencyCollection = CurrencyCollection.getInstance();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(data.getBytes("UTF-8")));

            doc.getDocumentElement().normalize();

            NodeList positions = doc.getElementsByTagName("pozycja");

            for (int i = 0; i < positions.getLength(); i++) {
                Element positionElement = (Element) positions.item(i);
                String currencyName = positionElement.getElementsByTagName("nazwa_waluty").item(0).getTextContent();
                String currencyCode = positionElement.getElementsByTagName("kod_waluty").item(0).getTextContent();
                double exchangeRate = Double.parseDouble(positionElement.getElementsByTagName("kurs_sredni").item(0).getTextContent().replace(",", "."));
                double factor = Double.parseDouble(positionElement.getElementsByTagName("przelicznik").item(0).getTextContent());

                Currency currency = new Currency(currencyCode, currencyName, exchangeRate, factor);
                currencyCollection.add(currency);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencyCollection;
    }
}
