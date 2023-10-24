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

                if(amount <= 0 ){
                    System.out.println("Amount cannot be less or equal zero");
                    continue;
                }

                System.out.println("Enter source currency: ");
                String sourceCurrencyCode = scanner.next();

                System.out.println("Enter target currency: ");
                String targetCurrencyCode = scanner.next();

                ICurrency sourceCurrency = findCurrencyByCode(currencyCollection, sourceCurrencyCode);
                ICurrency targetCurrency = findCurrencyByCode(currencyCollection, targetCurrencyCode);

                if (sourceCurrency == null || targetCurrency == null) {
                    System.out.println("Incorrect code. Please try again.");
                    continue;
                }

                double result = exchange.convert(amount, sourceCurrency, targetCurrency);


                System.out.println("Result: " + Math.round(result * 100.0) / 100.0  + " " + targetCurrencyCode);

            } catch (Exception e) {
                System.out.println("Error: Entered incorrect data. Try again");
                scanner.nextLine();
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
