package currencies;

import collections.ICurrencyCollection;

public class Exchange implements IExchange {
    private ICurrencyCollection<Currency> currencyDataCollection;

    public Exchange(ICurrencyCollection<Currency> currencyDataCollection){
        this.currencyDataCollection = currencyDataCollection;
    }

    @Override
    public double convert(double amount, ICurrency sourceCurrency, ICurrency targetCurrency){
        double sourceRate = sourceCurrency.getExchangeRate();
        double targetRate = targetCurrency.getExchangeRate();
        double sourceFactor = sourceCurrency.getFactor();
        double targetFactor = targetCurrency.getFactor();

        double result = amount * targetFactor / sourceFactor * (sourceRate / targetRate);

        return result;
    }
}
