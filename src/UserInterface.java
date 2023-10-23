import collections.CurrencyCollection;
import currencies.Exchange;
import currencies.ICurrency;
import dataproviders.DataProvider;
import dataproviders.XMLCurrencyCollectionProvider;

import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        DataProvider dataProvider = DataProvider.getInstance();
        String nbpData = dataProvider.fetchDataFromNBP("https://www.nbp.pl/kursy/xml/lasta.xml");

        XMLCurrencyCollectionProvider xmlProvider = new XMLCurrencyCollectionProvider();
        CurrencyCollection currencyCollection = (CurrencyCollection) xmlProvider.parse(nbpData);

        Exchange exchange = new Exchange(currencyCollection);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Enter amount: ");
                double amount = scanner.nextDouble();

                System.out.println("Enter source currency: ");
                String sourceCurrencyCode = scanner.next();

                System.out.println("Enter target currency: ");
                String targetCurrencyCode = scanner.next();

                ICurrency sourceCurrency = findCurrencyByCode(currencyCollection, sourceCurrencyCode);
                ICurrency targetCurrency = findCurrencyByCode(currencyCollection, targetCurrencyCode);

                if (sourceCurrency == null || targetCurrency == null) {
                    System.out.println("Nieprawidłowe kody walut. Spróbuj ponownie.");
                    continue;
                }

                double result = exchange.convert(amount, sourceCurrency, targetCurrency);

                System.out.println("Wynik wymiany: " + result + " " + targetCurrencyCode);

            } catch (Exception e) {
                System.out.println("Błąd: Wprowadzono nieprawidłowe dane. Spróbuj ponownie.");
                scanner.nextLine(); // Wyczyść bufor wejścia.
            }
        }
    }

    public static ICurrency findCurrencyByCode(CurrencyCollection currencyCollection, String code){
        for(int i = 0; i < currencyCollection.size(); i++){
            ICurrency currency = currencyCollection.get(i);
            if (currency.getCode().equals(code)) {
                return currency;
            }
        }
        return null;
    }
}
