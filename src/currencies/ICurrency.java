package currencies;

public interface ICurrency {
    String getCode();
    String getName();
    double getExchangeRate();

    double getFactor();
}
