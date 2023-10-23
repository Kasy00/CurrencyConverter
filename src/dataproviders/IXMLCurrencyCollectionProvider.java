package dataproviders;

import collections.ICurrencyCollection;
import currencies.Currency;

public interface IXMLCurrencyCollectionProvider {
    ICurrencyCollection<Currency> parse(String data);
}
