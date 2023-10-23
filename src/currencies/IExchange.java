package currencies;

public interface IExchange {
    double convert(double amount, ICurrency sourceCurrency, ICurrency targetCurrency);
}
